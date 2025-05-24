package jtm.extra06;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "Person")
public class Person {

    @Transient
    static final int defaultTypeId = 1;

    @Id
    @Column(nullable = false)
    private int id;

    @Column(nullable = false)
    private int typeId;

    @Column(name = "firstname", nullable = false, length = 100)
    private String firstName;

    @Column(name = "lastname", nullable = false, length = 100)
    private String lastName;

    // Default constructor required by JPA
    public Person() {}

    // Constructor using all parameters
    public Person(int id, int typeId, String firstName, String lastName) {
        this.id = id;
        this.typeId = typeId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Constructor using default typeId
    public Person(int id, String firstName, String lastName) {
        this(id, defaultTypeId, firstName, lastName);
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", typeId=" + typeId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return id == person.id &&
                typeId == person.typeId &&
                Objects.equals(firstName, person.firstName) &&
                Objects.equals(lastName, person.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, typeId, firstName, lastName);
    }
}
