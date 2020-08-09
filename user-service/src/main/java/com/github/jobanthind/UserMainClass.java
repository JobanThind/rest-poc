package com.github.jobanthind;

import com.github.jobanthind.dao.UserDaoImpl;
import com.github.jobanthind.servlets.HelloServlet;
import com.github.jobanthind.servlets.UserService;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

public class UserMainClass {

   public static void main(String[] args) throws LifecycleException {
      startServer();
   }

   private static void startServer() throws LifecycleException {
      Tomcat tomcat = new Tomcat();
      tomcat.setBaseDir("temp");
      tomcat.setPort(8080);
      String contextPath = "/user-service";
      String docBase = new File(".").getAbsolutePath();
      Context context = tomcat.addContext(contextPath, docBase);
      tomcat.addServlet(contextPath, HelloServlet.class.getName(), new HelloServlet());
      context.addServletMappingDecoded("/hello", HelloServlet.class.getName());
      tomcat.addServlet(contextPath, UserService.class.getName(), new UserService(new UserDaoImpl()));
      context.addServletMappingDecoded("/users/*", UserService.class.getName());


      tomcat.start();
      tomcat.getServer().await();
   }
}
