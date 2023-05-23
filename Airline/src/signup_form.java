import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class signup_form extends JDialog{
    private JPanel mypanel;
    private JTextField email_input;
    private JPasswordField password_input;
    private JLabel labelfor_username;
    private JLabel labelfor_email;
    private JLabel labelfor_password;
    private JButton register_button;
    private JTextField username_input;
    private JButton heading;
    private JButton cancelButton;


    public signup_form(JFrame parent){
        super(parent);

        setTitle("welcome");
        setContentPane(mypanel);
        setMinimumSize(new Dimension(450,474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        register_button.setText("Signup");
        register_button.setFocusable(false);
        heading.setFocusable(false);
        //heading.setEnabled(false);
        cancelButton.setFocusable(false);
        register_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                admin a = new admin();
                a.set_username(username_input.getText());
                a.set_email(email_input.getText());
                a.set_password(String.valueOf(password_input.getPassword()));

                if (!a.sign_up()){
                    JOptionPane.showMessageDialog(parent, "This Email Is Already Found!",
                            "Signup Failed", JOptionPane.ERROR_MESSAGE);
                }
                else{

                    JOptionPane.showMessageDialog(parent, "Welcome " + a.get_name(),
                            "Signup Successfully", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    //Login_form f = new Login_form(null);

                }
            }
        });


        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }


}
