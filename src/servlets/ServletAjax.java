package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domaine.Personne;
import services.HistoriqueService;
import services.PersonneService;

/**
 * Servlet implementation class ServletAjax
 */
@WebServlet("/ServletAjax")
public class ServletAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAjax() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		int idPersonneDonnante;
		int idPersonnePrenante;
		String motif;
		int idTypeCroix;
	    Personne utilisateurCourant = PersonneService.GetInstance().ChargerPersonneParLogin(((Personne)request.getUserPrincipal()).getLogin());
		action = action == null ? "" : action;
		switch(action){
		case "mettreCroix":
			idPersonneDonnante = ((Personne)request.getUserPrincipal()).getId();
			idPersonnePrenante = Integer.parseInt(request.getParameter("idPersonnePrenante"));
			motif = request.getParameter("motif");
			
			if(PersonneService.GetInstance().Mettrecroix(idPersonneDonnante, idPersonnePrenante, motif)){
				response.getWriter().write("true");
			}
			else{
				response.getWriter().write("false");
			}
			
			response.flushBuffer();
			break;
		case "mettreCroixSpeciale":
			idPersonnePrenante = Integer.parseInt(request.getParameter("idPersonnePrenante"));
			motif = request.getParameter("motif");
			idTypeCroix = Integer.parseInt(request.getParameter("idTypeCroix"));
			if(PersonneService.GetInstance().MettreCroixSpeciale(idPersonnePrenante, idTypeCroix, motif)){
				response.getWriter().write("true");
			}
			else{
				response.getWriter().write("false");
			}
			response.flushBuffer();
			break;
		case "resetCroix":
			int idPersonneAReset = Integer.parseInt(request.getParameter("idPersonneAReset"));
			if(PersonneService.GetInstance().ResetCroix(idPersonneAReset)){
				response.getWriter().write("true");
			}
			else{
				response.getWriter().write("false");
			}
			
			response.flushBuffer();
			break;
		case "resetIndicateurs":
			if(PersonneService.GetInstance().ResetIndicateurs()){
				response.getWriter().write("true");
			}
			else{
				response.getWriter().write("false");
			}
			break;
		case "checkNotifications":
		    response.setCharacterEncoding("UTF-8");
			try {
				response.getWriter().write(HistoriqueService.GetInstance().ChargerNotifications());
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "deconnexion":
		    response.setCharacterEncoding("UTF-8");
			try {
				request.getSession().invalidate();
				response.getWriter().write("true");
				response.flushBuffer();
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "setDate":
			response.setCharacterEncoding("UTF-8");
			String date = request.getParameter("date");
			try {
				String retour = PersonneService.GetInstance().MettreDate(date,utilisateurCourant);
				response.getWriter().write(retour);
				response.flushBuffer();
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		}
		
		

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
