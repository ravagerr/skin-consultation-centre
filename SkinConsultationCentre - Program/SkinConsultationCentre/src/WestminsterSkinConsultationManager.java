import javax.swing.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class WestminsterSkinConsultationManager implements SkinConsultationManager {
    // instance variable
    private List<Doctor> doctors;
    private List<Consultation> consultations;

    // constructor for the WestminsterSkinConsultationManager class
    public WestminsterSkinConsultationManager() {
        this.doctors = new ArrayList<>();
        consultations = new ArrayList<>();
    }

    public void addConsultation(Consultation consultation) {
        consultations.add(consultation);
    }

    public List<Consultation> getConsultations() {
        return consultations;
    }
    // method to add a doctor to the list
    public void addDoctor(Doctor doctor) {
        if (doctors.size() < 10) {
            doctors.add(doctor);
        } else {
            System.out.println("Cannot add more than 10 doctors to the centre.");
        }
    }

    // method to remove a doctor from the list
    public void removeDoctor(Doctor doctor) {
        doctors.remove(doctor);
    }

    // method to get the list of all doctors
    public List<Doctor> getDoctors() {
        return doctors;
    }

    // method to print the list of doctors
    public void printDoctors() {
        Doctor[] doctors = getDoctors().toArray(new Doctor[0]);
        Arrays.sort(doctors, Comparator.comparing(Person::getSurname));
        for (Doctor doctor : doctors) {
            System.out.println("Name: " + doctor.getName());
            System.out.println("Surname: " + doctor.getSurname());
            System.out.println("Age: " + doctor.getAge());
            System.out.println("Gender: " + doctor.getGender());
            System.out.println("Medical licence number: " + doctor.getMedicalLicenceNumber());
            System.out.println("Specialization: " + doctor.getSpecialization());
            System.out.println();
        }
    }

    // method to save the information to a file
    public void saveToFile() {
        File file = new File("doctors.txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Doctor doctor : doctors) {
                writer.write(doctor.getName() + "," + doctor.getSurname() + "," + doctor.getAge() + "," + doctor.getGender() + "," + doctor.getMedicalLicenceNumber() + "," + doctor.getSpecialization());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // method to read the information from a file
    public void readFromFile() {
        File file = new File("doctors.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0];
                String surname = parts[1];
                int age = Integer.parseInt(parts[2]);
                String gender = parts[3];
                int medicalLicenceNumber = Integer.parseInt(parts[4]);
                String specialization = parts[5];

                Doctor doctor = new Doctor(name, surname, age, gender, medicalLicenceNumber, specialization);
                addDoctor(doctor);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        WestminsterSkinConsultationManager manager = new WestminsterSkinConsultationManager();
        manager.readFromFile();
        Scanner input = new Scanner(System.in);
        int option;
        do {
            System.out.println("\nWelcome to the Westminster Skin Consultation Centre");
            System.out.println("1. Add a doctor");
            System.out.println("2. Remove a doctor");
            System.out.println("3. Print list of doctors");
            System.out.println("4. Save to file");
            System.out.println("5. Read from file");
            System.out.println("6. Exit");
            System.out.println("7. Open GUI");
            System.out.print("Enter your choice: ");
            option = input.nextInt();

            if (option == 1) {
                System.out.print("Enter the name of the doctor: ");
                String name = input.next();
                while (name.matches(".*\\d+.*")) { // check if name contains any numbers
                    System.out.print("Name must not contain any digits. Enter a valid name: ");
                    name = input.next();
                }
                System.out.print("Enter surname of doctor: ");
                String surname = input.next();
                while (surname.matches(".*\\d+.*")) { // check if surname contains any numbers
                    System.out.print("Surname must not contain any digits. Enter a valid surname: ");
                    surname = input.next();
                }
                System.out.print("Enter the age of the doctor: ");
                int age = 0;
                boolean ageValid = false;
                while (!ageValid) {
                    try {
                        age = input.nextInt();
                        if (age >= 18 && age <= 100) { // check if age is between 18 and 100
                            ageValid = true;
                        } else {
                            System.out.print("Age must be between 18 and 100. Enter a valid age: ");
                        }
                    } catch (InputMismatchException e) {
                        System.out.print("Age must be a number. Enter a valid age: ");
                        input.nextLine();
                    }
                }
                input.nextLine();
                System.out.print("Enter the gender of the doctor (M/F): ");
                String gender = input.nextLine();
                while (!gender.equals("M") && !gender.equals("F")) {
                    System.out.println("Invalid gender. Please enter M or F.");
                    System.out.print("Enter the gender of the doctor (M/F): ");
                    gender = input.nextLine();
                }
                System.out.print("Enter the medical licence number of the doctor: ");
                int medicalLicenceNumber = 0;
                boolean medicalLicenceNumberValid = false;
                while (!medicalLicenceNumberValid) {
                    try {
                        medicalLicenceNumber = input.nextInt();
                        if (String.valueOf(medicalLicenceNumber).length() == 8) { // check if medical licence number is 8 digits long
                            boolean medicalLicenceNumberExists = false;
                            for (Doctor doctor : manager.getDoctors()) {
                                if (doctor.getMedicalLicenceNumber().equals(String.valueOf(medicalLicenceNumber))) {
                                    medicalLicenceNumberExists = true;
                                    break;
                                }
                            }
                            if (medicalLicenceNumberExists) {
                                System.out.print("Medical licence number already exists. Enter a different medical licence number: ");
                            } else {
                                medicalLicenceNumberValid = true;
                            }
                        } else {
                            System.out.print("Medical licence number must be 8 digits long. Enter a valid medical licence number: ");
                        }
                    } catch (InputMismatchException e) {
                        System.out.print("Medical licence number must be a number. Enter a valid medical licence number: ");
                        input.nextLine();
                    }
                }
                input.nextLine();
                System.out.print("Enter the specialization of the doctor: ");
                String specialization = "";
                boolean specializationValid = false;
                while (!specializationValid) {
                    try {
                        specialization = input.nextLine();
                        if (specialization.length() > 0 && !specialization.matches(".*\\d+.*")) { // check if specialization is not an empty string and does not contain any numbers
                            specializationValid = true;
                        } else {
                            System.out.print("Specialization cannot be an empty string and must not contain any digits. Enter a valid specialization: ");
                        }
                    } catch (InputMismatchException e) {
                        System.out.print("Specialization must be a string. Enter a valid specialization: ");
                        input.nextLine();
                    }
                }
                Doctor newDoctor = new Doctor(name, surname, age, gender, medicalLicenceNumber, specialization);
                manager.addDoctor(newDoctor);
            } else if (option == 2) {
                System.out.print("Enter the medical licence number of the doctor to be removed: ");
                String medicalLicenceNumber = input.next();
                Doctor doctorToRemove = null;
                for (Doctor doctor : manager.getDoctors()) {
                    if (doctor.getMedicalLicenceNumber().equals(medicalLicenceNumber)) {
                        doctorToRemove = doctor;
                        break;
                    }
                }
                if (doctorToRemove != null) {
                    manager.removeDoctor(doctorToRemove);
                    System.out.println("Doctor with medical licence number " + medicalLicenceNumber + " has been removed.");
                    System.out.println("Number of remaining doctors: " + manager.getDoctors().size());
                } else {
                    System.out.println("Doctor with medical licence number " + medicalLicenceNumber + " not found.");
                }
            } else if (option == 3) {
                manager.printDoctors();
                System.out.println("Total number of doctors: " + manager.getDoctors().size());
            } else if (option == 4) {
                manager.saveToFile();
                System.out.println("Information saved to file.");
            } else if (option == 5) {
                manager.readFromFile();
                System.out.println("Information read from file.");
            }
            else if (option == 7) {
                GUI window = new GUI(manager);
                window.show();
            }
        } while (option != 6);
    }
}
