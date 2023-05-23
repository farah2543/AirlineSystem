import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class AddF_GUI extends JDialog {
    private JTextField ArrivalTime;
    private JTextField ArrivalDate;
    private JTextField DepartureTime;
    private JTextField DepartureDate;
    private JTextField destination;
    private JTextField source;
    private JTextField Availability;
    private JPanel root3Panel;
    private JButton addButton;
    private int AircraftID;
    private JComboBox AIDCombo;
    private Flight f = new Flight();

    public JPanel getRootPanel(){
        return root3Panel;
    }

    public AddF_GUI(JFrame parent) {
        super(parent);
        setTitle("welcome");
        setContentPane(root3Panel);
        setMinimumSize(new Dimension(450,474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        displayIDS();
        AIDCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer selectedAircraftID = (Integer) AIDCombo.getSelectedItem();
                if (selectedAircraftID != null) {
                    AircraftID = selectedAircraftID;
                    // You can perform any desired actions based on the selected ID
                    System.out.println("Selected Aircraft ID: " + selectedAircraftID);

                }
                
            }
        });
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("yLaaaaaaaaaaaaaaaahewaaaaaaaaaaa");
                String arrivalTimeText = ArrivalTime.getText();
                String arrivalDateText = ArrivalDate.getText();
                String departureTimeText = DepartureTime.getText();
                String departureDateText = DepartureDate.getText();
                String destinationText = destination.getText();
                String sourceText = source.getText();
                String availabilityText = Availability.getText();

                if (arrivalTimeText.isEmpty() || arrivalDateText.isEmpty() || departureDateText.isEmpty() || departureTimeText.isEmpty() || destinationText.isEmpty() || sourceText.isEmpty() || availabilityText.isEmpty()){
                    JOptionPane.showMessageDialog(parent,"Please Makesure you Entered All Required Fields Correctly","Error During Adding",JOptionPane.ERROR_MESSAGE);

                }
                else{

                    // Perform the saving logic with the entered information
                    if (f.addFlight(arrivalTimeText, arrivalDateText, departureTimeText,
                            departureDateText, destinationText, sourceText, availabilityText, AircraftID )){

                        JOptionPane.showMessageDialog(parent,"Flight Added Successfully","flight Added",JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                    }
                    else{
                        JOptionPane.showMessageDialog(parent,"There aro no aircrafts available to add a new flight","Unable to Add Flight",JOptionPane.ERROR_MESSAGE);
                        dispose();
                    }
                }


            }
        });

        setVisible(true);
    }
    void displayIDS(){


       List <Integer> AIDS = f.displayNewFIDs();
        DefaultComboBoxModel<Integer> model = new DefaultComboBoxModel<>();
        for (Integer id : AIDS) {
            model.addElement(id);
        }
       AIDCombo.setModel(model);
    }



}





