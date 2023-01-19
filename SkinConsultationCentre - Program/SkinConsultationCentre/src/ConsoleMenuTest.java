import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ConsoleMenuTest {
    @Test
    void addDoctorTest() {
        // Create a new object of the Manager class
        WestminsterSkinConsultationManager manager = new WestminsterSkinConsultationManager();

        // Create a new Doctor object with invalid values
        Doctor doctor = new Doctor("Joe", "Bloggs", 30, "Male", 12345, "Dermatology");

        // Pass the new doctor object as an argument
        manager.addDoctor(doctor);

        // Get the list of doctors from the manager object
        List<Doctor> doctors = manager.getDoctors();

        // Check if the list contains the newly added doctor
        assertTrue(doctors.contains(doctor));

        // Unfortunately, this passes the test. The problem here is validation done is on the input fields and not on the object itself.
    }
}

class addDoctorsMoreThanTen {
    @Test
    void addDoctorsMoreThanTen() {
        // Create a new object of the Manager class
        WestminsterSkinConsultationManager manager = new WestminsterSkinConsultationManager();

        // Add 10 valid doctors to the list
        for (int i = 0; i < 10; i++) {
            Doctor doctor = new Doctor("Name" + i, "Surname" + i, 30, "Male", 12345 + i, "Dermatology");
            manager.addDoctor(doctor);
        }

        // Try to add an 11th doctor
        Doctor doctor = new Doctor("Name10", "Surname10", 30, "Male", 12345 + 10, "Dermatology");
        manager.addDoctor(doctor);

        // Get the list of doctors from the manager object
        List<Doctor> doctors = manager.getDoctors();

        // Check if the list contains the 11th doctor
        assertFalse(doctors.contains(doctor));

        // Check if the list still only contains 10 doctors
        assertEquals(doctors.size(), 10);
    }
}
    class removeDoctorTest {
        @Test
        void removeDoctorTest() {
            // Create a new object of the Manager class
            WestminsterSkinConsultationManager manager = new WestminsterSkinConsultationManager();

            // Create a new Doctor object with valid values
            Doctor doctor = new Doctor("Joe", "Bloggs", 30, "Male", 12345, "Dermatology");

            // Add the new doctor object to the list of doctors
            manager.addDoctor(doctor);

            // Remove the doctor from the list of doctors
            manager.removeDoctor(doctor);

            // Get the list of doctors from the manager object
            List<Doctor> doctors = manager.getDoctors();

            // Check if the list does not contain the removed doctor
            assertFalse(doctors.contains(doctor));
        }
    }
    class printDoctorsTest {
        @Test
        void printDoctorsTest() {
            // Create a new object of the Manager class
            WestminsterSkinConsultationManager manager = new WestminsterSkinConsultationManager();

            // Create a Doctor object
            Doctor doctor = new Doctor("Joe", "Bloggs", 30, "Male", 98765, "Dermatology");

            // Add the Doctor objects to the manager's list
            manager.addDoctor(doctor);


            // Capture the output of the printDoctors method in a ByteArrayOutputStream
            ByteArrayOutputStream outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));

            // Call the printDoctors method
            manager.printDoctors();

            // Check the output is as expected
            assertEquals("Name: Joe\nSurname: Bloggs\nAge: 30\nGender: Male\nMedical licence number: 98765\nSpecialization: Dermatology\n\n", outContent.toString());
        }
    }

class saveToFileTest {
    @Test
    void saveToFileTest() {
        // Create a new object of the Manager class
        WestminsterSkinConsultationManager manager = new WestminsterSkinConsultationManager();

        // Create a Doctor object
        Doctor doctor = new Doctor("Joe", "Bloggs", 30, "Male", 12345, "Dermatology");

        // Add the Doctor object to the manager's list
        manager.addDoctor(doctor);

        // Call the savetoFile method
        manager.saveToFile();

        // Check if the file exists
        File file = new File("doctors.txt");
        assertTrue(file.exists());

        // Make sure the save is not empty
        assertTrue(file.length() > 0);
    }
}

class readFromFileTest {
    @Test
    void readFromFileTest() {
        // Create a new object of the Manager class
        WestminsterSkinConsultationManager manager = new WestminsterSkinConsultationManager();

        // Create a Doctor object and add it to the list
        Doctor doctor = new Doctor("Joe", "Bloggs", 30, "Male", 98765, "Dermatology");
        manager.addDoctor(doctor);

        // Save the list of doctors to the file
        manager.saveToFile();

        // Call the readFromFile method
        manager.readFromFile();

        // Get the list of doctors from the manager object
        List<Doctor> doctors = manager.getDoctors();

        // Check if the list contains the doctor that was saved to the file
        assertTrue(doctors.contains(doctor));
    }
}


