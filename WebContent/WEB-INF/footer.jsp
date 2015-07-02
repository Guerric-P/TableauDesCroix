<%@ page language="java" pageEncoding="UTF-8"%>
<div style="overflow:auto; height:150px; position:fixed; width:100%; bottom:0px; background-color: #fff;">
	<c:forEach items="${historique }" var="element">
		<div class=" col-sm-2 col-xs-12">${element.getDate() }</div>
		<div class=" col-sm-10 col-xs-12">
			<span class="bg-info">${element.getPersonneEmettrice().getNom() } ${element.getPersonneEmettrice().getPrenom() }</span>
				<c:choose>
					<c:when test="${element.getPersonneEmettrice().getId() ==  element.getPersonneReceptrice().getId() }"> s'est auto-mis une croix </c:when>
					<c:when test="${element.getTypeCroix() == 'CDS' }"><span class="bg-info">Croix CDS</span> à <span class="bg-info">${element.getPersonneReceptrice().getNom() } ${element.getPersonneReceptrice().getPrenom() }</span> </c:when>
					<c:when test="${element.getTypeCroix() == 'Reunion' }"><span class="bg-info">Croix réunion</span> à <span class="bg-info">${element.getPersonneReceptrice().getNom() } ${element.getPersonneReceptrice().getPrenom() }</span> </c:when>
					<c:when test="${element.getTypeCroix() == 'SortieDesFrontieres' }"><span class="bg-info">Croix sortie des frontières</span> à <span class="bg-info">${element.getPersonneReceptrice().getNom() } ${element.getPersonneReceptrice().getPrenom() }</span> </c:when>
					<c:otherwise> a mis une croix à <span class="bg-info">${element.getPersonneReceptrice().getNom() } ${element.getPersonneReceptrice().getPrenom() } </span></c:otherwise>
				</c:choose>
				pour le motif : 
			<span class="bg-info">${element.getMotif() }</span>
		</div>
	</c:forEach>
</div>