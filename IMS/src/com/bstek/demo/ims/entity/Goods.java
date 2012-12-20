package com.bstek.demo.ims.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Goods {
	private Integer id;
	private String name;
	private String shortName;
	private String birthplace;
	private String specification;
	private String packaging;
	private String comment;
	private Float storage;
	private Float totalStorage;
	private Float sales;
	private Integer categoryId;
	private Category category;
	private List<Sell> sell;
	private List<Purchasing> purchasing;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ID")
	@SequenceGenerator(name = "SEQ_ID", sequenceName = "SEQ_GOODS_ID")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "short_name")
	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getBirthplace() {
		return birthplace;
	}

	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}

	public String getSpecification() {
		return specification;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public String getPackaging() {
		return packaging;
	}

	public void setPackaging(String packagin) {
		this.packaging = packagin;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "goods")
	public List<Sell> getSell() {
		return sell;
	}

	public void setSell(List<Sell> sell) {
		this.sell = sell;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "goods")
	public List<Purchasing> getPurchasing() {
		return purchasing;
	}

	public void setPurchasing(List<Purchasing> purchasing) {
		this.purchasing = purchasing;
	}

	@Column(name = "category_id")
	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	@ManyToOne
	@JoinColumn(name = "category_id", insertable = false, updatable = false)
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getStorage() {
		return storage;
	}

	public void setStorage(Float storage) {
		this.storage = storage;
	}

	public Float getSales() {
		return sales;
	}

	public void setSales(Float sales) {
		this.sales = sales;
	}

	@Column(name = "total_storage")
	public Float getTotalStorage() {
		return totalStorage;
	}

	public void setTotalStorage(Float totalStorage) {
		this.totalStorage = totalStorage;
	}
}
