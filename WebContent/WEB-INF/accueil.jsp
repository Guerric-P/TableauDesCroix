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
				<div class="row top-buffer">
					<div class=" col-lg-2 col-sm-2">
						Mettre une croix à :
					</div>
					<div class=" col-lg-3 col-sm-3">
						<select name="" id="select_cible_croix"  class="form-control">
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
					<div class="col-lg-2 col-sm-2">
						<button type="button" class="btn btn-primary" <c:if test="${utilisateurCourant.isaMisSaCroix() }">disabled="disabled"</c:if> id="boutonMettreCroix_${element.getId() }" onClick="MettreCroix(this)">Valider</button>
					</div>
				</div>
				<div class="row top-buffer">
					<div class=" col-lg-2 col-sm-2">
					Date croissants :
					</div>
					<div class=" col-lg-3 col-sm-3">
						<input type="text" id="inputDate" disabled="disabled" value="${utilisateurCourant.getDateCroissantsPourIHM() }"/>
					</div>
					<div class=" col-lg-1 col-sm-1">
						<button id="btnDate" type="button" class="btn btn-primary" onClick="btnDate_Onclick(this)" >Editer</button>
					</div>
				</div>
			</div>
			<c:forEach items="${liste }" var="element">
				<c:set var="ilotPrecedent" value="${ilot }" scope="request" />
				<c:set var="ilot" value="${element.getIlot().getLibelle() }" />
				<c:if test="${ilotPrecedent == null || ilotPrecedent != ilot}">
					<div class="row">
						<div class=" col-lg-12 col-sm-12">
							<h3>${ilot }</h3>
						</div>
					</div>
				</c:if>
				<div class="row">
					<div class="form-group text-left">
		   				<label class="col-lg-12 col-sm-12">${element.getNom() } ${element.getPrenom() } ${element.getTrigramme() }	a ${element.getNombreCroix() } croix et <c:choose><c:when test="${element.isaMisSaCroix() }">a mis sa croix du jour.</c:when><c:otherwise>n'a pas mis sa croix du jour.</c:otherwise></c:choose><c:if test="${element.getDateCroissantsPourIHM() != null }"> Apportera les croissants le ${element.getDateCroissantsPourIHM() }.</c:if></label>
	   				</div>
				</div>
			</c:forEach>	
		</form>
	</div>
</div>
<%@ include file="footer.jsp" %>
</body>
</html>