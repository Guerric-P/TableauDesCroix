<%@ page language="java" contentType="text/html"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<%@ include file="header.jsp" %>
<body>
<%@ include file="navbar.jsp" %>
<div class="container ">
	<div class="starter-template">
		<form class="form-horizontal">
			<div class="content">
				<div class="row">
					<div class="col-sm-2 col-xs-4">
						<button type="button" class="btn btn-danger" onClick="ResetIndicateurs()">Reset des indicateurs "A mis sa croix"</button>
					</div>
				</div>
				<div class="row top-buffer">
					<label class="col-xs-3 col-sm-3">Mettre une croix de type : </label>
					<div class=" col-sm-2 col-xs-2">
						<select id="selectTypeCroix" class="form-control">
							<option value="1">CDS</option>
							<option value="2">Sortie des frontières</option>
							<option value="3">Réunion</option>
						</select>
					</div>
					<label class="col-xs-1 col-sm-1"> à  </label>
					<div class=" col-sm-2 col-xs-2">
						<select name="" id="selectCroixSpeciale"  class="form-control">
							<c:forEach items="${liste }" var="element2" varStatus="loop">
								<c:set var="ilotPrecedent2" value="${ilot2 }" scope="request" />
								<c:set var="ilot2" value="${element2.getIlot().getLibelle() }" />
								<c:if test="${loop.first}">
									<optgroup label="${ilot2 }">
								</c:if>
								<c:if test="${ilotPrecedent2 != ilot2 && ilotPrecedent2 != null && !loop.first}">
									</optgroup>
									<optgroup label="${ilot2 }">
								</c:if>
								<option value="${element2.getId()}">${element2.getNom() } ${element2.getPrenom() }</option>
								<c:if test="${loop.last }">
									</optgroup>
								</c:if>
							</c:forEach>							
			  			</select>
					</div>
					<div class="col-sm-3 col-xs-3">
						<button type="button" class="btn btn-danger" onClick="MettreCroixSpeciale()">Valider</button>
					</div>
				</div>
			</div>
			<c:forEach items="${liste }" var="element">
				<c:set var="ilotPrecedent" value="${ilot }" scope="request" />
				<c:set var="ilot" value="${element.getIlot().getLibelle() }" />
				<c:if test="${ilotPrecedent == null || ilotPrecedent != ilot}">
					<h3>${ilot }</h3>
					<br />
				</c:if>
				<div class="form-group text-left">
	   				<label class="col-xs-12 col-sm-5">${element.getNom() } ${element.getPrenom() } ${element.getTrigramme() }	a ${element.getNombreCroix() } croix et <c:choose><c:when test="${element.isaMisSaCroix() }">a mis sa croix du jour</c:when><c:otherwise>n'a pas mis sa croix du jour</c:otherwise></c:choose></label>
					<div class="col-sm-2 col-xs-4">
     					<button type="button" class="btn btn-danger" onClick="ResetCroix(this)" data-nom-personne="${element.getNom() } ${element.getPrenom() }" data-id-personne="${element.getId() }">Reset les croix</button>
	   				</div>
				</div>
			</c:forEach>		
		</form>
	</div>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>