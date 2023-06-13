package com.vending.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.vending.service.IService;
import com.vending.service.Service;

public class Controller {



	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

		IService s = context.getBean("service", IService.class);



		s.readItems();
		
		s.run();

		s.writeItems();
	

	}

}
