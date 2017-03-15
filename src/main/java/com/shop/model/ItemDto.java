package com.shop.model;

import java.io.Serializable;
import java.sql.Date;

public class ItemDto implements Serializable {

	private static final long serialVersionUID = -4272433331738633342L;
	
	private Integer itemId;
	private Integer listingId;
	private String itemName;
	private Integer categoryId;
	private String categoryName;
	private Integer brandId;
	private String brandName;
	private Integer unitId;
	private String unitName;
	private Integer attrId1;
	private Integer attrId2;
	private Integer attrValId1;
	private Integer attrValId2;
	private String attrName1;
	private String attrName2;
	private String attrValName1;
	private String attrValName2;
	private Float price;
	private Double minPrice; //同一个Listing下所有Item的最低价格 （一品多价）
	private String stock;
	private String imgUrl;
	private Integer shopId;
	private Integer qty;
	private Date createTime;
	private Date updateTime;
	
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public Integer getListingId() {
		return listingId;
	}
	public void setListingId(Integer listingId) {
		this.listingId = listingId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public Integer getBrandId() {
		return brandId;
	}
	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public Integer getUnitId() {
		return unitId;
	}
	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public Integer getAttrId1() {
		return attrId1;
	}
	public void setAttrId1(Integer attrId1) {
		this.attrId1 = attrId1;
	}
	public Integer getAttrId2() {
		return attrId2;
	}
	public void setAttrId2(Integer attrId2) {
		this.attrId2 = attrId2;
	}
	public Integer getAttrValId1() {
		return attrValId1;
	}
	public void setAttrValId1(Integer attrValId1) {
		this.attrValId1 = attrValId1;
	}
	public Integer getAttrValId2() {
		return attrValId2;
	}
	public void setAttrValId2(Integer attrValId2) {
		this.attrValId2 = attrValId2;
	}
	public String getAttrName1() {
		return attrName1;
	}
	public void setAttrName1(String attrName1) {
		this.attrName1 = attrName1;
	}
	public String getAttrName2() {
		return attrName2;
	}
	public void setAttrName2(String attrName2) {
		this.attrName2 = attrName2;
	}
	public String getAttrValName1() {
		return attrValName1;
	}
	public void setAttrValName1(String attrValName1) {
		this.attrValName1 = attrValName1;
	}
	public String getAttrValName2() {
		return attrValName2;
	}
	public void setAttrValName2(String attrValName2) {
		this.attrValName2 = attrValName2;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public Double getMinPrice() {
		return minPrice;
	}
	public void setMinPrice(Double minPrice) {
		this.minPrice = minPrice;
	}
	public String getStock() {
		return stock;
	}
	public void setStock(String stock) {
		this.stock = stock;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public Integer getShopId() {
		return shopId;
	}
	public void setShopId(Integer shopId) {
		this.shopId = shopId;
	}
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	@Override
	public String toString() {
		return "ItemDto [itemId=" + itemId + ", listingId=" + listingId + ", itemName=" + itemName + ", categoryId="
				+ categoryId + ", categoryName=" + categoryName + ", brandId=" + brandId + ", brandName=" + brandName
				+ ", unitId=" + unitId + ", unitName=" + unitName + ", attrId1=" + attrId1 + ", attrId2=" + attrId2
				+ ", attrValId1=" + attrValId1 + ", attrValId2=" + attrValId2 + ", attrName1=" + attrName1
				+ ", attrName2=" + attrName2 + ", attrValName1=" + attrValName1 + ", attrValName2=" + attrValName2
				+ ", price=" + price + ", minPrice=" + minPrice + ", stock=" + stock + ", imgUrl=" + imgUrl
				+ ", shopId=" + shopId + ", qty=" + qty + ", createTime=" + createTime + ", updateTime=" + updateTime
				+ "]";
	}
    
}
