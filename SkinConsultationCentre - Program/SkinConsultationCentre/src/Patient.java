public class Patient extends Person {
    // instance variables for the Patient class
    private String patientId;

    // constructor for the Patient class
    public Patient(String name, String surname, int age, String gender, String patientId) {
        super(name, surname, age, gender); // Include the surname attribute in the constructor
        this.patientId = patientId;
    }

    // getter and setter methods for the instance variables
    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }
}