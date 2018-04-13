package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.google.gson.Gson;

import dao.ProductDao;
import dao.SubCategoryDao;
import entities.SubCategory;

@WebServlet("/product")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50)

public class ProductController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String SAVE_DIR = "ElectronicsStore/WebContent/images";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		SubCategoryDao subCategoryDao = new SubCategoryDao();
		System.out.println("IDDD:" + request.getParameter("categoryId"));
		List<SubCategory> listSubCate = subCategoryDao.getCategoryMap().get(Integer.parseInt(request.getParameter("categoryId")));
	    response.setContentType("application/json");		
	    response.setCharacterEncoding("UTF-8"); // sets the encoding
	    String json = new Gson().toJson(listSubCate);
		System.out.println(listSubCate);
		response.getWriter().write(json);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Integer price, quantity, sub_category_id;
		String name, description, image;
		String urlRedirect = "admin/product.jsp";
		
		// handle image
		String appPath = request.getServletContext().getRealPath("");
		appPath = appPath.substring(0, (appPath.lastIndexOf(".metadata")));
		// constructs path of the directory to save uploaded file
		String savePath = appPath + SAVE_DIR;
		String fileName = null;
		Part part = request.getPart("file");
		fileName = extractFileName(part);
		fileName = new File(fileName).getName();
		part.write(savePath + File.separator + fileName);

		//assign data
		image = fileName;
		name = request.getParameter("name");
		description = request.getParameter("description");
		price = Integer.parseInt(request.getParameter("price"));
		quantity = Integer.parseInt(request.getParameter("quantity"));
		sub_category_id = Integer.parseInt(request.getParameter("sub_category"));

		ProductDao productDao = new ProductDao();
		productDao.save(name, description, price, image, quantity, sub_category_id);

		response.sendRedirect(urlRedirect);
	}

	private String extractFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				return s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		return "";
	}
}
