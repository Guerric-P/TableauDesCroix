var hidden, visibilityChange; 
var pileNotifications = [];

$(document).ready(function() {
	$("#inputDate").keypress(function(event){
	    if(event.keyCode == 13){
	        event.preventDefault();
	        var element = document.getElementById('btnDate');
	        btnDate_Onclick(element);
	    }
	});

	Notification.requestPermission(function (permission) {

	      // Quelque soit la réponse de l'utilisateur, nous nous assurons de stocker cette information
	      if(!('permission' in Notification)) {
	        Notification.permission = permission;
	      }
	})
});

if (typeof document.hidden !== "undefined") { // Opera 12.10 and Firefox 18 and later support 
  hidden = "hidden";
  visibilityChange = "visibilitychange";
} else if (typeof document.mozHidden !== "undefined") {
  hidden = "mozHidden";
  visibilityChange = "mozvisibilitychange";
} else if (typeof document.msHidden !== "undefined") {
  hidden = "msHidden";
  visibilityChange = "msvisibilitychange";
} else if (typeof document.webkitHidden !== "undefined") {
  hidden = "webkitHidden";
  visibilityChange = "webkitvisibilitychange";
}

function handleVisibilityChange() {
	if (!document[hidden]) {
		document.location.reload(true);
	}
}
	
if (typeof document.addEventListener === "undefined" || typeof document[hidden] === "undefined") {
	alert("Le rechargement automatique de la page ne fonctionne pas sur ce navigateur.");
} else {
	// Handle page visibility change   
	document.addEventListener(visibilityChange, handleVisibilityChange, false);
}

setInterval('checkNotifications()',5000);

function checkNotifications(){
	var request = new XMLHttpRequest();
	request.open("GET", contexte + "/Ajax?action=checkNotifications", true);
	request.onreadystatechange = function (aEvt) {
		if (request.readyState == 4 && request.status == 200) {
			var data = JSON.parse(request.responseText);
			for (var i = 0; i < data.length; ++i){
				var trouve = false;
				for (var j = 0; j < pileNotifications.length; ++j){
					if (data[i].id ==  pileNotifications[j].id){
						trouve = true;
					}
				}
				if(!trouve){
					var historique = data[i];
					switch(historique.typeCroix){
						case 'Personnelle':
							notifyMe("Croix de " + historique.personneEmettrice.nom + " " + historique.personneEmettrice.prenom + " à " + historique.personneReceptrice.nom + " " + historique.personneReceptrice.prenom  + " : " + historique.motif);
							break;
						case 'CDS':
							notifyMe("Croix CDS à " + historique.personneReceptrice.nom + " " + historique.personneReceptrice.prenom  + " : " + historique.motif);
							break;
						case 'SortieDesFrontieres':
							notifyMe("Croix sortie des frontières à " + historique.personneReceptrice.nom + " " + historique.personneReceptrice.prenom  + " : " + historique.motif);
							break;
						case 'Reunion':
							notifyMe("Croix réunion à " + historique.personneReceptrice.nom + " " + historique.personneReceptrice.prenom  + " : " + historique.motif);
							break;
					}

					pileNotifications.push(historique);
				}
			}
  		}
	};

	request.send();
}

function notifyMe(message) {
		var options = {icon: contexte + "/images/notification_croix.png"};
	  // Voyons si le navigateur supporte les notifications
	  if (!("Notification" in window)) {
	    alert("Ce navigateur ne supporte pas les notifications desktop");
	  }

	  // Voyons si l'utilisateur est OK pour recevoir des notifications
	  else if (Notification.permission === "granted") {
	    // Si c'est ok, créons une notification
	    var notification = new Notification(message, options);
	  }

	  // Sinon, nous avons besoin de la permission de l'utilisateur
	  // Note : Chrome n'implémente pas la propriété statique permission
	  // Donc, nous devons vérifier s'il n'y a pas 'denied' à la place de 'default'
	  else if (Notification.permission !== 'denied') {
	    Notification.requestPermission(function (permission) {

	      // Quelque soit la réponse de l'utilisateur, nous nous assurons de stocker cette information
	      if(!('permission' in Notification)) {
	        Notification.permission = permission;
	      }

	      // Si l'utilisateur est OK, on crée une notification
	      if (permission === "granted") {
	        var notification = new Notification(message, options);
	      }
	    });
	  }

	  // Comme ça, si l'utlisateur a refusé toute notification, et que vous respectez ce choix,
	  // il n'y a pas besoin de l'ennuyer à nouveau.
	}

function MettreCroix(bouton){
	var idPersonnePrenante = bouton.parentNode.previousElementSibling.children[0].value;
	var personnePrenante = bouton.parentNode.previousElementSibling.children[0].options[ bouton.parentNode.previousElementSibling.children[0].selectedIndex ].text;
	var motif = prompt("Motif de la croix :", "Parce que j'ai envie.");
	if (motif != null){
		if(confirm("Confirmer la croix à " + personnePrenante + " pour le motif : " + motif)){
			var request = new XMLHttpRequest();
			request.open("GET", contexte + "/Ajax?action=mettreCroix&idPersonnePrenante=" + idPersonnePrenante + "&motif=" + encodeURIComponent(motif), true);
			request.onreadystatechange = function (aEvt) {
				if (request.readyState == 4 && request.status == 200) {
			    	if(request.responseText == "true"){
			   	 		//alert("La croix a été attribuée !");
			    	 	document.location.reload(true);
			   		}
			     	else{
			    	 	alert("La croix n'a pas été attribuée !");		
			     	}
		  		}
			};
	
			request.send();
		}
	}
}

function MettreCroixSpeciale(){
	var idPersonnePrenante = document.getElementById("selectCroixSpeciale").value;
	var idTypeCroix = document.getElementById("selectTypeCroix").value;
	var typeCroix = document.getElementById("selectTypeCroix").options[ document.getElementById("selectTypeCroix").selectedIndex ].text;
	var personnePrenante = document.getElementById("selectCroixSpeciale").options[ document.getElementById("selectCroixSpeciale").selectedIndex ].text;
	var motif = prompt("Motif de la croix :", "Parce que c'est comme ça.");
	if (motif != null){
		if(confirm("Confirmer la croix " + typeCroix + " à " + personnePrenante + " pour le motif : " + motif)){
			var request = new XMLHttpRequest();
			request.open("GET", contexte + "/Ajax?action=mettreCroixSpeciale&idPersonnePrenante=" + idPersonnePrenante + "&idTypeCroix=" + idTypeCroix + "&motif=" + encodeURIComponent(motif), true);
			request.onreadystatechange = function (aEvt) {
				if (request.readyState == 4 && request.status == 200) {
			    	if(request.responseText == "true"){
			   	 		//alert("La croix a été attribuée !");
			    	 	document.location.reload(true);
			   		}
			     	else{
			    	 	alert("La croix n'a pas été attribuée !");		
			     	}
		  		}
			};
	
			request.send();
		}
	}
}

function ResetCroix(bouton){
	var idPersonneAReset = bouton.getAttribute("data-id-personne");
	var personneAReset = bouton.getAttribute("data-nom-personne");
	if(confirm("Reset les croix de " + personneAReset + " ?")){
		var request = new XMLHttpRequest();
		request.open("GET", contexte + "/Ajax?action=resetCroix&idPersonneAReset=" + idPersonneAReset, true);
		request.onreadystatechange = function (aEvt) {
			if (request.readyState == 4 && request.status == 200) {
		    	if(request.responseText == "true"){
		    	 	document.location.reload(true);
		   		}
	  		}
		};

		request.send();
	}
}

function ResetIndicateurs(){
	if(confirm("Reset les indicateurs ?")){
		var request = new XMLHttpRequest();
		request.open("GET", contexte + "/Ajax?action=resetIndicateurs", true);
		request.onreadystatechange = function (aEvt) {
			if (request.readyState == 4 && request.status == 200) {
		    	if(request.responseText == "true"){
		    	 	document.location.reload(true);
		   		}
	  		}
		};

		request.send();
	}
}

function btnDate_Onclick(element){
	var inputDate = document.getElementById('inputDate');
	if(inputDate.getAttribute('disabled') == 'disabled'){
		inputDate.removeAttribute('disabled');
		element.innerHTML = 'Enregistrer';
	}
	else{
		if(ValiderDate(inputDate.value)){
			var request = new XMLHttpRequest();
			request.open("GET", contexte + "/Ajax?action=setDate&date=" + encodeURIComponent(inputDate.value), true);
			request.onreadystatechange = function (aEvt) {
				if (request.readyState == 4 && request.status == 200) {
					var data = JSON.parse(request.responseText);
			    	if(data[0] == true){
			    	 	document.location.reload(true);
			   		}
			    	else if (data[1] == false){
			    		alert('La date ne peut pas être antérieure à la date du jour.');
			    	}
			    	else if (data[2] != null){
			    		alert(data[2] + ' a déjà posé sa date ce jour là.');
			    	}
			    	else{
			    		alert('Erreur interne au serveur.');
			    	}
		  		}
			};

			request.send();
		}
		else{
			alert('La date doit être au format jj/mm/aaaa');
		}

	}
}

function ValiderDate(date){
    // regular expression to match required date format
    var re = /(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\d\d/;

    if(date != '' && !date.match(re)) {
    	return false;
    }
    else{
    	return true;
    }
}

function Deconnexion(){
	var request = new XMLHttpRequest();
	request.open("GET", contexte +"/Ajax?action=deconnexion");
	request.onreadystatechange = function (aEvt) {
		if (request.readyState == 4 && request.status == 200) {
			document.location.reload(true);
  		}
	};
	request.send();
}