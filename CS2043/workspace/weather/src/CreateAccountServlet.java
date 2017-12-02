

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Servlet implementation class CreateAccountServlet
 */
@WebServlet("/CreateAccountServlet")
public class CreateAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateAccountServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 DataManager dm = new DataManager();
         CreateAccountControl control = new CreateAccountControl(dm);
         PrintWriter writer = response.getWriter();
        
         String[] keywords = request.getParameterValues("name");
         String name = keywords[0];
         String[] passs = request.getParameterValues("password");
         String pass = passs[0];
         String[] locations = request.getParameterValues("location");
         String location = locations[0];
         String[] genders = request.getParameterValues("gender");
         String gender = genders[0];
         String[] ages = request.getParameterValues("age");
         String age = ages[0];
         boolean yes = control.processCreateAccount(name);
         if(yes) {
        	 writer.println("Create account successful! <br>");
        	 UserObject user = new UserObject(name, pass, location, gender, age);
        	 dm.addUser(user);
 		     writer.println("<p><a href='Login.html'> Login </a><p>");
         }
         else {
        	 writer.println("User name already exists, please try another one. <br>");
        	 writer.println("<p><a href='CreateAccount.html'> Create account </a><p>");
         }
	}

}
