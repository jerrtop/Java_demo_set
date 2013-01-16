package com.bstek.demo.ims.dao;

import org.springframework.stereotype.Repository;

import com.bstek.demo.ims.entity.Purchasing;
import com.bstek.dorado.hibernate.HibernateDao;

@Repository
public class PurchasingDao extends HibernateDao<Purchasing, Integer> {
}
