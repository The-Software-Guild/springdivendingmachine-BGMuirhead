package com.vending.service;

import java.math.BigDecimal;
import java.util.List;

import com.vending.dto.ItemDto;

public interface IService {
	
	void displayItems();
	void getSelection();
	void setBudget();
	
	void readItems();
	void writeItems();
	void run();
	void returnFunds();
	
	
	
	
	

}
