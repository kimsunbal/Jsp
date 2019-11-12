package blog.action.reply;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import blog.action.Action;
import blog.dao.ReplyDao;
import blog.model.Reply;

public class ReplyListAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BufferedReader in = request.getReader();
		int commentId = Integer.parseInt(in.readLine());
		
		ReplyDao dao = new ReplyDao();
		List<Reply> replys = dao.findByCommentId(commentId);
		
		if (replys != null) {
			Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss.S").create();
			String replyJson = gson.toJson(replys);
			response.setContentType("application/json; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print(replyJson);
			out.flush();
		}
	}
}
