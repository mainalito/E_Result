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
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;
import users.User;

/**
 *
 * @author Charl3s
 */
public class register extends HttpServlet {

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

            User u = new User();

            //reads values form form fields
//            String Reg_no = request.getParameter("Reg_no");
//            String phone_number = request.getParameter("phone_number");
//            String password = request.getParameter("password");
            conn = d.getConnection();
            String Reg_no = jsonObject.getString("Reg_no");
            String password = jsonObject.getString("password");
            String phone_number = jsonObject.getString("phone_number");
            //out.print(Validate.getHashSHA1(password));
            //base64 encode
           
            final byte[] authBytes = password.getBytes(StandardCharsets.UTF_8);
            final String encod = Base64.getEncoder().encodeToString(authBytes);
//            System.out.println(password + " => " + encoded);
            u.setReg_no(Reg_no);
            u.setPassword(encod);
            u.setPhone_number(phone_number);
            String query = "insert into STUDENT_REG_DETAILS(Reg_no,password,phone_number) VALUES (?,?,?)";
            JSONObject j = new JSONObject();
            JSONObject j1 = new JSONObject();
            JSONArray jA = new JSONArray();
            if (!Validate.checkUser(Reg_no) && Validate.checkStudentUser(Reg_no)) {
                PreparedStatement ps = conn.prepareStatement(query);
                // For the first parameter, 
                // get the data using request object 
                // sets the data to st pointer
                ps.setString(1, u.getReg_no());
                ps.setString(2, u.getPassword());
                ps.setString(3, u.getPhone_number());
                ps.executeUpdate();
                
                j.put("status", 0);

                out.print(j);
            } else {
                j.put("status", 1);
                out.print(j);
            }

        } catch (Exception ex) {
            Logger.getLogger(register.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
