/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author User
 */
public class DBConnect {
public ResultSet rs=null;
public Statement st=null;
public Connection con=null;
public int sch=0;
public DBConnect(){
    try {
        Class.forName("com.mysql.jdbc.Driver");
        con=DriverManager.getConnection("jdbc:mysql://localhost/mmc","root","root");
        st=con.createStatement();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
public ResultSet ReadData(String sql){
    try {
        rs=st.executeQuery(sql);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return rs;
}
public int ins_upd_del(String sql){
    int i=-1;
    try {
        i=st.executeUpdate(sql);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return i;
}
public void closeDB(){
    try {
        con.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

}
