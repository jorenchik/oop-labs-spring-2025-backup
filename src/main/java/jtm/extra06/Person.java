package jtm.extra06;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import java.util.Objects;

// This is persistent class using Jakarta JPA annotations
// TODO Annotate class as an @Entity, to say that its instances can be stored in the database.
@Table(name = "Person")
public class Person {
    // TODO Annotate this field as @Transient (so it will not be saved in database)
    static final int defaultTypeId = 1;

    // TODO Annotate id field as @Id to mark it as a primary key field
    // and as  @Column(nullable = false) to save it in database disallowing nulls
    private int id;

    // TODO mark this property as @Column(nullable = false)
    private int typeId;
    // TODO mark this property as @Column(name = "firstname", nullable = false, length = 100)
    // Note that column in database has all lowercase letters
    private String firstName;

    // TODO mark this property as @Column(name = "lastname", nullable = false, length = 100)
    // Note that column in database has all lowercase letters
    private String lastName;

    // TODO create empty default constructor required by JPA

    // TODO create public constructor using all parameters for properties

    // TODO create public constructor taking parameters: int id, String firstName, String lastName
    // in which case defaultTypeId is set for typeId

    // TODO generate public getters and setters for properties
    // TODO generate toString() showing all properties: id, typeId, firstName, and lastName

    // TODO generate equals(...) and hashCode() methods using all properties:id, typeId, firstName, and lastName,
}
