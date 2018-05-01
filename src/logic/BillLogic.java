package logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import dao.BillDao;
import dao.ProductDao;
import entities.Bill;
import entities.Category;
import entities.Product;
import ultils.Constant;

public class BillLogic {
	public boolean writeTransactionToFile(int numberTrans, String productFile, String categoryFile){
		BillDao billDao = new BillDao();
		ProductDao productDao = new ProductDao();
		HashMap <Integer, Integer> map = productDao.getProductMap();
		JSONObject jsonOb;
		try {
			File myProductFile = new File(productFile);
			myProductFile.createNewFile();
			
			File myCategoryFile = new File(categoryFile);
			myCategoryFile.createNewFile();
			
			FileOutputStream fosp = new FileOutputStream(myProductFile);
			FileOutputStream fosc = new FileOutputStream(myCategoryFile);
			PrintWriter pwp = new PrintWriter(fosp);
			PrintWriter pwc = new PrintWriter(fosc);
			List<Bill> billList = billDao.getListBill(numberTrans);
			for(Bill bill : billList){
				JSONArray jsonArray = new JSONArray();
				JSONParser jsonParser = new JSONParser();
				
				String str = bill.getCartString();
				jsonArray = (JSONArray) jsonParser.parse(str);
				for(int i = 0; i < jsonArray.size(); i++){
					jsonOb = (JSONObject) jsonArray.get(i);
					Set<String> keys = jsonOb.keySet();
					for(String key : keys){
						pwp.print(key + " ");
						pwc.print(map.get(Integer.parseInt(key)) + " ");
					}
				}
				pwp.println();
				pwc.println();
				
			}
			pwp.close();
			pwc.close();
			fosp.flush();
			fosc.flush();
			fosp.close();
			fosc.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}
	
	public boolean exportBillToFile(String billFile){
		BillDao billDao = new BillDao();
		
		try {
			FileOutputStream fosc = new FileOutputStream(billFile);
			PrintWriter pwc = new PrintWriter(fosc);
			List<Bill> listBill= billDao.getListBill(2000);
			for(Bill bill : listBill){
				pwc.print(bill.getCartString() + "|");
				pwc.print(bill.getAddress() + "|");
				pwc.print(bill.getPhone() + "|");
				pwc.print(bill.getAccountId() + "|");
				pwc.print(bill.getChecked() + "|");
				pwc.println();
			}
			pwc.close();
			fosc.flush();
			fosc.close();
			System.out.println("Export Bill done!");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return true;
	}
	
	public boolean importBillFromFile(String billFile){
		BillDao billDao = new BillDao();
		Bill bill = new Bill();
		
		try(BufferedReader br = new BufferedReader(new FileReader(billFile))) {
		    for(String line; (line = br.readLine()) != null; ) {
		    	String[] data = line.split(Pattern.quote("|"));
		    	bill.setCartString(data[0]);
		    	bill.setAddress(data[1]);
		    	bill.setPhone(data[2]);
		    	bill.setAccountId(Integer.parseInt(data[3]));
		    	bill.setChecked(Integer.parseInt(data[4]));
		    	billDao.importBill(bill);
		    }
			System.out.println("Import Bill done!");
		    // line is not visible here.
		} catch (FileNotFoundException e) {
			System.out.println("Import Bill fail!");
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Import Bill fail!");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	public static void main(String []args) {
		BillLogic billLogic = new BillLogic();
		billLogic.writeTransactionToFile(Constant.NUMBER_TRANSACTION, Constant.PRODUCT_INPUT_FILE, Constant.SUB_CATEGORY_INPUT_FILE);
		billLogic.exportBillToFile("bill_export");
//		billLogic.importBillFromFile("bill_export");
	}
}
