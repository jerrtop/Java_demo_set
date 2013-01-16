package com.bstek.demo.ims.business;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.bstek.demo.ims.dao.CustomerDao;
import com.bstek.demo.ims.entity.Customer;
import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.annotation.DataResolver;
import com.bstek.dorado.annotation.Expose;
import com.bstek.dorado.data.provider.Criteria;
import com.bstek.dorado.data.provider.Page;
import com.bstek.dorado.hibernate.HibernateUtils;

@Component
public class CustomerPR {
	@Resource
	CustomerDao customerDao;

	@DataProvider
	public void getCustomer(Page<Customer> page, Criteria criteria)
			throws Exception {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(Customer.class);

		if (criteria != null) {
			HibernateUtils.createFilter(detachedCriteria, criteria);
		}
		customerDao.find(page, detachedCriteria
				.getExecutableCriteria(customerDao.getSession()));

	}

	@DataProvider
	public List<Customer> getAllCustomer() {
		return customerDao.find("from Customer");
	}

	@Expose
	public String validatorCompanyName(String name) {
		if (StringUtils.hasText(name)) {
			if (customerDao.findUnique("from Customer where companyName=?",
					new Object[] { name }) != null) {
				return "客户名称重复!";
			}
		}
		return null;
	}

	@DataResolver
	@Transactional
	public void saveAll(List<Customer> customer) {
		customerDao.persistEntities(customer);
	}
}
