public class Person {
    // instance variables for the Person class
    private String name;

    private String surname;
    private int age;
    private String gender;

    // constructor for the Person class
    public Person(String name, String surname, int age, String gender) {
        this.name = name;
        this.surname = surname; // Include the surname attribute in the constructor
        this.age = age;
        this.gender = gender;
    }

    // getter and setter methods for the instance variables
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    // Setter method for the surname attribute
    public void setSurname(String surname) {
        this.surname = surname;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}