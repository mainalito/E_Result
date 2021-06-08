/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import users.User;

/**
 *
 * @author Charl3s
 */
public class set {

    String Query = "Select  * from STUDENT_REG_DETAILS ";
  public  List<User> getUsers() throws Exception {
        List<User> results = new ArrayList<>();

        try {
            dbConn d = new dbConn();
            Connection con;
            con = d.getConnection();
            Statement ps = con.createStatement();
            ResultSet rs = ps.executeQuery(Query);
            while (rs.next()) {

                User u = new User();
                u.setReg_no(rs.getString("Reg_no"));
                u.setPhone_number(rs.getString("phone_number"));

                results.add(u);

            }

        } catch (SQLException e) {

        }
        // log any exceptions ...
        return results;
    }
}
