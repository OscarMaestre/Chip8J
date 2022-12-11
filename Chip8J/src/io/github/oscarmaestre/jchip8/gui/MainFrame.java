/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package io.github.oscarmaestre.jchip8.gui;

import io.github.oscarmaestre.jchip8.CPUChip8;
import io.github.oscarmaestre.jchip8.ensamblador.Ensamblador;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JButton;

/**
 *
 * @author usuario
 */
public class MainFrame extends javax.swing.JFrame {

    Ensamblador e;
    CPUChip8   cpu;
    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        
        initComponents();
        this.anadirPantalla();
        
        e=new Ensamblador();
        cpu=new CPUChip8(this.display);
        ArrayList<String> lineas=new ArrayList<>();
        lineas.add(":INICIO");
        lineas.add("CLS;");
        lineas.add("CALL :INICIO;");
        byte[] ensamblarLineasComoBytes = e.ensamblarLineasComoBytes(lineas);
        cpu.cargarBytesEnMemoria(ensamblarLineasComoBytes, 512);
        cpu.setPC(512);
        this.actualizarInformacionDepuracion();
        
    }
    private void anadirPantalla(){
        display=new Chip8Display(64, 32, 1);
        
        this.screenPanel.add(display);

    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        btnStep = new javax.swing.JButton();
        btnRun = new javax.swing.JButton();
        screenPanel = new javax.swing.JPanel();
        spnScale = new javax.swing.JSpinner();
        lblScale = new javax.swing.JLabel();
        btnStop = new javax.swing.JButton();
        debugPanel1 = new io.github.oscarmaestre.jchip8.gui.DebugPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setName("mainFrame"); // NOI18N
        getContentPane().setLayout(new java.awt.GridBagLayout());

        btnStep.setText("Dar un paso");
        btnStep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStepActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(btnStep, gridBagConstraints);

        btnRun.setText("Ejecutar");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(btnRun, gridBagConstraints);

        screenPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        screenPanel.setMinimumSize(new java.awt.Dimension(64, 32));
        screenPanel.setLayout(new java.awt.BorderLayout());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 7;
        gridBagConstraints.gridheight = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 5.0;
        getContentPane().add(screenPanel, gridBagConstraints);

        spnScale.setModel(new javax.swing.SpinnerNumberModel(1, 1, 4, 1));
        spnScale.setName("spnScale"); // NOI18N
        spnScale.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spnScaleStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(spnScale, gridBagConstraints);

        lblScale.setText("Scale");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        getContentPane().add(lblScale, gridBagConstraints);

        btnStop.setText("Parar");
        getContentPane().add(btnStop, new java.awt.GridBagConstraints());
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        getContentPane().add(debugPanel1, gridBagConstraints);

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void spnScaleStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spnScaleStateChanged
        int newScale=(int) this.spnScale.getValue();
        System.out.println("New scale:"+newScale);
        this.display.setScale(newScale);
    }//GEN-LAST:event_spnScaleStateChanged

    private void btnStepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStepActionPerformed
        System.out.println("Damos un paso");
        cpu.ejecutarInstruccion();
        this.actualizarInformacionDepuracion();
    }//GEN-LAST:event_btnStepActionPerformed

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
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    public void actualizarInformacionDepuracion(){
        byte[] registros = this.cpu.getRegistros();
        int registroI = this.cpu.getRegistroI();
        this.debugPanel1.setPC(registroI);
        this.debugPanel1.setRegistersLabels(registros);
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRun;
    private javax.swing.JButton btnStep;
    private javax.swing.JButton btnStop;
    private io.github.oscarmaestre.jchip8.gui.DebugPanel debugPanel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JLabel lblScale;
    private javax.swing.JPanel screenPanel;
    private javax.swing.JSpinner spnScale;
    // End of variables declaration//GEN-END:variables
    private Chip8Display display;
}
