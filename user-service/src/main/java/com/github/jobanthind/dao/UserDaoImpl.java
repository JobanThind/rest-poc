package com.github.jobanthind.dao;

import com.github.jobanthind.config.Constants;
import com.github.jobanthind.config.DataSourceConfig;
import com.github.jobanthind.model.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

   @Override
   public List<User> getUsers() {
      try (Connection connection = DataSourceConfig.getConnection()
      ) {
         String getUserQuery = Constants.GET_ALL_USERS;
         Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery(getUserQuery);
         List<User> users = new ArrayList<>();
         while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("name"));
            user.setAge(resultSet.getInt("age"));
            user.setPhone(resultSet.getString("phone"));
            users.add(user);
         }
         return users;
      } catch (Exception e) {
         System.out.println("Error occurred while getting users");
         throw new RuntimeException(e);
      }
   }

   @Override
   public void addNewUser(String name, int age, String phone) {
   }

   @Override
   public void updateUser(int id, User user) {
      try (Connection connection = DataSourceConfig.getConnection()) {
         String query = Constants.UPDATE_USER;
         PreparedStatement statement = connection.prepareStatement(query);
         statement.setString(1, user.getName());
         statement.setInt(2, user.getAge());
         statement.setString(3, user.getPhone());
         statement.setInt(4, user.getId());
         if (statement.executeUpdate() > 0) {
            System.out.println("Update successful");
         } else {
            throw new RuntimeException("Unable to perform update operation");
         }
      } catch (Exception e) {
         System.out.println("Error occurred during update operation");
         throw new RuntimeException(e);
      }
   }

   @Override
   public void deleteUser(int id) {
   }
}
