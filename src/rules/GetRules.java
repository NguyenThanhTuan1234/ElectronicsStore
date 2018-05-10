package rules;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.print.DocFlavor.URL;

public class GetRules {
	public static void main(String [] arg) throws FileNotFoundException, IOException{
		String input = fileToPath("/home/tuankun/workspace/ElectronicsStore/product_inputx.txt");
	}
	
	public static String fileToPath(String filename) throws UnsupportedEncodingException{
		java.net.URL url = GetRules.class.getResource(filename);
		 return java.net.URLDecoder.decode(url.getPath(),"UTF-8");
	}
}
