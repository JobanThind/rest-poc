package com.github.jobanthind.dao;

import com.github.jobanthind.model.User;

import java.util.List;

public interface UserDao {

   List<User> getUsers();

   void addNewUser(User user);

   void updateUser(int id, User user);

   void deleteUser(int id);
}
