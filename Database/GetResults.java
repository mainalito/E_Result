/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.json.JSONObject;

/**
 *
 * @author Charl3s
 */
public class GetResults {

    JSONObject j = new JSONObject();
public ArrayList r = new ArrayList<>();
    public ArrayList getResult(String Reg_no) throws Exception {
        ArrayList al = new ArrayList<>();
        try {
            
            dbConn d = new dbConn();
            Connection conn;
            conn = d.getConnection();
            
            String query = " select  UNITS.Unit_name,  result.cat,result.assignment,result.total_marks,result.grade FROM STUDENT INNER JOIN result ON STUDENT.Reg_no = result.Reg_no INNER JOIN UNITS ON result.Unit_code = UNITS.Unit_code where result.Reg_no='r2'";
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                 
                j.put("Unit_name", rs.getString("Unit_name"));
                j.put("cat", rs.getString("cat"));
                j.put("assignment", rs.getString("assignment"));
                j.put("Total_marks", rs.getString("total_marks"));
                j.put("Grade", rs.getString("grade"));
                al.add(j);
            }

        } catch (SQLException e) {

        }
        return al;
    }

    public GetResults() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
