/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import users.Exam;

/**
 *
 * @author Charl3s
 */
public class Validate {

    Exam u = new Exam();
    private static String grade;

    public static boolean checkUser(String Reg_no, String password) throws Exception {
        boolean st = false;
        try {
            dbConn d = new dbConn();
            Connection conn;
            conn = d.getConnection();
            String Query = "Select  * from STUDENT_REG_DETAILS WHERE Reg_no=? AND password=?";
            PreparedStatement ps = conn.prepareStatement(Query);
            ps.setString(1, Reg_no);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            st = rs.next();
        } catch (Exception e) {
        }
        return st;
    }

    
    public static boolean checkUser(String Reg_no) throws Exception {
        boolean st = false;
        try {
            dbConn d = new dbConn();
            Connection conn;
            conn = d.getConnection();
            String Query = "Select  * from STUDENT_REG_DETAILS WHERE Reg_no=?";
            PreparedStatement ps = conn.prepareStatement(Query);
            ps.setString(1, Reg_no);
            ResultSet rs = ps.executeQuery();
            st = rs.next();
        } catch (Exception e) {
        }
        return st;
    }

    public static boolean checkStudentUser(String Reg_no) throws Exception {
        boolean st = false;
        try {
            dbConn d = new dbConn();
            Connection conn;
            conn = d.getConnection();
            String Query = "Select  * from STUDENT WHERE Reg_no=?";
            PreparedStatement ps = conn.prepareStatement(Query);
            ps.setString(1, Reg_no);
            ResultSet rs = ps.executeQuery();
            st = rs.next();
        } catch (Exception e) {
        }
        return st;
    }

    public static boolean checkUnit(String Unit_code) throws Exception {
        boolean st = false;
        try {
            dbConn d = new dbConn();
            Connection conn;
            conn = d.getConnection();
            String Query = "Select  * from Unit WHERE Unit_code=?";
            PreparedStatement ps = conn.prepareStatement(Query);
            ps.setString(1, Unit_code);
            ResultSet rs = ps.executeQuery();
            st = rs.next();
        } catch (Exception e) {
        }
        return st;
    }

    public static boolean checkResultUser(String Reg_no) throws Exception {
        boolean st = false;
        try {
            dbConn d = new dbConn();
            Connection conn;
            conn = d.getConnection();
            String Query = "Select  * from result WHERE Reg_no=?";
            PreparedStatement ps = conn.prepareStatement(Query);
            ps.setString(1, Reg_no);
            ResultSet rs = ps.executeQuery();
            st = rs.next();
        } catch (Exception e) {
        }
        return st;
    }

    public static boolean ResultUser(String Reg_no, String Unit_code) throws Exception {
        boolean st = false;
        try {
            dbConn d = new dbConn();
            Connection conn;
            conn = d.getConnection();
            String Query = "Select  * from result WHERE Reg_no=? AND Unit_code=?";
            PreparedStatement ps = conn.prepareStatement(Query);
            ps.setString(1, Reg_no);
            ps.setString(2, Unit_code);
            ResultSet rs = ps.executeQuery();
            st = rs.next();
        } catch (Exception e) {
        }
        return st;
    }
}


//    public static String getHashSHA1(String password){
//        try{
//            MessageDigest md = MessageDigest.getInstance("SHA-1");
//            md.update(password.getBytes());
//            byte byteData[] = md.digest();
//            StringBuilder sb  = new StringBuilder();
//            for(int i =0;i<byteData.length;i++){
//                sb.append(Integer.toString(byteData[i] & 0xff+ 0x100,16).substring(1));
//                
//            }
//            return sb.toString();
//            
//        }catch(NoSuchAlgorithmException ex){
//            
//        }
//    
//}
