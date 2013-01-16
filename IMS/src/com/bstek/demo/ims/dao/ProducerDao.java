package com.bstek.demo.ims.dao;

import org.springframework.stereotype.Repository;

import com.bstek.demo.ims.entity.Producer;
import com.bstek.dorado.hibernate.HibernateDao;

@Repository
public class ProducerDao  extends HibernateDao<Producer, Integer>{
}
