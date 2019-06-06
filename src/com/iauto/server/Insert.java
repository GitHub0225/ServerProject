package com.iauto.server;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Insert {
    public void run(String content,int flag){
        String[] paths = (String[]) content.toString().split("_");

        Connection con;
        ConnectionPool connectionPool = new ConnectionPool();
        con = connectionPool.getConnection();
        Statement statement = null;
        String sql = null;
        try {
            statement = con.createStatement();
            if (flag == 1 ) {
                sql = "insert into visit.jingdian(地址,简介,特色风味,旅游时间,口碑网评) values( \'" + paths[0] + " \',  \'" + paths[1] + " \', \'" + paths[2] + " \',\'" + paths[3] + " \',\'" + paths[4] + " \');";
            }
            if (flag == 2){
                sql = "insert into visit.orderhotel(姓名,性别,身份证号,手机号码,酒店名称,房间规格,入住人,入住日期,退住日期) values( \'" + paths[1] + " \', \'" + paths[2] + "\',\'" + paths[3] + " \',\'" + paths[4] + " \',\'" + paths[0] + " \',\'" + paths[6] + " \',\'" + paths[5] + " \',\'" + paths[7] + " \',\'" +  paths[8] + " \');";
            }
            if (flag == 3){
                sql = "insert into visit.jingdianpingjia(景点名称,景点评价) values( \'" + paths[0] + " \', \'" + paths[1]  + " \');";
            }
            statement.execute(sql);

        } catch (SQLException e) {
            e.printStackTrace();
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
    }
}
