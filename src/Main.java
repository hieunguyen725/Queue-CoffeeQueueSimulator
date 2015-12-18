/*
 * Hieu Trung Nguyen
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 * Simulator of the coffeeshop, it takes in an input test file 
 * and display the shop for every processing input.
 */
public class Main {
	public static void main(String[] args) throws FileNotFoundException {
		CoffeeShop shop = new CoffeeShop();
		// Change file name to test other files
		File file = new File("testOverallProgram.txt");
		Scanner input = new Scanner(file);
		while (input.hasNextLine()) {
			String line = input.nextLine();
			System.out.println("Processing Input: " + line);
			if (line.charAt(0) == 'C' || line.charAt(0) == 'I') {
				if (line.substring(0, 3).equals("CSS")) {
					shop.offer(new CSSStudent(line));
				} else if (line.substring(0, 3).equals("CES")) {
					shop.offer(new CESStudent(line));
				} else if (line.substring(0, 3).equals("ITS")) {
					shop.offer(new ITSStudent(line));
				}
				shop.displayShop();
			} else {
				MyQueue servedCustomers = shop.poll();
				shop.displayShop();
				System.out.println("Satisfied Customers: " + servedCustomers);
				
			}
			System.out.println();
		}
	}
}
