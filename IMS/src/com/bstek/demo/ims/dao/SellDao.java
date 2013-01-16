package com.bstek.demo.ims.dao;

import org.springframework.stereotype.Repository;

import com.bstek.demo.ims.entity.Sell;
import com.bstek.dorado.hibernate.HibernateDao;

@Repository
public class SellDao extends HibernateDao<Sell, Integer> {
}

