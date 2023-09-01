import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.text.DateFormat;
public class StudentUpdate {
    StudentUpdate() {
        JFrame StudentsUpdate = new JFrame("Update Students");
        StudentsUpdate.setBounds(300, 200, 700, 450);
        JLabel massageLabel, studentId, firstnamLabel, lastnamLabel, emaiLabel, phoneLabel, classLabel, addressLabel, DOBLabel, showmassge;
        JTextField id, firstnamField, lastnamField, emailField, phoneField, classField, addressField;
        int phone, classs;
        String firstnm, lastnm, email, address, DOB = null; 
         JFormattedTextField DOBField;
        studentId = new JLabel("Student Id : ");
        studentId.setBounds(50, 50, 150, 30);
        JButton search = new JButton("Search");
        search.setBounds(430, 50, 100, 40);
        firstnamLabel = new JLabel("First Name : ");
        firstnamLabel.setBounds(50, 100, 150, 30);
        lastnamLabel = new JLabel("Last Name : ");
        lastnamLabel.setBounds(335, 100, 150, 30);
        //emaiLabel = new JLabel("Email : ");
        //emaiLabel.setBounds(50, 150, 150, 30);
        phoneLabel = new JLabel("Phone No. : ");
        phoneLabel.setBounds(335, 150, 150, 30);
        classLabel = new JLabel("Class : ");
        classLabel.setBounds(50, 200, 150, 30);
       // addressLabel = new JLabel("Address : ");
       // addressLabel.setBounds(335, 200, 150, 30);
DOBLabel = new JLabel("Date of Birth");
  DOBLabel.setBounds(50, 250, 150, 30);
  showmassge = new JLabel();
  id = new JTextField("");
  id.setBounds(140, 50, 150, 30);
  firstnamField = new JTextField("");
  firstnamField.setBounds(140, 100, 150, 30);
  lastnamField = new JTextField("");
  lastnamField.setBounds(430, 100, 150, 30);
  //emailField = new JTextField("");
  //emailField.setBounds(140, 150, 150, 30);
  phoneField = new JTextField("");
  phoneField.setBounds(430, 150, 150, 30);
  classField = new JTextField("");
  classField.setBounds(140, 200, 150, 30);
  //addressField = new JTextField("");
  //addressField.setBounds(430, 200, 150, 30);
  DOBField = new JFormattedTextField(DateFormat.getDateInstance(DateFormat.SHORT));
  DOBField.setBounds(140, 250, 150, 30);
  JButton cancel = new JButton("Cancel");
  cancel.setBounds(300, 330, 100, 40);
  JButton update = new JButton("Update");
  update.setBounds(430, 330, 100, 40);
  StudentsUpdate.add(studentId);
  StudentsUpdate.add(search);
  StudentsUpdate.add(firstnamLabel);
  StudentsUpdate.add(lastnamLabel);
 // StudentsUpdate.add(emaiLabel);
  StudentsUpdate.add(phoneLabel);
  StudentsUpdate.add(classLabel);
 // StudentsUpdate.add(addressLabel);
  StudentsUpdate.add(DOBLabel);
  StudentsUpdate.add(showmassge);
  StudentsUpdate.add(id);
  StudentsUpdate.add(firstnamField);
  StudentsUpdate.add(lastnamField);
 // StudentsUpdate.add(emailField);
  StudentsUpdate.add(phoneField);
  StudentsUpdate.add(classField);
  //StudentsUpdate.add(addressField);
  StudentsUpdate.add(DOBField);
  StudentsUpdate.add(cancel);
  StudentsUpdate.add(update);
  StudentsUpdate.setLayout(null);
  StudentsUpdate.setVisible(true);
update.addActionListener(new ActionListener() {
    @Override  public void actionPerformed(ActionEvent e) {
        String url = "jdbc:mysql://localhost:3306/sonu";
        String username = "root";
        String password = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String query = "UPDATE student SET FirstName=?, LastName=?, Phone=?, Class=?, Dateofbirth=? WHERE Id=?";
PreparedStatement preparedStatement = connection.prepareStatement(query);
preparedStatement.setString(1, firstnamField.getText());
preparedStatement.setString(2, lastnamField.getText());
preparedStatement.setString(3, phoneField.getText());
preparedStatement.setString(4, classField.getText());
preparedStatement.setString(5, DOBField.getText());
preparedStatement.setString(6, id.getText()); // <-- This is the correct index

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(StudentsUpdate, "Student updated successfully.");
            } else {
                JOptionPane.showMessageDialog(StudentsUpdate, "Error: Unable to update the student.");
            }  preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(StudentsUpdate, "Error: Unable to update the student.");
        }  }  });
search.addActionListener(new ActionListener() {
    @Override  public void actionPerformed(ActionEvent e) {
        String url = "jdbc:mysql://localhost:3306/sonu";
        String username = "root";
        String password = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String query = "SELECT * FROM student WHERE Id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, id.getText());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                firstnamField.setText(resultSet.getString("FirstName"));
                lastnamField.setText(resultSet.getString("LastName"));
                //emailField.setText(resultSet.getString("Email"));
                phoneField.setText(resultSet.getString("Phone"));
                classField.setText(resultSet.getString("Class"));
                //addressField.setText(resultSet.getString("Address"));
                DOBField.setText(resultSet.getString("Dateofbirth"));
            } else {  JOptionPane.showMessageDialog(StudentsUpdate, "Student not found.");
        }  resultSet.close();
        preparedStatement.close();
        connection.close();
    } catch (ClassNotFoundException | SQLException ex) { 
         ex.printStackTrace();
           JOptionPane.showMessageDialog(StudentsUpdate, "Error: Unable to search for the student.");
          }  }  });  }
public static void main(String[] args) {  SwingUtilities.invokeLater(() -> new StudentUpdate());  }}
