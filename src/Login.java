
import java.awt.CardLayout;
import java.util.List;
import javax.swing.JOptionPane;
import one.connect.ConnectionFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.quick.Company;


public class Login extends javax.swing.JFrame {
    CardLayout cl;
    public static String companyId;
    public Login() {
        initComponents();
        cl=(CardLayout)jPanel1.getLayout();
        cl.show(jPanel1, "home");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jPasswordField4 = new javax.swing.JPasswordField();
        jPasswordField5 = new javax.swing.JPasswordField();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jPasswordField3 = new javax.swing.JPasswordField();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextArea1 = new javax.swing.JTextArea();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jPasswordField1 = new javax.swing.JPasswordField();
        jPasswordField2 = new javax.swing.JPasswordField();
        jButton3 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new java.awt.CardLayout());

        jPanel5.setBackground(new java.awt.Color(102, 204, 255));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel15.setText("Forget Password");
        jPanel5.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 40, -1, -1));
        jPanel5.add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 130, 170, 30));
        jPanel5.add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 200, 170, 30));
        jPanel5.add(jPasswordField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 270, 170, 30));
        jPanel5.add(jPasswordField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 340, 170, 30));

        jButton7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton7.setText("Change Password");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 430, -1, -1));

        jButton8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton8.setText("Back");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel5.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 580, -1, -1));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel16.setText("Enter Company Id");
        jPanel5.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 130, 170, 30));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel17.setText("Enter GST No");
        jPanel5.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 200, 130, 30));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel18.setText("Enter New Password");
        jPanel5.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 270, 190, 30));

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel19.setText("Confirm New Password");
        jPanel5.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 340, 220, 30));

        jPanel1.add(jPanel5, "forget");

        jPanel4.setBackground(new java.awt.Color(102, 204, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel12.setText("Login");
        jPanel4.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 20, 70, 50));
        jPanel4.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 180, 180, 30));
        jPanel4.add(jPasswordField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 260, 180, 30));

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton4.setText("Forget Password");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 340, -1, -1));

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton5.setText("Login");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 390, -1, -1));

        jButton6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton6.setText("Back");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 580, -1, -1));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel13.setText("Comapny ID");
        jPanel4.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 180, 140, 30));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel14.setText("Password");
        jPanel4.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 260, 130, 30));

        jPanel1.add(jPanel4, "login");

        jPanel3.setBackground(new java.awt.Color(102, 204, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setText("Registration Form");
        jPanel3.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 20, 240, 40));
        jPanel3.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 100, 200, 30));
        jPanel3.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 150, 200, 30));
        jPanel3.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 200, 200, 30));
        jPanel3.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 250, 200, 30));
        jPanel3.add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 380, 200, 30));

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jPanel3.add(jTextArea1, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 300, 210, 60));

        jRadioButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jRadioButton1.setText("Electricity Bill");
        jPanel3.add(jRadioButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 430, -1, -1));

        jRadioButton2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jRadioButton2.setText("Shop Bill");
        jPanel3.add(jRadioButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 430, 90, -1));

        jRadioButton3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jRadioButton3.setText("Gas Bill");
        jPanel3.add(jRadioButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 430, 90, -1));

        jRadioButton4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jRadioButton4.setText("Telephone Bill");
        jPanel3.add(jRadioButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 430, 120, -1));
        jPanel3.add(jPasswordField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 470, 200, 30));
        jPanel3.add(jPasswordField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 510, 200, 30));

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jButton3.setText("Register");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 570, 110, 30));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Company Id");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 100, 220, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Address");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 300, 220, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("GST No");
        jPanel3.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 380, 220, 30));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setText("Company Name");
        jPanel3.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 150, 220, 30));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel6.setText("Registration Id");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 200, 220, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel7.setText("Contact No");
        jPanel3.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 250, 220, 30));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel8.setText("Bill Type");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 430, 220, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel9.setText("New Password");
        jPanel3.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 470, 220, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setText("Confirm New Password");
        jPanel3.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 510, 220, 30));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/register.gif"))); // NOI18N
        jLabel11.setText("jLabel11");
        jPanel3.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 230, 160));

        jPanel1.add(jPanel3, "register");

        jPanel2.setBackground(new java.awt.Color(102, 204, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton1.setText("Register Company");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 210, 210, 50));

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton2.setText("Sign In");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 360, 210, 50));

        jPanel1.add(jPanel2, "home");

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1065, 615));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    cl.show(jPanel1, "register");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        cl.show(jPanel1, "login");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
SessionFactory sf=ConnectionFactory.emergencyconnection();
           Session session=sf.openSession();
           Transaction tx=session.beginTransaction();     
           String str=jTextField1.getText();
           String str1=jTextField2.getText();
           String str2=jTextField3.getText();
           String str3=jTextField4.getText();
           String str4=jTextArea1.getText();
           String str5=jTextField5.getText();
           String str6="", str7="",str8="",str9="";
          if(jRadioButton1.isSelected()==true) 
          {
           str6=jRadioButton1.getActionCommand()+",";
          }
          if(jRadioButton2.isSelected()==true)
          {
            str7=jRadioButton2.getText()+",";
          }
          if(jRadioButton3.isSelected()==true)
          {
           str8=jRadioButton3.getText()+",";
          }
          if(jRadioButton4.isSelected()==true)
          {
            str9=jRadioButton4.getText()+",";
          }
           char ch[]=jPasswordField1.getPassword();
           String str10=new String(ch);
           char ch1[]=jPasswordField2.getPassword();
           String str11=new String(ch1);
           String str12=str6+str7+str8+str9;
           int length=str12.length();
           char ch2[]=new char[length];
           char ch3[]=new char[length-1];
           for(int i=0;i<length;i++)
           {
               ch2[i]=str12.charAt(i);
           }
           for(int j=0;j<length-1;j++)
           {
               ch3[j]=ch2[j];
           }
           String str13=new String(ch3);
           if(str10.equals(str11))
           {
               Company company=new Company(str, str1, str2, str3, str4, str5, str13, str10);
               session.save(company);
               tx.commit();
               cl.show(jPanel1, "login");
           }
           else
           {
               JOptionPane.showMessageDialog(this, "new password and confirm password doesnot match");
           }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        cl.show(jPanel1, "home");
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
      SessionFactory sf=ConnectionFactory.emergencyconnection();
           Session session=sf.openSession();
           
           companyId=jTextField6.getText();
           char ch1[]=jPasswordField3.getPassword();
           String str1=new String(ch1);
           Criteria ct=session.createCriteria(Company.class);
           ct.add(Restrictions.and(Restrictions.eq("companyId",companyId),Restrictions.eq("password", str1)));
           List<Company> list=ct.list();
           if(list.isEmpty())
           {
               JOptionPane.showMessageDialog(this, "Invalid Id/Password");
           }
           else
           {
               Home home=new Home();
               home.setVisible(true);
               dispose();
           }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
cl.show(jPanel1, "forget");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        cl.show(jPanel1, "login");
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
     SessionFactory sf=ConnectionFactory.emergencyconnection();
           Session session=sf.openSession();
           Transaction tx=session.beginTransaction();     
           String str=jTextField7.getText();
           String str1=jTextField8.getText();
           char ch[]=jPasswordField4.getPassword();
           String str2=new String(ch);
           char ch1[]=jPasswordField5.getPassword();
           String str3=new String(ch1);
           Criteria ct=session.createCriteria(Company.class);
            ct.add(Restrictions.and(Restrictions.eq("companyId", str),Restrictions.eq("gstNo", str1)));
           List<Company> list=ct.list();
           if(list.isEmpty())
           {
               JOptionPane.showMessageDialog(this, "Invalid company Id/GST No");
           }
           else
           {
               if(str2.equals(str3))
               {
                   Company company=(Company)session.get(Company.class, str);
                   company.setPassword(str3);
                   session.update(company);
                   tx.commit();
                    JOptionPane.showMessageDialog(this, "Password Changed");
                 cl.show(jPanel1, "login");
               }
               else
               {
                   JOptionPane.showMessageDialog(this, "new Password and Confirm new Password");
               }
           }
    }//GEN-LAST:event_jButton7ActionPerformed

    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JPasswordField jPasswordField3;
    private javax.swing.JPasswordField jPasswordField4;
    private javax.swing.JPasswordField jPasswordField5;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    // End of variables declaration//GEN-END:variables
}
