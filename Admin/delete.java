/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import Database.dbConn;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import users.Exam;
import users.User;

/**
 *
 * @author Charl3s
 */
public class delete extends HttpServlet {

    dbConn d = new dbConn();
    Connection conn;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            PrintWriter out = response.getWriter();
            request.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
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
//            ArrayList error = new ArrayList<>();

            Exam e = new Exam();
            User u = new User();

            conn = d.getConnection();
            String Reg_no = jsonObject.getString("Reg_no");
            String Unit_code = jsonObject.getString("Unit_code");
            //setter
            u.setReg_no(Reg_no);
            e.setUnit_code(Unit_code);
            JSONObject j = new JSONObject();
            //query
            String query = "delete from result where Reg_no=? AND Unit_code=?";
            PreparedStatement pstmt =  conn.prepareStatement(query);
            pstmt.setString(1, u.getReg_no());
            pstmt.setString(2, e.getUnit_code());
            int i=pstmt.executeUpdate();
            if(i>0){
                  j.put("status",0);
           
            out.print(j);
            }
            else{
                  j.put("status",2);
                
                out.print(j);
            }
        } catch (SQLException e) {

        } catch (Exception ex) {
            Logger.getLogger(delete.class.getName()).log(Level.SEVERE, null, ex);
        }

    }}
