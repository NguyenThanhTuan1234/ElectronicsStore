package logic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.regex.Pattern;

import dao.CategoryDao;
import entities.Category;

public class CategoryLogic {
	public boolean exportCategoryToFile(String categoryFile){
		CategoryDao categoryDao = new CategoryDao();
		
		try {
			FileOutputStream fosc = new FileOutputStream(categoryFile);
			PrintWriter pwc = new PrintWriter(fosc);
			List<Category> listCategory = categoryDao.getListCategory();
			for(Category category : listCategory){
				pwc.print(category.getName() + "|");
				pwc.print(category.getDescription());
				pwc.println();
			}
			pwc.close();
			fosc.flush();
			fosc.close();
			System.out.println("Export Category done!");
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return true;
	}
	
	public boolean importCategoryFromFile(String categoryFile){
		CategoryDao categoryDao = new CategoryDao();
		
		try(BufferedReader br = new BufferedReader(new FileReader(categoryFile))) {
		    for(String line; (line = br.readLine()) != null; ) {
		    	String[] data = line.split(Pattern.quote("|"));
		        categoryDao.save(data[0], data[1]);
		    }
			System.out.println("Import Category done!");
		    // line is not visible here.
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Import Category fail!");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Import Category fail!");
			e.printStackTrace();
		}
		return true;
	}
	
	public static void main(String []args) {
		CategoryLogic categoryLogic = new CategoryLogic();
//		categoryLogic.exportCategoryToFile("category_export");		
//		categoryLogic.importCategoryFromFile("category_export");
	}
}
