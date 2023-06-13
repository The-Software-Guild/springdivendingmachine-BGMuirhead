package com.vending.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.vending.dao.Dao;
import com.vending.dto.ItemDto;
import com.vending.enums.Coins;
import com.vending.exception.InsufficientFundsException;
import com.vending.exception.NoItemInventoryException;

public class Service implements IService {
	private Scanner sc = new Scanner(System.in);
	private List<ItemDto> items = new ArrayList<>();
	private BigDecimal zero = new BigDecimal(0).setScale(2, RoundingMode.HALF_UP);
	private BigDecimal budget = zero;

	@Autowired
	private Dao dao;

	private LocalDate readDate() {

		boolean flag = false;

		do {
			try {
				String relDate = sc.nextLine();
				if (relDate.length() != 10) {
					System.out.println("Incorrect Format. Try Again");
					continue;
				}
				String[] split = relDate.split("/");
				if (split.length != 3) {
					System.out.println("Incorrect Format. Try Again");
					continue;
				}

				int day = Integer.parseInt(split[0]);
				int month = Integer.parseInt(split[1]);
				int year = Integer.parseInt(split[2]);

				return LocalDate.of(year, month, day);
			} catch (Exception e) {

			}
		} while (!flag);

		return null;

	}

	private double readDouble() {

		boolean flag = false;

		do {
			try {
				double i = Double.parseDouble(sc.nextLine());

				if (i < 0) {
					throw new Exception();
				} else {
					return i;
				}

			} catch (Exception e) {
				System.out.println("Error, try again");
			}
		} while (!flag);

		return 0;

	}

	private int readInt() {

		boolean flag = false;

		do {
			try {
				int i = Integer.parseInt(sc.nextLine());

				if (i < 0) {
					throw new Exception();
				} else {
					return i;
				}

			} catch (Exception e) {
				System.out.println("Error, try again");
			}
		} while (!flag);

		return 0;

	}

	@Override
	public void displayItems() {

		System.out.println("0. Select to conclude transaction");
		int counter = 1;

		for (ItemDto i : items) {
			if (i.getStockCount() == 0) {
				counter++;
				continue;
			}
			System.out.println(counter + ". " + i.format());
			counter++;
		}
		System.out.println();

	}

	@Override
	public void getSelection() {

		while (true) {
			displayItems();
			System.out.println("Please select and item");
			int i = readInt();
			

			if (i > items.size()) {
				System.out.println("Invalid input, try again");
				i = readInt();
				
			}
			if (i == 0) {
				return;
			}
			

			// get the item
			ItemDto item = items.get(i - 1);
			
			
			
			try {
				if (item.getStockCount()==0) {
					throw new NoItemInventoryException("Item selected is out of stock");
				}
			} catch (NoItemInventoryException nie) {
				System.out.println(nie.getMessage() +"\n");
				continue;
			}
			
			BigDecimal temp = budget.subtract(item.getPrice());
			// check valid
			try {
				if (temp.compareTo(zero) < 0) {
					throw new InsufficientFundsException("Insufficient Funds!");

				} else {
					System.out.println("Item selected");
					items.get(i - 1).setStockCountl(items.get(i - 1).getStockCount() - 1);
					budget = temp;

					if (temp.compareTo(zero) == 0) {
						return;
					}
					System.out.println("Remaining funds: " + budget);
					System.out.println();

				}
			} catch (InsufficientFundsException ife) {
				System.out.println(ife.getMessage());
				System.out.println("Remaining funds: " + budget);
				System.out.println();
			}
		}

	}

	@Override
	public void setBudget() {
		System.out.println("Please input your funds:");
		budget = new BigDecimal(String.valueOf(readDouble()));
		System.out.println();

	}

	@Override
	public void readItems() {
		items = dao.readItemsDao();
	}

	@Override
	public void writeItems() {
		dao.writeItemsDao(items);
		

	}

	@Override
	public void run() {

		setBudget();
		getSelection();
		returnFunds();

	}

	public void returnFunds() {
		if (budget.compareTo(zero) == 0) {
			System.out.println("No funds remaining. Have a nice day!");
		} else {
			System.out.println(determineChange());
		}

	}

	public String determineChange() {

		BigDecimal value = budget;

		BigDecimal quater = new BigDecimal(Coins.Quater.getValue());
		BigDecimal dime = new BigDecimal(Coins.Dime.getValue());
		BigDecimal nickel = new BigDecimal(Coins.Nickel.getValue());
		BigDecimal penny = new BigDecimal(Coins.Penny.getValue());
		BigDecimal bd;
		BigDecimal numQ = null, numD = null, numN = null, numP = null;
		try {
			numQ = value.divide(quater, RoundingMode.DOWN).setScale(0, RoundingMode.DOWN);
			bd = numQ.multiply(quater);
			value = value.subtract(bd);
		} catch (ArithmeticException e) {

		}
		try {
			numD = value.divide(dime, RoundingMode.DOWN).setScale(0, RoundingMode.DOWN);
			bd = numD.multiply(dime);
			value = value.subtract(bd);
		} catch (ArithmeticException e) {

		}
		try {
			numN = value.divide(nickel, RoundingMode.DOWN).setScale(0, RoundingMode.DOWN);
			bd = numN.multiply(nickel);
			value = value.subtract(bd);
		} catch (ArithmeticException e) {

		}
		try {
			numP = value.divide(penny, RoundingMode.DOWN).setScale(0, RoundingMode.DOWN);
			bd = numP.multiply(penny);
			value = value.subtract(bd);
		} catch (ArithmeticException e) {

		}
		BigDecimal zero = new BigDecimal(0);
		String qs = (numQ == null) || numQ.compareTo(zero) == 0 ? "" : "Quaters: " + numQ.toString() + "\n";
		String ds = (numD == null) || numD.compareTo(zero) == 0 ? "" : "Dimes: " + numD.toString() + "\n";
		String ns = (numN == null) || numN.compareTo(zero) == 0 ? "" : "Nickles: " + numN.toString() + "\n";
		String ps = (numP == null) || numP.compareTo(zero) == 0 ? "" : "Pennies: " + numP.toString() + "\n";

		return "Change Provided: \n" + qs + ds + ns + ps;

	}

}
