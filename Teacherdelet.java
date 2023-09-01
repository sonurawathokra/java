import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class teacherdelete {
    JFrame frame1 = new JFrame("Delete Teacher");
    JButton searchbutton = new JButton("Search");
    JButton deletebutton = new JButton("Delete");
    JButton cancelbutton = new JButton("Cancel");

    JLabel idlLabel, nameLabel, ageLabel, freeLabel;
    JTextField idTextField, nameTextField, ageTextField;
    int id;

    teacherdelete() {
        frame1.setBounds(500, 250, 600, 400);
        frame1.setVisible(true);

        idlLabel = new JLabel("Id :");
        nameLabel = new JLabel(" Name :");
        ageLabel = new JLabel("Age  :");
        freeLabel = new JLabel("");

        idTextField = new JTextField();
        nameTextField = new JTextField();
        nameTextField.setEditable(false);
        ageTextField = new JTextField();
        ageTextField.setEditable(false);

        idlLabel.setBounds(60, 50, 100, 40);
        nameLabel.setBounds(60, 150, 100, 40);
        ageLabel.setBounds(300, 150, 100, 40);

        idTextField.setBounds(130, 50, 150, 40);
        nameTextField.setBounds(130, 150, 150, 40);
        ageTextField.setBounds(400, 150, 150, 40);

        searchbutton.setBounds(400, 50, 120, 40);
        cancelbutton.setBounds(200, 300, 120, 40);
        deletebutton.setBounds(400, 300, 120, 40);

        frame1.add(idlLabel);
        frame1.add(idTextField);
        frame1.add(nameLabel);
        frame1.add(nameTextField);
        frame1.add(ageLabel);
        frame1.add(ageTextField);
        frame1.add(searchbutton);
        frame1.add(cancelbutton);
        frame1.add(deletebutton);
        frame1.add(freeLabel);

        deletebutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://localhost:3306/sonu";
                String username = "root";
                String password = "";

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(url, username, password);

                    String query = "DELETE FROM teacher WHERE Id=?";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, idTextField.getText());

                    int rowsDeleted = preparedStatement.executeUpdate();

                    if (rowsDeleted > 0) {
                        JOptionPane.showMessageDialog(frame1, "record deleted successfully.");
                    } else {
                        JOptionPane.showMessageDialog(frame1, "Error: Unable to delete the record.");
                    }

                    preparedStatement.close();
                    connection.close();
                } catch (ClassNotFoundException | SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame1, "Error: Unable to delete the record.");
                }
            }
        });

        searchbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://localhost:3306/sonu";
                String username = "root";
                String password = "";

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(url, username, password);

                    String query = "SELECT * FROM teacher WHERE Id=?";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, idTextField.getText());

                    ResultSet resultSet = preparedStatement.executeQuery();

                    if (resultSet.next()) {
                        nameTextField.setText(resultSet.getString("Name"));
                        ageTextField.setText(resultSet.getString("Age"));
                    } else {
                        JOptionPane.showMessageDialog(frame1, "record not found.");
                    }

                    resultSet.close();
                    preparedStatement.close();
                    connection.close();
                } catch (ClassNotFoundException | SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame1, "Error: Unable to search for the record.");
                }
            }
        });
    }
}
