import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login_form extends JDialog {
    private JPanel mypanel;
    private JButton loginButton;
    private JTextField email_input;
    private JPasswordField password_input;
    private JButton submit;
    private JLabel labelfor_email;
    private JLabel labelfor_password;
    private JButton cancel_button;


    Login_form(JFrame parent){
        super(parent);

        setTitle("welcome");
        setContentPane(mypanel);
        setMinimumSize(new Dimension(450,474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        loginButton.setFocusable(false);
        //loginButton.setEnabled(false);
        /*ImageIcon img = new ImageIcon("airplane.png");
        loginButton.setIcon(img);*/
        submit.setFocusable(false);
        cancel_button.setFocusable(false);

        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                admin a = new admin();
                a.set_email(email_input.getText());
                a.set_password(String.valueOf(password_input.getPassword()));

                if (!a.login()){
                    JOptionPane.showMessageDialog(parent, "invalid credentials!,try again",
                            "Login Failed", JOptionPane.ERROR_MESSAGE);
                }

                else{
                    JOptionPane.showMessageDialog(parent, "Welcome " + a.get_name(),
                            "Login Successfully", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                    admin_menu menu = new admin_menu(null);
                }

            }
        });

        cancel_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                //signup_form s = new signup_form(null);

            }
        });
        setVisible(true);
    }
}
