

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String task = request.getParameter("task");
		response.setContentType("text/plain");		
		if(task.equals("check_user_login"))
		{
			HttpSession session=request.getSession();
			UserObject user = (UserObject) session.getAttribute("UserObject");
			if(user==null) {
				response.getWriter().write("");
			} else {
				response.getWriter().write(user.getName()+";"+user.getLocationString()+";"+user.getAgeRange()+";"+user.getGender());
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter writer = response.getWriter();
		String[] name = request.getParameterValues("name");
		String[] pass = request.getParameterValues("password");
		String uname = name[0];
		String password = pass[0];
		DataManager dm = new DataManager();
		LoginControl loginCtrl = new LoginControl(dm);
		boolean user = loginCtrl.processLogin(uname, password);
		if(user) {
			HttpSession session=request.getSession(); 
			session.setAttribute("UserObject", loginCtrl.getUserObject());
			writer.println("Login successful.<br>");
	        writer.println("<p><a href='WeatherMain.html'> Home </a> </p>");
		}else {
			
	        writer.println("Login failed.<br>");
			writer.println("<p><a href='Login.html'> Login </a><p>");
			writer.println("<p><a href='CreateAccount.html'> Create Account </a><p>");
			writer.println("<p><a href='WeatherMain.html'> Home </a><p>");
	    }
	}

}
