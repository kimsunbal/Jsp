package blog.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import blog.action.Action;
import blog.dao.UserDao;
import blog.model.User;
import blog.util.SHA256;
import blog.util.Script;

public class UserUpdateAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		목적: form 태그에 있는 name 값을 받아서 DB에 insert하고 나서 페이지 이동

		// null값 처리하기,유효성 검사(나중에)

		String path = request.getServletContext().getRealPath("media");
		System.out.println("path>> " + path);
		MultipartRequest multi = new MultipartRequest(request, path, 1024 * 1024 * 2, "UTF-8",
				new DefaultFileRenamePolicy() // 동일한 파일명이 들어오면 파일명 뒤에 숫자를 붙임.
		);

		int id = Integer.parseInt(multi.getParameter("id"));
		String username = multi.getParameter("username");
		String rawPassword = multi.getParameter("password");
		String email = multi.getParameter("email");
		String address = multi.getParameter("address");
		String password = SHA256.getEncrypt(rawPassword, "cos");
		String filename = multi.getFilesystemName("userProfile");// 정책에 의해서 변경된 이름
		
		
		
		String userProfileInput = multi.getParameter("userProfileInput");
		System.out.println("userProfileInput"+userProfileInput);
		
		
		
//		String originFilename = multi.getOriginalFileName("userProfile");
		String contextpath = request.getServletContext().getContextPath();
		String filepath = contextpath + "/media/" + filename;

		System.out.println("filepath>> " + filepath);
		System.out.println("userProfileInput>> " + userProfileInput);
		
		if (filepath.equals("/blog/media/null")) {
			filepath = "/blog/media/defaultProfile.jpg";// 기본이미지 폴더위치로 변경
		}
		
		User user = new User();
		user.setId(id);
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		user.setAddress(address);
		user.setUserProfile(filepath);
		// DAO 연결하기
		UserDao dao = new UserDao();
		int result = dao.update(user);

		if (result == 1) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			response.sendRedirect("/blog/index.jsp");
		} else {
			Script.back(response);
		}

	}
}
