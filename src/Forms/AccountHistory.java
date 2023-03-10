package Forms;

import Services.AccountService;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

/**
 * @author mprzybyla
 */
public class AccountHistory extends javax.swing.JDialog {

    /**
     * Creates new form AccountHistoryDialog
     */
    public AccountHistory(java.awt.Frame parent, boolean modal, int _loggedUserId) {
        super(parent, modal);
        initComponents();
        ShowAllOperationsRButton.setSelected(true);
        this.loggedUserId=_loggedUserId;
        
        this.historyEntries = AccountService.GetAccountHistory(AccountService.GetAccountId(this.loggedUserId));
        DefaultTableModel tableModel = (DefaultTableModel)HistoryTable.getModel();
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        
        
        
        int i=0;
        while(i<this.historyEntries.size())
        {
            String convertedOperationDate = this.historyEntries.get(i).OperationDate.format(formatter);
            tableModel.addRow(new Object[]{"-"+Integer.toString(this.historyEntries.get(i).WithdrawAmount)+" PLN","+ "+Integer.toString(this.historyEntries.get(i).DepositAmount)+" PLN",Integer.toString(this.historyEntries.get(i).AccountBalance)+" PLN",convertedOperationDate});
            i++;
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        HistoryTable = new javax.swing.JTable();
        ShowDepositsRButon = new javax.swing.JRadioButton();
        ShowWithdrawsRButton = new javax.swing.JRadioButton();
        ShowAllOperationsRButton = new javax.swing.JRadioButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        HistoryTable.setAutoCreateRowSorter(true);
        HistoryTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Wyp??ata", "Wp??ata", "Stan konta", "Data operacji"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        HistoryTable.setColumnSelectionAllowed(true);
        jScrollPane1.setViewportView(HistoryTable);
        HistoryTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        if (HistoryTable.getColumnModel().getColumnCount() > 0) {
            HistoryTable.getColumnModel().getColumn(0).setMinWidth(120);
            HistoryTable.getColumnModel().getColumn(0).setPreferredWidth(120);
            HistoryTable.getColumnModel().getColumn(0).setMaxWidth(120);
            HistoryTable.getColumnModel().getColumn(1).setMinWidth(120);
            HistoryTable.getColumnModel().getColumn(1).setPreferredWidth(120);
            HistoryTable.getColumnModel().getColumn(1).setMaxWidth(120);
            HistoryTable.getColumnModel().getColumn(2).setMinWidth(120);
            HistoryTable.getColumnModel().getColumn(2).setPreferredWidth(120);
            HistoryTable.getColumnModel().getColumn(2).setMaxWidth(120);
            HistoryTable.getColumnModel().getColumn(3).setMinWidth(180);
            HistoryTable.getColumnModel().getColumn(3).setPreferredWidth(180);
            HistoryTable.getColumnModel().getColumn(3).setMaxWidth(180);
        }

        ShowDepositsRButon.setText("Wp??aty");
        ShowDepositsRButon.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                ShowDepositsRButonStateChanged(evt);
            }
        });
        ShowDepositsRButon.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ShowDepositsRButonFocusGained(evt);
            }
        });
        ShowDepositsRButon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShowDepositsRButonActionPerformed(evt);
            }
        });
        ShowDepositsRButon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ShowDepositsRButonKeyPressed(evt);
            }
        });

        ShowWithdrawsRButton.setText("Wyp??aty");
        ShowWithdrawsRButton.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ShowWithdrawsRButtonFocusGained(evt);
            }
        });
        ShowWithdrawsRButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ShowWithdrawsRButtonActionPerformed(evt);
            }
        });

        ShowAllOperationsRButton.setText("Pe??ne zestawienie wp??at i wyp??at");
        ShowAllOperationsRButton.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                ShowAllOperationsRButtonFocusGained(evt);
            }
        });

        jButton1.setText("Historia przelew??w");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(ShowAllOperationsRButton)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(ShowWithdrawsRButton)
                                .addGap(55, 55, 55)
                                .addComponent(ShowDepositsRButon)
                                .addGap(109, 109, 109)
                                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                                .addGap(12, 12, 12)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(ShowWithdrawsRButton)
                    .addComponent(ShowDepositsRButon))
                .addGap(18, 18, 18)
                .addComponent(ShowAllOperationsRButton)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ShowDepositsRButonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShowDepositsRButonActionPerformed
        // TODO add your handling code here:

        
        
    }//GEN-LAST:event_ShowDepositsRButonActionPerformed

    private void ShowDepositsRButonStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_ShowDepositsRButonStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_ShowDepositsRButonStateChanged

    private void ShowDepositsRButonFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ShowDepositsRButonFocusGained
        // TODO add your handling code here:
        ShowWithdrawsRButton.setSelected(false);
        ShowAllOperationsRButton.setSelected(false);
        
        DefaultTableModel tableModel = (DefaultTableModel)HistoryTable.getModel();
        tableModel.setRowCount(0);

        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
   
        int i=0;
        while(i<this.historyEntries.size())
        {
            int amount = this.historyEntries.get(i).DepositAmount;
            if(amount!=0)
            {
            String convertedOperationDate = this.historyEntries.get(i).OperationDate.format(formatter);
            tableModel.addRow(new Object[]{"-"+Integer.toString(this.historyEntries.get(i).WithdrawAmount)+" PLN","+ "+Integer.toString(this.historyEntries.get(i).DepositAmount)+" PLN",Integer.toString(this.historyEntries.get(i).AccountBalance)+" PLN",convertedOperationDate});
            }
            i++;
            
        }
        
    }//GEN-LAST:event_ShowDepositsRButonFocusGained

    private void ShowDepositsRButonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ShowDepositsRButonKeyPressed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_ShowDepositsRButonKeyPressed

    private void ShowWithdrawsRButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ShowWithdrawsRButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ShowWithdrawsRButtonActionPerformed

    private void ShowWithdrawsRButtonFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ShowWithdrawsRButtonFocusGained
        // TODO add your handling code here:
        ShowDepositsRButon.setSelected(false);
        ShowAllOperationsRButton.setSelected(false);
        
        DefaultTableModel tableModel = (DefaultTableModel)HistoryTable.getModel();
        tableModel.setRowCount(0);

        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
   
        int i=0;
        while(i<this.historyEntries.size())
        {
            int amount = this.historyEntries.get(i).DepositAmount;
            if(amount==0)
            {
            String convertedOperationDate = this.historyEntries.get(i).OperationDate.format(formatter);
            tableModel.addRow(new Object[]{"-"+Integer.toString(this.historyEntries.get(i).WithdrawAmount)+" PLN","+ "+Integer.toString(this.historyEntries.get(i).DepositAmount)+" PLN",Integer.toString(this.historyEntries.get(i).AccountBalance)+" PLN",convertedOperationDate});
            }
            i++;
            
        }
    }//GEN-LAST:event_ShowWithdrawsRButtonFocusGained

    private void ShowAllOperationsRButtonFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_ShowAllOperationsRButtonFocusGained
        // TODO add your handling code here:
        ShowWithdrawsRButton.setSelected(false);
        ShowDepositsRButon.setSelected(false);
        
        DefaultTableModel tableModel = (DefaultTableModel)HistoryTable.getModel();
        tableModel.setRowCount(0);

        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
   
        int i=0;
        while(i<this.historyEntries.size())
        {
            int amount = this.historyEntries.get(i).DepositAmount;
            String convertedOperationDate = this.historyEntries.get(i).OperationDate.format(formatter);
            tableModel.addRow(new Object[]{"-"+Integer.toString(this.historyEntries.get(i).WithdrawAmount)+" PLN","+ "+Integer.toString(this.historyEntries.get(i).DepositAmount)+" PLN",Integer.toString(this.historyEntries.get(i).AccountBalance)+" PLN",convertedOperationDate});

            i++;
            
        }
    }//GEN-LAST:event_ShowAllOperationsRButtonFocusGained

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
        JFrame parentFrame = (JFrame) this.getParent();
        TransferHistory transferDialog = new TransferHistory((java.awt.Frame)parentFrame, true, this.loggedUserId);
        transferDialog.setLocationRelativeTo(this);
        transferDialog.show();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(AccountHistoryDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(AccountHistoryDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(AccountHistoryDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(AccountHistoryDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                AccountHistoryDialog dialog = new AccountHistoryDialog(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable HistoryTable;
    private javax.swing.JRadioButton ShowAllOperationsRButton;
    private javax.swing.JRadioButton ShowDepositsRButon;
    private javax.swing.JRadioButton ShowWithdrawsRButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

    private ArrayList<Entities.AccountHistoryEntity> historyEntries = null;

    /**
     * Get the value of stringhistoryEntries
     *
     * @return the value of stringhistoryEntries
     */
    public ArrayList<Entities.AccountHistoryEntity> getStringhistoryEntries() {
        return historyEntries;
    }

    /**
     * Set the value of stringhistoryEntries
     *
     * @param stringhistoryEntries new value of stringhistoryEntries
     */
    public void setStringhistoryEntries(ArrayList<Entities.AccountHistoryEntity> stringhistoryEntries) {
        this.historyEntries = stringhistoryEntries;
    }

    private int loggedUserId;

    /**
     * Get the value of loggedUserId
     *
     * @return the value of loggedUserId
     */
    public int getLoggedUserId() {
        return loggedUserId;
    }

    /**
     * Set the value of loggedUserId
     *
     * @param loggedUserId new value of loggedUserId
     */
    public void setLoggedUserId(int loggedUserId) {
        this.loggedUserId = loggedUserId;
    }
}
