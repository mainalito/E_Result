/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Charl3s
 */
public class Exam {
    private int cat,assignment,main_exam,total_marks;
    private String grade;
    private String unit_code,Unit_name;

    public String getUnit_name() {
        return Unit_name;
    }

    public void setUnit_name(String Unit_name) {
        this.Unit_name = Unit_name;
    }

    

    public int getCat() {
        return cat;
    }

    public void setCat(int cat) {
        this.cat = cat;
    }

    public int getAssignment() {
        return assignment;
    }

    public void setAssignment(int assignment) {
        this.assignment = assignment;
    }
@JsonIgnore
    public int getMain_exam() {
        return main_exam;
    }

    public void setMain_exam(int main_exam) {
        this.main_exam = main_exam;
    }

    public String getUnit_code() {
        return unit_code;
    }

    public void setUnit_code(String unit_code) {
        this.unit_code = unit_code;
    }

    public int getTotal_marks() {
        return total_marks;
    }

    public void setTotal_marks(int total_marks) {
        this.total_marks = total_marks;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
    
}
