package com.bstek.demo.ims.business;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.bstek.demo.ims.dao.CategoryDao;
import com.bstek.demo.ims.dao.GoodsDao;
import com.bstek.demo.ims.entity.Category;
import com.bstek.demo.ims.entity.Goods;
import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.annotation.DataResolver;
import com.bstek.dorado.annotation.Expose;
import com.bstek.dorado.data.provider.Criteria;
import com.bstek.dorado.data.provider.Page;
import com.bstek.dorado.hibernate.HibernateUtils;

@Component
public class CategoryGoodsPR {

	@Resource
	CategoryDao categoryDao;

	@Resource
	GoodsDao goodsDao;

	@DataProvider
	public List<Category> getAllCategories() {
		return categoryDao.find("from Category ");
	}
	
	@DataProvider
	public void getGoods(Page<Goods> page, int categoryId)
			throws Exception {
		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(Goods.class);
		detachedCriteria.add(Restrictions.eq("categoryId", categoryId));
		goodsDao.find(page,
				detachedCriteria.getExecutableCriteria(goodsDao.getSession()));
	}
	
	@Expose
	public String validatorCategory(String name) {
		if (StringUtils.hasText(name)) {
			if (categoryDao.findUnique("from Category where name=?",
					new Object[] { name }) != null) {
				return "分类名称重复!";
			}
		}
		return null;
	}
	
	
	@DataResolver
	@Transactional
	public void updateAll(List<Category> categories) {
		categoryDao.persistEntities(categories);
		for (Category category : categories) {
			List<Goods> goods = category.getGoods();
			if (goods != null) {
				for (Goods singleGoods : goods) {
					singleGoods.setCategoryId(category.getId());
				}
				goodsDao.persistEntities(goods);
			}
		}
	}
}
