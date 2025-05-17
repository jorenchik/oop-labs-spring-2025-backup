package jtm.activity17;

// This class is used as a definition for persistent objects
// Do not change this class!

import jtm.activity16.Person;

public class Student extends Person {

    static final int typeId = 1; // Assuming 1 = student in database
    private final int year;      // Example specific property for Student

    public Student(int id, String firstName, String lastName, int year) {
        super(id, typeId, firstName, lastName);
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    @Override
    public String toString() {
        return super.toString() + " year " + year;
    }
}
