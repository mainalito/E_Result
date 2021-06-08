/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBSERVLET;

import Database.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import users.User;

/**
 *
 * @author Charl3s
 */
public class Login extends HttpServlet {

    private dbConn d = new dbConn();
    private Connection conn;
    
    

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
            JSONObject j = new JSONObject();
            User u = new User();

            //reads values form form fields
//            String Reg_no = request.getParameter("Reg_no");
//            String phone_number = request.getParameter("phone_number");
//            String password = request.getParameter("password");
            setConn(getD().getConnection());
            String Reg_no = jsonObject.getString("Reg_no");
            String password = jsonObject.getString("password");
             
            final byte[] authBytes = password.getBytes(StandardCharsets.UTF_8);
            final String encoded = Base64.getEncoder().encodeToString(authBytes);
            u.setReg_no(Reg_no);
            u.setPassword(encoded);
            if (Validate.checkUser(Reg_no,encoded)) {
               
                j.put("welcome ",Reg_no);
              
                  j.put("status",0);
                out.print(j);
            }
            else{
                  j.put("status",1);
                   
             
                out.print(j);
            }

        } catch (SQLException e) {

        } catch (Exception ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    /**
     * @return the d
     */
    public dbConn getD() {
        return d;
    }

    /**
     * @param d the d to set
     */
    public void setD(dbConn d) {
        this.d = d;
    }

    /**
     * @return the conn
     */
    public Connection getConn() {
        return conn;
    }

    /**
     * @param conn the conn to set
     */
    public void setConn(Connection conn) {
        this.conn = conn;
    }

}
