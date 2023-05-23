import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class test extends JDialog {
    private JPanel mypanel;
    private JButton welcomeToAirlineButton;
    private JTextField welcomeToAirlineTextField;

    //private String[] columnNames = {"ID","Username", "Email", "Password"};
    private JMenuBar mybar;
    private JMenu Customer;
    private JMenu Admin;

    private JMenuItem A_Login;

    private JMenuItem A_Signup;

    private JMenuItem C_Login;

    private JMenuItem C_Signup;

   /*private void load_table() {
        Object data[][] = {{"1",2,2,3},{"5",6,7,8}};

       table1.setModel(new DefaultTableModel(data, new String[]{"adminid", "USername", "email", "password"}));

        /*final String DB_Url = "jdbc:mysql://localhost/first?serverTimezone=UTC";
        final String Username = "root";
        final String Pass = "Boody_500";


        try (Connection con = DriverManager.getConnection(DB_Url,Username,Pass)){
            String query = "select * from admin";

            PreparedStatement stmnt = con.prepareStatement(query);
            ResultSet result = stmnt.executeQuery();

            while (result.next()){
                model.addRow(new Object[]{result.getInt("adminid"),result.getString("username"),result.getString("email"),result.getString("password")});
            }






        }
        catch (Exception e){
            e.printStackTrace();
        }*/


    public test(JFrame parent){
        super(parent);
        setTitle("welcome");
        setContentPane(mypanel);
        setMinimumSize(new Dimension(450,474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        welcomeToAirlineButton.setFocusable(false);

        //noinspection BoundFieldAssignment
        mybar = new JMenuBar();
        Admin = new JMenu("Admin");
        Customer = new JMenu("Customer");

        A_Signup = new JMenuItem("Signup");
        A_Login = new JMenuItem("Login");



        C_Signup = new JMenuItem("Signup");
        C_Login = new JMenuItem("Login");


        Admin.add(A_Signup);
        Admin.add(A_Login);
        Customer.add(C_Signup);
        Customer.add(C_Login);

        mybar.add(Admin);
        mybar.add(Customer);
        setJMenuBar(mybar);


        A_Signup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signup_form f = new signup_form(null);

            }
        });

        A_Login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login_form f = new Login_form(null);

            }
        });

        C_Signup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Registeration r = new Registeration(null);

            }
        });

        C_Login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LogIn l = new LogIn(null);

            }
        });



        setVisible(true);


    }
}
