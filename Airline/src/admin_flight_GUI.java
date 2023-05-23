import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class admin_flight_GUI extends JDialog{

    private JPanel mypanel;
    private JButton AddFlight;
    private JButton UpdateFlight;
    private JButton Back;


    admin_flight_GUI(JFrame parent){
        super(parent);

        setTitle("welcome");
        setContentPane(mypanel);
        setMinimumSize(new Dimension(450,474));
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        AddFlight.setFocusable(false);
        UpdateFlight.setFocusable(false);
        Back.setFocusable(false);

        AddFlight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //dispose();
                AddF_GUI a =new AddF_GUI(null);
            }
        });
        UpdateFlight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //dispose();
                UpdateF_GUI a = new UpdateF_GUI(null);
            }
        });
        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }
}
