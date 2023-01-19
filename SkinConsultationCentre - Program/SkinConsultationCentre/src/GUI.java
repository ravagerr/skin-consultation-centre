import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

import javax.swing.*;

public class GUI {

    private WestminsterSkinConsultationManager manager;

    public GUI(WestminsterSkinConsultationManager manager) {
        this.manager = manager;
    }

    public void show() {
        JFrame frame = new JFrame("Skin Consultation Centre");
        frame.setSize(400, 400);
        frame.setResizable(false);
        JButton displayButton = new JButton("Display Doctors");
        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create new window to display list of doctors
                JFrame doctorListWindow = new JFrame("Doctor List");
                doctorListWindow.setLocationRelativeTo(null); // center the window on the screen
                doctorListWindow.setResizable(false);
                doctorListWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                // Create panel to hold the list of doctors
                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

                // Add a label for each doctor to the panel
                Doctor[] doctors = manager.getDoctors().toArray(new Doctor[0]);
                Arrays.sort(doctors, Comparator.comparing(Person::getName));

                String labelText = "<table>"
                        + "<tr>"
                        + "<td class='header'>Name</td>"
                        + "<td class='header'>Surname</td>"
                        + "<td class='header'>Age</td>"
                        + "<td class='header'>Gender</td>"
                        + "<td class='header'>Medical licence number</td>"
                        + "<td class='header'>Specialization</td>"
                        + "</tr>";

                for (Doctor doctor : doctors) {
                    labelText += "<tr>"
                            + "<td class='cell'>" + doctor.getName() + "</td>"
                            + "<td class='cell'>" + doctor.getSurname() + "</td>"
                            + "<td class='cell'>" + doctor.getAge() + "</td>"
                            + "<td class='cell'>" + doctor.getGender() + "</td>"
                            + "<td class='cell'>" + doctor.getMedicalLicenceNumber() + "</td>"
                            + "<td class='cell'>" + doctor.getSpecialization() + "</td>"
                            + "</tr>";
                }

                labelText += "</table>";

                String css = ".header { font-weight: bold; border: 1px solid black; padding: 5px; }"
                        + ".cell { padding: 8px; }";

                JLabel label = new JLabel("<html>"
                        + "<style>" + css + "</style>"
                        + "<body style='width: 100vw;'>" + labelText + "</body>"
                        + "</html>");
                panel.add(label);

                doctorListWindow.add(panel);
                doctorListWindow.pack();
                doctorListWindow.setVisible(true);
            }
        });

        JButton btnBookConsultation = new JButton("Book Consultation");
        btnBookConsultation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create new window for booking a consultation
                JFrame bookConsultationWindow = new JFrame("Book Consultation");
                bookConsultationWindow.setSize(500, 200);
                bookConsultationWindow.setResizable(false);
                bookConsultationWindow.setLocationRelativeTo(null);
                bookConsultationWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                // Create panel to hold the list of doctors
                JPanel panel = new JPanel();
                // Add a label and combo box for selecting a doctor
                JLabel selectDoctorLabel = new JLabel("Select a doctor:");
                panel.add(selectDoctorLabel, BorderLayout.NORTH);
                JComboBox<String> doctorComboBox = new JComboBox<>();
                for (Doctor doctor : manager.getDoctors()) {
                    String doctorLabel = "Surname: " + doctor.getSurname() + " | Specialization: " + doctor.getSpecialization();
                    doctorComboBox.addItem(doctorLabel);
                }
                JButton continueBooking = new JButton("Continue");
                continueBooking.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Create new window for booking a consultation
                        JFrame inputFieldsWindow = new JFrame("Book Consultation");
                        inputFieldsWindow.setSize(600, 500);
                        inputFieldsWindow.setLocationRelativeTo(null);
                        inputFieldsWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                        // Create panel to hold the input fields
                        JPanel panel = new JPanel();
                        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

                        // Add input fields to the panel
                        JLabel dateLabel = new JLabel("Date (dd/mm/yyyy):");
                        JTextField dateField = new JTextField();
                        panel.add(dateLabel);
                        panel.add(dateField);

                        JLabel timeLabel = new JLabel("Time (hhmm):");
                        JTextField timeField = new JTextField();
                        panel.add(timeLabel);
                        panel.add(timeField);

                        JLabel costLabel = new JLabel("Cost (£):");
                        JTextField costField = new JTextField();
                        panel.add(costLabel);
                        panel.add(costField);

                        JButton bookButton = new JButton("Book");
                        bookButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                // Get the values from the input fields
                                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                                Date date = null;
                                try {
                                    date = dateFormat.parse(dateField.getText());
                                } catch (ParseException ex) {
                                    throw new RuntimeException(ex);
                                }
                                int time = Integer.parseInt(timeField.getText().replace(":", ""));

                                double cost = Double.parseDouble(costField.getText());

                                // Get the selected doctor from the combo box
                                int selectedIndex = doctorComboBox.getSelectedIndex();
                                Doctor doctor = manager.getDoctors().get(selectedIndex);

                                // Create a new consultation with the input values
                                Consultation consultation = new Consultation(date, time, cost, "", doctor, null);

                                // Add the consultation to the manager class
                                manager.addConsultation(consultation);

                                // Format the time string with a colon between the hour and minute
                                String formattedTime = timeField.getText().substring(0, 2) + ":" + timeField.getText().substring(2, 4);


                                // JOption pane showing the user the booking info
                                String message = "Consultation booked!\n\n"
                                        + "Date: " + dateFormat.format(date) + "\n"
                                        + "Time: " + formattedTime + "\n"
                                        + "Cost: " + cost + "\n"
                                        + "Doctor: " + doctor.getName() + " " + doctor.getSurname();

                                JOptionPane.showMessageDialog(inputFieldsWindow, message, "Success", JOptionPane.INFORMATION_MESSAGE);
                            }
                        });
                        panel.add(bookButton);

                        inputFieldsWindow.add(panel);
                        inputFieldsWindow.pack();
                        inputFieldsWindow.setVisible(true);
                    }
                });
                panel.add(doctorComboBox);
                panel.add(continueBooking);

                bookConsultationWindow.add(panel);
                bookConsultationWindow.setVisible(true);
            }
        });
        JButton showConsultationsButton = new JButton("Show Booked Consultations");
        showConsultationsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create new window to display list of consultations
                JFrame consultationListWindow = new JFrame("Consultation List");
                consultationListWindow.setLocationRelativeTo(null); // center the window on the screen
                consultationListWindow.setResizable(false);
                consultationListWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                // Create panel to hold the list of consultations
                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

                // Add a label for each consultation to the panel
                Consultation[] consultations = manager.getConsultations().toArray(new Consultation[0]);
                Arrays.sort(consultations, Comparator.comparing(Consultation::getDate));

                String labelText = "<table>"
                        + "<tr>"
                        + "<td class='header'>Date</td>"
                        + "<td class='header'>Time</td>"
                        + "<td class='header'>Cost</td>"
                        + "<td class='header'>Doctor</td>"
                        + "</tr>";

                for (Consultation consultation : consultations) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    String dateString = dateFormat.format(consultation.getDate());

                    labelText += "<tr>"
                            + "<td class='cell'>" + dateString + "</td>"
                            + "<td class='cell'>" + consultation.getTimeSlot() + "</td>"
                            + "<td class='cell'>" + consultation.getCost() + "</td>"
                            + "<td class='cell'>" + consultation.getDoctor().getName() + " " + consultation.getDoctor().getSurname() + "</td>"
                            + "</tr>";
                }

                labelText += "</table>";

                String css = ".header { font-weight: bold; border: 1px solid black; padding: 5px; }"
                        + ".cell { padding: 8px; }";

                JLabel label = new JLabel("<html>"
                        + "<style>" + css + "</style>"
                        + "<body style='width: 100vw;'>" + labelText + "</body>"
                        + "</html>");
                panel.add(label);

                consultationListWindow.add(panel);
                consultationListWindow.pack();
                consultationListWindow.setVisible(true);
            }
        });
        frame.add(showConsultationsButton);
        frame.add(btnBookConsultation, BorderLayout.SOUTH);
        frame.add(displayButton, BorderLayout.NORTH);
        frame.setVisible(true);
        frame.pack();
    }
}