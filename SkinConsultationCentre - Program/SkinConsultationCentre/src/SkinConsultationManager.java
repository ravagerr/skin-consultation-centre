import java.util.List;

public interface SkinConsultationManager {
    // method to add a doctor to the list
    void addDoctor(Doctor doctor);

    // method to remove a doctor from the list
    void removeDoctor(Doctor doctor);

    // method to get the list of all doctors
    List<Doctor> getDoctors();
}