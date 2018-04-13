<%@page import="dao.CategoryDao"%>
<%@page import="entities.Category"%>
<%@page import="dao.SubCategoryDao"%>
<%@page import="entities.SubCategory"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Header</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/font-awesome.min.css" rel="stylesheet">
<link href="css/datepicker3.css" rel="stylesheet">
<link href="css/styles.css" rel="stylesheet">

<!--Custom Font-->
<link
	href="https://fonts.googleapis.com/css?family=Montserrat:300,300i,400,400i,500,500i,600,600i,700,700i"
	rel="stylesheet">

<script src="js/jquery-1.11.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/chart.min.js"></script>
<script src="js/chart-data.js"></script>
<script src="js/easypiechart.js"></script>
<script src="js/easypiechart-data.js"></script>
<script src="js/bootstrap-datepicker.js"></script>
<script src="js/custom.js"></script>
<script>
	
$(document).ready(function() {
	$('select[name=category]').change(function(){
	    var categoryId = $('#category').val();
	    $.ajax({
	        url: "../product",
	        data: {
	        	categoryId: categoryId
	        	},
	        success: function(data){
	        	var slctSubcat=$('#sub_category'), option="";
	            slctSubcat.empty();

	            for(var i=0; i<data.length; i++){
	                option = option + "<option value='" + data[i].id + "'>" + data[i].name + "</option>";
	            }
	            slctSubcat.append(option);
	        },
	        error:function(){
	            alert("error");
	        }

	    });
	});
});

</script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="container col-md-offset-5 col-md-3 algo">
		<table class="table table-bordered">
			<tbody>
				<tr>
					<td>Add Product</td>
				</tr>
				<tr>
					<td>
						<form class="form-horizontal" name="product" id="product"
							method="post" action="../product"
							enctype="multipart/form-data">
							<div class="control-group">
								<label class="span2 control-label">Category&nbsp</label>
								<div class="controls">
									<select name="category" id="category">
										<%
											CategoryDao categoryDao = new CategoryDao();
											for (Category category : categoryDao.getListCategory()) {
										%>
										<option value="<%=category.getId()%>"><%=category.getName()%></option>

										<%
											}
										%>
									</select>
								</div>
							</div>
							<div class="control-group">
								<label class="span2 control-label">Sub Category&nbsp</label>
								<div class="controls">
									<select name="sub_category" id="sub_category">
										<%
											Category category = categoryDao.getFirstCategory();
											SubCategoryDao subCategoryDao = new SubCategoryDao();
											for (SubCategory subCategory : subCategoryDao.getCategoryMap().get(category.getId())) {
										%>
										<option value="<%= subCategory.getId()%>"><%= subCategory.getName()%></option>
										<% 
											}
										%>
									</select>
								</div>
							</div>
							<div class="control-group">
								<label class="span2 control-label">Name&nbsp</label>
								<div class="controls">
									<input type="text" name="name" required>
								</div>
							</div>
							<div class="control-group">
								<label class="span2 control-label">Description&nbsp</label>
								<div class="controls">
									<input type="text" name="description" value="San pham tot">
								</div>
							</div>
							<div class="control-group">
								<label class="span2 control-label">Price&nbsp</label>
								<div class="controls">
									<input type="text" name="price" value="150000" required>
								</div>
							</div>
							<div class="control-group">
								<label class="span2 control-label">Image&nbsp</label>
								<div class="controls">
									<input type="file" name="file" required>
								</div>
							</div>
							<div class="control-group">
								<label class="span2 control-label">Quantity&nbsp</label>
								<div class="controls">
									<input type="text" name="quantity" value="50" required>
								</div>
							</div>
							<div class="col-md-offset-4 algo">
								<input type="submit" class="btn btn-primary"
									value="Add Product">
							</div>
						</form>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>