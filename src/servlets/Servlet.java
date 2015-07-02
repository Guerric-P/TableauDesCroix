package servlets;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.HistoriqueService;
import services.PersonneService;
import domaine.Historique;
import domaine.Personne;

/**
 * Servlet implementation class Servlet
 */
@WebServlet("/Servlet")
public class Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		    try {
		    String URI = request.getRequestURI().substring(request.getContextPath().length(), request.getRequestURI().length());
		    Personne utilisateurCourant = PersonneService.GetInstance().ChargerPersonneParLogin(((Personne)request.getUserPrincipal()).getLogin());
		    this.getServletContext().setAttribute("utilisateurCourant", utilisateurCourant);
		    List<Personne> liste;
		    List<Historique> historique;		    
		    switch(URI){
			    case "/admin":
			    	liste = PersonneService.GetInstance().ChargerTous();
			    	historique = HistoriqueService.GetInstance().ChargerSemaineCourante();
					this.getServletContext().setAttribute("liste", liste);
					this.getServletContext().setAttribute("historique", historique);
			    	request.getSession().setAttribute("ongletCourant", "admin");
			    	this.getServletContext().getRequestDispatcher("/WEB-INF/admin.jsp").forward(request, response);
			    	break;
			    case "/histoire":
			    	historique = HistoriqueService.GetInstance().ChargerSemaineCourante();
					this.getServletContext().setAttribute("historique", historique);
			    	request.getSession().setAttribute("ongletCourant", "histoire");
			    	this.getServletContext().getRequestDispatcher("/WEB-INF/histoire.jsp").forward(request, response);
			    	break;
			    case "/accueil":
			    default:
			    	liste = PersonneService.GetInstance().ChargerTous();
			    	historique = HistoriqueService.GetInstance().ChargerSemaineCourante();
					this.getServletContext().setAttribute("liste", liste);
					this.getServletContext().setAttribute("historique", historique);
					request.getSession().setAttribute("ongletCourant", "accueil");
					this.getServletContext().getRequestDispatcher("/WEB-INF/accueil.jsp").forward(request, response);
			    	break;		    	
		    }
		    
		    	
			} catch (Exception e) {
				this.getServletContext().setAttribute("exception", e.getMessage() + e.getStackTrace());
				this.getServletContext().getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
				e.printStackTrace();
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
