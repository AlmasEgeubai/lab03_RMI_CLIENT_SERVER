
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import eak_rmi_client_server.Variables;
import eak_rmi_client_server.IRemoteSolution;
import java.awt.Color;

public class NewJFrame extends javax.swing.JFrame {

    private Registry registry = null;
    private IRemoteSolution remoteServer = null;/////********************************************

    public NewJFrame() {
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

        jLabelPrimer = new javax.swing.JLabel();
        jLabelA = new javax.swing.JLabel();
        jLabelB = new javax.swing.JLabel();
        jLabelX = new javax.swing.JLabel();
        jLabelResult = new javax.swing.JLabel();
        jTextFieldA = new javax.swing.JTextField();
        jTextFieldB = new javax.swing.JTextField();
        jTextFieldX = new javax.swing.JTextField();
        jButtonClear = new javax.swing.JButton();
        jButtonSolve = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("RMI Client Server");
        setResizable(false);
        getContentPane().setLayout(null);

        jLabelPrimer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/primer.png"))); // NOI18N
        getContentPane().add(jLabelPrimer);
        jLabelPrimer.setBounds(0, 0, 620, 290);

        jLabelA.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabelA.setText("A = ");
        getContentPane().add(jLabelA);
        jLabelA.setBounds(20, 310, 35, 24);

        jLabelB.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabelB.setText("B = ");
        getContentPane().add(jLabelB);
        jLabelB.setBounds(260, 310, 34, 24);

        jLabelX.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabelX.setText("X = ");
        getContentPane().add(jLabelX);
        jLabelX.setBounds(490, 310, 35, 24);

        jLabelResult.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jLabelResult.setText("Ответ: Y = ");
        getContentPane().add(jLabelResult);
        jLabelResult.setBounds(20, 410, 280, 24);

        jTextFieldA.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jTextFieldA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldAActionPerformed(evt);
            }
        });
        getContentPane().add(jTextFieldA);
        jTextFieldA.setBounds(60, 310, 70, 25);

        jTextFieldB.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        getContentPane().add(jTextFieldB);
        jTextFieldB.setBounds(300, 310, 70, 25);

        jTextFieldX.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        getContentPane().add(jTextFieldX);
        jTextFieldX.setBounds(530, 310, 70, 25);

        jButtonClear.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jButtonClear.setText("Очистить");
        jButtonClear.setName(""); // NOI18N
        jButtonClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonClearActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonClear);
        jButtonClear.setBounds(90, 360, 115, 33);

        jButtonSolve.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jButtonSolve.setText("Решить");
        jButtonSolve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSolveActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonSolve);
        jButtonSolve.setBounds(410, 360, 95, 33);

        setSize(new java.awt.Dimension(635, 485));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldAActionPerformed

    private void jButtonClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonClearActionPerformed
        jTextFieldA.setText("");
        jTextFieldB.setText("");
        jTextFieldX.setText("");
        jLabelResult.setText("Ответ: Y = ");
        jLabelResult.setForeground(Color.BLACK);
    }//GEN-LAST:event_jButtonClearActionPerformed

    private void jButtonSolveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSolveActionPerformed
        try {

            int port = IRemoteSolution.PORT;
            String name = IRemoteSolution.SERVICE_NAME;
            String address = "localhost";
            if (registry == null || remoteServer == null) {
                System.out.println("Подключение...");
                registry = LocateRegistry.getRegistry(address, port);
                //ystem.out.println("Подключение2...");
                remoteServer = (IRemoteSolution) registry.lookup(name);
                System.out.println("Подключено к //" + address + ":" + port + "/" + name);
            }

            double a = Double.parseDouble(jTextFieldA.getText());
            double b = Double.parseDouble(jTextFieldB.getText());
            double x = Double.parseDouble(jTextFieldX.getText());
            Variables data = new Variables(a, b, x);
            data = (Variables) remoteServer.getData(data);

            jLabelResult.setText(String.format("Ответ: Y = %.3f", data.getY()));

        } catch (NumberFormatException | NotBoundException | RemoteException e) {
            jLabelResult.setText("Ошибка");
            jLabelResult.setForeground(Color.red);
        }
    }//GEN-LAST:event_jButtonSolveActionPerformed

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
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new NewJFrame().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonClear;
    private javax.swing.JButton jButtonSolve;
    private javax.swing.JLabel jLabelA;
    private javax.swing.JLabel jLabelB;
    private javax.swing.JLabel jLabelPrimer;
    private javax.swing.JLabel jLabelResult;
    private javax.swing.JLabel jLabelX;
    private javax.swing.JTextField jTextFieldA;
    private javax.swing.JTextField jTextFieldB;
    private javax.swing.JTextField jTextFieldX;
    // End of variables declaration//GEN-END:variables
}
