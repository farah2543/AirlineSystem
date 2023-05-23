import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class admin_menu extends JDialog{
    private JButton Heading;

    private JButton ok;
    private JButton Logout;
    private JPanel mypanel;
    private JLabel labelfor_box;

    private String arr[] = {""};
    private JComboBox comboBox1;
    public admin_menu(JFrame parent){
        super(parent);
        setTitle("welcome");
        setContentPane(mypanel);
        setMinimumSize(new Dimension(450,474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        Heading.setText("welcome");
        Heading.setFocusable(false);
        ok.setFocusable(false);
        Logout.setFocusable(false);

        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String action = comboBox1.getItemAt(comboBox1.getSelectedIndex()).toString();

                if (action.isEmpty()){
                    JOptionPane.showMessageDialog(parent,"You Must Select Option","Selection is Mandatory",JOptionPane.ERROR_MESSAGE);

                }
                else if (action == "Add/Update Flight"){

                    admin_flight_GUI a = new admin_flight_GUI(null);

                }


                else if(action == "Add/Update Aircraft"){}
            }
        });

        Logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Login_form l = new Login_form(null);
            }
        });
        setVisible(true);
    }
}
