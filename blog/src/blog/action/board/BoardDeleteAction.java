package blog.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blog.action.Action;
import blog.dao.BoardDao;
import blog.model.Board;
import blog.util.Script;

public class BoardDeleteAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Board board = new Board();
		board.setId(Integer.parseInt(request.getParameter("id")));

		BoardDao dao = new BoardDao();
		int result = dao.delete(board);

		if (result == 1) {
			response.sendRedirect("/blog/index.jsp");
		} else {
			Script.back(response);
		}
	}
}