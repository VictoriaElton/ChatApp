
import javax.swing.*;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

public class MainForm extends JFrame implements Observer{
    // Variables declaration - do not modify
    private static javax.swing.JTextField IPText = new javax.swing.JTextField();
    private javax.swing.JTextArea chatBox = new javax.swing.JTextArea();
    private javax.swing.JButton clearButton = new javax.swing.JButton();
    private static javax.swing.JButton connectButton = new javax.swing.JButton();
    private static javax.swing.JButton dissconectButton = new javax.swing.JButton();
    private javax.swing.JPanel jPanel1 = new javax.swing.JPanel();
    private javax.swing.JPanel  jPanel2 = new javax.swing.JPanel();
    private javax.swing.JScrollPane jScrollPane3 = new javax.swing.JScrollPane();
    private javax.swing.JScrollPane jScrollPane2 = new javax.swing.JScrollPane();
    private javax.swing.JButton logInButton = new javax.swing.JButton();
    private javax.swing.JTextField  logInText = new javax.swing.JTextField();
    private static javax.swing.JTextArea   messageText = new javax.swing.JTextArea();
    private static javax.swing.JButton sendButton = new javax.swing.JButton();

    private Caller cl = new Caller();
    private CallListener cllis =new CallListener();
    private Connection connect;
    private  CommandThread ct = new CommandThread();

    // End of variables declaration

    public MainForm() throws IOException {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);
        setBackground(new java.awt.Color(102, 102, 102));
        setFocusable(false);

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Settings", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Monotype Corsiva", 0, 18))); // NOI18N


        logInButton.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        logInButton.setText("Log in");
        logInButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logInButtonActionPerformed(evt);
            }
        });

        connectButton.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        connectButton.setText("Connect");
        connectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectButtonActionPerformed(evt);
            }
        });

        dissconectButton.setFont(new java.awt.Font("Comic Sans MS", 0, 12)); // NOI18N
        dissconectButton.setText("Dissconect");
        dissconectButton.setEnabled(false);
        dissconectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dissconectButtonActionPerformed(evt);
            }
        });

        IPText.setText("Enter IP");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(0, 10, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(logInButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(dissconectButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(connectButton, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(logInText)
                                        .addComponent(IPText))
                                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(logInText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(logInButton)
                                .addGap(23, 23, 23)
                                .addComponent(IPText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(connectButton)
                                        .addComponent(dissconectButton))
                                .addContainerGap(148, Short.MAX_VALUE))
        );

        sendButton.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        sendButton.setText("Send");
        sendButton.setEnabled(false);
        sendButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        sendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtonActionPerformed(evt);
            }
        });

        clearButton.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        clearButton.setText("Clear");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        messageText.setColumns(20);
        messageText.setRows(5);
        messageText.setEnabled(false);
        jScrollPane2.setViewportView(messageText);

        chatBox.setColumns(20);
        chatBox.setRows(5);
        chatBox.setEnabled(false);
        jScrollPane3.setViewportView(chatBox);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane3)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(sendButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(clearButton, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE))
                                                .addGap(0, 2, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane3)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addComponent(sendButton)
                                                .addGap(11, 11, 11)
                                                .addComponent(clearButton))
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>


    //ATTENTION!!
    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {
        connect.sendMsg(messageText.getText() + "\n");
        chatBox.append(messageText.getText() + "\n");
        messageText.setText("");

    }

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {
        messageText.setText("");
    }

    private void logInButtonActionPerformed(java.awt.event.ActionEvent evt) {
        cl.setLocalNick(logInText.getText());
        cllis.setLocalNick(logInText.getText());

        logInText.setEnabled(false);
        logInButton.setEnabled(false);

    }

    private void dissconectButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:

        sendButton.setEnabled(false);
        clearButton.setEnabled(false);
        messageText.setEnabled(false);
        logInText.setEnabled(true);
        logInButton.setEnabled(true);
        IPText.setEnabled(true);
        connectButton.setEnabled(true);
        dissconectButton.setEnabled(false);
    }

    private void connectButtonActionPerformed(java.awt.event.ActionEvent evt) {
        //SocketAddress sa= new InetSocketAddress(IPText.getText(),5832);
        cl.setIp(IPText.getText());
        chatBox.append(IPText.getText());
      connect =  cl.call();
        connect.sendNickHello("CHATAPPISHE 2015",cl.getLocalNick());

        IPText.setEnabled(false);
        connectButton.setEnabled(false);
        dissconectButton.setEnabled(true);
        messageText.setEnabled(true);
        sendButton.setEnabled(true);
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
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainForm mf = new MainForm();
                    CallListenerThread clt =new CallListenerThread(IPText, connectButton, dissconectButton, messageText, sendButton);
                    clt.start();
                    clt.addObserver(mf);
                    mf.setVisible(true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    @Override
    public void update(Observable o, Object arg) {
        chatBox.append((String)arg);
    }


}
