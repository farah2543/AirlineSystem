import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LogIn extends JDialog{
    private JTextField IDP;
    private JPasswordField passwordField;
    private JButton logInButton;
    private JButton cancelButton;
    private JPanel logP;
    public boolean user;
    public CustomerAuthentication cust=new CustomerAuthentication();

    public LogIn(JFrame parent)
    {
        super(parent);
        setTitle("Log In :) ");
        setContentPane(logP);
        setMinimumSize(new Dimension(550, 474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id=IDP.getText();
                String pass=String.valueOf(passwordField.getPassword());
                user = getAuthenicationUser(id,pass);
                if(user)
                {
                    dispose();
                    cust.setPASSWORD(pass);
                    cust.setNATIONALID2(id);
                }
                else
                {
                    JOptionPane.showMessageDialog(LogIn.this,"ID or password Invalid","try again",JOptionPane.ERROR_MESSAGE);
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
    private boolean getAuthenicationUser(String id, String pass) {
        final String DB_Url = "jdbc:mysql://localhost/reservation?serverTimezone=UTC";
        final String Username = "root";
        final String Pass ="T#9758@qlph";
        try
        {
            Connection con= DriverManager.getConnection(DB_Url,Username,Pass);
            String query = "SELECT 1 FROM customer WHERE NATIONALID2 = ? AND PASSWORD = ?  LIMIT 1";
            try (PreparedStatement statement = con.prepareStatement(query)) {
                statement.setString(1, id);
                statement.setString(2,pass);
                try (ResultSet rs = statement.executeQuery()) {
                    return rs.next(); // If there is a matching row, the login is valid; otherwise, it's invalid.
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args)
    {
        LogIn LogForm = new LogIn(null);
        if(LogForm.user )
        {
            System.out.println("log in successfully\n");
        }
        else
        {
            System.out.println("logged in canceled");
        }
    }
}
