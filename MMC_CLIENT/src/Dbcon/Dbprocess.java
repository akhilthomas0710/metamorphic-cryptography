/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Dbcon;
import java.sql.*;
import java.util.*;
/**
 *
 * @author user
 */
public class Dbprocess {
    Database db = new Database();

     public boolean Userlogin_Validation(String uname, String pass) throws Exception {

        boolean flag = false;
        String sql = "select uname,upass from login where uname='" + uname + "' and upass='" + pass + "' and access='Active' and utype='user'";
        ResultSet rs = db.getData(sql);
        if (rs.next()) {
            flag = true;
        }
        return flag;
    }

}
