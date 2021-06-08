/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import Database.Validate;
import Database.dbConn;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
public class InsertMarks extends HttpServlet {

    private dbConn d = new dbConn();
    private Connection conn;

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


            setConn(getD().getConnection());
            String Reg_no = jsonObject.getString("Reg_no");
            String Unit_code = jsonObject.getString("Unit_code");
            int cat = jsonObject.getInt("cat");
            int assignment = jsonObject.getInt("assignment");
            int main_exam = jsonObject.getInt("main_exam");
            int total_marks = ((cat + assignment) / 0x2 + main_exam);
           
        
            
          
            e.setTotal_marks(total_marks);
            u.setReg_no(Reg_no);
            e.setUnit_code(Unit_code);
            e.setCat(cat);
            e.setAssignment(assignment);
            e.setMain_exam(main_exam);

            JSONObject j = new JSONObject();
            JSONObject j1 = new JSONObject();
            String query = "insert into result(Reg_no,Unit_code,cat,assignment,main_exam,total_marks,grade) VALUES (?,?,?,?,?,?,?)";
            if (!Validate.ResultUser(Reg_no, Unit_code) && Validate.checkStudentUser(Reg_no) && !Validate.checkUnit(Unit_code)) {

                PreparedStatement pstmt = getConn().prepareStatement(query);
                pstmt.setString(1, u.getReg_no());
                pstmt.setString(2, e.getUnit_code());
                pstmt.setInt(3, e.getCat());
                pstmt.setInt(4, e.getAssignment());
                pstmt.setInt(5, e.getMain_exam());
                pstmt.setInt(6, e.getTotal_marks());
                pstmt.setString(7, e.getGrade());

                pstmt.executeUpdate();
                j.put("status", 0x0);
              
//                j.put("description", "successfull inserted marks");
                out.print(j);

            } else {
                j.put("status", 0x1);

                out.print(j);
            }

        } catch (Exception ex) {
            Logger.getLogger(InsertMarks.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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
