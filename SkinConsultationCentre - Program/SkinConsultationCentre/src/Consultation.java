import java.util.Date;

public class Consultation {
    // instance variables for the Consultation class
    private Date date;
    private int timeSlot;
    private double cost;
    private String notes;
    private Doctor doctor;
    private Patient patient;

    // constructor for the Consultation class
    public Consultation(Date date, int timeSlot, double cost, String notes, Doctor doctor, Patient patient) {
        this.date = date;
        this.timeSlot = timeSlot;
        this.cost = cost;
        this.notes = notes;
        this.doctor = doctor;
        this.patient = patient;
    }

    // getter and setter methods for the instance variables
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(int timeSlot) {
        this.timeSlot = timeSlot;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}