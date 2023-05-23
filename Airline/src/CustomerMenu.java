import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerMenu extends JDialog{
    private JComboBox comboBox;
    private JPanel menuPan;
    private JButton choose;
    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
    public CustomerMenu(JFrame parent) {


        super(parent);
        setTitle("Welcome :) ");
        setContentPane(menuPan);
        setMinimumSize(new Dimension(450, 474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        choose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String cho=comboBox.getSelectedItem().toString();
                if(cho == "Book")
                {

                }
                else if(cho == " Cancel Book")
                {

                }
                else if(cho == "Change ticket Class")
                {

                }
                else if(cho == " ")
                {
                    JOptionPane.showMessageDialog(CustomerMenu.this,"you must select option","Selection is required",JOptionPane.ERROR_MESSAGE);

                }
            }
        });
        setVisible(true);
    }
    public static void main(String[] args)
    {
        CustomerMenu menu=new CustomerMenu(null);
    }
}
