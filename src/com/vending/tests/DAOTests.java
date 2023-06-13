package com.vending.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.vending.dto.ItemDto;

class DAOTests {
	ItemDto item;
	@BeforeEach
	void setUp() throws Exception {
		item = new ItemDto("Name" , new BigDecimal("1.50"), 5 );
	}

	@AfterEach
	void tearDown() throws Exception {
		item = null;
	}

	@Test
	void testName() {
		String nameValue = "Name";
		boolean isEquals = nameValue.equals(item.getName());
		assertEquals(isEquals, true);
		
		item.setName("Empty");
		isEquals = nameValue.equals(item.getName());
		assertNotEquals(isEquals, true);
		
		
	}
	
	@Test
	void testPrice() {
		BigDecimal bdValue = new BigDecimal("1.50");
		boolean isEquals = bdValue.equals(item.getPrice());
		assertEquals(isEquals, true);
		
		item.setPrice(new BigDecimal("5.25"));
		
		isEquals = bdValue.equals(item.getPrice());
		assertNotEquals(isEquals, true);

		
	}
	
	@Test
	void testStock() {
		int stockValue = 5;
		boolean isEquals = stockValue ==item.getStockCount();
		assertEquals(isEquals, true);
		
		item.setStockCountl(1);
		assertEquals(item.getStockCount(), 1);
		
		
		
	}

}
