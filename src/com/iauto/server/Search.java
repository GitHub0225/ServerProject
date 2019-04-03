package com.iauto.server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Search {
    public String run(String table,String city){
        String string = "暂未收录_暂未收录_暂未收录_暂未收录_暂未收录";

        Connection con;
        ConnectionPool connectionPool = new ConnectionPool();
        con = connectionPool.getConnection();
        Statement statement = null;
        try {
            statement = con.createStatement();
            String sql = "select * from "+table+" where 地址 = "+"'"+city+"';";
            ResultSet rs = statement.executeQuery(sql);

            while(rs.next()){
                string = rs.getString("地址")+"_"+rs.getString("简介")+"_"+rs.getString("特色风味")+"_"+rs.getString("旅游时间")+"_"+rs.getString("口碑网评");
            }
            rs.close();
        } catch (SQLException e) {
            string = "暂未收录_暂未收录_暂未收录_暂未收录_暂未收录";

        }
        if(connectionPool.connPool.size()<5){
            connectionPool.connPool.add(con);
        }else {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return string;
    }
}
