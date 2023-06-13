package com.vending.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.vending.dto.ItemDto;
import com.vending.service.Service;

class ServiceTests {
	Service service;
	@BeforeEach
	void setUp() throws Exception {
		service = new Service();
	}

	@AfterEach
	void tearDown() throws Exception {
		service = null;
	}

	@Test
	void testService() {
		
		String change = service.determineChange();
		// shouldnt append any thing to base message
		
		assertEquals(change, "Change Provided: \n");
		
	}
	
	
	
	
}
