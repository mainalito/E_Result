/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import Database.*;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

/**
 *
 * @author Charl3s
 */
public class viewUsers extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        try {
            response.setCharacterEncoding("UTF-8");
            dbConn d = new dbConn();
            Connection con;
            con = d.getConnection();

            set s = new set();

            JSONObject j = new JSONObject();
          
             j.put("status", 0);
            j.put("data", s.getUsers());
          
            out.print(j);
            con.close();
        } catch (SQLException e) {

        } catch (Exception ex) {
            Logger.getLogger(viewUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
