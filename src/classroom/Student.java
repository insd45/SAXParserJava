/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classroom;

/**
 *
 * @author dale
 */
public class Student {
    private final String pawprint;
    private final String firstName;
    private final String lastName;
    private final double grade;
    private final int id;
    
    public Student(int id, String pawprint, String firstName, String lastName, double grade) {
        this.id = id;
        this.pawprint = pawprint;
        this.firstName = firstName;
        this.lastName = lastName;
        this.grade = grade;
    }
    
    public int getId() {
        return id;
    }
    
    public String getPawprint() {
        return pawprint;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public double getGrade() {
        return grade;
    }
}
