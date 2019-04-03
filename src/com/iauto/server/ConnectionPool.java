package com.iauto.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;


public class ConnectionPool {
    private Connection con;
    private String driver = "com.mysql.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/visit?useSSL=false";
    private String user = "root";
    private String password = "111111";
    public LinkedList<Connection> connPool = new LinkedList<Connection>();

    public Connection getConnection(){

        try {
            Class.forName(driver);

            for(int i = 0; i < 5; i++){
                con = DriverManager.getConnection(url, user, password);
                connPool.add(con);
            }
        } catch(ClassNotFoundException e) {
            //数据库驱动类异常处理
            e.printStackTrace();
        } catch(SQLException e) {
            //数据库连接失败异常处理
            e.printStackTrace();
        }catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return connPool.removeFirst();
    }

}
