package blog.action.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blog.action.Action;
import blog.dao.UserDao;
import blog.model.User;
import blog.util.SHA256;
import blog.util.Script;

public class UserJoinAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		목적: form 태그에 있는 name 값을 받아서 DB에 insert하고 나서 페이지 이동

		// null값 처리하기,유효성 검사(나중에)
		String username = request.getParameter("username");
		String rawPassword = request.getParameter("password");
		String email = request.getParameter("email");
		String address =request.getParameter("address");
		String password = SHA256.getEncrypt(rawPassword, "cos");

		User user = new User();
		user.setUsername(username);
		user.setPassword(password);// Encryption(암호화)해야한다.
		user.setEmail(email);
		user.setAddress(address);

		// DAO 연결하기
		UserDao dao = new UserDao();
		int result = dao.save(user);

		if (result == 1) {
			response.sendRedirect("/blog/user/login.jsp");
		} else {
			Script.back(response);
		}

	}
}
