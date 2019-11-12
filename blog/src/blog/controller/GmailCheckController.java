package blog.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blog.dao.UserDao;
import blog.model.User;

@WebServlet("/api/gmailCheck")
public class GmailCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GmailCheckController() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String email = request.getParameter("email");
		System.out.println("GmailCheckController 인증 email>> "+email);
		// Result가 정상일때
		// response.sendRedirect("/blog/test/gmailSendActionTest.jsp?email=" + email);

		UserDao dao = new UserDao();
		int result = dao.emailCheck(email);
		System.out.println("GmailCheckController result 값>> "+ result);
		if (result == 1) {
			RequestDispatcher dis = request.getRequestDispatcher("/user/login.jsp");
			dis.forward(request, response);
		} else {
			RequestDispatcher dis = request.getRequestDispatcher("/index.jsp");
			dis.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}