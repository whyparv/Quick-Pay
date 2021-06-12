
import java.awt.CardLayout;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import one.connect.ConnectionFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.quick.Company;
import org.quick.ElectricityBill;
import org.quick.GasBill;
import org.quick.Product;
import org.quick.ShopBill;
import org.quick.TelephoneBill;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ACER
 */
public class Home extends javax.swing.JFrame implements Runnable {
    CardLayout cl;
    int q=0,w=0,e=0,r=0,t=0,m=1,esum=0,ssum=0,tsum=0,gsum=0;
    String array[]={""};
    DefaultTableModel dtm,dtm1;
    public void  view()
{
         SessionFactory sf=ConnectionFactory.emergencyconnection();
         dtm1=(DefaultTableModel)jTable2.getModel();
        Session session=sf.openSession();
    Criteria ct=session.createCriteria(Product.class);
    ct.add(Restrictions.eq("companyId", Login.companyId ));
    List<Product> list=ct.list();
    for(Product pr:list)
    {
        Object obj[]={pr.getProductId(),pr.getProductName(),pr.getProductPrice(),pr.getProductQuantity(),pr.getWarranty()};
        dtm1.addRow(obj);
    }
}
    public Home() {
        String str1="",comapany="",register="",gst="";
    

        initComponents();
        cl=(CardLayout)jPanel1.getLayout();
        cl.show(jPanel1, "dashboard");
        SessionFactory sf=ConnectionFactory.emergencyconnection();
           Session session=sf.openSession();
           Criteria ct=session.createCriteria(Company.class);
           ct.add(Restrictions.eq("companyId",Login.companyId));
           List<Company> list=ct.list();
          for(Company com: list)
          {
            String str= com.getBillType();
            comapany=com.getCompanyName();
            register=com.getRegistrationId();
            gst=com.getGstNo();
             array = str.split(",", 10);
          for(int i=0;i<array.length;i++)
          {
              jComboBox1.addItem(array[i]);
              jComboBox4.addItem(array[i]);
              jComboBox7.addItem(array[i]);
              if(array[i].equals("Shop Bill"))
              {
                  str1=array[i];
              }
          }
           if(str1.equals("Shop Bill"))
              {
                  jComboBox2.setVisible(true);
                  jLabel21.setVisible(true);
                  jLabel25.setVisible(true);

              }
              else
              {
                  jComboBox2.setVisible(false);
                  jLabel21.setVisible(false);
                  jLabel25.setVisible(false);
              }
          }
          jLabel15.setText(comapany);
          jLabel16.setText(register);
          jLabel17.setText(gst);
          
          for(int i=0;i<array.length;i++)
          {
              if(array[i].equals("Electricity Bill"))
              {
                  Criteria ct1=session.createCriteria(ElectricityBill.class);
                  ct1.add(Restrictions.eq("companyId", Login.companyId));
                  List<ElectricityBill> list1=ct1.list();
                  for(ElectricityBill a:list1)
                  {
                      q++;
                      esum=esum+Integer.parseInt(a.getTotal());
                  }
              }
              if(array[i].equals("Shop Bill"))
              {
                  Criteria ct1=session.createCriteria(ShopBill.class);
                  ct1.add(Restrictions.eq("companyId", Login.companyId));
                  List<ShopBill> list1=ct1.list();
                  for(ShopBill a:list1)
                  {
                      w++;
                      ssum=ssum+Integer.parseInt(a.getTotal());
                  }
              }
              if(array[i].equals("Gas Bill"))
              {
                  Criteria ct1=session.createCriteria(GasBill.class);
                  ct1.add(Restrictions.eq("companyId", Login.companyId));
                  List<GasBill> list1=ct1.list();
                  for(GasBill a:list1)
                  {
                      e++;
                      gsum=gsum+Integer.parseInt(a.getTotal());
                  }
              }
              if(array[i].equals("Telephone Bill"))
              {
                  Criteria ct1=session.createCriteria(TelephoneBill.class);
                  ct1.add(Restrictions.eq("companyId", Login.companyId));
                  List<TelephoneBill> list1=ct1.list();
                  for(TelephoneBill a:list1)
                  {
                      r++;
                      tsum=tsum+Integer.parseInt(a.getTotal());
                  }
              }
          }
          
          esum=esum+ssum+gsum+tsum;
          Criteria ct1=session.createCriteria(Product.class);
          ct1.add(Restrictions.eq("companyId", Login.companyId));
                  List<Product> list1=ct1.list();
                  for(Product a:list1)
                  {
                      t++;
                   }
          jLabel22.setText(Integer.toString(q+w+e+r));
          jLabel24.setText(Integer.toString(esum));
          jLabel25.setText(Integer.toString(t));
          Thread th=new Thread(this);
          th.start();
    }
    @Override
    public void run()
{       
    for(int i=0;i<array.length;i++)
            {
         if(array[i].equals("Electricity Bill"))
         {
                 try {
            jLabel19.setText("Total number of Electricity Bills");
            jLabel23.setText(Integer.toString(q));
            Thread.sleep(3000);
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
         }
          if(array[i].equals("Shop Bill"))
         {
                 try {
            jLabel19.setText("Total number of Shop Bills ");
            jLabel23.setText(Integer.toString(w));
            Thread.sleep(3000);
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
         }
           if(array[i].equals("Gas Bill"))
         {
                 try {
            jLabel19.setText("Total number of Gas Bills");
            jLabel23.setText(Integer.toString(e));
            Thread.sleep(3000);
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
         }
            if(array[i].equals("Telephone Bill"))
         {
                 try {
            jLabel19.setText("Total number of Telephone Bills");
            jLabel23.setText(Integer.toString(r));
            Thread.sleep(3000);
            
        } catch (InterruptedException ex) {
            Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
        }
         }
            }
    run();
}
    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jLabel162 = new javax.swing.JLabel();
        jLabel163 = new javax.swing.JLabel();
        jComboBox7 = new javax.swing.JComboBox<>();
        jLabel164 = new javax.swing.JLabel();
        jTextField40 = new javax.swing.JTextField();
        jButton18 = new javax.swing.JButton();
        jPanel17 = new javax.swing.JPanel();
        jLabel158 = new javax.swing.JLabel();
        jLabel159 = new javax.swing.JLabel();
        jLabel160 = new javax.swing.JLabel();
        jLabel161 = new javax.swing.JLabel();
        jPasswordField1 = new javax.swing.JPasswordField();
        jPasswordField2 = new javax.swing.JPasswordField();
        jPasswordField3 = new javax.swing.JPasswordField();
        jButton17 = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        jLabel154 = new javax.swing.JLabel();
        jLabel155 = new javax.swing.JLabel();
        jTextField39 = new javax.swing.JTextField();
        jComboBox6 = new javax.swing.JComboBox<>();
        jLabel156 = new javax.swing.JLabel();
        jLabel157 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        jButton16 = new javax.swing.JButton();
        jPanel15 = new javax.swing.JPanel();
        jLabel150 = new javax.swing.JLabel();
        jLabel151 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jLabel152 = new javax.swing.JLabel();
        jLabel153 = new javax.swing.JLabel();
        jTextField37 = new javax.swing.JTextField();
        jTextField38 = new javax.swing.JTextField();
        jButton15 = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jLabel148 = new javax.swing.JLabel();
        jLabel149 = new javax.swing.JLabel();
        jTextField36 = new javax.swing.JTextField();
        jButton14 = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jLabel147 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        jLabel109 = new javax.swing.JLabel();
        jLabel142 = new javax.swing.JLabel();
        jLabel143 = new javax.swing.JLabel();
        jLabel144 = new javax.swing.JLabel();
        jLabel145 = new javax.swing.JLabel();
        jLabel146 = new javax.swing.JLabel();
        jTextField16 = new javax.swing.JTextField();
        jTextField27 = new javax.swing.JTextField();
        jTextField33 = new javax.swing.JTextField();
        jTextField34 = new javax.swing.JTextField();
        jTextField35 = new javax.swing.JTextField();
        jButton13 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jLabel114 = new javax.swing.JLabel();
        jTextField23 = new javax.swing.JTextField();
        jTextField26 = new javax.swing.JTextField();
        jLabel115 = new javax.swing.JLabel();
        jTextField28 = new javax.swing.JTextField();
        jLabel122 = new javax.swing.JLabel();
        jLabel123 = new javax.swing.JLabel();
        jLabel124 = new javax.swing.JLabel();
        jTextField29 = new javax.swing.JTextField();
        jButton11 = new javax.swing.JButton();
        jLabel125 = new javax.swing.JLabel();
        jLabel127 = new javax.swing.JLabel();
        jTextField31 = new javax.swing.JTextField();
        jLabel128 = new javax.swing.JLabel();
        jTextField32 = new javax.swing.JTextField();
        jLabel141 = new javax.swing.JLabel();
        jTextField30 = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jLabel111 = new javax.swing.JLabel();
        jTextField19 = new javax.swing.JTextField();
        jTextField20 = new javax.swing.JTextField();
        jLabel112 = new javax.swing.JLabel();
        jLabel113 = new javax.swing.JLabel();
        jTextField21 = new javax.swing.JTextField();
        jTextField22 = new javax.swing.JTextField();
        jLabel116 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        jLabel117 = new javax.swing.JLabel();
        jLabel118 = new javax.swing.JLabel();
        jLabel119 = new javax.swing.JLabel();
        jTextField24 = new javax.swing.JTextField();
        jTextField25 = new javax.swing.JTextField();
        jLabel120 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jLabel100 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jLabel101 = new javax.swing.JLabel();
        jLabel102 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        jLabel103 = new javax.swing.JLabel();
        jLabel104 = new javax.swing.JLabel();
        jLabel105 = new javax.swing.JLabel();
        jTextField14 = new javax.swing.JTextField();
        jButton9 = new javax.swing.JButton();
        jLabel106 = new javax.swing.JLabel();
        jLabel107 = new javax.swing.JLabel();
        jLabel108 = new javax.swing.JLabel();
        jTextField15 = new javax.swing.JTextField();
        jTextField17 = new javax.swing.JTextField();
        jLabel110 = new javax.swing.JLabel();
        jTextField18 = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel90 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        jLabel96 = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        jLabel98 = new javax.swing.JLabel();
        jLabel99 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel75 = new javax.swing.JLabel();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel60 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel121 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel126 = new javax.swing.JLabel();
        jLabel129 = new javax.swing.JLabel();
        jLabel130 = new javax.swing.JLabel();
        jLabel131 = new javax.swing.JLabel();
        jLabel132 = new javax.swing.JLabel();
        jLabel133 = new javax.swing.JLabel();
        jLabel134 = new javax.swing.JLabel();
        jLabel135 = new javax.swing.JLabel();
        jLabel136 = new javax.swing.JLabel();
        jLabel137 = new javax.swing.JLabel();
        jLabel138 = new javax.swing.JLabel();
        jLabel139 = new javax.swing.JLabel();
        jLabel140 = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jComboBox4 = new javax.swing.JComboBox<>();
        jLabel27 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(102, 204, 255));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel2.setText("Quick Pay");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 0, 160, 40));

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jButton3.setText("Logout");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 730, 150, 30));

        jComboBox1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Generate Bill" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 280, 150, 30));

        jComboBox2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Product", "Add New Product", "Delete Product", "View Product", "Update Product" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 350, 150, 30));

        jComboBox3.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Settings", "Update Profile", "Change Password", "Delete Bill" }));
        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 670, 150, 30));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jButton1.setText("Dashboard");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, 150, 30));

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jButton2.setText("View Invoice");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, 150, 30));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/qw.gif"))); // NOI18N
        jLabel11.setText("jLabel11");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 430, 180, 180));

        jLabel5.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        jLabel5.setText("NPL Infotech");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 10, 90, -1));

        jLabel8.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel8.setText("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 1200, 10));

        jLabel6.setFont(new java.awt.Font("Dialog", 2, 14)); // NOI18N
        jLabel6.setText("Contact No : 9346268080");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 30, -1, -1));

        jLabel10.setBackground(new java.awt.Color(0, 51, 153));
        jLabel10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 3));
        jLabel10.setOpaque(true);
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 270, 720));

        jLabel9.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel9.setText("Payments Made Easy");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, 180, -1));

        jLabel7.setFont(new java.awt.Font("Dialog", 2, 14)); // NOI18N
        jLabel7.setText("E-Mail : NPL.info@ Gmail.com");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 50, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 2, 14)); // NOI18N
        jLabel4.setText("Created and Designed  By-");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 10, 180, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/payyy.gif"))); // NOI18N
        jLabel3.setText("jLabel3");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 70, 50));

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new java.awt.CardLayout());

        jLabel162.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel162.setText("Delete Bill");

        jLabel163.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel163.setText("Enter Bill Tpe");

        jComboBox7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Bill Type" }));

        jLabel164.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel164.setText("Enter Bill No :");

        jButton18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton18.setText("Delete");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addContainerGap(272, Short.MAX_VALUE)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                        .addComponent(jLabel162, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(350, 350, 350))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel164, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel163, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(105, 105, 105)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField40)
                            .addComponent(jComboBox7, 0, 177, Short.MAX_VALUE))
                        .addGap(202, 202, 202))))
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(420, 420, 420)
                .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel162, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel163, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(114, 114, 114)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField40, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel164, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(125, 125, 125)
                .addComponent(jButton18)
                .addContainerGap(250, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel18, "dbill");

        jLabel158.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel158.setText("Change Password");

        jLabel159.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel159.setText("Old Password");

        jLabel160.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel160.setText("New Password");

        jLabel161.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel161.setText("Confirm New Password");

        jButton17.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton17.setText("Change ");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addGap(269, 269, 269)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel161, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel160, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel159, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE)
                    .addComponent(jPasswordField2)
                    .addComponent(jPasswordField3))
                .addGap(246, 246, 246))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel17Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel158)
                .addGap(312, 312, 312))
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(399, 399, 399)
                .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addComponent(jLabel158, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 82, Short.MAX_VALUE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel159, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel160, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPasswordField2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel161, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPasswordField3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(100, 100, 100)
                .addComponent(jButton17)
                .addGap(260, 260, 260))
        );

        jPanel1.add(jPanel17, "password");

        jPanel16.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel154.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel154.setText("Update Profile");
        jPanel16.add(jLabel154, new org.netbeans.lib.awtextra.AbsoluteConstraints(342, 12, 226, 51));

        jLabel155.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel155.setText("What Do Want To Update :");
        jPanel16.add(jLabel155, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 100, 230, 29));
        jPanel16.add(jTextField39, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 190, 170, 32));

        jComboBox6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Update", "Name", "Registration No", "Contact No", "Address", "GST No", "Bill Type" }));
        jComboBox6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox6ActionPerformed(evt);
            }
        });
        jPanel16.add(jComboBox6, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 100, 160, 29));

        jLabel156.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel156.setText("jLabel156");
        jPanel16.add(jLabel156, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 190, 208, 32));

        jLabel157.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel157.setText("Choose Bill type");
        jPanel16.add(jLabel157, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 270, 208, 34));

        jRadioButton1.setText("Electricity Bill");
        jPanel16.add(jRadioButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 270, -1, -1));

        jRadioButton2.setText("Shop Bill");
        jPanel16.add(jRadioButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 270, -1, -1));

        jRadioButton3.setText("Gas Bill");
        jPanel16.add(jRadioButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 270, -1, -1));

        jRadioButton4.setText("Telephone Bill");
        jPanel16.add(jRadioButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 270, -1, -1));

        jButton16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton16.setText("Update");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });
        jPanel16.add(jButton16, new org.netbeans.lib.awtextra.AbsoluteConstraints(421, 410, 140, 33));

        jPanel1.add(jPanel16, "uprofile");

        jLabel150.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel150.setText("Update Product");

        jLabel151.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel151.setText("What do want to Update :");

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Update", "Product Name", "Product Price", "Product Quantity", "Product Warranty" }));
        jComboBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox5ActionPerformed(evt);
            }
        });

        jLabel152.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel152.setText("jLabel152");

        jLabel153.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel153.setText("Enter Product Id :");

        jButton15.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton15.setText("Update ");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(186, 186, 186)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel151, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel152, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel153, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(121, 121, 121)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField37, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jComboBox5, 0, 153, Short.MAX_VALUE)
                        .addComponent(jTextField38)))
                .addContainerGap(241, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel150, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(322, 322, 322))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                        .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(352, 352, 352))))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel150, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(86, 86, 86)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel153, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField37, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel151, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel152, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField38, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(123, 123, 123)
                .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(222, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel15, "uproduct");

        jLabel148.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel148.setText("Delete Product");

        jLabel149.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel149.setText("Enter Product Id :");

        jButton14.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton14.setText("Delete");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel148, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(338, 338, 338))
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(210, 210, 210)
                        .addComponent(jLabel149, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(95, 95, 95)
                        .addComponent(jTextField36, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(389, 389, 389)
                        .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(233, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel148, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(149, 149, 149)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel149, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField36, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(194, 194, 194)
                .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(226, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel14, "dproduct");

        jPanel13.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel147.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel147.setText("Product List");
        jPanel13.add(jLabel147, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, 247, 47));

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product Id", "Name", "Price", "Quantity", "Warranty"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jPanel13.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 930, 620));

        jPanel1.add(jPanel13, "vproduct");

        jPanel12.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel109.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel109.setText("Add New Product");
        jPanel12.add(jLabel109, new org.netbeans.lib.awtextra.AbsoluteConstraints(348, 0, 277, 64));

        jLabel142.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel142.setText("Enter Product Id :");
        jPanel12.add(jLabel142, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 130, 200, 30));

        jLabel143.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel143.setText("Enter Product Name");
        jPanel12.add(jLabel143, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 200, 200, 30));

        jLabel144.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel144.setText("Enter Product price :");
        jPanel12.add(jLabel144, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 270, 200, 30));

        jLabel145.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel145.setText("Enter Product Quantity :");
        jPanel12.add(jLabel145, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 340, 200, 30));

        jLabel146.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel146.setText("Enter Product Warranty :");
        jPanel12.add(jLabel146, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 410, 200, 30));
        jPanel12.add(jTextField16, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 130, 200, 30));
        jPanel12.add(jTextField27, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 200, 200, 30));
        jPanel12.add(jTextField33, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 270, 200, 30));
        jPanel12.add(jTextField34, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 340, 200, 30));

        jTextField35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField35ActionPerformed(evt);
            }
        });
        jPanel12.add(jTextField35, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 410, 200, 30));

        jButton13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton13.setText("ADD");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        jPanel12.add(jButton13, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 530, 110, 40));

        jPanel1.add(jPanel12, "aproduct");

        jPanel11.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel114.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel114.setText("Telephone Id :");
        jPanel11.add(jLabel114, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 280, 200, 30));

        jTextField23.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel11.add(jTextField23, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 150, 200, 30));

        jTextField26.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel11.add(jTextField26, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 90, 200, 30));

        jLabel115.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bill (2).gif"))); // NOI18N
        jLabel115.setText("jLabel91");
        jPanel11.add(jLabel115, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, 175, -1));

        jTextField28.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel11.add(jTextField28, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 280, 200, 30));

        jLabel122.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel122.setText("Plan :");
        jPanel11.add(jLabel122, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 340, 200, 30));

        jLabel123.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel123.setText("Plan Duration (Months) :");
        jPanel11.add(jLabel123, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 400, 200, 30));

        jLabel124.setFont(new java.awt.Font("Tahoma", 1, 28)); // NOI18N
        jLabel124.setText("Telephone Bill");
        jPanel11.add(jLabel124, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 10, 204, 54));

        jTextField29.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel11.add(jTextField29, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 400, 200, 30));

        jButton11.setText("Generate");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanel11.add(jButton11, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 540, 130, -1));

        jLabel125.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel125.setText("Customer Name :");
        jPanel11.add(jLabel125, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 90, 200, 30));

        jLabel127.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel127.setText("Contact Number :");
        jPanel11.add(jLabel127, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 150, 200, 30));

        jTextField31.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel11.add(jTextField31, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 210, 200, 30));

        jLabel128.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel128.setText("Address :");
        jPanel11.add(jLabel128, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 210, 200, 30));

        jTextField32.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel11.add(jTextField32, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 340, 200, 30));

        jLabel141.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel141.setText("Bill Date :");
        jPanel11.add(jLabel141, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 460, 200, 30));

        jTextField30.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel11.add(jTextField30, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 460, 200, 30));

        jPanel1.add(jPanel11, "tbill");

        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel111.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel111.setText("AGL No:");
        jPanel10.add(jLabel111, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 300, 200, 30));

        jTextField19.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel10.add(jTextField19, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 160, 200, 30));

        jTextField20.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel10.add(jTextField20, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 90, 200, 30));

        jLabel112.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bill (2).gif"))); // NOI18N
        jLabel112.setText("jLabel91");
        jPanel10.add(jLabel112, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 266, 175, -1));

        jLabel113.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel113.setText("Unit Consumed :");
        jPanel10.add(jLabel113, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 370, 200, 30));

        jTextField21.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel10.add(jTextField21, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 370, 200, 30));

        jTextField22.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel10.add(jTextField22, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 300, 200, 30));

        jLabel116.setFont(new java.awt.Font("Tahoma", 1, 28)); // NOI18N
        jLabel116.setText("Gas Bill");
        jPanel10.add(jLabel116, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 10, 204, 54));

        jButton10.setText("Generate");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel10.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 550, 130, -1));

        jLabel117.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel117.setText("Customer Name :");
        jPanel10.add(jLabel117, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 90, 200, 30));

        jLabel118.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel118.setText("Bill Date :");
        jPanel10.add(jLabel118, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 430, 200, 30));

        jLabel119.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel119.setText("Contact Number :");
        jPanel10.add(jLabel119, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 160, 200, 30));

        jTextField24.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel10.add(jTextField24, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 440, 200, 30));

        jTextField25.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel10.add(jTextField25, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 230, 200, 30));

        jLabel120.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel120.setText("Address :");
        jPanel10.add(jLabel120, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 230, 200, 30));

        jPanel1.add(jPanel10, "gbill");

        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel100.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel100.setText("Product Id :");
        jPanel9.add(jLabel100, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 210, 200, 30));

        jTextField10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel9.add(jTextField10, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 150, 200, 30));

        jTextField11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel9.add(jTextField11, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 90, 200, 30));

        jLabel101.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bill (2).gif"))); // NOI18N
        jLabel101.setText("jLabel91");
        jPanel9.add(jLabel101, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 266, 175, -1));

        jLabel102.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel102.setText("Warranty");
        jPanel9.add(jLabel102, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 400, 200, 30));

        jTextField12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel9.add(jTextField12, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 400, 200, 30));

        jTextField13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel9.add(jTextField13, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 210, 200, 30));

        jLabel103.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel103.setText("Product Quantity");
        jPanel9.add(jLabel103, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 270, 200, 30));

        jLabel104.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel104.setText("Discount (in%) :");
        jPanel9.add(jLabel104, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 330, 200, 30));

        jLabel105.setFont(new java.awt.Font("Tahoma", 1, 28)); // NOI18N
        jLabel105.setText("Shop Bill");
        jPanel9.add(jLabel105, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 10, 204, 54));

        jTextField14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel9.add(jTextField14, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 330, 200, 30));

        jButton9.setText("Generate");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel9.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 590, 130, -1));

        jLabel106.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel106.setText("Customer Name :");
        jPanel9.add(jLabel106, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 90, 200, 30));

        jLabel107.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel107.setText("Tax :");
        jPanel9.add(jLabel107, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 460, 200, 30));

        jLabel108.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel108.setText("Contact Number :");
        jPanel9.add(jLabel108, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 150, 200, 30));

        jTextField15.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel9.add(jTextField15, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 460, 200, 30));

        jTextField17.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel9.add(jTextField17, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 270, 200, 30));

        jLabel110.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel110.setText("Bill Date :");
        jPanel9.add(jLabel110, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 520, 90, 30));
        jPanel9.add(jTextField18, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 520, 200, 30));

        jPanel1.add(jPanel9, "sbill");

        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel90.setFont(new java.awt.Font("Tahoma", 1, 28)); // NOI18N
        jLabel90.setText("Electricity Bill");
        jPanel8.add(jLabel90, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 10, 204, 54));

        jLabel91.setIcon(new javax.swing.ImageIcon(getClass().getResource("/bill (2).gif"))); // NOI18N
        jLabel91.setText("jLabel91");
        jPanel8.add(jLabel91, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 266, 175, -1));

        jLabel92.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel92.setText("Customer Name :");
        jPanel8.add(jLabel92, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 90, 200, 30));

        jLabel93.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel93.setText("Contact Number :");
        jPanel8.add(jLabel93, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 150, 200, 30));

        jLabel94.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel94.setText("Address :");
        jPanel8.add(jLabel94, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 210, 200, 30));

        jLabel95.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel95.setText("IVRS No:");
        jPanel8.add(jLabel95, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 280, 200, 30));

        jLabel96.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel96.setText("Meter Number :");
        jPanel8.add(jLabel96, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 340, 200, 30));

        jLabel97.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel97.setText("Phases :");
        jPanel8.add(jLabel97, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 400, 200, 30));

        jLabel98.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel98.setText("Unit Consumed :");
        jPanel8.add(jLabel98, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 470, 200, 30));

        jLabel99.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel99.setText("Bill Date :");
        jPanel8.add(jLabel99, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 540, 200, 30));

        jButton8.setText("Generate");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel8.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 630, 130, -1));

        jTextField2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel8.add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 90, 200, 30));

        jTextField3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel8.add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 150, 200, 30));

        jTextField4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel8.add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 210, 200, 30));

        jTextField5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel8.add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 280, 200, 30));

        jTextField6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel8.add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 340, 200, 30));

        jTextField7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel8.add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 400, 200, 30));

        jTextField8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel8.add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 470, 200, 30));

        jTextField9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jPanel8.add(jTextField9, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 540, 200, 30));

        jPanel1.add(jPanel8, "ebill");

        jLabel75.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel75.setText("Invoice");

        jLabel76.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel76.setText("jLabel76");

        jLabel77.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel77.setText("jLabel76");

        jLabel78.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel78.setText("jLabel76");

        jLabel79.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel79.setText("jLabel76");

        jLabel80.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel80.setText("jLabel76");

        jLabel81.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel81.setText("jLabel76");

        jLabel82.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel82.setText("jLabel76");

        jLabel83.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel83.setText("jLabel76");

        jLabel84.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel84.setText("jLabel76");

        jLabel85.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel85.setText("jLabel76");

        jLabel86.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel86.setText("Help Line");

        jLabel87.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel87.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel87.setText("jLabel76");

        jLabel88.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel88.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel88.setText("jLabel76");

        jLabel89.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel89.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel89.setText("jLabel76");

        jButton7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton7.setText("Print");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(387, 387, 387)
                        .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel86, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel87, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(160, 160, 160)
                                .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(160, 160, 160)
                                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel82, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel84, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel85, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel89, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton7)
                .addGap(92, 92, 92))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel75)
                .addGap(61, 61, 61)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel77, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel82, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel78, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(70, 70, 70)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel86, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel84, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel87, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel85, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(30, 30, 30)
                .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel89, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7)
                        .addGap(29, 29, 29))))
        );

        jPanel1.add(jPanel7, "tinvoice");

        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel60.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel60.setText("Invoice");
        jPanel6.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 12, 121, 39));

        jLabel59.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel59.setText("jLabel59");
        jPanel6.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, 300, 30));

        jLabel61.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel61.setText("jLabel59");
        jPanel6.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 300, 30));

        jLabel62.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel62.setText("jLabel59");
        jPanel6.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 300, 30));

        jLabel63.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel63.setText("jLabel59");
        jPanel6.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, 300, 30));

        jLabel64.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel64.setText("jLabel59");
        jPanel6.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 100, 300, 30));

        jLabel65.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel65.setText("jLabel59");
        jPanel6.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 150, 300, 30));

        jLabel66.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel66.setText("jLabel59");
        jPanel6.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 200, 300, 30));

        jLabel67.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel67.setText("jLabel59");
        jPanel6.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 250, 300, 30));

        jLabel68.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel68.setText("jLabel59");
        jPanel6.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 390, 300, 30));

        jLabel69.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel69.setText("jLabel59");
        jPanel6.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 440, 300, 30));

        jLabel70.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel70.setText("jLabel59");
        jPanel6.add(jLabel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 490, 300, 30));

        jLabel71.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel71.setText("Help Line");
        jPanel6.add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, 300, 30));

        jLabel72.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel72.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel72.setText("jLabel59");
        jPanel6.add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 460, 300, 30));

        jLabel73.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel73.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel73.setText("jLabel59");
        jPanel6.add(jLabel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 520, 300, 30));

        jLabel74.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel74.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel74.setText("jLabel59");
        jPanel6.add(jLabel74, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 580, 300, 30));

        jButton6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jButton6.setText("Print");
        jPanel6.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 660, -1, -1));

        jPanel1.add(jPanel6, "ginvoice");

        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel121.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel121.setText("Invoice");
        jPanel5.add(jLabel121, new org.netbeans.lib.awtextra.AbsoluteConstraints(401, 0, 126, 52));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product", "Quantity", "Price", "total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel5.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 90, 460, 270));

        jLabel126.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel126.setText("jLabel126");
        jPanel5.add(jLabel126, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 300, 30));

        jLabel129.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel129.setText("jLabel126");
        jPanel5.add(jLabel129, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 300, 30));

        jLabel130.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel130.setText("jLabel126");
        jPanel5.add(jLabel130, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 300, 30));

        jLabel131.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel131.setText("jLabel126");
        jPanel5.add(jLabel131, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 260, 300, 30));

        jLabel132.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel132.setText("jLabel126");
        jPanel5.add(jLabel132, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 300, 30));

        jLabel133.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel133.setText("jLabel126");
        jPanel5.add(jLabel133, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 400, 300, 30));

        jLabel134.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel134.setText("jLabel126");
        jPanel5.add(jLabel134, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 450, 300, 30));

        jLabel135.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel135.setText("jLabel126");
        jPanel5.add(jLabel135, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 500, 300, 30));

        jLabel136.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel136.setText("jLabel126");
        jPanel5.add(jLabel136, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 550, 300, 30));

        jLabel137.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel137.setText("Helpline");
        jPanel5.add(jLabel137, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 450, 300, 30));

        jLabel138.setFont(new java.awt.Font("Dialog", 2, 18)); // NOI18N
        jLabel138.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel138.setText("jLabel126");
        jPanel5.add(jLabel138, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 500, 300, 30));

        jLabel139.setFont(new java.awt.Font("Dialog", 2, 18)); // NOI18N
        jLabel139.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel139.setText("jLabel126");
        jPanel5.add(jLabel139, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 540, 300, 30));

        jLabel140.setFont(new java.awt.Font("Dialog", 2, 18)); // NOI18N
        jLabel140.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel140.setText("jLabel126");
        jPanel5.add(jLabel140, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 580, 300, 30));

        jButton12.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton12.setText("Print");
        jPanel5.add(jButton12, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 660, -1, -1));

        jPanel1.add(jPanel5, "sinvoice");

        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel28.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel28.setText("Invoice");
        jPanel4.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 0, 130, 40));

        jLabel29.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel29.setText("Date :");
        jPanel4.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 60, 30));

        jLabel30.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel30.setText("Bill No :");
        jPanel4.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, 80, 30));

        jLabel31.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel31.setText("Company Id : ");
        jPanel4.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, 175, 30));

        jLabel32.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel32.setText("Customer Name : ");
        jPanel4.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 260, 175, 30));

        jLabel33.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel33.setText("Contact Number : ");
        jPanel4.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 310, 175, 30));

        jLabel34.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel34.setText("Due Date : ");
        jPanel4.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 100, 175, 30));

        jLabel35.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel35.setText("Address");
        jPanel4.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 150, 175, 30));

        jLabel36.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel36.setText("IVRS No :");
        jPanel4.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 200, 175, 30));

        jLabel37.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel37.setText("Meter No :");
        jPanel4.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 260, 175, 30));

        jLabel38.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel38.setText("Phases :");
        jPanel4.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 310, 175, 30));

        jLabel39.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel39.setText("Unit Consumed :");
        jPanel4.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 410, 175, 30));

        jLabel40.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel40.setText("Amount Per Unit :");
        jPanel4.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 460, 175, 30));

        jLabel41.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel41.setText("Sub Total :");
        jPanel4.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 510, 175, 30));

        jLabel42.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel42.setText("Sub Total :");
        jPanel4.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 175, 30));

        jLabel43.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel43.setText("Sub Total :");
        jPanel4.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 160, 175, 30));

        jLabel44.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel44.setText("Sub Total :");
        jPanel4.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 210, 175, 30));

        jLabel45.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel45.setText("Sub Total :");
        jPanel4.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 260, 175, 30));

        jLabel46.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel46.setText("Sub Total :");
        jPanel4.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 310, 175, 30));

        jLabel47.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel47.setText("Sub Total :");
        jPanel4.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 100, 220, 30));

        jLabel48.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel48.setText("Sub Total :");
        jPanel4.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 150, 175, 30));

        jLabel49.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel49.setText("Sub Total :");
        jPanel4.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 200, 175, 30));

        jLabel50.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel50.setText("Sub Total :");
        jPanel4.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 260, 175, 30));

        jLabel51.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel51.setText("Sub Total :");
        jPanel4.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 310, 175, 30));

        jLabel52.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel52.setText("Sub Total :");
        jPanel4.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 410, 175, 30));

        jLabel53.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel53.setText("Sub Total :");
        jPanel4.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 460, 175, 30));

        jLabel54.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel54.setText("Sub Total :");
        jPanel4.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 510, 175, 30));

        jButton5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton5.setText("Print");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel4.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 670, -1, -1));

        jLabel55.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel55.setText("Help Line");
        jPanel4.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 410, 150, 30));

        jLabel56.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel56.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel56.setText("jLabel56");
        jPanel4.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 460, 320, 40));

        jLabel57.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel57.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel57.setText("jLabel56");
        jPanel4.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 520, 320, 40));

        jLabel58.setFont(new java.awt.Font("Tahoma", 2, 18)); // NOI18N
        jLabel58.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel58.setText("jLabel56");
        jPanel4.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 580, 320, 40));

        jPanel1.add(jPanel4, "einvoice");

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 30)); // NOI18N
        jLabel26.setText("View Invoice");
        jLabel26.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jComboBox4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jComboBox4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Invoice Type" }));

        jLabel27.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel27.setText("Enter Bill No :");

        jTextField1.setText("jTextField1");

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton4.setText("View");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(377, 377, 377)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                            .addComponent(jComboBox4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(289, 289, 289)
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(247, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(371, 371, 371))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(98, 98, 98)
                .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(102, 102, 102)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(117, 117, 117)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(182, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3, "invoice");

        jPanel2.setOpaque(false);
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setText("Company Name");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 40, 220, 40));

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel13.setText("Registration ID");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 80, 220, 40));

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel14.setText("GST NO : ");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 120, 220, 40));

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel15.setText("jLabel12");
        jPanel2.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 40, 220, 40));

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel16.setText("jLabel12");
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 80, 220, 40));

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel17.setText("jLabel12");
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 120, 220, 40));

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel18.setText("Total Number of bills");
        jPanel2.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 300, 220, 40));

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel19.setText("jLabel18");
        jPanel2.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 380, 300, 40));

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel20.setText("Total Amount Recieved");
        jPanel2.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 460, 220, 40));

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel21.setText("Total Number of Product ");
        jPanel2.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 550, 260, 40));

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel22.setText("jLabel18");
        jPanel2.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 300, 220, 40));

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel23.setText("jLabel18");
        jPanel2.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 380, 220, 40));

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel24.setText("jLabel18");
        jPanel2.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 460, 220, 40));

        jLabel25.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel25.setText("jLabel18");
        jPanel2.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 550, 220, 40));

        jPanel1.add(jPanel2, "dashboard");

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 80, 930, 720));

        jLabel1.setBackground(new java.awt.Color(102, 204, 255));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setOpaque(true);
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1200, 800));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
     String str=(String)jComboBox2.getSelectedItem();
     if(str.equals("Add New Product"))
     {
         cl.show(jPanel1, "aproduct");
     }
     if(str.equals("View Product"))
     {
         if(m>1)
         {
             dtm1.setRowCount(0);
         } 
            
         view();
         m++;
         cl.show(jPanel1, "vproduct");
     }
     if(str.equals("Delete Product"))
     {
         cl.show(jPanel1, "dproduct");
     }
     if(str.equals("Update Product"))
     {
         jLabel152.setVisible(false);
         jTextField38.setVisible(false);
         cl.show(jPanel1, "uproduct");
     }
     if(str.equals("Product"))
     {
    JOptionPane.showMessageDialog(this, "No input");
     }
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
cl.show(jPanel1, "dashboard");
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
      cl.show(jPanel1, "invoice");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
       String str=(String)jComboBox4.getSelectedItem();
       String invoice=jTextField1.getText();
         SessionFactory sf=ConnectionFactory.emergencyconnection();
           Session session=sf.openSession();
          Criteria ct1=session.createCriteria(Company.class);
          ct1.add(Restrictions.eq("companyId", Login.companyId));
          List<Company> list1=ct1.list();
          String contact="",address="";
          for(Company com:list1)
          {
              contact=com.getContactNo();
              address=com.getAddress();
          }
       if(str.equals("Electricity Bill"))
       {
           Criteria ct=session.createCriteria(ElectricityBill.class);
           ct.add(Restrictions.and(Restrictions.eq("companyId", Login.companyId), Restrictions.eq("serialNo", Integer.parseInt(invoice))));
           List<ElectricityBill> list=ct.list();
           if(list.isEmpty())
           {
               JOptionPane.showMessageDialog(this, "No Invoice Found");
           }
           else
           {
               for(ElectricityBill eb:list)
               {
                   String str1=eb.getDate();
                   String str2=eb.getCustomerName();
                   String str3=eb.getContactNo();
                   String str4=eb.getDueDate();
                   String str5=eb.getAddress();
                   String str6=eb.getIvrsNo();
                   String str7=eb.getMeterNo();
                   String str8=eb.getPhases();
                   String str9=eb.getUnitConsumed();
                   String str10=eb.getPricePerUnit();
                   String str11=eb.getTotal();
                   jLabel42.setText(str1);
                   jLabel43.setText(invoice);
                   jLabel44.setText(Login.companyId);
                   jLabel45.setText(str2);
                   jLabel46.setText(str3);
                   jLabel47.setText(str4);
                   jLabel48.setText(str5);
                   jLabel49.setText(str6);
                   jLabel50.setText(str7);
                   jLabel51.setText(str8);
                   jLabel52.setText(str9);
                   jLabel53.setText("Rs"+str10+"/-");
                   jLabel54.setText("Rs"+str11+"/-");
                   jLabel56.setText("Contact No :"+contact);
                   jLabel57.setText("E-Mail :"+Login.companyId+"@gmail.com");
                   jLabel58.setText("Address :"+address);
                   
               }
               cl.show(jPanel1, "einvoice");
           }
       }
        if(str.equals("Shop Bill"))
       {
           Criteria ct=session.createCriteria(ShopBill.class);
           ct.add(Restrictions.and(Restrictions.eq("companyId", Login.companyId), Restrictions.eq("serialNo", Integer.parseInt(invoice))));
           List<ShopBill> list=ct.list();
           if(list.isEmpty())
           {
               JOptionPane.showMessageDialog(this, "No Invoice Found");
           }
           else
           {
               for(ShopBill eb:list)
               {
              String pId=eb.getProductName();
              String quantit=eb.getProductQuantity();
              String date=eb.getDate();
              String cust=eb.getCustomerName();
              String warranty=eb.getWarranty();
              String contact1=eb.getContactNo();
              String tax=eb.getTax();
              String discount=eb.getProductPrice();
               String []array1 = pId.split(",", 10);
         String []array2 = quantit.split(",", 10);
        
          int total1=0,t1=0,d1=0;
        for(int i=0;i<array1.length;i++)
        {
            Criteria ct4=session.createCriteria(Product.class);
            ct4.add(Restrictions.and(Restrictions.eq("productId", array1[i]), Restrictions.eq("companyId", Login.companyId)));
            List<Product> list4=ct4.list();
            for(Product p:list4)
            {
                total1=total1+(Integer.parseInt(array2[i])*Integer.parseInt(p.getProductPrice()));
                
            }
        }
        t1=(total1*Integer.parseInt(tax))/100;
                d1=(total1*Integer.parseInt(discount))/100;
              total1=total1+t1-d1;
       
            String name="",price="",total2="";
          for(int i=0;i<array1.length;i++)
          {
        
             Criteria ct3=session.createCriteria(Product.class);
              ct3.add(Restrictions.and(Restrictions.eq("productId", array1[i]), Restrictions.eq("companyId", Login.companyId)));
            List<Product> list3=ct3.list();
          
            for(Product p:list3)
            {
                name=name+p.getProductName()+",";
                price=price+p.getProductPrice()+",";
                total2=total2+Integer.toString(Integer.parseInt(array2[i])*Integer.parseInt(p.getProductPrice()))+",";
            }
          }
          dtm=(DefaultTableModel)jTable1.getModel();
           quantit=quantit+",";
             String []array6 = quantit.split(",", 10);
          String []array3 = name.split(",", 10);
            String []array4 = price.split(",", 10);
             String []array5 = total2.split(",", 10);
             for(int i=0;i<array3.length;i++)
             {
                 Object obj[]={array3[i],array6[i],array4[i],array5[i]};
                 dtm.addRow(obj);
             }
                   jLabel126.setText("Date :"+date);
                   jLabel129.setText("Bill No :"+invoice);
                   jLabel130.setText("Company Id :"+Login.companyId);
                   jLabel131.setText("Customer Name :"+cust);
                   jLabel132.setText("Contact No :"+contact1); 
                   jLabel133.setText("Warranty :"+warranty); 
                   jLabel134.setText("Tax :"+Integer.toString(t1)); 
                   jLabel135.setText("Discount :"+Integer.toString(d1)); 
                   jLabel136.setText("Sub Total :"+Integer.toString(total1)); 
                   
                   jLabel138.setText("Contact No :"+contact);
                   jLabel139.setText("E-Mail :"+Login.companyId+"@gmail.com");
                   jLabel140.setText("Address :"+address);
                   
               }
               cl.show(jPanel1, "sinvoice");
           }
      }
         if(str.equals("Gas Bill"))
       {
           Criteria ct=session.createCriteria(GasBill.class);
           ct.add(Restrictions.and(Restrictions.eq("companyId", Login.companyId), Restrictions.eq("serialNo", Integer.parseInt(invoice))));
           List<GasBill> list=ct.list();
           if(list.isEmpty())
           {
               JOptionPane.showMessageDialog(this, "No Invoice Found");
           }
           else
           {
               for(GasBill eb:list)
               {
                   String str1=eb.getDate();
                   String str2=eb.getCustomerName();
                   String str3=eb.getContactNo();
                   String str4=eb.getDueDate();
                   String str5=eb.getAddress();
                   String str6=eb.getAglNo();
                   String str9=eb.getUnitConsumed();
                   String str10=eb.getPricePerUnit();
                   String str11=eb.getTotal();
                   jLabel59.setText("Date :"+str1);
                   jLabel61.setText("Bill No :"+invoice);
                   jLabel62.setText("Company Id :"+Login.companyId);
                   jLabel63.setText("Customer name :"+str2);
                   jLabel64.setText("Due Date :"+str4);
                   jLabel65.setText("Contact No:"+str3);
                   jLabel66.setText("AGL No"+str6);
                   jLabel67.setText("Address :"+str5);
                   jLabel68.setText("Unit Consumed :"+str9);
                   jLabel69.setText("Amount Per Unit :Rs"+str10+"/-");
                   jLabel70.setText("Sub Total :Rs"+str11+"/-");
                   jLabel72.setText("Contact No :"+contact);
                   jLabel73.setText("E-Mail :"+Login.companyId+"@gmail.com");
                   jLabel74.setText("Address :"+address);
                   
               }
               cl.show(jPanel1, "ginvoice");
           }
       }
         if(str.equals("Telephone Bill"))
       {
           Criteria ct=session.createCriteria(TelephoneBill.class);
           ct.add(Restrictions.and(Restrictions.eq("companyId", Login.companyId), Restrictions.eq("serialNo", Integer.parseInt(invoice))));
           List<TelephoneBill> list=ct.list();
           if(list.isEmpty())
           {
               JOptionPane.showMessageDialog(this, "No Invoice Found");
           }
           else
           {
               for(TelephoneBill eb:list)
               {
                   String str1=eb.getDate();
                   String str2=eb.getCustomerName();
                   String str3=eb.getContactNo();
                   String str4=eb.getDueDate();
                   String str5=eb.getAddress();
                   String str6=eb.getTelephoneId();
                   String str7=eb.getPlan();
                   String str11=eb.getTotal();
                   jLabel76.setText("Date :"+str1);
                   jLabel77.setText("Bill No :"+invoice);
                   jLabel78.setText("Company Id :"+Login.companyId);
                   jLabel79.setText("Customer name :"+str2);
                   jLabel80.setText("Due Date :"+str4);
                   jLabel81.setText("Contact No:"+str3);
                   jLabel82.setText("Telephone Id :"+str6);
                   jLabel83.setText("Address :"+str5);
                   jLabel84.setText("Plan :Rs"+str7+"/-");
                  
                   jLabel85.setText("Sub Total :Rs"+str11+"/-");
                   jLabel87.setText("Contact No :"+contact);
                   jLabel88.setText("E-Mail :"+Login.companyId+"@gmail.com");
                   jLabel89.setText("Address :"+address);
                   
               }
               cl.show(jPanel1, "tinvoice");
           }
       }
         if(str.equals("Invoice Type"))
         {
             JOptionPane.showMessageDialog(this, "Please select you Invoice type");
         }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
       PrinterJob pj = PrinterJob.getPrinterJob();

    if (pj.printDialog()) {
        try {pj.print();}
        catch (PrinterException exc) {
            System.out.println(exc);
         }
     }   
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
       String str=(String)jComboBox1.getSelectedItem();
       if(str.equals("Electricity Bill"))
       {
           cl.show(jPanel1, "ebill");
       }
        if(str.equals("Shop Bill"))
       {
           cl.show(jPanel1, "sbill");
       }
         if(str.equals("Gas Bill"))
       {
           cl.show(jPanel1, "gbill");
       }
          if(str.equals("Telephone Bill"))
       {
           cl.show(jPanel1, "tbill");
       } 
          if(str.equals("Generate Bill"))
       {
           JOptionPane.showMessageDialog(this, "Please enter Bill generation type");
       }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
      int serial=0;
        String cust=jTextField2.getText();
        String contact=jTextField3.getText();
        String address=jTextField4.getText();
        String ivrs=jTextField5.getText();
        String meter=jTextField6.getText();
        String phases=jTextField7.getText();
        String units=jTextField8.getText();
        String date=jTextField9.getText();
        
        SessionFactory sf=ConnectionFactory.emergencyconnection();
        Session session=sf.openSession();
        Transaction tx=session.beginTransaction();
        int total=60+(5*Integer.parseInt(units));
        ElectricityBill eb=new ElectricityBill(Login.companyId, cust, contact, address, ivrs, date, "7 Days from Due Date", meter, phases, units, "5", Integer.toString(total), "paid");
        session.save(eb);
        tx.commit();
        Criteria ct=session.createCriteria(ElectricityBill.class);
        ct.add(Restrictions.and(Restrictions.eq("ivrsNo",ivrs), Restrictions.eq("date", date)));
          Criteria ct1=session.createCriteria(Company.class);
          ct1.add(Restrictions.eq("companyId", Login.companyId));
            List<ElectricityBill> list=ct.list();
          String invoice="";
          for(ElectricityBill com:list)
          {
          invoice=Integer.toString(com.getSerialNo());
          }
          List<Company> list1=ct1.list();
          String contact1="",address1="";
          for(Company com:list1)
          {
              contact1=com.getContactNo();
              address1=com.getAddress();
          }
                   jLabel42.setText(date);
                   jLabel43.setText(invoice);
                   jLabel44.setText(Login.companyId);
                   jLabel45.setText(cust);
                   jLabel46.setText(contact);
                   jLabel47.setText(" 7 Days from Bill Date ");
                   jLabel48.setText(address);
                   jLabel49.setText(ivrs);
                   jLabel50.setText(meter);
                   jLabel51.setText(phases);
                   jLabel52.setText(units);
                   jLabel53.setText("Rs"+"5"+"/-");
                   jLabel54.setText("Rs"+Integer.toString(total)+"/-");
                   jLabel56.setText("Contact No :"+contact1);
                   jLabel57.setText("E-Mail :"+Login.companyId+"@gmail.com");
                   jLabel58.setText("Address :"+address1);
                   cl.show(jPanel1, "einvoice");
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
    String cust=jTextField11.getText();
        String contact=jTextField10.getText();
      
        String pId=jTextField13.getText();
        String quantit=jTextField17.getText();
        String discount=jTextField14.getText();
        String warranty=jTextField12.getText();
        String tax=jTextField15.getText();
        String date=jTextField18.getText();
        String []array1 = pId.split(",", 10);
         String []array2 = quantit.split(",", 10);
          SessionFactory sf=ConnectionFactory.emergencyconnection();
        Session session=sf.openSession();
        Transaction tx=session.beginTransaction();
        int total=0,t=0,d=0;
        for(int i=0;i<array1.length;i++)
        {
            Criteria ct=session.createCriteria(Product.class);
            ct.add(Restrictions.and(Restrictions.eq("productId", array1[i]), Restrictions.eq("companyId", Login.companyId)));
            List<Product> list=ct.list();
            for(Product p:list)
            {
                total=total+(Integer.parseInt(array2[i])*Integer.parseInt(p.getProductPrice()));
                
            }
        }
        t=(total*Integer.parseInt(tax))/100;
                d=(total*Integer.parseInt(discount))/100;
              total=total+t-d;
       ShopBill sb= new ShopBill(Login.companyId, cust, date, contact, pId, quantit, discount, warranty, tax, Integer.toString(total), "Paid");
       session.save(sb);
       tx.commit();
       String invoice="";
       Criteria ct1=session.createCriteria(ShopBill.class);
       ct1.add(Restrictions.and(Restrictions.eq("date", date), Restrictions.eq("productName", pId)));
       List<ShopBill> list1=ct1.list();
       for(ShopBill bs:list1)
       {
           invoice=Integer.toString(bs.getSerialNo());
       }
        Criteria ct2=session.createCriteria(Company.class);
          ct2.add(Restrictions.eq("companyId", Login.companyId));
          List<Company> list2=ct2.list();
          String contact1="",address1="";
          for(Company com:list2)
          {
              contact1=com.getContactNo();
              address1=com.getAddress();
          }
            String name="",price="",total1="";
          for(int i=0;i<array1.length;i++)
          {
        
             Criteria ct3=session.createCriteria(Product.class);
              ct3.add(Restrictions.and(Restrictions.eq("productId", array1[i]), Restrictions.eq("companyId", Login.companyId)));
            List<Product> list3=ct3.list();
          
            for(Product p:list3)
            {
                name=name+p.getProductName()+",";
                price=price+p.getProductPrice()+",";
                total1=total1+Integer.toString(Integer.parseInt(array2[i])*Integer.parseInt(p.getProductPrice()))+",";
            }
          }
          dtm=(DefaultTableModel)jTable1.getModel();
           quantit=quantit+",";
             String []array6 = quantit.split(",", 10);
          String []array3 = name.split(",", 10);
            String []array4 = price.split(",", 10);
             String []array5 = total1.split(",", 10);
             for(int i=0;i<array3.length;i++)
             {
                 Object obj[]={array3[i],array6[i],array4[i],array5[i]};
                 dtm.addRow(obj);
             }
                   jLabel126.setText("Date :"+date);
                   jLabel129.setText("Bill No :"+invoice);
                   jLabel130.setText("Company Id :"+Login.companyId);
                   jLabel131.setText("Customer Name :"+cust);
                   jLabel132.setText("Contact No :"+contact); 
                   jLabel133.setText("Warranty :"+warranty); 
                   jLabel134.setText("Tax :"+Integer.toString(t)); 
                   jLabel135.setText("Discount :"+Integer.toString(d)); 
                   jLabel136.setText("Sub Total :"+Integer.toString(total)); 
                   
                   jLabel138.setText("Contact No :"+contact1);
                   jLabel139.setText("E-Mail :"+Login.companyId+"@gmail.com");
                   jLabel140.setText("Address :"+address1);
                   cl.show(jPanel1, "sinvoice");
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        String cust=jTextField20.getText();
        String contact=jTextField19.getText();
        String address=jTextField25.getText();
        String agl=jTextField22.getText();
        String units=jTextField21.getText();
        String date=jTextField24.getText();
        int total=55*Integer.parseInt(units);
          SessionFactory sf=ConnectionFactory.emergencyconnection();
        Session session=sf.openSession();
        Transaction tx=session.beginTransaction();
        GasBill gas=new GasBill(Login.companyId, cust, contact, address, agl, date, "7 Days from Bill date", units,"55", Integer.toString(total),"paid");
        session.save(gas);
        tx.commit();
        String invoice="";
        Criteria ct=session.createCriteria(GasBill.class);
        ct.add(Restrictions.and(Restrictions.eq("aglNo", agl), Restrictions.eq("date", date)));
        List<GasBill> list=ct.list();
        for(GasBill gb:list)
        {
            invoice=Integer.toString(gb.getSerialNo());
        }
        Criteria ct2=session.createCriteria(Company.class);
          ct2.add(Restrictions.eq("companyId", Login.companyId));
          List<Company> list2=ct2.list();
          String contact1="",address1="";
          for(Company com:list2)
          {
              contact1=com.getContactNo();
              address1=com.getAddress();
          }
          jLabel59.setText("Date :"+date);
                   jLabel61.setText("Bill No :"+invoice);
                   jLabel62.setText("Company Id :"+Login.companyId);
                   jLabel63.setText("Customer name :"+cust);
                   jLabel64.setText("Due Date :"+"7 Days from Bill Date");
                   jLabel65.setText("Contact No:"+contact);
                   jLabel66.setText("AGL No"+agl);
                   jLabel67.setText("Address :"+address);
                   jLabel68.setText("Unit Consumed :"+units);
                   jLabel69.setText("Amount Per Unit :Rs"+"55"+"/-");
                   jLabel70.setText("Sub Total :Rs"+Integer.toString(total)+"/-");
                   jLabel72.setText("Contact No :"+contact1);
                   jLabel73.setText("E-Mail :"+Login.companyId+"@gmail.com");
                   jLabel74.setText("Address :"+address1);
                     cl.show(jPanel1, "ginvoice");
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
          String cust=jTextField26.getText();
        String contact=jTextField23.getText();
        String address=jTextField31.getText();
        String telephone=jTextField28.getText();
        String plans=jTextField32.getText();
        String date=jTextField30.getText();
        String month=jTextField29.getText();
        int total=Integer.parseInt(plans)*Integer.parseInt(month);
         SessionFactory sf=ConnectionFactory.emergencyconnection();
        Session session=sf.openSession();
        Transaction tx=session.beginTransaction();
        TelephoneBill tb=new TelephoneBill(Login.companyId, cust, contact, address, telephone, date, "7 days from Bill date", plans,Integer.toString(total), month);
        session.save(tb);
        tx.commit();
         String invoice="";
        Criteria ct=session.createCriteria(TelephoneBill.class);
        ct.add(Restrictions.and(Restrictions.eq("telephoneId",telephone), Restrictions.eq("date", date)));
        List<TelephoneBill> list=ct.list();
        for(TelephoneBill gb:list)
        {
            invoice=Integer.toString(gb.getSerialNo());
        }
        Criteria ct2=session.createCriteria(Company.class);
          ct2.add(Restrictions.eq("companyId", Login.companyId));
          List<Company> list2=ct2.list();
          String contact1="",address1="";
          for(Company com:list2)
          {
              contact1=com.getContactNo();
              address1=com.getAddress();
          }
        jLabel76.setText("Date :"+date);
                   jLabel77.setText("Bill No :"+invoice);
                   jLabel78.setText("Company Id :"+Login.companyId);
                   jLabel79.setText("Customer name :"+cust);
                   jLabel80.setText("Due Date :"+"7 Days form Bill Date");
                   jLabel81.setText("Contact No:"+contact);
                   jLabel82.setText("Telephone Id :"+telephone);
                   jLabel83.setText("Address :"+address);
                   jLabel84.setText("Plan :Rs"+plans+"/-");
                  
                   jLabel85.setText("Sub Total :Rs"+Integer.toString(total)+"/-");
                   jLabel87.setText("Contact No :"+contact1);
                   jLabel88.setText("E-Mail :"+Login.companyId+"@gmail.com");
                   jLabel89.setText("Address :"+address1);
                   cl.show(jPanel1, "tinvoice");
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jTextField35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField35ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField35ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        String pId=jTextField16.getText();
        String pName=jTextField27.getText();
        String pPrice=jTextField33.getText();
        String pQuantity=jTextField34.getText();
        String pWarranty=jTextField35.getText();
        SessionFactory sf=ConnectionFactory.emergencyconnection();
        Session session=sf.openSession();
        Transaction tx=session.beginTransaction();
        Product p=new Product(Login.companyId, pId, pName, pPrice, pQuantity, pWarranty, "Available");
        session.save(p);
        tx.commit();
        JOptionPane.showMessageDialog(this, "Product Added");
        jTextField16.setText("");
        jTextField27.setText("");
        jTextField33.setText("");
        jTextField34.setText("");
        jTextField35.setText("");
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
          SessionFactory sf=ConnectionFactory.emergencyconnection();
        Session session=sf.openSession();
        Transaction tx=session.beginTransaction();
        String str=jTextField36 .getText();
        Criteria ct=session.createCriteria(Product.class);
        ct.add(Restrictions.and(Restrictions.eq("productId", str), Restrictions.eq("companyId", Login.companyId)));
       List<Product> list=ct.list();
       if(list.isEmpty())
       {
           JOptionPane.showMessageDialog(this,"No Product Found");
       }
       else
       {
       int id=0;
        for(Product pro:list)
        {
            id=pro.getSerialNo();
        }
        Product pr=(Product)session.get(Product.class, id);
        session.delete(pr);
        tx.commit();
        JOptionPane.showMessageDialog(this, "Product Deleted");
        jTextField36.setText("");
       }
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
       String  item=(String)jComboBox5.getSelectedItem();
       String str=jTextField37.getText();
       String value=jTextField38.getText();
        SessionFactory sf=ConnectionFactory.emergencyconnection();
        Session session=sf.openSession();
        Transaction tx=session.beginTransaction();
        Criteria ct=session.createCriteria(Product.class);
        ct.add(Restrictions.and(Restrictions.eq("productId", str), Restrictions.eq("companyId", Login.companyId)));
       List<Product> list=ct.list();
       if(list.isEmpty())
       {
           JOptionPane.showMessageDialog(this,"No Product Found");
       }
       else
       {
       int id=0;
        for(Product pro:list)
        {
            id=pro.getSerialNo();
        }
        if(item.equals("Product Name"))
        {
        Product pr=(Product)session.get(Product.class, id);
        pr.setProductName(value);
        session.update(pr);
        tx.commit();
        }
        if(item.equals("Product Price"))
        {
        Product pr=(Product)session.get(Product.class, id);
        pr.setProductPrice(value);
        session.update(pr);
        tx.commit();
        }
        if(item.equals("Product Quantity"))
        {
        Product pr=(Product)session.get(Product.class, id);
        pr.setProductQuantity(value);
        session.update(pr);
        tx.commit();
        }
        if(item.equals("Product Warranty"))
        {
        Product pr=(Product)session.get(Product.class, id);
        pr.setWarranty(value);
        session.update(pr);
        tx.commit();
        }
        JOptionPane.showMessageDialog(this, "Product Updated");
        jTextField37.setText("");
        jTextField38.setText("");
       }
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jComboBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox5ActionPerformed
         String str=(String)jComboBox5.getSelectedItem();
         if(str.equals("Product Name"))
         {
             jLabel152.setVisible(true);
         jTextField38.setVisible(true);
      jLabel152.setText("Enter Updated Name");
         }
         if(str.equals("Product Price"))
         {
             jLabel152.setVisible(true);
         jTextField38.setVisible(true);
      jLabel152.setText("Enter Updated Price");
         }
         if(str.equals("Product Quantity"))
         {
             jLabel152.setVisible(true);
         jTextField38.setVisible(true);
      jLabel152.setText("Enter Updated Quantity");
         }
         if(str.equals("Product Warranty"))
         {
             jLabel152.setVisible(true);
         jTextField38.setVisible(true);
      jLabel152.setText("Enter Updated Warranty");
         }
         if(str.equals("Update"))
         {
         JOptionPane.showMessageDialog(this, "Please Enter Field to update");
         }
    }//GEN-LAST:event_jComboBox5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        dispose();
        Login loig=new Login();
        loig.setVisible(true);
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        String item=(String)jComboBox3.getSelectedItem();
        if(item.equals("Update Profile"))
                {
                    jLabel156.setVisible(false);
                    jLabel157.setVisible(false);
                    jTextField39.setVisible(false);
                    jRadioButton1.setVisible(false);
                    jRadioButton2.setVisible(false);
                    jRadioButton3.setVisible(false);
                    jRadioButton4.setVisible(false);
                    cl.show(jPanel1, "uprofile");
                }
        if(item.equals("Change Password"))
                {
                    cl.show(jPanel1, "password");
                }
        if(item.equals("Delete Bill"))
                {
                    cl.show(jPanel1, "dbill");
                }
        
        if(item.equals("Settings"))
                {
                    JOptionPane.showMessageDialog(this, "Wrong Choice");
                }
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jComboBox6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox6ActionPerformed
      String str=(String)jComboBox6.getSelectedItem();
      if(str.equals("Name"))
      {
          jLabel156.setVisible(true);
             jLabel157.setVisible(false);
                jRadioButton1.setVisible(false);
                    jRadioButton2.setVisible(false);
                    jRadioButton3.setVisible(false);
                    jRadioButton4.setVisible(false);  
           jTextField39.setVisible(true);
           jLabel156.setText("Enter Updated Name");
      }
      if(str.equals("Registration No"))
      {
          jLabel156.setVisible(true);
             jLabel157.setVisible(false);
                jRadioButton1.setVisible(false);
                    jRadioButton2.setVisible(false);
                    jRadioButton3.setVisible(false);
                    jRadioButton4.setVisible(false);
           jTextField39.setVisible(true);
           jLabel156.setText("Enter Updated Registrartion No");
      }
      if(str.equals("Contact No"))
      {
          jLabel156.setVisible(true);
             jLabel157.setVisible(false);
                  jRadioButton1.setVisible(false);
                    jRadioButton2.setVisible(false);
                    jRadioButton3.setVisible(false);
                    jRadioButton4.setVisible(false);
           jTextField39.setVisible(true);
           jLabel156.setText("Enter Updated Contact");
      }
      if(str.equals("Address"))
      {
          jLabel156.setVisible(true);
             jLabel157.setVisible(false);
                 jRadioButton1.setVisible(false);
                    jRadioButton2.setVisible(false);
                    jRadioButton3.setVisible(false);
                    jRadioButton4.setVisible(false);
           jTextField39.setVisible(true);
           jLabel156.setText("Enter Updated Address");
      }
      if(str.equals("GST No"))
      {
          jLabel156.setVisible(true);
             jLabel157.setVisible(false);
                  jRadioButton1.setVisible(false);
                    jRadioButton2.setVisible(false);
                    jRadioButton3.setVisible(false);
                    jRadioButton4.setVisible(false);
           jTextField39.setVisible(true);
           jLabel156.setText("Enter Updated GST No");
      }
      if(str.equals("Bill Type"))
      {
           jLabel156.setVisible(false);
                    jLabel157.setVisible(true);
                    jTextField39.setVisible(false);
                    jRadioButton1.setVisible(true);
                    jRadioButton2.setVisible(true);
                    jRadioButton3.setVisible(true);
                    jRadioButton4.setVisible(true);
      }
      if(str.equals("Update"))
      {
      JOptionPane.showMessageDialog(this, "Please select  a Option");
      }
    }//GEN-LAST:event_jComboBox6ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
       String str=(String)jComboBox6.getSelectedItem();
       String str1=jTextField39.getText();
       
        SessionFactory sf=ConnectionFactory.emergencyconnection();
        Session session=sf.openSession();
        Transaction tx=session.beginTransaction();
      if(str.equals("Name"))
      {
       Company com=(Company)session.get(Company.class,Login.companyId);
       com.setCompanyName(str1);
       session.update(com);
       tx.commit();
      }
      if(str.equals("Registration No"))
      {
           Company com=(Company)session.get(Company.class,Login.companyId);
       com.setRegistrationId(str1);
       session.update(com);
       tx.commit();
      }
      if(str.equals("Contact No"))
      {
           Company com=(Company)session.get(Company.class,Login.companyId);
       com.setContactNo(str1);
       session.update(com);
       tx.commit();
      }
      if(str.equals("Address"))
      {
         Company com=(Company)session.get(Company.class,Login.companyId);
       com.setAddress(str1);
       session.update(com);
       tx.commit();
      }
      if(str.equals("GST No"))
      {
           Company com=(Company)session.get(Company.class,Login.companyId);
       com.setGstNo(str1);
       session.update(com);
       tx.commit();
      }
      if(str.equals("Bill Type"))
      { String str6="", str7="",str8="",str9="";
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
            Company com=(Company)session.get(Company.class,Login.companyId);
       com.setBillType(str13);
       session.update(com);
       tx.commit();
       dispose();
       Home hom=new Home();
       hom.setVisible(true);
      }
      JOptionPane.showMessageDialog(this, "Profile Updated");
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
     SessionFactory sf=ConnectionFactory.emergencyconnection();
        Session session=sf.openSession();
        Transaction tx=session.beginTransaction();
        char ch[]=jPasswordField1.getPassword();
           String str=new String(ch);
           char ch1[]=jPasswordField2.getPassword();
           String str1=new String(ch1);
           char ch2[]=jPasswordField3.getPassword();
           String str2=new String(ch2);
           Criteria ct=session.createCriteria(Company.class);
           ct.add(Restrictions.and(Restrictions.eq("companyId", Login.companyId), Restrictions.eq("password", str)));
           List<Company> list=ct.list();
           if(list.isEmpty())
           {
               JOptionPane.showMessageDialog(this, "Old Password does not Match");
           }
           else
           {
               if(str1.equals(str2))
               {
                   Company com=(Company)session.get(Company.class,Login.companyId);
       com.setPassword(str1);
       session.update(com);
       tx.commit();
       JOptionPane.showMessageDialog(this, "Password Changed");
               }
               else
               {
                   JOptionPane.showMessageDialog(this, "New Password and Confirm New Password Does Match");
               }
           }
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
      String str=(String)jComboBox7.getSelectedItem();
      String str1=jTextField40.getText();
      SessionFactory sf=ConnectionFactory.emergencyconnection();
        Session session=sf.openSession();
        Transaction tx=session.beginTransaction();
      if(str.equals("Electricity Bill"))
      {
      Criteria ct=session.createCriteria(ElectricityBill.class);
      ct.add(Restrictions.and(Restrictions.eq("companyId", Login.companyId), Restrictions.eq("serialNo", Integer.parseInt(str1))));
      List<ElectricityBill> list=ct.list();
      if(list.isEmpty())
      {
          JOptionPane.showMessageDialog(this, "No Bill Found");
      }
      else
      {
          ElectricityBill eb=(ElectricityBill)session.get(ElectricityBill.class, Integer.parseInt(str1));
          session.delete(eb);
          tx.commit();
          JOptionPane.showMessageDialog(this, "Bill Deleted");
      }
      }
      if(str.equals("Shop Bill"))
      {
      Criteria ct=session.createCriteria(ShopBill.class);
      ct.add(Restrictions.and(Restrictions.eq("companyId", Login.companyId), Restrictions.eq("serialNo", Integer.parseInt(str1))));
      List<ShopBill> list=ct.list();
      if(list.isEmpty())
      {
          JOptionPane.showMessageDialog(this, "No Bill Found");
      }
      else
      {
          ShopBill eb=(ShopBill)session.get(ShopBill.class, Integer.parseInt(str1));
          session.delete(eb);
          tx.commit();
          JOptionPane.showMessageDialog(this, "Bill Deleted");
      }
      }
      if(str.equals("Gas Bill"))
      {
      Criteria ct=session.createCriteria(GasBill.class);
      ct.add(Restrictions.and(Restrictions.eq("companyId", Login.companyId), Restrictions.eq("serialNo", Integer.parseInt(str1))));
      List<GasBill> list=ct.list();
      if(list.isEmpty())
      {
          JOptionPane.showMessageDialog(this, "No Bill Found");
      }
      else
      {
          GasBill eb=(GasBill)session.get(GasBill.class, Integer.parseInt(str1));
          session.delete(eb);
          tx.commit();
          JOptionPane.showMessageDialog(this, "Bill Deleted");
      }
      }
      if(str.equals("Telephone Bill"))
      {
      Criteria ct=session.createCriteria(TelephoneBill.class);
      ct.add(Restrictions.and(Restrictions.eq("companyId", Login.companyId), Restrictions.eq("serialNo", Integer.parseInt(str1))));
      List<TelephoneBill> list=ct.list();
      if(list.isEmpty())
      {
          JOptionPane.showMessageDialog(this, "No Bill Found");
      }
      else
      {
          TelephoneBill eb=(TelephoneBill)session.get(TelephoneBill.class, Integer.parseInt(str1));
          session.delete(eb);
          tx.commit();
          JOptionPane.showMessageDialog(this, "Bill Deleted");
      }
      }
      if(str.equals("Bill Type"))
      {
          JOptionPane.showMessageDialog(this, "Select Bill Type");
      }
    }//GEN-LAST:event_jButton18ActionPerformed

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
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JComboBox<String> jComboBox7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel117;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel122;
    private javax.swing.JLabel jLabel123;
    private javax.swing.JLabel jLabel124;
    private javax.swing.JLabel jLabel125;
    private javax.swing.JLabel jLabel126;
    private javax.swing.JLabel jLabel127;
    private javax.swing.JLabel jLabel128;
    private javax.swing.JLabel jLabel129;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel130;
    private javax.swing.JLabel jLabel131;
    private javax.swing.JLabel jLabel132;
    private javax.swing.JLabel jLabel133;
    private javax.swing.JLabel jLabel134;
    private javax.swing.JLabel jLabel135;
    private javax.swing.JLabel jLabel136;
    private javax.swing.JLabel jLabel137;
    private javax.swing.JLabel jLabel138;
    private javax.swing.JLabel jLabel139;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel140;
    private javax.swing.JLabel jLabel141;
    private javax.swing.JLabel jLabel142;
    private javax.swing.JLabel jLabel143;
    private javax.swing.JLabel jLabel144;
    private javax.swing.JLabel jLabel145;
    private javax.swing.JLabel jLabel146;
    private javax.swing.JLabel jLabel147;
    private javax.swing.JLabel jLabel148;
    private javax.swing.JLabel jLabel149;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel150;
    private javax.swing.JLabel jLabel151;
    private javax.swing.JLabel jLabel152;
    private javax.swing.JLabel jLabel153;
    private javax.swing.JLabel jLabel154;
    private javax.swing.JLabel jLabel155;
    private javax.swing.JLabel jLabel156;
    private javax.swing.JLabel jLabel157;
    private javax.swing.JLabel jLabel158;
    private javax.swing.JLabel jLabel159;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel160;
    private javax.swing.JLabel jLabel161;
    private javax.swing.JLabel jLabel162;
    private javax.swing.JLabel jLabel163;
    private javax.swing.JLabel jLabel164;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JPasswordField jPasswordField3;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField20;
    private javax.swing.JTextField jTextField21;
    private javax.swing.JTextField jTextField22;
    private javax.swing.JTextField jTextField23;
    private javax.swing.JTextField jTextField24;
    private javax.swing.JTextField jTextField25;
    private javax.swing.JTextField jTextField26;
    private javax.swing.JTextField jTextField27;
    private javax.swing.JTextField jTextField28;
    private javax.swing.JTextField jTextField29;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField30;
    private javax.swing.JTextField jTextField31;
    private javax.swing.JTextField jTextField32;
    private javax.swing.JTextField jTextField33;
    private javax.swing.JTextField jTextField34;
    private javax.swing.JTextField jTextField35;
    private javax.swing.JTextField jTextField36;
    private javax.swing.JTextField jTextField37;
    private javax.swing.JTextField jTextField38;
    private javax.swing.JTextField jTextField39;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField40;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}
