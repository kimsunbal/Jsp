package blog.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import blog.action.Action;
import blog.dao.BoardDao;
import blog.dao.CommentDao;
import blog.model.Board;
import blog.model.Comment;
import blog.model.User;
import blog.util.Script;
import blog.util.Utils;

public class BoardDetailAction implements Action {

	private static final String TAG = "BoardDetailAction: ";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("id") == null || request.getParameter("id").equals(""))
			return;
		int id = Integer.parseInt(request.getParameter("id"));
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");

		String username = "";
		String cookieUsername = "";
		try {
			username = user.getUsername();
		} catch (NullPointerException e) {
			System.out.println("유저 정보 없어");
		}

		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			System.out.print(cookie.getName());
			System.out.println("   " + cookie.getMaxAge());
			if (cookie.getName().equals(id + "board" + username)) {
				cookieUsername = cookie.getValue();
			}
		}

		BoardDao dao = new BoardDao();
		Board board = dao.findById(id);
		
		CommentDao commentDao = new CommentDao();
		List<Comment> comments = commentDao.findAll(id);

		if (board != null) {

			// 조회수 증가 - 쿠키를 저장해서 (1일) 쿠키가 있을 때 새로고침 방지하기
			int result = -1;
			if (username.equals("") || cookieUsername.equals(username)) {// username이 없을때, cookie와 username이 동일할 때
				System.out.println(TAG + "쿠키 저장 안함");
				result = 1;
			} else if (cookieUsername.equals("") || !cookieUsername.equals(username)) {// 쿠키에 값이 없을 때, 쿠키에 유저네임 값이 없을 때
				System.out.println(TAG + "쿠키 저장>>" + username);
				// 쿠키는 타임을 따로 지정해줘야 한다.
				Cookie c = new Cookie(id + "board" + username, username);// board cookie
				c.setMaxAge(60*60);// 1시간
				response.addCookie(c);
				result = dao.increaseReadCount(id);
			}

			if (result == 1) {
				// 유튜브 주소 파싱
				Utils.setPreviewYoutube(board);
				// board를 request에 담고 dispatcher로 이동
				request.setAttribute("board", board);
				request.setAttribute("comments", comments);
				RequestDispatcher dis = request.getRequestDispatcher("/board/detail.jsp");
				dis.forward(request, response);
			} else {
				System.out.println(1);
				Script.back(response);
			}
		} else {
			System.out.println(2);
			Script.back(response);
		}

	}
}
