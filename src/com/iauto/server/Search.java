package com.iauto.server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Search {
    public String run(String table,String content,int i){
        String string = "暂未收录,可点击以下新增内容按钮进行添加_暂未收录_暂未收录_暂未收录_暂未收录";
        String sql = null;
        Connection con;
        ConnectionPool connectionPool = new ConnectionPool();
        con = connectionPool.getConnection();
        Statement statement = null;
        if (i == 0) {
        try {
            statement = con.createStatement();

                sql = "select * from " + table + " where 地址 = " + "'" + content + "';";
                ResultSet rs = statement.executeQuery(sql);

                while(rs.next()){
                    string = rs.getString("地址")+"_"+rs.getString("简介")+"_"+rs.getString("特色风味")+"_"+rs.getString("旅游时间")+"_"+rs.getString("口碑网评");
                }

                rs.close();


        } catch (SQLException e) {
            string = "暂未收录,可点击以下新增内容按钮进行添加_暂未收录_暂未收录_暂未收录_暂未收录";

            }
        }if (i == 1){
            try{
                statement = con.createStatement();

                sql = "select * from " + table + " where 手机号码 = " + "'" + content + "';";
                ResultSet rs = statement.executeQuery(sql);
                string = "";
                while(rs.next()){
                    string = string+ rs.getString("姓名")+"_"+ rs.getString("酒店名称")+"_"+ rs.getString("房间规格")+"_"+ rs.getString("入住人")+"_"+ rs.getString("入住日期")+"_"+ rs.getString("退住日期")+"_";
                    string = string +"@";
                }
                if(string.length() == 0){
                    string = "null";

                }
                rs.close();
            }catch (SQLException e){
                e.printStackTrace();
                string = "null";
            }
        }if (i == 2){
            try{
                statement = con.createStatement();

                sql = "select * from " + table + " where 景点名称 = " + "'" + content + "';";
                ResultSet rs = statement.executeQuery(sql);
                string = "";
                while(rs.next()){
                    string = string+ rs.getString("景点名称")+"_"+ rs.getString("景点评价")+"_";
                    string = string +"@";
                }
                if(string.length() == 0){
                    string = "null";

                }
                rs.close();
            }catch (SQLException e){
                e.printStackTrace();
                string = "null";
            }
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
