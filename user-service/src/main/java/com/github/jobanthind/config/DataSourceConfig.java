package com.github.jobanthind.config;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DataSourceConfig {

   private static MysqlDataSource dataSource;

   private DataSourceConfig() {
   }

   private static MysqlDataSource createDataSource() throws SQLException {
      MysqlDataSource dataSource = new MysqlDataSource();
      dataSource.setUrl("jdbc:mysql://localhost:3306/users");
      dataSource.setUser("root");
      dataSource.setPassword("");
      dataSource.setServerTimezone("UTC");
      return dataSource;
   }

   public static Connection getConnection() {
      try {
         if (dataSource == null) {
            synchronized (DataSourceConfig.class) {
               if (dataSource == null) {
                  dataSource = createDataSource();
               }
            }
         }
         return dataSource.getConnection();
      } catch (SQLException e) {
         System.out.println("Error occurred while connecting to database");
         throw new RuntimeException(e);
      }
   }
}
