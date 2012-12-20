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
public class Purchasing {

	private Integer id;
	private Float totalnumber;
	private Float payout;
	private Float totalPrice;
	private Float unitPrice;
	private Integer producerId;
	private Producer producer;
	private Date storeTime;
	private Integer goodsId;
	private Goods goods;
	private String comment;


	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ID")
	@SequenceGenerator(name = "SEQ_ID", sequenceName = "SEQ_PURCHASING_ID")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Float getPayout() {
		return payout;
	}

	public void setPayout(Float payout) {
		this.payout = payout;
	}

	@Column(name="total_price")
	public Float getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Float totalPrice) {
		this.totalPrice = totalPrice;
	}

	@ManyToOne
	@JoinColumn(name="producer_id",updatable=false,insertable=false)
	public Producer getProducer() {
		return producer;
	}

	public void setProducer(Producer producer) {
		this.producer = producer;
	}

	@Column(name="producer_id")
	public Integer getProducerId() {
		return producerId;
	}

	public void setProducerId(Integer producerId) {
		this.producerId = producerId;
	}
	
	@Column(name = "store_time")
	public Date getStoreTime() {
		return storeTime;
	}

	public void setStoreTime(Date storeTime) {
		this.storeTime = storeTime;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
	public Float getTotalnumber() {
		return totalnumber;
	}

	public void setTotalnumber(Float totalnumber) {
		this.totalnumber = totalnumber;
	}

	@Column(name = "unit_price")
	public Float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Float unitPrice) {
		this.unitPrice = unitPrice;
	}
	@Column(name = "goods_id", insertable = false, updatable = false)
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
	
}
