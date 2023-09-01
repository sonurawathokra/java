import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class TeacherAdd {
    JFrame frame = new JFrame();

    TeacherAdd() {
        frame.setSize(1000, 700);
        frame.setBounds(100, 100, 1000, 600);
        frame.setTitle("Teacher Information");
        frame.setLayout(null);

        JTextField t1 = new JTextField();
        t1.setBounds(320, 50, 200, 30);
        JLabel l1 = new JLabel("Name:");
        l1.setBounds(155, 50, 200, 30);

        JTextField t2 = new JTextField();
        t2.setBounds(320, 100, 200, 30);
        JLabel l2 = new JLabel("Age:");
        l2.setBounds(155, 100, 200, 30);

        JTextField t3 = new JTextField();
        t3.setBounds(320, 150, 200, 30);
        JLabel l3 = new JLabel("Subject:");
        l3.setBounds(155, 150, 200, 30);

        JTextField t5 = new JTextField();
        t5.setBounds(320, 200, 200, 30);
        JLabel l5 = new JLabel("Phone:");
        l5.setBounds(150, 200, 200, 30);

        JTextField t6 = new JTextField();
        t6.setBounds(320, 250, 200, 30);
        JLabel l6 = new JLabel("Email:");
        l6.setBounds(155, 250, 200, 30);

        JTextField t7 = new JTextField();
        t7.setBounds(320, 300, 200, 30);
        JLabel l7 = new JLabel("Address:");
        l7.setBounds(150, 300, 210, 50);

        JButton Bt1 = new JButton("Cancel");
        Bt1.setBounds(320, 400, 100, 30);

        JButton Bt2 = new JButton("Save");
        Bt2.setBounds(450, 400, 100, 30);

        frame.add(t1);
        frame.add(t2);
        frame.add(t3);
        frame.add(t5);
        frame.add(t6);
        frame.add(t7);
        frame.add(l1);
        frame.add(l2);
        frame.add(l3);
        frame.add(l5);
        frame.add(l6);
        frame.add(l7);
        frame.add(Bt1);
        frame.add(Bt2);

        frame.setVisible(true);

        Bt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://localhost:3306/sonu";
                String username = "root";
                String password = "";

                try {
                    Connection connection = DriverManager.getConnection(url, username, password);
                    String sql = "INSERT INTO `sonu`.`teacher` (`Name`, `Age`, `Subject`, `Phone`, `Email`, `Address`)"
                               + " VALUES ('" + t1.getText() + "', '" + t2.getText() + "', '" + t3.getText() + "', '"
                               + t5.getText() + "', '" + t6.getText() + "', '" + t7.getText() + "')";
                    Statement statement = connection.createStatement();
                    int rowsInsert = statement.executeUpdate(sql);

                    if (rowsInsert > 0) {
                        JOptionPane.showMessageDialog(frame, "Insert Teacher successful");
                        frame.dispose();
                    } else {
                        JOptionPane.showMessageDialog(frame, t1);
                    }
                } catch (Exception ex) {
                    System.out.print(ex);
                    JOptionPane.showMessageDialog(frame, t1);
                }
            }
        });

        Bt1.addActionListener(event -> {
            frame.dispose();
        });
    }

    public static void main(String[] args) {
        new TeacherAdd();
    }
}
