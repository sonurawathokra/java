import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class markattendance {
    JFrame frame = new JFrame("Attendance Management");
    JLabel date, name, Attendance, free;
    JTextField dateField, nameField;
    JButton save, cancel;
    String nm, dt, combo;

    public  markattendance() {
        frame.setTitle("Attendance");
        frame.setBounds(400, 250, 500, 400);
        frame.setVisible(true);

        name = new JLabel("Name:");
        date = new JLabel("Date:");
        Attendance = new JLabel("Attendance Status");
        free = new JLabel("");

        name.setBounds(45, 50, 150, 40);
        date.setBounds(45, 120, 150, 40);
        Attendance.setBounds(45, 190, 150, 40);

        nameField = new JTextField("");
        dateField = new JTextField("");

        nameField.setBounds(170, 50, 170, 30);
        dateField.setBounds(170, 120, 170, 30);

        String attendance[] = {"Present", "Absent"};
        JComboBox<String> cb = new JComboBox<>(attendance);
        cb.setBounds(170, 190, 150, 30);

        frame.add(cb);

        save = new JButton("Save");
        cancel = new JButton("Cancel");

        save.setBounds(110, 290, 110, 30);
        cancel.setBounds(250, 290, 100, 30);

        frame.add(save);
        frame.add(cancel);
        frame.add(date);
        frame.add(name);
        frame.add(Attendance);
        frame.add(dateField);
        frame.add(nameField);
        frame.add(free);

        save.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String DB_URL = "jdbc:mysql://localhost:3306/sonu";
                String USER = "root";
                String PASS = "";
                try {
                    nm = nameField.getText();
                    dt = dateField.getText();
                    combo = (String) cb.getSelectedItem();  
                } catch (NumberFormatException r) {
                    JOptionPane.showMessageDialog(frame, "Wrong Attendance..");
                    return;
                }
                if (e.getSource() == save) {
                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                        Statement stmt = conn.createStatement();
                        String sql = "INSERT INTO attendace(Name, Date, Attendace) VALUES('" + nm + "', '" + dt + "','" + combo + "')";
                        stmt.executeUpdate(sql);
                        JOptionPane.showMessageDialog(frame, "Attendance Saved Successfully..");
                    } catch (SQLDataException sql) {
                        System.out.println(sql);
                    } catch (Exception o) {
                        o.printStackTrace();
                    }
                }
            }
        });

        cancel.addActionListener(event -> {
            frame.dispose();
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new markattendance());
    }
}
