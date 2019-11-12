package blog.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import blog.action.Action;
import blog.dao.UserDao;
import blog.model.User;
import blog.util.SHA256;
import blog.util.Script;

public class UserLoginAction implements Action {
	private static final String TAG = "UserLoginAction: ";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// null값 처리하기,유효성 검사(나중에)
		String username = request.getParameter("username");
		String rawPassword = request.getParameter("password");
		String password = SHA256.getEncrypt(rawPassword, "cos");
		String rememberMe = request.getParameter("rememberMe");
		
		
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);// Encryption(암호화)해야한다.

		// DAO 연결하기
		UserDao dao = new UserDao();
		int result = dao.login(user);

		if (result == 1) {
			
			if(rememberMe != null) {
				System.out.println(TAG+"쿠키 저장");
				Cookie c = new Cookie("username", username);
				//쿠키는 타임을 따로 지정해줘야 한다.
				c.setMaxAge(6000);//100분
				response.addCookie(c);
			}else {
				System.out.println(TAG+"쿠키 삭제");
				Cookie c = new Cookie("username", null);
				c.setMaxAge(0);//즉시 사라짐
				response.addCookie(c);
			}		
			
			
			HttpSession session = request.getSession();
			user = dao.findByUsername(username);
			session.setAttribute("user", user);
			response.sendRedirect("/blog/index.jsp");
		} else {
			Script.back(response);
		}

	}
}
