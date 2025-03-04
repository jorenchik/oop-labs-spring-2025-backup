package jtm.activity16;

// This class is used as a definition for persistent objects
// Do not change this class!

public class Person {

    static final int defaultTypeId = 1;
    private final int id;
    private final int typeId;
    private final String firstName;
    private final String lastName;

    public Person(int id, String firstName, String lastName) {
        this.id = id;
        this.typeId = defaultTypeId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Person(int id, int typeId, String firstName, String lastName) {
        this.id = id;
        this.typeId = typeId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public int getTypeId() {
        return typeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

}
