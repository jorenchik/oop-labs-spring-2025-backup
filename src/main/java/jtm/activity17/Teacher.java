package jtm.activity17;

// This class is used as a definition for persistent objects
// Do not change this class!

import jtm.activity16.Person;

public class Teacher extends Person {

    static final int typeId = 2;
    private final String department;

    public Teacher(int id, String firstName, String lastName, String department) {
        super(id, typeId, firstName, lastName);
        this.department = department;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return super.toString() + " " + department;
    }
}
