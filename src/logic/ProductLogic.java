package logic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.regex.Pattern;

import dao.ProductDao;
import entities.Product;

public class ProductLogic {
	public boolean exportProductToFile(String productFile){
		ProductDao productDao = new ProductDao();
		
		try {
			FileOutputStream fosc = new FileOutputStream(productFile);
			PrintWriter pwc = new PrintWriter(fosc);
			HashMap<Integer, Product> mapProduct = productDao.getAllProductMap();
			for (Integer key : mapProduct.keySet()) {
				Product product = mapProduct.get(key);
				pwc.print(product.getName() + "|");
				pwc.print(product.getDescription() + "|");
				pwc.print(product.getPrice() + "|");
				pwc.print(product.getImage() + "|");
				pwc.print(product.getQuantity() + "|");
				pwc.print(product.getSubCategoryId());
				pwc.println();
			}
			System.out.println("Export Product done!");
			pwc.close();
			fosc.flush();
			fosc.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return true;
	}
	
	public boolean importProductFromFile(String productFile){
		ProductDao productDao = new ProductDao();
		
		try(BufferedReader br = new BufferedReader(new FileReader(productFile))) {
		    for(String line; (line = br.readLine()) != null; ) {
		    	String[] data = line.split(Pattern.quote("|"));
		    	productDao.save(data[0], data[1], Integer.parseInt(data[2]), 
		    			data[3], Integer.parseInt(data[4]), Integer.parseInt(data[5]));
		    }
			System.out.println("Import Product done!");
		    // line is not visible here.
		} catch (FileNotFoundException e) {
			System.out.println("Import Product fail!");
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Import Product fail!");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	public static void main(String []args) {
		ProductLogic productLogic = new ProductLogic();
		productLogic.exportProductToFile("product_export");
//		productLogic.importProductFromFile("product_export");
	}
}
