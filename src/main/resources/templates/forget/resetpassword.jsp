<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reset Password</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous">

<!-- OWN CSS -->
<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="css/main.css">

</head>

<body>

	<div class="container-fluid" style="margin-top: 70px;">


		<div class=" d-flex flex-column align-items-center">
			<div class="col-md-5">
				<a href="home"> <img src="img/blue.png" alt="logo" width="180"
					class="align-self-start mb-4">
				</a>
			</div>
			<div class="shadow col-md-5 p-5 bg-light"
				style="border-radius: 10px;">
				<div class="text-center">
					<h2 class="text-dark fw-bold mb-3">Create a New Password</h2>
					<%
						String error_message = (String) request.getAttribute("message");
						if (error_message != null) {
					%>
						<div class="alert alert-danger" role="alert"><%=error_message%></div>
					<%}%>
				</div>

				<form action="reset" method="post" class="p-2">

					<div class="row">
						<div class="form-group mb-0">
							<input type="password"
								class="form-control inp-txt-light p-3 ps-3 mt-2 mb-1"
								placeholder="New Password" name="password" required>
						</div>
						<div class="form-group mb-3">
							<input type="password"
								class="form-control inp-txt-light p-3 ps-3 mt-2 mb-1"
								placeholder="Confirm Password" name="cpassword" required>
						</div>
					</div>

					<div class="d-grid col-12 mx-auto">
						<button class="btn btn-primary rounded-pill p-2" type="submit">Submit</button>
					</div>

				</form>

			</div>
		</div>

	</div>



	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
		crossorigin="anonymous"></script>
</body>

</html>