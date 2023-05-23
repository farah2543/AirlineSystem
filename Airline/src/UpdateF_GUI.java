
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class UpdateF_GUI extends JDialog{
    private JTextField textField1;
    private JButton button1;
    private JTable table1;
    private JPanel root2panel;
    private JTextField value;
    private JButton update;
    private JComboBox<Integer> FIDCombo;
    private JComboBox changeCombo;
    private Flight flight = new Flight();
    List<Flight> flights = flight.getFlights();
    Integer selectedFID;
    String selectedChange;

    public JPanel getRootPanel() {
        return root2panel;
    }

    public UpdateF_GUI(JFrame parent) {

        super(parent);

        setTitle("welcome");
        setContentPane(root2panel);
        setMinimumSize(new Dimension(450,474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        create_table();
        displayIDS();

        FIDCombo.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed ( ActionEvent e ) {
                selectedFID = (Integer) FIDCombo.getSelectedItem();
                if ( selectedFID != null ) {
                    int FID = selectedFID;
                    System.out.println( "Selected Flight ID: " + selectedFID );

                }
                else{
                    JOptionPane.showMessageDialog(parent,"Select FID From the Combobox","Selection is Required",JOptionPane.ERROR_MESSAGE);
                }
            }
        } );

        changeCombo.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed ( ActionEvent e ) {
                selectedChange = (String) changeCombo.getSelectedItem();
                if (selectedChange != null){

                    System.out.println( "Selected Change: " + selectedChange );

                }
                else{
                    JOptionPane.showMessageDialog(parent,"Select Value to Change From the Combobox","Selection is Required",JOptionPane.ERROR_MESSAGE);

                }

            }
        } );

        update.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed ( ActionEvent e ) {
                String new_val = value.getText();
                if (new_val.isEmpty() || selectedChange == null || selectedFID == null){
                    JOptionPane.showMessageDialog(parent,"Please Makesure you Entered all required Fields Correctly","Error During Update",JOptionPane.ERROR_MESSAGE);

                }
                else{

                    if (flight.updateFlightInfo(new_val,selectedFID,selectedChange  )){
                        JOptionPane.showMessageDialog(parent,"Flight Updated Successfully","Updated done",JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                    }

                    else{
                        JOptionPane.showMessageDialog(parent,"Updating Failed","Updated Failed",JOptionPane.ERROR_MESSAGE);
                        dispose();
                    }
                }

            }
        } );
        setVisible(true);

    }



    void create_table() {
        Object[][] data = new Object[flights.size()][8];
        for (int i = 0; i < flights.size(); i++) {
            Flight f = flights.get(i);
            data[i] = new Object[]{
                    f.getFlightId(),
                    f.getSource(),
                    f.getDestination(),
                    f.getDepartureDate(),
                    f.getDepartureTime(),
                    f.getArrivalDate(),
                    f.getArrivalTime(),
                    f.isAvailable()
            };
        }

        DefaultTableModel model = new DefaultTableModel(data, new String[]{
                "flight id",
                "source", "destination", "Departure Date", "Departure time",
                "Arrival date", "Arrival time", "availability"
        });

        table1.setModel(model);
    }

    void displayIDS() {
        List<Integer> FIDS = flight.displayFIDs();
        DefaultComboBoxModel<Integer> model = new DefaultComboBoxModel<>();
        for (Integer id : FIDS) {
            model.addElement(id);
        }
        FIDCombo.setModel(model);
    }
}
