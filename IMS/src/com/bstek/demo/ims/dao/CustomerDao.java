package com.bstek.demo.ims.dao;

import org.springframework.stereotype.Repository;

import com.bstek.demo.ims.entity.Customer;
import com.bstek.dorado.hibernate.HibernateDao;

@Repository
public class CustomerDao extends HibernateDao<Customer, Integer>{
}
