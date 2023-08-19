<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../component/header.jsp">
	<jsp:param value="SearchResult" name="HTMLtitle" />
</jsp:include>

<!-- MAIN RESULT -->
<div class="container mt-3 mb-3">
	<div class="row p-1">


		<div
			class="col-lg-10 mx-auto p-4 rounded border-secondary border-3 bg-light shadow main-section">
			<h2
				class="fw-bold mb-1 text-center text-lg-start fs-4 p-3 border-bottom border-1">Showing
				Relevant Result</h2>


			<!-- CONTENT -->
			<h4 class="text-center p-3">${notFound == true ? "Not Found" : ""}</h4>
			<c:forEach var="s" items="${selected}">
				<div class="row d-flex justify-content-between align-items-lg-end border-bottom border-2 pt-4 pb-4  m-2">
					<div class="col-lg-5 d-flex align-items-center p-0 justify-content-center justify-content-lg-start">
					
						<!-- <img src="img/p.jpg" alt="img" class="rounded-pill small-profile"> -->
						
						<div class="my-sm-profile">
                            <h1><span>${s.getFirstName().charAt(0)}</span><span>${s.getLastName().charAt(0)}</span></h1>
                        </div>
						
						<div class="ms-3 mb-3 mt-2">
							<h4 class="m-0 fs-5">${s.getFirstName()} ${s.getLastName()}</h4>
							<p class="m-0">${s.getHeadline()}</p>
							<p class="m-0 lh-1 text-primary">
								<!-- <span>Malang</span>, -->
								<span>${s.getCountry()}</span>
							</p>
						</div>
					</div>
	
					<!-- spacer in mobile -->
					<div class="mt-2 mb-2 d-block d-lg-none"></div>
	
					<div
                        class="
                        col-lg-4 text-lg-end text-center p-0 d-flex justify-content-lg-end justify-content-center align-items-center">
                        <button class="btn border-primary border-2 text-primary me-3"><span>
                                <i class="fa-solid fa-user-plus text-primary"></i>
                            </span>Connect</button>
                        <form action="result" method="post" class="m-0">
                       
                            <input type="hidden" name="uId" value="${s.getUserId()}" class="m-0">
                            <button type="submit" class="btn ms-auto" style="background-color: rgb(229, 229, 229);">View Profile</button>
                        </form>
                    </div>
				</div>
			</c:forEach>


		</div>

	</div>

</div>


<jsp:include page="../component/footer.jsp"></jsp:include>