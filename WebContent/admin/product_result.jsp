<%@page import="entities.Account"%>
<%@page import="entities.Product"%>
<%@page import="java.util.HashMap"%>
<%@page import="dao.ProductDao"%>
<%@page import="entities.ProductRule"%>
<%@page import="dao.ProductRuleDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Result</title>
</head>
<body>
	<%
		Account account = null;
		if (session.getAttribute("account") != null) {
			account = (Account) session.getAttribute("account");
			if (account.getRole() != 1) {
				response.sendRedirect("/ElectronicsStore/index.jsp");
			}
		} else {
			response.sendRedirect("/ElectronicsStore/login.jsp");
		}
		ProductRuleDao productRuleDao = new ProductRuleDao();
		ProductDao productDao = new ProductDao();
		Product product1, product2;
		HashMap<Integer, Product> map = productDao.getAllProductMap();
	%>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="container col-md-offset-2 result">
		<h2 class=" rule-in">Danh sách luật kết hợp</h2>
		<table class="table table-striped">
			<thead>
				<tr>
					<th scope="col">STT</th>
					<th scope="col">Sản Phẩm 1</th>
					<th scope="col">Sản Phẩm 2</th>
					<th scope="col">Support</th>
					<th scope="col">Confidence</th>
				</tr>
			</thead>
			<tbody>
				<%
					int i = 0;
					for (ProductRule rule : productRuleDao.getAllRule()) {
						i++;
						int productId1 = rule.getProductId1();
						int productId2 = rule.getProductId2();
						
				%>
				<tr>
					<th scope="row"><%=i %></th>
					<td><%=map.get(productId1).getName()%></td>
					<td><%=map.get(productId2).getName() %></td>
					<td><%=rule.getSupCount() %></td>
					<td><%=rule.getConf() %></td>
				</tr>
				<%
					}
				%>
			</tbody>
		</table>
	</div>
</body>
</html>