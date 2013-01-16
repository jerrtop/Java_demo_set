package com.bstek.demo.ims.business;

import hirondelle.date4j.DateTime;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

import javax.annotation.Resource;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import com.bstek.demo.ims.dao.GoodsDao;
import com.bstek.demo.ims.entity.Goods;
import com.bstek.dorado.annotation.DataProvider;
import com.bstek.dorado.data.entity.EntityUtils;
import com.bstek.dorado.data.provider.Criteria;
import com.bstek.dorado.data.provider.Page;
import com.bstek.dorado.hibernate.HibernateUtils;
import com.bstek.dorado.web.DoradoContext;

@Component
public class StockPR {

	@Resource
	GoodsDao goodsDao;

	/**
	 * 带Criteria参数的查询
	 * @param page 分页信息
	 * @param criteria  Criteria对象
	 * @param parameter 其它参数
	 * @throws Exception
	 */
	@DataProvider
	public void getStock(Page<Goods> page, Criteria criteria,
			Map<String, Object> parameter) throws Exception {
		doQuery(page, criteria, parameter);
	}

	/**
	 * 不带Criteria参数的查询
	 * @param page 分页信息
	 * @param parameter 其他参数信息
	 * @throws Exception
	 */
	@DataProvider
	public void getStock(Page<Goods> page, Map<String, Object> parameter)
			throws Exception {
		doQuery(page, null, parameter);
	}

	private void doQuery(Page<Goods> page, Criteria filterCriteria,
			Map<String, Object> params) throws Exception {
		if (params == null)
			params = new HashMap<String, Object>();

		initQueryParams(params);

		List<Goods> allGoods = new ArrayList<Goods>();
		Page<Goods> goodsPage = new Page<Goods>(page.getPageSize(),
				page.getPageNo());

		List<Map<String, Object>> stockInfos = getBaseStock(goodsPage, params,
				filterCriteria);

		for (Map<String, Object> stockInfo : stockInfos) {
			Goods goods = (Goods) stockInfo.get("goods");
			Object dynaSaleTotalnumber = stockInfo.get("dynaSaleTotalnumber");
			Object dynaStoreTotalnumber = stockInfo.get("dynaStoreTotalnumber");

			goods = EntityUtils.toEntity(goods);
			EntityUtils.setValue(goods, "dynaStoreTotalnumber",
					dynaStoreTotalnumber);
			EntityUtils.setValue(goods, "dynaSaleTotalnumber",
					dynaSaleTotalnumber);
			allGoods.add(goods);
		}
		page.setEntities(allGoods);

		pushContextParams(params);
	}

	public List<Map<String, Object>> getBaseStock(Page<Goods> page,
			Map<String, Object> params, Criteria filterCriteria)
			throws Exception {

		DetachedCriteria criteria = assemblingHibernateCriteria(filterCriteria,
				params);

		// 查询商品基本信息
		goodsDao.find(page,
				criteria.getExecutableCriteria(goodsDao.getSession()));

		List<Map<String, Object>> stockInfos = new ArrayList<Map<String, Object>>();
		// 查询具体的库存数据及统计数据
		for (Goods goods : page.getEntities()) {
			Map<String, Object> temp = getStockCount(goods, params);
			if (temp == null)
				temp = new HashMap<String, Object>();
			temp.put("goods", goods);
			stockInfos.add(temp);
		}

		return stockInfos;
	}

	private Map<String, Object> getStockCount(Goods goods,
			Map<String, Object> params) {
		StringBuffer sqlStock = new StringBuffer();
		List<Object> stockParams = new ArrayList<Object>();

		Iterator<String> iterator = params.keySet().iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			Object value = params.get(key);

			// 入库
			if ("producerId".equals(key) && value != null) {
				sqlStock.append(" and purchasing.producer.id=?");
				stockParams.add(value);
			}
			// 出库
			else if ("customerId".equals(key) && value != null) {
				sqlStock.append(" and sell.customer.id=?");
				stockParams.add(value);
			}
			// 时间
			else if ("beginTime".equals(key) && value != null) {
				sqlStock.append(" and sell.sellTime>=?");
				sqlStock.append(" and purchasing.storeTime>=?");
				stockParams.add(value);
				stockParams.add(value);
			} else if ("endTime".equals(key) && value != null) {
				sqlStock.append(" and sell.sellTime<=?");
				sqlStock.append(" and purchasing.storeTime<=?");
				stockParams.add(value);
				stockParams.add(value);
			}
		}

		String sql = "select new map(goods as goods,sum(purchasing.totalnumber) as dynaStoreTotalnumber,sum(sell.totalnumber) as dynaSaleTotalnumber) "
				+ " from Goods as goods left join goods.purchasing as purchasing left join goods.sell as sell  where 1=1 "
				+ sqlStock.toString()
				+ " group by goods.id having goods.id="
				+ goods.getId();

		return goodsDao.findUnique(sql, stockParams.toArray());
	}

	private DetachedCriteria assemblingHibernateCriteria(
			Criteria filterCriteria, Map<String, Object> params)
			throws Exception {

		DetachedCriteria detachedCriteria = DetachedCriteria
				.forClass(Goods.class);

		if (filterCriteria != null) {
			HibernateUtils.createFilter(detachedCriteria, filterCriteria);
		}

		Iterator<String> iterator = params.keySet().iterator();
		while (iterator.hasNext()) {
			String key = iterator.next();
			Object value = params.get(key);
			if ("goodsName".equals(key) && value != null) {
				detachedCriteria.add(Restrictions.like("goods.name", "%"
						+ value + "%"));
			} else if ("categoryId".equals(key) && value != null) {
				detachedCriteria.add(Restrictions.eq(" goods.category.id",
						value));
			}else if ("minStorage".equals(key) && value != null) {
				detachedCriteria.add(Restrictions.ge(" goods.storage", value));
			} else if ("maxStorage".equals(key) && value != null) {
				detachedCriteria.add(Restrictions.le(" goods.storage", value));
			}
		}

		return detachedCriteria;
	}

	private void pushContextParams(Map<String, Object> params) {

		Date endTime = (Date) params.get("endTime");
		Date beginTime = (Date) params.get("beginTime");
		DoradoContext context = DoradoContext.getCurrent();

		if (beginTime != null)
			context.setAttribute(DoradoContext.VIEW, "beginTime",
					getDefaultDataTime(beginTime).format("YYYY-MM-DD"));
		if (endTime != null)
			context.setAttribute(DoradoContext.VIEW, "endTime",
					getDefaultDataTime(endTime).format("YYYY-MM-DD"));
	}

	private void initQueryParams(Map<String, Object> params) {

		Date beginTime = (Date) params.get("beginTime");
		Date endTime = (Date) params.get("endTime");
		DateTime month = DateTime.now(TimeZone.getDefault()).getStartOfMonth();
		if (beginTime == null && endTime == null) {

			beginTime = getStartDayOfMonth(new Date(
					month.getMilliseconds(TimeZone.getDefault())));
			endTime = getEndDayOfMonth(new Date(month.getMilliseconds(TimeZone
					.getDefault())));
		} else if (endTime == null) {
			endTime = getEndDayOfMonth(new Date(month.getMilliseconds(TimeZone
					.getDefault())));
		}
		params.put("beginTime", beginTime);
		params.put("endTime", endTime);

	}

	private Date getStartDayOfMonth(Date month) {
		return new Date(getDefaultDataTime(month).getMilliseconds(
				TimeZone.getDefault()));
	}

	private Date getEndDayOfMonth(Date month) {
		return new Date(getDefaultDataTime(month).getEndOfMonth()
				.getMilliseconds(TimeZone.getDefault()));
	}

	private DateTime getDefaultDataTime(Date date) {
		return DateTime.forInstant(date.getTime(), TimeZone.getDefault());
	}
}
