<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Header</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/font-awesome.min.css" rel="stylesheet">
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

</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div class="container col-md-offset-5 col-md-3 algo">
		<table class="table table-bordered">
			<tbody>
				<tr>
					<td>Export data</td>
				</tr>
				<tr>
					<td>
						<form class="form-horizontal" name="export" id="export"
							method="post" action="../export"
							enctype="multipart/form-data">
							<div class="col-md-offset-4 algo">
								<input type="submit" class="btn btn-primary"
									value="Export to file">
							</div>
						</form>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>