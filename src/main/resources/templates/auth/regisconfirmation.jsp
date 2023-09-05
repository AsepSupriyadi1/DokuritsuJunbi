<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Registration Confirmation</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
	crossorigin="anonymous">

<!-- OWN CSS -->
<link rel="stylesheet" href="css/main.css">
<!-- font awesome -->
<script src="https://kit.fontawesome.com/b7d9f56fc5.js"
	crossorigin="anonymous"></script>
	
	
<!-- JQUERY -->
	<script src="js/jquery-3.6.1.min.js"></script>

</head>

<body>

	<div class="container-fluid" style="margin-top: 80px;">


		<div class=" d-flex flex-column align-items-center">
			<div class="col-md-5">
				<a href="landingPage.html"> <img src="img/blue.png" alt="logo"
					width="180" class="align-self-start mb-4">
				</a>
			</div>
			<div class="shadow col-md-5 p-5 bg-light"
				style="border-radius: 18px;">
				<div class="text-center">
					<h2 class="text-dark fw-bold mb-3">Confirm Your Email</h2>
					<p class="m-0">
						Dear <b>${firstName}</b>, Thank you for Signing up for ABC Jobs Pte
						Ltd. Please note that to access the account actively, you have to
						enter the confirmation code that we have sent
					</p>
					<%-- <%
                        String error_message = (String) request.getAttribute("message");
                        if (error_message != null) {
					%>
					<div class="alert alert-danger" role="alert"><%=error_message%></div>
					<%
					    }
					%> --%>
				</div>

				<!-- <form action="regisconfirmation" method="post"> -->

					<div class="row d-flex justify-content-between">
						<div class="form-group mb-1 p-1 justify-content-center d-flex">
							<!-- <input type="text" class="form-control inp-txt-light p-3 ps-3 mt-2 mb-2"
								placeholder="Enter Verification Code" name="verif_code"
								style="width: 95%;" required> -->
						</div>
					</div>

					<div class="d-grid col-12 mx-auto text-center">
						<button class="btn btn-primary rounded-pill p-2" 
						type="submit" data-bs-toggle="modal" 
						data-bs-target="#verifmodal">Get Confirmation Link</button>
						<!-- <p class="mt-3">
							didn't get the code? <a class="p-0 m-0 text-decoration-none"
								data-bs-toggle="modal" data-bs-target="#verifmodal">Send
								Again</a>
						</p> -->
					</div>

				<!-- </form> -->

			</div>
		</div>

	</div>




	<!-- Modal -->
	<div class="modal fade" id="verifmodal" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body text-center">
					<i class="fa-solid fa-envelope-circle-check mb-3"
						style="font-size: 5em;"></i>
					<h3>Successful</h3>
					<p>The link has been sent it to your email</p>
					
					
					<%-- <p>The Code Is : ${verif_code} ${verif_code2}</p> --%>
				</div>
				<div class="modal-footer">
					<!-- <button type="button" class="mx-auto btn btn-secondary"
						data-bs-dismiss="modal">continue</button> -->
					<a href="verified" class="mx-auto btn btn-secondary"
						>continue</a>	
				</div>
			</div>
		</div>
	</div>
	
	



	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
		crossorigin="anonymous"></script>

	<!-- <script>
		$(document).ready(function() {

			//let index = $(this).index();
			$('#verifmodal').modal('show');

		});
	</script> -->
</body>

</html>