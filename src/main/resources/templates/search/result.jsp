<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../component/header.jsp">
	<jsp:param value="Result" name="HTMLtitle" />
</jsp:include>




<!-- TOP CONTENT -->
<div
	class="container-fluid head-body m-0 border-bottom border-4 gradient-1">

	<div class="d-flex align-items-center flex-column">

		<div class="col-lg-10 col-12">
			<div class="img-container">
				<img src="img/bg-01.jpg" alt="this is img">
			</div>
		</div>

		<div class="col-lg-9 col-sm-12 pb-5">

			<div class="d-flex flex-column flex-lg-row justify-content-between">

				<div class="col-lg-7 col-sm-12">

					<div
						class="d-flex flex-column flex-lg-row justify-content-lg-start align-items-center ">
						
						
						
						
						<!-- PROFILE IMG -->
						<div class="my-profile">
                            <h1><span>${f}</span><span>${l}</span></h1>
                        </div>
						
						
						
						<div class=" text-lg-start p-3 text-center">
							<h2 class="fw-bold" style="font-size: 1.7em;">${fullName}</h2>
							<p class="m-0">${headline}</p>
							<p class="m-0 text-secondary">
								<!-- <span>Bandung Regency</span>, -->
								<span>${country}</span>
							</p>
							<p class="text-primary m-0">500+ Connections</p>
						</div>
					</div>


				</div>

				<div class="col-lg-5 col-12 text-lg-end align-self-end text-center">
					<!-- <button class="btn border-primary text-primary border-3 m-3" id="add-mdl-btn">
                            <span class="pe-3">
                                <i class="fa-regular fa-square-plus"></i>
                            </span>Add Profile Section</button> -->
					<button class="btn btn-primary">
						<span class="pe-1"> <i
							class="fa-solid fa-user-plus text-white"></i>
						</span>Connect
					</button>
				</div>

			</div>

		</div>

	</div>


</div>

<!-- MAIN CONTENT -->
<div class="container-fluid m-0 mt-3 mb-3 main-section">

	<div
		class="d-flex flex-lg-row justify-content-center flex-column align-items-center align-items-lg-start">

		<div class="col-11 col-lg-10  p-2">

			<div
				class="row d-flex flex-column flex-lg-row justify-content-evenly">


				<div class=" col-lg-7 p-0
					${about != '-' || certificate.size() != 0 || 
					certificate.size() != 0 || skills.size() != 0 ? 'd-block' : 'd-none' } " 
					style="height: fit-content;" id="left-side">
					
					
	
				
					<div class="row m-0 p-0 gx-0">


						
						<!-- ABOUT -->
						<div class="col-12 bg-light p-4 rounded gx-0 shadow m-0 mb-2 ${about != '-' ? 'd-block' : 'd-none' } ">

							<h4 class="fw-bold">About Me</h4>

							<!-- content -->
							<p>${about}</p>


						</div>
						
						


						<!-- EDUCATION -->
						<div class="col-12 bg-light  p-4 rounded gx-0 shadow mb-2 mt-2 ${education.size() == 0 ? 'd-none' : 'd-block'}">
							<h4 class="fw-bold">Education</h4>

							<!-- content -->
										
								<c:forEach var="ed" items="${education}">
								<div
									class="d-flex align-items-center pt-3 pb-3 border-bottom border-1">
									<img src="img/education.png" alt="img"
										class="profile-additional">
									<div class="ms-3">
										<h6 class="m-0">${ed.getUniversityName()}</h6>
										<p class="m-0">${ed.getMajoredIn()}</p>
										<p class="m-0 text-secondary">
											<span>${ed.getEdu_startDate()}</span> - <span>${ed.getEdu_endDate()}</span>
										</p>
									</div>
								</div>
								</c:forEach>
			


						</div>

						<!-- LICENSES -->
						<div class="col-12 bg-light  p-4 rounded gx-0 shadow mb-2 mt-2 ${certificate.size() == 0 ? 'd-none' : 'd-block' }">

							<h4 class="fw-bold">Licenses & Certifications</h4>



							<!-- content -->
							
							<c:forEach var="l" items="${certificate}">
									<!-- content -->
									<div
										class="d-flex align-items-center pt-3 pb-3 border-bottom border-1">
										<img src="img/certificate.png" alt="img" class="profile-additional">
										<div class="ms-3">
											<h6 class="m-0">${l.getCertificateName()}</h6>
											<p class="m-0">${l.getOrganization()}</p>
											<p class="m-0 text-secondary">
												<!-- <span>Dec 2022</span> - <span>Dec 2025</span> -->
												Issued at ${l.getCerticate_dateIssued()}
											</p>
										</div>
									</div>
								</c:forEach>


						</div>

					

						<!-- SKILLS -->
						<div class="col-12 bg-light  p-4 rounded gx-0 shadow mb-2 mt-2 ${skills.size() == 0 ? 'd-none' : 'd-block' }">

							<h4 class="fw-bold">Skills</h4>

							<!-- content -->							
							<c:forEach var="skills" items="${skills}">
								<div class="align-items-center pt-3 pb-3 border-bottom border-1">
									<h6 class="m-0 mb-3">${skills.getSkill_name()}</h6>
								</div>
							</c:forEach>





						</div>



					</div>
				</div>


				<div class="col-lg-4 bg-light p-4 rounded shadow"
					style="height: fit-content;" id="right-side">
					<h4 class="fw-bold">Add To Your Feed</h4>
					<div class="d-flex flex-lg-column flex-row flex-wrap" id="left_element">
						<div class="d-flex flex-row flex-lg-row p-3 align-items-center">
							<img src="img/p.jpg" alt="profile"
								class="small-profile rounded-pill me-3">
							<div>
								<h5 class="m-0 fs-6">Shima Rin</h5>
								<p class="m-0 mb-2 fw-lighter">UI Designer</p>
								<button class="btn-sm btn-primary  rounded-pill"
									style="width: 100px; border: none;">Connect</button>
							</div>
						</div>
						<div class="d-flex p-3 align-items-center">
							<img src="img/p2.jpg" alt="profile"
								class="small-profile rounded-pill me-3">
							<div>
								<h5 class="m-0 fs-6">Kanna Kamui</h5>
								<p class="m-0 mb-2 fw-lighter">UI Designer</p>
								<button class="btn-sm btn-primary  rounded-pill"
									style="width: 100px; border: none;">Connect</button>
							</div>
						</div>
						<div class="d-flex p-3 align-items-center">
							<img src="img/p3.jpg" alt="profile"
								class="small-profile rounded-pill me-3">
							<div>
								<h5 class="m-0 fs-6">Goto Hitori</h5>
								<p class="m-0 mb-2 fw-lighter">UI Designer</p>
								<button class="btn-sm btn-primary  rounded-pill"
									style="width: 100px; border: none;">Connect</button>
							</div>
						</div>
					</div>
				</div>


			</div>


		</div>



	</div>

</div>



<jsp:include page="../component/footer.jsp"></jsp:include>
