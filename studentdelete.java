import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
class studentdelete 
{
 JFrame frame1 = new JFrame("Delete Student");
 JButton searchbutton = new JButton("Search");
 JButton deletebutton = new JButton("Delete");
 JButton cancelbutton = new JButton("Cancel");

 JLabel idlLabel, firstnmLabel,lastnmLabel,freeLabel;
 JTextField idTextField,firsmnTextField,lastnmTextField;
 String firstnm,lastnm;
 int id;
 studentdelete()
 {
  frame1.setBounds(500,250 ,600,400);
  frame1.setVisible(true);

  idlLabel = new JLabel("Id :");
  firstnmLabel = new JLabel("First Name :");
  lastnmLabel = new JLabel("Last Name :");
  freeLabel = new JLabel("");

  idTextField = new JTextField();
  firsmnTextField = new JTextField();
  firsmnTextField.setEditable(false);
  lastnmTextField = new JTextField();
  lastnmTextField.setEditable(false);
  idlLabel.setBounds(60,50,100,40);
  firstnmLabel.setBounds(60,150,100,40);
  lastnmLabel.setBounds(300,150,100,40);

  idTextField.setBounds(130,50,150,40);
  firsmnTextField.setBounds(130,150,150,40);
  lastnmTextField.setBounds(400,150,150,40);
  searchbutton.setBounds(400,50,120,40);
  cancelbutton.setBounds(200,300,120,40);
  deletebutton.setBounds(400,300,120,40);

  frame1.add(idlLabel);
  frame1.add(idTextField);
  frame1.add(firstnmLabel);
  frame1.add(firsmnTextField);
  frame1.add(lastnmLabel);
  frame1.add(lastnmTextField);
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
 
    Statement statement = connection.createStatement();
    String query = "DELETE FROM student WHERE Id=?";
    PreparedStatement preparedStatement = connection.prepareStatement(query);
    preparedStatement.setInt(1, Integer.parseInt(idTextField.getText()));
    
    int rowsDeleted = preparedStatement.executeUpdate();
    
    if (rowsDeleted > 0) {
        JOptionPane.showMessageDialog(frame1, "record deleted successfully.");
        // Clear the text fields after successful deletion
        firsmnTextField.setText("");
        lastnmTextField.setText("");
    } else {
        JOptionPane.showMessageDialog(frame1, "record not found or unable to delete.");
    }
    
    preparedStatement.close();
    connection.close();
   } catch (ClassNotFoundException | SQLException ex) {
    ex.printStackTrace();
    // Handle database errors and display an error message
    JOptionPane.showMessageDialog(frame1, "Error: Unable to update the record.");
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
 
    String query = "SELECT * FROM student WHERE Id=?";
    PreparedStatement preparedStatement = connection.prepareStatement(query);
    preparedStatement.setString(1, idTextField.getText());
 
    ResultSet resultSet = preparedStatement.executeQuery();
 
    if (resultSet.next()) {
     firsmnTextField.setText(resultSet.getString("FirstName"));
     lastnmTextField.setText(resultSet.getString("LastName"));
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
