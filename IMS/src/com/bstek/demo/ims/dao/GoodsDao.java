package com.bstek.demo.ims.dao;

import org.springframework.stereotype.Repository;

import com.bstek.demo.ims.entity.Goods;
import com.bstek.dorado.hibernate.HibernateDao;

@Repository
public class GoodsDao extends HibernateDao<Goods, Integer> {
}

