/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Dbcon;
import Forms.Login;
import java.sql.*;
/**
 *
 * @author user
 */
public class Database {
     Connection con;
    Statement st;
    ResultSet rs;

    public Database() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
         
             con = DriverManager.getConnection("jdbc:mysql://"+Login.server+"/mmc", "root", "root");
            st = con.createStatement();

        } catch (Exception e) {
            System.out.println("Error" + e);
        }
    }

    public ResultSet getData(String sql) {
        try {
            rs = st.executeQuery(sql);

        } catch (Exception e) {
            System.out.println("Error" + e);
        }

        return rs;
    }

    public int putData(String sql) {
        int i = 0;
        try {
            i = st.executeUpdate(sql);
        } catch (Exception e) {
            System.out.println("Error" + e);

        }
        return i;

    }

}
