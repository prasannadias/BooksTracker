
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.*;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Pras
 */
public class BooksInfo extends javax.swing.JFrame {

    /**
     * Creates new form BooksInfo
     */
    public BooksInfo() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sqlDateModel1 = new org.jdatepicker.impl.SqlDateModel();
        isbnLabel = new javax.swing.JLabel();
        isbnText = new javax.swing.JTextField();
        bookNameLabel = new javax.swing.JLabel();
        bookNameText = new javax.swing.JTextField();
        addBookButton = new javax.swing.JButton();
        viewReport = new javax.swing.JButton();
        datePick = new org.jdesktop.swingx.JXDatePicker();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Books Information");

        isbnLabel.setText("ISBN #");

        bookNameLabel.setText("Book Name");

        addBookButton.setText("Add");
        addBookButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBookButtonActionPerformed(evt);
            }
        });

        viewReport.setText("View Report");
        viewReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewReportActionPerformed(evt);
            }
        });

        jLabel1.setText("Date Read");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addBookButton)
                        .addGap(18, 18, 18)
                        .addComponent(viewReport))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(bookNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(isbnLabel)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(isbnText, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bookNameText, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(datePick, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(77, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(isbnLabel)
                    .addComponent(isbnText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bookNameLabel)
                    .addComponent(bookNameText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(datePick, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(viewReport)
                    .addComponent(addBookButton))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addBookButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBookButtonActionPerformed
        // TODO add your handling code here:
        String isbnNo = isbnText.getText();
        String bookName = bookNameText.getText();
        Date dateRead = convertUtilToSql(datePick.getDate());
        addBooks(isbnNo, bookName,dateRead);
    }//GEN-LAST:event_addBookButtonActionPerformed

     private static java.sql.Date convertUtilToSql(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }

    private void viewReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewReportActionPerformed
        // TODO add your handling code here:
        try {
            Connection conn = connecttoDB();
            String sql1 = "Select count(ISBN) from book";
            String sql = "Select ISBN,BookName,DateRead from book";
            Statement st = conn.createStatement();
            Statement st1 = conn.createStatement();
            ResultSet rs1 = st1.executeQuery(sql1);
            ResultSet rs = st.executeQuery(sql);
            
            
            FileWriter write = new FileWriter("C:/Users/Pras/Desktop/Pics/book.txt");
            PrintWriter print_line = new PrintWriter(write);
            while (rs1.next()) {
                int count = rs1.getInt(1);
                System.out.println("Total number of books read:" + count);
                print_line.println("Total number of books read till date:"+count);
                print_line.println("The details are as follows:");
                print_line.println();

            }
            //System.out.println(sql);
            print_line.format("%s| %s |%s", "ISBN", "Book Name","Date Read");
            print_line.println();
            print_line.println("---------------------------");
            while (rs.next()) {
                String isbn = rs.getString("ISBN");
                String bookName = rs.getString("BookName");
                Date dt = rs.getDate("DateRead");
                System.out.format("%s, %s, %s\n", isbn, bookName,dt);
                
                print_line.format("%s| %s |%s\n", isbn, bookName,dt);
                print_line.println();
                
            }
            
            print_line.close();
            st1.close();
            st.close();
          // java.awt.Desktop.getDesktop().open("C:/Users/Pras/Desktop/Pics/book.txt");
           new ProcessBuilder("notepad","C:/Users/Pras/Desktop/Pics/book.txt").start();
        } catch (Exception e) {
            System.err.println("Exception occured");
            System.err.println(e);
        }
    }//GEN-LAST:event_viewReportActionPerformed

    public void addBooks(String isbn, String bookN, Date dt) {
        try {
            Connection con = connecttoDB();
            Statement stmt = con.createStatement();

            String sql = "Insert into book (ISBN, BookName, DateRead) VALUES ('" + isbn + "','" + bookN + "','"+dt+"')";
            System.out.println(sql);

            stmt.executeUpdate(sql);
            System.out.println("Record inserted successfully");
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public Connection connecttoDB() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/books", "root", "");
            System.out.println("Connection successful!!");

        } catch (Exception e) {
            System.err.println(e);
        }
        return con;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BooksInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BooksInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BooksInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BooksInfo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BooksInfo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBookButton;
    private javax.swing.JLabel bookNameLabel;
    private javax.swing.JTextField bookNameText;
    private org.jdesktop.swingx.JXDatePicker datePick;
    private javax.swing.JLabel isbnLabel;
    private javax.swing.JTextField isbnText;
    private javax.swing.JLabel jLabel1;
    private org.jdatepicker.impl.SqlDateModel sqlDateModel1;
    private javax.swing.JButton viewReport;
    // End of variables declaration//GEN-END:variables
}
