

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ClothingServlet
 */
@WebServlet("/ClothingServlet")
public class ClothingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private RecommendationControl ctrl = null;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClothingServlet() {
        super();
        this.ctrl = new RecommendationControl();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String we = request.getParameter("we");
		response.setContentType("text/plain");		

		HttpSession session=request.getSession();
		UserObject user = (UserObject) session.getAttribute("UserObject");
		if(user==null) {
			response.getWriter().write("");
		} else {
			response.getWriter().write(this.ctrl.processRecommendation(user.getGender(), user.getAgeRange(), we));
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
