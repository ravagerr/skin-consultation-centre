// Doctor subclass that extends the Person superclass
public class Doctor extends Person {
    // instance variables for the Doctor class
    private String medicalLicenceNumber;
    private String specialization;

    // constructor for the Doctor class
    public Doctor(String name, String surname, int age, String gender, int medicalLicenceNumber, String specialization) {
        super(name, surname, age, gender);
        this.medicalLicenceNumber = String.valueOf(medicalLicenceNumber);
        this.specialization = specialization;
    }

    // getter and setter methods for the instance variables
    public String getMedicalLicenceNumber() {
        return medicalLicenceNumber;
    }

    public void setMedicalLicenceNumber(String medicalLicenceNumber) {
        this.medicalLicenceNumber = medicalLicenceNumber;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}