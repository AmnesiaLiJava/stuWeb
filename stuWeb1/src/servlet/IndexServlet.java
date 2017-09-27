package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			String type = request.getParameter("type");

			if (type == null) {

				request.getRequestDispatcher("WEB-INF/index/index.jsp")
						.forward(request, response);

			} else if (type.equals("header")) {
				request.getRequestDispatcher("WEB-INF/index/header.jsp")
						.forward(request, response);

			} else if (type.equals("left")) {
				request.getRequestDispatcher("WEB-INF/index/left.jsp")
						.forward(request, response);

			} else if (type.equals("footer")) {
				request.getRequestDispatcher("WEB-INF/index/footer.jsp")
						.forward(request, response);

			} 
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}
}
