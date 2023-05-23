import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Registeration extends JDialog{
    private JTextField TKID;
    private JTextField TFName;
    private JTextField TFEmail;
    private JTextField TFAge;
    private JButton registerButton;
    private JButton cancelButton;
    private JPanel RegisterationForm;
    private JPanel label;
    private JPasswordField passwordField;
    private JPasswordField CONpasswordField;
    private JTextField Confirm;
    public CustomerAuthentication cust;

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
    public Registeration(JFrame parent) {
        super(parent);
        setTitle("Welcome :) ");
        setContentPane(RegisterationForm);
        setMinimumSize(new Dimension(550, 474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterUser();
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
    public boolean EmailValidator(String email){
        String strongEmailRegex ="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        return email.matches(strongEmailRegex);
    }
    public boolean RegisterUser;
    private void RegisterUser() {
        String id=TKID.getText();
        String name=TFName.getText();
        String Password=String.valueOf(passwordField.getPassword());
        String confirm=String.valueOf(CONpasswordField.getPassword());
        String Email=TFEmail.getText();
        String Age=TFAge.getText();
        if(id.isEmpty() || Email.isEmpty() || Password.isEmpty() || name.isEmpty() || Age.isEmpty())
        {
            JOptionPane.showMessageDialog(this,"please enter all fields","try again",JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(!EmailValidator(Email))
        {
            JOptionPane.showMessageDialog(this,"please enter valid Email","try again",JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(!Password.equals(confirm))
        {
            JOptionPane.showMessageDialog(this,"Confirm Password doesn't match password","try again",JOptionPane.ERROR_MESSAGE);
            return;
        }
        RegisterUser=addUserToDataBase(id,name,Age,Password,Email);
        cust=new CustomerAuthentication(id,name,Password,Email,Age);
    }
    private boolean addUserToDataBase(String id, String name, String age, String password, String email) {
        final String DB_Url = "jdbc:mysql://localhost/reservation?serverTimezone=UTC";
        final String Username = "root";
        final String Pass ="T#9758@qlph";
        try
        {
            Connection con= DriverManager.getConnection(DB_Url,Username,Pass);
            String query = "INSERT INTO CUSTOMER (NATIONALID2, USERNAME, AGE, PASSWORD, EMAIL) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement statement = con.prepareStatement(query)) {
                statement.setString(1, id);
                statement.setString(2, name);
                statement.setString(3, age);
                statement.setString(4, password);
                statement.setString(5, email);
                return statement.executeUpdate()>0;

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
        Registeration myForm = new Registeration(null);
        if(myForm.RegisterUser)
            System.out.println("successful registeration \n");
        else
            System.out.println("Registeration Canceled\n");
    }
}
