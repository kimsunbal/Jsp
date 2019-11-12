package blog.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blog.dao.UserDao;


@WebServlet("/api/user")
public class UserRestController extends HttpServlet {
   private static final long serialVersionUID = 1L;
       

    public UserRestController() {
        super();
    }


   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      request.setCharacterEncoding("utf-8"); //빼먹지 말자. 빼먹으면 한글깨짐.
      response.setCharacterEncoding("utf-8");      
      
      String username = request.getParameter("username");
      UserDao dao = new UserDao();
      PrintWriter out = response.getWriter();
      if(dao.findByUsername(username) != null) {
         //사용할 수 없음, 아이디가 존재함
         //println쓰면 개행문자까지 들어가서 안됨
         out.print("impossible");
         out.flush();
      }else {
         //사용할 수 있음, 아이디가 존재하지 않음
         out.print("possible");
         out.flush();
      }
      
   }

   
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}