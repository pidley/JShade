package org.codegaucho.pidley;

import java.awt.* ;
import java.awt.event.* ;
import javax.swing.* ;
import javax.swing.border.* ;

/**
 *
 * @author beckman
 */
public class JShadeDriver {
JShadeItem item   =   null ;

   private static class ShadeItemMouseHandler implements MouseListener, MouseMotionListener {

      @Override
      public void mouseEntered(MouseEvent e) { }

      @Override
      public void mouseExited(MouseEvent e) { }

      @Override
      public void mousePressed(MouseEvent e) { }

      @Override
      public void mouseReleased(MouseEvent e) { }

      @Override
      public void mouseClicked(MouseEvent e) {

         System.out.println("Mouse clicked") ;
      }

      @Override
      public void mouseDragged(MouseEvent e) { }

      @Override
      public void mouseMoved(MouseEvent arg0) { }
   }

   public static JShadeItem createShadeItem(String number) {
   JShadeItem   item       =   null ;
   Color        foreColor  =   null ;
   JLabel       label      =   null ;
   Font         font       =   null ;

      foreColor   =   Color.WHITE ;
      label       =   new JLabel("Bob " + number, JLabel.LEFT) ;
      label.setForeground(foreColor) ;
      label.setBackground(Color.YELLOW);

      try {
         item        =   new JShadeItem() ;
         item.setBackgroundColor(Color.BLUE) ;
         //item.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED)) ;
         item.addMouseListener(new ShadeItemMouseHandler()) ;
         item.add(label) ;
      } catch (Exception e) {
         System.out.print("Shade item error") ;
      }
      return item ;
   }

   public static void main(String[] args) {
       
      java.awt.EventQueue.invokeLater(new Runnable() {
         @Override
         public void run() {
         JFrame        frame   =   null ;
         JShade   demo    =   null ;
         JShadeItem    item    =   null ;

            frame   =   new JFrame("Shade Panel") ;
            //frame.setLayout(null);
            frame.setMinimumSize(new Dimension(400, 300));
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            frame.add(new JLabel("XXXXXXXXXXXXXXXXX"));
            try {
               demo    =   new JShade(frame);
               demo.setContentOrientation(Alignment.BOTTOM_UP) ;
               demo.addItem(createShadeItem("1")) ;
               demo.addItem(createShadeItem("2")) ;
/*
               demo.addItem(createShadeItem("3")) ;
               demo.addItem(createShadeItem("4")) ;
               demo.addItem(createShadeItem("5")) ;
               demo.addItem(createShadeItem("6")) ;
               demo.addItem(createShadeItem("7")) ;
               demo.addItem(createShadeItem("8")) ;
               demo.addItem(createShadeItem("A")) ;
               demo.addItem(createShadeItem("B")) ;
               demo.addItem(createShadeItem("C")) ;
               demo.addItem(createShadeItem("D")) ;
               demo.addItem(createShadeItem("E")) ;
               demo.addItem(createShadeItem("F")) ;
               demo.addItem(createShadeItem("G")) ;
               demo.addItem(createShadeItem("H")) ;
 * 
 */
            } catch (Exception e) {
               System.out.println("error\n") ;
               e.printStackTrace() ;
             }
            
            frame.setVisible(true);
         }
      });
   }
}

