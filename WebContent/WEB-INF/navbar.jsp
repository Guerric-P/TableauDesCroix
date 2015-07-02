<%@ page language="java" pageEncoding="UTF-8"%>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
 	<div class="container">
 		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
	            <span class="sr-only">Toggle navigation</span>
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
	            <span class="icon-bar"></span>
          	</button>
       	 	<ul class="nav nav navbar-nav">
                <li class="dropdown">
	         	 	<a class="navbar-brand dropdown-toggle"  id="dropdownMenu1" role="button" data-toggle="dropdown" aria-expanded="false">
		         	 	${utilisateurCourant.getPrenom() } ${ utilisateurCourant.getNom() }
		         	 	<span class="caret"></span>
	         	 	</a>
			  		<ul class="dropdown-menu">
		    			<li><a href="javascript:Deconnexion()">Déconnexion</a></li>
			  		</ul>
		  		</li>
		  	</ul>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
          	<ul class="nav navbar-nav">
            	<li class="<%= ((String)request.getSession().getAttribute("ongletCourant")).equals("accueil") ? "active" : "" %>"><a href="<%= request.getContextPath() %>/accueil">Accueil</a></li>
            	<li class="<%= ((String)request.getSession().getAttribute("ongletCourant")).equals("admin") ? "active" : "" %>"><a href="<%= request.getContextPath() %>/admin">Administration</a></li>
            	<li class="<%= ((String)request.getSession().getAttribute("ongletCourant")).equals("histoire") ? "active" : "" %>"><a href="<%= request.getContextPath() %>/histoire">Histoire</a></li>
            	<li><a href="mailto:guerric.phalippou@soprasteria.com">Contact</a></li>
          	</ul>
        </div><!--/.nav-collapse -->
	</div>
</nav>
<script>
function Deconnexion(){
	var request = new XMLHttpRequest();
	request.open("GET", "<%= request.getContextPath() %>/Ajax?action=deconnexion");
	request.onreadystatechange = function (aEvt) {
		if (request.readyState == 4 && request.status == 200) {
			document.location.reload(true);
  		}
	};
	request.send();
}
</script>