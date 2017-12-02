

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AddCityServlet
 */
@WebServlet("/AddCityServlet")
public class AddCityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DataManager dm = null;
	private AddCityControl ctrl = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCityServlet() {
        super();
        this.dm = new DataManager();
    	this.ctrl = new AddCityControl(this.dm);
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		response.setContentType("text/plain");
		HttpSession session=request.getSession();
		UserObject user = (UserObject) session.getAttribute("UserObject");
		if(user==null || user.getLocationString().contains(name)) {
			response.getWriter().write("");
			return;
		}
		if(this.ctrl.processAddCity(name)) {
			if(user.addCity(name)) {
				if(this.dm.updateCity(user))
				{
					response.getWriter().write("y");
				} else {
					response.getWriter().write("");
				}
			}else {
				response.getWriter().write("");
			}
		}else {
			response.getWriter().write("");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
