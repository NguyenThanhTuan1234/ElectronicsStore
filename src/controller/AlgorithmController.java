package controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ca.pfv.spmf.algorithms.associationrules.agrawal94_association_rules.AssocRules;
import logic.AlgorithmsLogic;
import logic.BillLogic;
import logic.ProductRuleLogic;
import logic.SubCategoryRuleLogic;
import ultils.Constant;

/**
 * Servlet implementation class AlgorithmController
 */
@WebServlet("/AlgorithmController")
public class AlgorithmController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		BillLogic billLogic = new BillLogic();
		AlgorithmsLogic algo = new AlgorithmsLogic();
		AssocRules rules;
		String url = "";
		
		billLogic.writeTransactionToFile(Constant.NUMBER_TRANSACTION, Constant.PRODUCT_INPUT_FILE, Constant.SUB_CATEGORY_INPUT_FILE);
		
		double min_sup = Double.parseDouble(request.getParameter("min_sup"))/100;
		double min_conf = Double.parseDouble(request.getParameter("min_conf"))/100;
		String level = request.getParameter("level");
		
		if("category".equals(level)){
			SubCategoryRuleLogic subCategoryRuleLogic = new SubCategoryRuleLogic();
			rules = algo.generateRulesByFPGrowth(Constant.SUB_CATEGORY_INPUT_FILE, min_sup, min_conf);
			subCategoryRuleLogic.insertRules(rules);
			File f = new File(Constant.SUB_CATEGORY_INPUT_FILE);
		    f.delete();
			url = "admin/sub_category_result.jsp";
			
		}else {
			ProductRuleLogic productRuleLogic = new ProductRuleLogic();
			rules = algo.generateRulesByFPGrowth(Constant.PRODUCT_INPUT_FILE, min_sup, min_conf);
			productRuleLogic.insertRules(rules);
			File f = new File(Constant.PRODUCT_INPUT_FILE);
		    f.delete();
		    url = "admin/product_result.jsp";
		}
		
		
		
		response.sendRedirect(url);
	}

}
