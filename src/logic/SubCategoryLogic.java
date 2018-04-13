package logic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.regex.Pattern;

import dao.SubCategoryDao;
import entities.SubCategory;

public class SubCategoryLogic {
	public boolean exportSubCategoryToFile(String subCategoryFile){
		SubCategoryDao subCategoryDao = new SubCategoryDao();
		
		try {
			FileOutputStream fosc = new FileOutputStream(subCategoryFile);
			PrintWriter pwc = new PrintWriter(fosc);
			HashMap<Integer, SubCategory> mapSubCategory = subCategoryDao.getAllSubCategoryMap();
			for (Integer key : mapSubCategory.keySet()) {
				SubCategory subCategory = mapSubCategory.get(key);
				pwc.print(subCategory.getCategoryId() + "|");
				pwc.print(subCategory.getName() + "|");
				pwc.print(subCategory.getDescription());
				pwc.println();
			}
			System.out.println("Export SubCategory done!");
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
	
	public boolean importSubCategoryFromFile(String subCategoryFile){
		SubCategoryDao subCategoryDao = new SubCategoryDao();
		
		try(BufferedReader br = new BufferedReader(new FileReader(subCategoryFile))) {
		    for(String line; (line = br.readLine()) != null; ) {
		    	String[] data = line.split(Pattern.quote("|"));
		        subCategoryDao.save(Integer.parseInt(data[0]), data[1], data[2]);
		    }
			System.out.println("Import SubCategory done!");
		    // line is not visible here.
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Import SubCategory fail!");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Import SubCategory fail!");
			e.printStackTrace();
		}
		return true;
	}
	
	public static void main(String []args) {
		SubCategoryLogic subCategoryLogic = new SubCategoryLogic();
//		subCategoryLogic.exportSubCategoryToFile("sub_category_export");		
//		subCategoryLogic.importSubCategoryFromFile("sub_category_export");
	}
}
