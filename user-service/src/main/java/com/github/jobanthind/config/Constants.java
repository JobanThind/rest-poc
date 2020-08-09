package com.github.jobanthind.config;

public class Constants {

   public static final String GET_ALL_USERS = "select * from users";
   public static final String UPDATE_USER = "update users set name = ? , age = ? , phone = ? where id = ?";
   public static final String DELETE_USER = "delete from users where id = ?";
   public static final String ADD_USER = "insert into users (name , age , phone ) values (?,?,?)";
}
