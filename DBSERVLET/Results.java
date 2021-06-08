/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBSERVLET;

import Database.Validate;
import Database.dbConn;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;
import users.Exam;
import users.User;

/**
 *
 * @author Charl3s
 */
@WebServlet(name = "Service", urlPatterns = "/results")
public class Results extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            dbConn d = new dbConn();
            Connection con;
            con = d.getConnection();
            response.setCharacterEncoding("UTF-8");

            StringBuilder jb = new StringBuilder();
            String line = null;
            try {
                BufferedReader reader = request.getReader();
                while ((line = reader.readLine()) != null) {
                    jb.append(line);
                }
            } catch (IOException e) {
                /*report an error*/ }
            JSONObject jsonObject = new JSONObject(jb.toString());
            JSONObject j = new JSONObject();
            JSONObject j1 = new JSONObject();

            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            String Reg_no = jsonObject.getString("Reg_no");
            User u = new User();
            Exam e = new Exam();
            u.setReg_no(Reg_no);
            JSONObject jO = new JSONObject();
            if (Validate.checkResultUser(u.getReg_no())) {
                String query = " select  UNITS.Unit_name,  result.cat,result.assignment,result.main_exam,result.total_marks,result.grade FROM result\n"
                        + "INNER JOIN STUDENT ON result.Reg_no = STUDENT.Reg_no\n"
                        + "INNER JOIN UNITS ON result.Unit_code = UNITS.Unit_code \n"
                        + "where result.Reg_no = ? ";
                PreparedStatement pstmt = con.prepareStatement(query);
                pstmt.setString(1, u.getReg_no());
                ResultSet rs;
                rs = pstmt.executeQuery();
                List<JSONObject> resList = new ArrayList<>();
                try {
                    // get column names
                    ResultSetMetaData rsMeta = rs.getMetaData();
                    int columnCnt = rsMeta.getColumnCount();
                    List<String> columnNames = new ArrayList<>();
                    for (int i = 1; i <= columnCnt; i++) {
                        columnNames.add(rsMeta.getColumnName(i).toUpperCase());
                    }

                    while (rs.next()) { // convert each object to an human readable JSON object
                        JSONObject obj = new JSONObject();
                        for (int i = 1; i <= columnCnt; i++) {
                            String key = columnNames.get(i - 1);
                            String value = rs.getString(i);
                            obj.put(key, value);
                        }

                        resList.add(obj);
                    }

                    jO.put("status", 0);
                    jO.put("data", resList);
                    out.print(jO);
                } catch (SQLException | JSONException se) {
                }
            } else {
                jO.put("status", 1);
                out.print(jO);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Results.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Results.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
