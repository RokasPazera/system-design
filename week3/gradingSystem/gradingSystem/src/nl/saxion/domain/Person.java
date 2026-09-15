package nl.saxion.domain;

public abstract class Person {
    protected final String name;

    // more info on individual student: date of birth, address, telephone number, etc.
    // these details are not important
    protected final String dateOfBirth;

    public Person(String name) {
        this.name = name;
        this.dateOfBirth = "on some day";
        this.save();
    }


    public abstract String getName();

    public abstract void save();

}
