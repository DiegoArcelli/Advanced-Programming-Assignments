/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package eightpuzzle;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author diego
 */
public class EightBoard extends javax.swing.JFrame {

    /**
     * Creates new form EightBoard
     */
    public EightBoard() {
        initComponents();
                
        /*
        The Controller registers as vetoable listener of all the tiles, so that 
        it can veto modification of the Label property of the tiles
        */
        
        this.tile1.addVetoableChangeListener(controller);
        this.tile2.addVetoableChangeListener(controller);
        this.tile3.addVetoableChangeListener(controller);
        this.tile4.addVetoableChangeListener(controller);
        this.tile5.addVetoableChangeListener(controller);
        this.tile6.addVetoableChangeListener(controller);
        this.tile7.addVetoableChangeListener(controller);
        this.tile8.addVetoableChangeListener(controller);
        this.tile9.addVetoableChangeListener(controller);
        
        
        /*
        Every tile registers as property change listener of its adjacent tiles,
        so that if the label property of one of the adjacent tile is changed,
        then the tile can adjust its label accordingly
        */
        this.tile1.addPropertyChangeListeners(tile2);
        this.tile1.addPropertyChangeListeners(tile4);
        
        this.tile2.addPropertyChangeListeners(tile1);
        this.tile2.addPropertyChangeListeners(tile3);
        this.tile2.addPropertyChangeListeners(tile5);
        
        this.tile3.addPropertyChangeListeners(tile2);
        this.tile3.addPropertyChangeListeners(tile6);
        
        this.tile4.addPropertyChangeListeners(tile1);
        this.tile4.addPropertyChangeListeners(tile5);
        this.tile4.addPropertyChangeListeners(tile7);
        
        this.tile5.addPropertyChangeListeners(tile2);
        this.tile5.addPropertyChangeListeners(tile4);
        this.tile5.addPropertyChangeListeners(tile6);
        this.tile5.addPropertyChangeListeners(tile8);
        
        this.tile6.addPropertyChangeListeners(tile3);
        this.tile6.addPropertyChangeListeners(tile5);
        this.tile6.addPropertyChangeListeners(tile9);
        
        this.tile7.addPropertyChangeListeners(tile4);
        this.tile7.addPropertyChangeListeners(tile8);
        
        this.tile8.addPropertyChangeListeners(tile7);
        this.tile8.addPropertyChangeListeners(tile5);
        this.tile8.addPropertyChangeListeners(tile9);
       
        this.tile9.addPropertyChangeListeners(tile6);
        this.tile9.addPropertyChangeListeners(tile8);
        
        
        /*
        All the tiles and the Controller register as listeners of the restert event
        which can be fired by the restart button, so that the tiles can set their 
        label after the restart and the controller can update its internal state
        of the board
        */
        this.restart.addRestartListener(controller);
        this.restart.addRestartListener(tile1);
        this.restart.addRestartListener(tile2);
        this.restart.addRestartListener(tile3);
        this.restart.addRestartListener(tile4);
        this.restart.addRestartListener(tile5);
        this.restart.addRestartListener(tile6);
        this.restart.addRestartListener(tile7);
        this.restart.addRestartListener(tile8);
        this.restart.addRestartListener(tile9);
        
        
        /*
        The controller registers as listener of the flip event so that if it is
        modified it can handle 
        */
        this.flip.addFlipListener(controller);
        
        
        // set up an initial configurationof the board
        List<Integer> initConf = Arrays.asList(8,9,6,5,4,7,2,3,1);
        controller.setConfiguration(initConf);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tile1 = new eightpuzzle.EightTile(1, 8);
        tile2 = new eightpuzzle.EightTile(2, 9);
        tile3 = new eightpuzzle.EightTile(3, 6);
        tile4 = new eightpuzzle.EightTile(4, 5);
        tile5 = new eightpuzzle.EightTile(5, 4);
        tile6 = new eightpuzzle.EightTile(6, 7);
        tile7 = new eightpuzzle.EightTile(7, 2);
        tile8 = new eightpuzzle.EightTile(8, 3);
        tile9 = new eightpuzzle.EightTile(9, 1);
        controller = new eightpuzzle.EightController();
        flip = new eightpuzzle.Flip();
        restart = new eightpuzzle.Restart();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tile1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tile1ActionPerformed(evt);
            }
        });

        tile2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tile2ActionPerformed(evt);
            }
        });

        tile3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tile3ActionPerformed(evt);
            }
        });

        tile4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tile4ActionPerformed(evt);
            }
        });

        tile5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tile5ActionPerformed(evt);
            }
        });

        tile6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tile6ActionPerformed(evt);
            }
        });

        tile7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tile7ActionPerformed(evt);
            }
        });

        tile8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tile8ActionPerformed(evt);
            }
        });

        tile9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tile9ActionPerformed(evt);
            }
        });

        flip.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                flipActionPerformed(evt);
            }
        });

        restart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restartActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(controller, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(flip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(restart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tile4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tile5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tile6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tile7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tile8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tile9, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(tile1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tile2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tile3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tile3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tile2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tile1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tile6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tile5, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tile4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tile8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tile7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tile9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(controller, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(flip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(restart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    /*
    Whenever one of the tiles is clicked the Board tries to change its label to 9
    (the label of the empty tile) and the change has to be approved by the Controller
    */
    private void tile1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tile1ActionPerformed
        tile1.setLabel(9);
    }//GEN-LAST:event_tile1ActionPerformed

    private void tile2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tile2ActionPerformed
        tile2.setLabel(9);
    }//GEN-LAST:event_tile2ActionPerformed

    private void tile3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tile3ActionPerformed
        tile3.setLabel(9);
    }//GEN-LAST:event_tile3ActionPerformed

    private void tile4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tile4ActionPerformed
        tile4.setLabel(9);
    }//GEN-LAST:event_tile4ActionPerformed

    private void tile5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tile5ActionPerformed
        tile5.setLabel(9);
    }//GEN-LAST:event_tile5ActionPerformed

    private void tile6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tile6ActionPerformed
        tile6.setLabel(9);
    }//GEN-LAST:event_tile6ActionPerformed

    private void tile7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tile7ActionPerformed
        tile7.setLabel(9);
    }//GEN-LAST:event_tile7ActionPerformed

    private void tile8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tile8ActionPerformed
        tile8.setLabel(9);
    }//GEN-LAST:event_tile8ActionPerformed

    private void tile9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tile9ActionPerformed
        tile9.setLabel(9);
    }//GEN-LAST:event_tile9ActionPerformed

    // when the flip button is pressed
    private void flipActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_flipActionPerformed
        this.flip.update(tile1, tile2);
        
    }//GEN-LAST:event_flipActionPerformed

    // when the restart button is pressed the restart event is fired
    private void restartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restartActionPerformed
        this.restart.permute();
    }//GEN-LAST:event_restartActionPerformed
    
    
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
            java.util.logging.Logger.getLogger(EightBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EightBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EightBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EightBoard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EightBoard().setVisible(true);
            }
        });
    }

    private eightpuzzle.EightController controller;
    private eightpuzzle.Flip flip;
    private eightpuzzle.Restart restart;
    private eightpuzzle.EightTile tile1;
    private eightpuzzle.EightTile tile2;
    private eightpuzzle.EightTile tile3;
    private eightpuzzle.EightTile tile4;
    private eightpuzzle.EightTile tile5;
    private eightpuzzle.EightTile tile6;
    private eightpuzzle.EightTile tile7;
    private eightpuzzle.EightTile tile8;
    private eightpuzzle.EightTile tile9;

}
