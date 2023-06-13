package com.vending.dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import com.vending.dto.ItemDto;

@Component
public class Dao {

	File file = new File("res\\inventory.txt");
	Scanner sc;
	PrintWriter pw;

	public List<ItemDto> readItemsDao() {

		
		
//		System.out.println(file.getAbsolutePath());
		List<ItemDto> inventory = new ArrayList<>();
		try {
			sc = new Scanner(file);
			while (sc.hasNextLine()) {
				String[] arr = sc.nextLine().split(",");
				String iName = arr[0];
				BigDecimal iPrice = new BigDecimal(arr[1]);
				int iCount = Integer.parseInt(arr[2]);
				
				ItemDto item = new ItemDto(iName,iPrice,iCount);
				
				inventory.add(item);

			}
			
//			System.out.println("All items imported Successfully");
			return inventory;

		} catch (FileNotFoundException fnf) {
			// TODO Auto-generated catch block
			fnf.printStackTrace();
		}  catch (Exception e) {
			e.printStackTrace();
		} finally {
			sc.close();
		}

		return null;
	}

	public void writeItemsDao(List<ItemDto> items) {
		try {
			 pw = new PrintWriter(file);
			 
			 Stream<String> values = items.stream().map( i -> ( i.getName() + ","+ i.getPrice() + "," + i.getStockCount()+"\r\n"));
			 
			 Iterator<String> iter = values.iterator();
			 while(iter.hasNext())
			 {
				 pw.write(iter.next());
			 }
			
			

		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (Exception ef) {
			ef.printStackTrace();
			System.out.println(ef.getMessage());
		} finally {
			pw.close();
		}
		
//		System.out.println("Save successful");

	}
}
