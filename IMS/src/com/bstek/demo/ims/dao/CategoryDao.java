package com.bstek.demo.ims.dao;

import org.springframework.stereotype.Repository;

import com.bstek.demo.ims.entity.Category;
import com.bstek.dorado.hibernate.HibernateDao;

@Repository
public class CategoryDao extends HibernateDao<Category, Integer> {
}
