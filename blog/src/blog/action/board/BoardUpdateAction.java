package blog.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import blog.action.Action;
import blog.dao.BoardDao;
import blog.dao.UserDao;
import blog.model.Board;
import blog.model.User;
import blog.util.Script;

public class BoardUpdateAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// null값 처리하기,유효성 검사(나중에)
		
		int id = Integer.parseInt(request.getParameter("id"));
		User user =(User)request.getSession().getAttribute("user");
		String title = request.getParameter("title");
		String content = request.getParameter("content");


		Board board = new Board();
		board.setId(id);
		board.setTitle(title);
		board.setContent(content);

		BoardDao boardDao = new BoardDao();
		
		int result = boardDao.update(board);
		if (result == 1) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			response.sendRedirect("/blog/board?cmd=detail&id="+id);
		} else {
			Script.back(response);
		}

	}
}
