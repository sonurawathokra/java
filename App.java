import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
class App
{
  public static void main(String[] args) {
    JFrame frame = new JFrame();
    frame.setTitle("Jagruti School");
    frame.setSize(800,600);
    frame.setVisible(true);

  JMenuBar menubar = new JMenuBar();
  JMenu student = new JMenu("Student");
  JMenuItem addstudent = new JMenuItem("Add");
  JMenuItem updatestudent = new JMenuItem("Update");
  JMenuItem deletestudent = new JMenuItem("Delete");
  JMenuItem viewallstudent = new JMenuItem("View All");

  student.add(addstudent);
  student.add(updatestudent);
  student.add(deletestudent);
  student.add(viewallstudent);

  menubar.add(student);
  frame.setJMenuBar(menubar);

  JMenu teacher = new JMenu("Teacher");
  JMenuItem addteacher = new JMenuItem("Add");
  JMenuItem updateteacher = new JMenuItem("Update");
  JMenuItem deleteteacher = new JMenuItem("Delete");
  JMenuItem viewallteacher = new JMenuItem("View All");

  teacher.add(addteacher);
  teacher.add(updateteacher);
  teacher.add(deleteteacher);
  teacher.add(viewallteacher);

  menubar.add(teacher);

  JMenu attendance = new JMenu("Attendance");
  JMenuItem markattendance = new JMenuItem("Mark Attendance");
  JMenuItem viewallattendance = new JMenuItem("View All Attendance");

  attendance.add(markattendance);
  attendance.add(viewallattendance);

  menubar.add(attendance);

  addstudent.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e)
    {
      new AddStudent();
    }
    
  });

  updatestudent.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e)
    {
      new StudentUpdate();
    }
    
  });

  deletestudent.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e)
    {
      new studentdelete();
    }
    
  });
  viewallstudent.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        String url = "jdbc:mysql://localhost:3306/sonu";
        String username = "root";
        String password = "";
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            
            String query = "SELECT * FROM student";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.addColumn("First Name");
            tableModel.addColumn("Last Name");
            tableModel.addColumn("Phone");
            tableModel.addColumn("Class");
            tableModel.addColumn("Date Of Birth");
            //tableModel.addColumn("Email");
            //tableModel.addColumn("Address");
            
            while (resultSet.next()) {
                String firstName = resultSet.getString("Firstname");
                String lastName = resultSet.getString("Lastname");
                String phone = resultSet.getString("Phone");
                String studentClass = resultSet.getString("Class"); // Changed variable name
                String dateOfBirth = resultSet.getString("Dateofbirth");
               // String email = resultSet.getString("Email"); // Changed variable name
               // String address = resultSet.getString("Address");
                
                tableModel.addRow(new Object[]{firstName, lastName, phone, studentClass, dateOfBirth});
            }
            
            JTable readerTable = new JTable(tableModel);
            JScrollPane scrollPane = new JScrollPane(readerTable);
            
            frame.getContentPane().removeAll();
            frame.getContentPane().add(scrollPane);
            frame.revalidate();
            
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error: Unable to retrieve student information.");
        }
    }
});


  addteacher.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e)
    {
      new TeacherAdd();
    }
    
  });

   updateteacher.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e)
    {
      new TeacherUpdate();
    }
    
  });
   deleteteacher.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e)
    {
      new teacherdelete();
    }
    
  });

  viewallteacher.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        String url = "jdbc:mysql://localhost:3306/sonu";
        String username = "root";
        String password = "";
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            
            String query = "SELECT * FROM teacher";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.addColumn("Name");
            tableModel.addColumn("Age");
            tableModel.addColumn("Subject");
            tableModel.addColumn("Phone");
            tableModel.addColumn("Email");
            tableModel.addColumn("Address");
            
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String age = resultSet.getString("age");
                String subject = resultSet.getString("subject");
                String phone = resultSet.getString("phone");
                String email = resultSet.getString("Email");
                String address = resultSet.getString("Address");
                
                tableModel.addRow(new Object[]{name, age, subject, phone, email, address});
            }
            
            JTable teacherTable = new JTable(tableModel);
            JScrollPane scrollPane = new JScrollPane(teacherTable);
            
            frame.getContentPane().removeAll();
            frame.getContentPane().add(scrollPane);
            frame.revalidate();
            
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error: Unable to retrieve teacher information.");
        }
    }
});

markattendance.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e)
    {
      new markattendance();
    }
    
  });
  viewallattendance.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e)
    {
      new ViewAttendance();
    }
    
  });

  }
}