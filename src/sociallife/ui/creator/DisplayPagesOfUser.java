/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sociallife.ui.creator;

import sociallife.ui.friends.*;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import sociallife.ui.home.*;
import sociallife.database.DatabaseHandler;
import sociallife.ui.login.Login;

/**
 *
 * @author anonymous
 */
public class DisplayPagesOfUser extends javax.swing.JFrame {

    /**
     * Creates new form ChangeStatus
     */
    
    DatabaseHandler handler = null;
   
    // this is awesome you have a vector on top but you dont show it in frontend
    DefaultListModel model = null;
    
    Vector<Integer> pageIds = new Vector<Integer>();

    public DisplayPagesOfUser() {
        initComponents();
        
        handler = DatabaseHandler.getInstance();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jTextField3 = new javax.swing.JTextField();
        sumbitButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        pagesList = new javax.swing.JList<>();
        searchBox = new javax.swing.JTextField();
        selectPageButton = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();

        jTextField1.setText("jTextField1");

        jTextField2.setText("jTextField2");

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        jTextField3.setText("jTextField3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        sumbitButton.setText("Search Pages");
        sumbitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sumbitButtonActionPerformed(evt);
            }
        });

        jScrollPane1.setViewportView(pagesList);

        selectPageButton.setText("Show Page");
        selectPageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectPageButtonActionPerformed(evt);
            }
        });

        jMenu1.setText("Home");
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jMenu1MousePressed(evt);
            }
        });
        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 331, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(searchBox, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sumbitButton))
                    .addComponent(selectPageButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sumbitButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(selectPageButton)
                .addGap(45, 45, 45))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sumbitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sumbitButtonActionPerformed

        showPages();

    }//GEN-LAST:event_sumbitButtonActionPerformed

    private void showPages()
    {
        // to clear list
             model = new DefaultListModel();
     try
     {
         
        ResultSet rs = handler.getExistingPages(Login.getUserId(), searchBox.getText() );
        
        if (rs != null)
        {
                        
            do
            {
                pageIds.add(rs.getInt("page_id"));
                // add Username to Friendlist
               model.addElement(rs.getString("page_name")) ;
               pagesList.setModel(model);
            }
            while (rs.next());

            
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Page Name could Not Be Found!"); 
        }
     }
     catch (Exception e)
     {

         System.out.println("");
     }
    }
            
    
    private void jMenu1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MousePressed
        this.dispose();                         
        Home l = new Home();
        l.setVisible(true);
        l.setLocationRelativeTo(null);

    }//GEN-LAST:event_jMenu1MousePressed

    private void selectPageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectPageButtonActionPerformed
        int index = pagesList.getSelectedIndex();

        if ( index >= 0 )
        {
            
            DisplayParticularPageOfUser d = new DisplayParticularPageOfUser(pageIds.get(index));
            this.dispose();                         
            d.setVisible(true);
            d.setLocationRelativeTo(null);

        }
        

        // TODO add your handling code here:
        /*
        int userFrom = Login.getUserId();
        int index = pagesList.getSelectedIndex();
        // System.out.println(index);
        if (index >= 0 && userFrom >= 0)
        {
            int userTo = userIds.get(index);
            handler.insertIntoFriendRequests(userFrom, userTo);
        }
        */
    }//GEN-LAST:event_selectPageButtonActionPerformed

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
            java.util.logging.Logger.getLogger(DisplayPagesOfUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DisplayPagesOfUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DisplayPagesOfUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DisplayPagesOfUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DisplayPagesOfUser().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JList<String> pagesList;
    private javax.swing.JTextField searchBox;
    private javax.swing.JButton selectPageButton;
    private javax.swing.JButton sumbitButton;
    // End of variables declaration//GEN-END:variables
}
