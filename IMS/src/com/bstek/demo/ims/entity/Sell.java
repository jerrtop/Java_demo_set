package com.bstek.demo.ims.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;


@Entity
public class Sell {

	private Integer id;
	private Float paid;
	private Float totalnumber;
	private Float unitPrice;
	private Float totalPrice;
	private Integer customerId;
	private Customer customer;
	private Date sellTime;
	private String comment;
	private Integer goodsId;
	private Goods goods;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ID")
	@SequenceGenerator(name = "SEQ_ID", sequenceName = "SEQ_SELL_ID")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Float getPaid() {
		return paid;
	}

	public void setPaid(Float paid) {
		this.paid = paid;
	}

	@Column(name = "unit_price")
	public Float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Float unitPrice) {
		this.unitPrice = unitPrice;
	}

	@Column(name = "total_price")
	public Float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	@Column(name = "customer_id")
	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	@ManyToOne
	@JoinColumn(name = "customer_id",insertable=false,updatable=false)
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Column(name = "sell_time")
	public Date getSellTime() {
		return sellTime;
	}

	public void setSellTime(Date sellTime) {
		this.sellTime = sellTime;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Column(name = "goods_id",insertable=false,updatable=false)
	public Integer getGoodsId() {
		return goodsId;
	}
	
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	@ManyToOne
	@JoinColumn(name = "goods_id")
	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public Float getTotalnumber() {
		return totalnumber;
	}

	public void setTotalnumber(Float totalnumber) {
		this.totalnumber = totalnumber;
	}
}
