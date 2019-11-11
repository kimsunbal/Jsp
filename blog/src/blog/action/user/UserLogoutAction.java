package blog.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import blog.action.Action;

public class UserLogoutAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//세션 만료
		HttpSession session = request.getSession();
		session.invalidate();// 세션 무효화
		//리스트 페이지
		response.sendRedirect("/blog/index.jsp");
	}
}
