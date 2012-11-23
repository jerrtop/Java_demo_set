package com.bstek.demo.ims.dao;

import org.springframework.stereotype.Repository;

import com.bstek.demo.ims.entity.Customer;
import com.bstek.dorado.hibernate.HibernateDao;

/**
 * <p>
 * 为了开发简便，工程中采用Dorado7提供的Hibernate Add On扩展，可以通过继承HibernateDao对象来实现自己的业务Dao对象。
 * HibernateDao已经实现了增删改查的基本方法，所以Dao类暂时不需要编写具体的内容。依次方法，为所有的实体对象创建Dao类。
 * </p>
 * @author JERR
 * 
 */

@Repository
public class CustomerDao extends HibernateDao<Customer, Integer> {

}
