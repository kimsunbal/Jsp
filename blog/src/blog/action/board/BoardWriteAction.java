package blog.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import blog.action.Action;
import blog.dao.BoardDao;
import blog.model.Board;
import blog.model.User;
import blog.util.Script;
import blog.util.Utils;

public class BoardWriteAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// null값 처리하기,유효성 검사(나중에)
		
		User user =(User)request.getSession().getAttribute("user");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String searchContent = Utils.getPureContent(content);


		Board board = new Board();
		board.setUserId(user.getId());
		board.setTitle(title);
		board.setContent(content);
		board.setSearchContent(searchContent);

		BoardDao boardDao = new BoardDao();
		
		int result = boardDao.save(board);
		if (result == 1) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			response.sendRedirect("/blog/index.jsp");
		} else {
			Script.back(response);
		}

	}
}
