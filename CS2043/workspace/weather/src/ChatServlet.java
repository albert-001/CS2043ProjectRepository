

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ChatServlet
 */
@WebServlet("/ChatServlet")
public class ChatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DataManager dm = null;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChatServlet() {
        super();
        dm = new DataManager();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ts = request.getParameter("ts"); //time stamp of the latest msg on the GUI
		String type = request.getParameter("type");
		response.setContentType("text/plain");
		ArrayList<ChattingObject> msg = null;
		HttpSession session=request.getSession();
		UserObject user = (UserObject) session.getAttribute("UserObject");
		if(user==null) {
			response.getWriter().write("");
			return;
		}
		if(type.equals("new")) {
			msg = dm.getNewMessage(ts);
		}else {
			msg = dm.getHistoryMessage();
		}
		String msgs = "";
		for (ChattingObject co : msg) {
			if(user.getName().equals(co.getName())) {
				msgs += (co.getDate() + ",me," + co.getMessage() + ";");
			}else {
				msgs += (co.toString() + ";");
			}
	    }
		response.getWriter().write(msgs);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {       
        String[] msgs = request.getParameterValues("msg");
        String msg = msgs[0];
		HttpSession session=request.getSession();
		UserObject user = (UserObject) session.getAttribute("UserObject");
		if(user==null) {
			response.getWriter().write("");
			return;
		}
        if(dm.writeMessage(user.getName(), msg)) {
        	response.getWriter().write("y");
        }else {
        	response.getWriter().write("");
        }
	}

}
