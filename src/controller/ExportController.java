package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logic.BillLogic;
import logic.CategoryLogic;
import logic.ProductLogic;
import logic.SubCategoryLogic;
import ultils.Constant;

@WebServlet("/export")
public class ExportController extends HttpServlet {
	

	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExportController() {
	super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
    	doPost(request, response);
    }
    
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
		String urlRedirect = "/ElectronicsStore/admin/algorithm.jsp";
		
//		CategoryLogic categoryLogic = new CategoryLogic();
//		SubCategoryLogic subCategoryLogic = new SubCategoryLogic();
//		ProductLogic productLogic = new ProductLogic();
//		
//		categoryLogic.exportCategoryToFile(Constant.CATEGORY_EXPORT_FILE);
//		subCategoryLogic.exportSubCategoryToFile(Constant.SUB_CATEGORY_EXPORT_FILE);
//		productLogic.exportProductToFile(Constant.PRODUCT_EXPORT_FILE);
		BillLogic billLogic = new BillLogic();
		billLogic.exportBillToFile(Constant.BILL_EXPORT_FILE);
		
		response.sendRedirect(urlRedirect);
    }
}
