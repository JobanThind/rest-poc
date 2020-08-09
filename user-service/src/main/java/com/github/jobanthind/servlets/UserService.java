package com.github.jobanthind.servlets;

import com.github.jobanthind.dao.UserDao;
import com.github.jobanthind.model.User;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/users/**"})
public class UserService extends HttpServlet {

   private UserDao userDao;

   public UserService(UserDao userDao) {
      this.userDao = userDao;
   }

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      try {
         List<User> users = userDao.getUsers();
         JSONArray jsonArray = new JSONArray(users);
         resp.addHeader("content-type", "application/json");
         resp.getWriter().print(jsonArray);
      } catch (Exception e) {
         System.out.println("Error occurred while fetching users");
         e.printStackTrace();
         resp.setStatus(500);
      }
   }

   @Override
   protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      try {
         String path = req.getPathInfo();
         String[] parts = path.split("/");
         String userId = parts[parts.length - 1];
         BufferedReader reader = req.getReader();
         StringBuilder builder = new StringBuilder();
         String line;
         while ((line = reader.readLine()) != null) {
            builder.append(line);
         }
         JSONObject jsonObject = new JSONObject(builder.toString());
         User user = new User();
         user.setId(Integer.parseInt(userId));
         user.setPhone(jsonObject.getString("phone"));
         user.setAge(jsonObject.getInt("age"));
         user.setName(jsonObject.getString("name"));
         userDao.updateUser(Integer.parseInt(userId), user);
         resp.addHeader("content-type", "application/json");
         resp.getWriter().print(builder.toString());
      } catch (Exception e) {
         e.printStackTrace();
         resp.setStatus(500);
      }
   }
}
