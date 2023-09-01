viewReader.addActionListener(new ActionListener() {
  @Override
  public void actionPerformed(ActionEvent e) {
    String url = "jdbc:mysql://localhost:3306/librarymanagement";
    String username = "root";
    String password = "";

    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection connection = DriverManager.getConnection(url, username, password);

      String query = "SELECT * FROM reader";
      PreparedStatement preparedStatement = connection.prepareStatement(query);

      ResultSet resultSet = preparedStatement.executeQuery();

      DefaultTableModel tableModel = new DefaultTableModel();
      tableModel.addColumn("First Name");
      tableModel.addColumn("Last Name");
      tableModel.addColumn("Email");
      tableModel.addColumn("Registration Date");
      tableModel.addColumn("Address");

      while (resultSet.next()) {
        String readerFirstName = resultSet.getString("first_name");
        String readerLastName = resultSet.getString("last_name");
        String readerEmail = resultSet.getString("email");
        String readerRegistrationDate = resultSet.getString("registration_date");
        String readerAddress = resultSet.getString("address");

        tableModel.addRow(new Object[]{readerFirstName, readerLastName, readerEmail, readerRegistrationDate, readerAddress});
      }

      JTable readerTable = new JTable(tableModel);
      JScrollPane scrollPane = new JScrollPane(readerTable);

      fram.getContentPane().removeAll(); 
      fram.getContentPane().add(scrollPane);
      fram.revalidate();

      resultSet.close();
      preparedStatement.close();
      connection.close();
    } catch (ClassNotFoundException | SQLException ex) {
      ex.printStackTrace();
      JOptionPane.showMessageDialog(fram, "Error: Unable to retrieve book information.");
    }
  }
});

}
}
,Yesterday 9:35PM,
