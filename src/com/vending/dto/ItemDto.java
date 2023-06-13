package com.vending.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public class ItemDto implements Serializable {
	
	private String name;
	private BigDecimal price;
	private int stockCount;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	public int getStockCount() {
		return stockCount;
	}
	public void setStockCountl(int stockCount) {
		this.stockCount = stockCount;
	}
	public ItemDto(String name, BigDecimal price, int stockCount) {
		super();
		this.name = name;
		this.price = price;
		this.stockCount = stockCount;
	}
	
	@Override
	public String toString() {
		return "ItemDto [name=" + name + ", price=" + price + "]";
	}
	
	public String format() {
		return name + ": " + "$"+price;
			
	}
	
	
	
	
	
	
	
	
	
	

}
