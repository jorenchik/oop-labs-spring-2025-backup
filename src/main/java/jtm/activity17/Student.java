package jtm.activity17;

import jtm.activity16.Person;

public class Student extends Person {

    static final int typeId = 1;

    public Student(int id, String firstName, String lastName) {
        super(id, typeId, firstName, lastName);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
