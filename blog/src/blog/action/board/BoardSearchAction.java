package blog.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blog.action.Action;
import blog.dao.BoardDao;

public class BoardSearchAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String content = "%"+request.getParameter("content")+"%";
		System.out.println("BoardSearchAction >>> "+content);
		BoardDao dao = new BoardDao();
		int result = dao.searchCount(content);
		System.out.println(result+"건이 검색되었습니다");
	}
}
