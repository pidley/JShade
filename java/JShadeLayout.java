package org.codegaucho.pidley;

import java.awt.* ;
import javax.swing.* ;

/**
 * Layout manager for managing the resizing of <code>Shade</code> within its
 * containing frame.
 *
 * @author R. L. E C K M A N
 * @version 1.0
 */
class JShadeLayout implements LayoutManager {
private JButton   draggableButton   =   null ;
private JFrame    containingFrame   =   null ;
private JPanel    contentPanel      =   null ;

   JShadeLayout(JFrame frame, JButton button, JPanel panel) {

      if ( !JShadeResources.isNull(frame) && !JShadeResources.isNull(button) && !JShadeResources.isNull(panel) ) {
         setContainingFrame(frame) ;
         setDraggableButton(button) ;
         setContentPanel(panel) ;
      } else {
         throw new IllegalArgumentException(JShadeException.ERROR_016) ;
      }
   }

   @Override
   public void addLayoutComponent(String name, Component component) { }

   private JFrame getContainingFrame() {
   
      return containingFrame ;
   }
   
   private JPanel getContentPanel() {
       
      return contentPanel ;
   }
   
   private JButton getDraggableButton() {
       
      return draggableButton ;
   }
   
   @Override
   public void layoutContainer(Container container) {
   Point   location   =   null ;

      if ( !JShadeResources.isNull(container) ) {
         try {
            location   =   getDraggableButton().getLocation() ;
            getDraggableButton().setBounds(location.x, location.y, getContainingFrame().getContentPane().getWidth() , getDraggableButton().getHeight());
            getContentPanel().setBounds(0,0,getContainingFrame().getContentPane().getWidth(), location.y) ;
         } catch (Exception e) {
            throw new JShadeException(JShadeException.ERROR_017, e) ;
         }
      } else
         throw new IllegalArgumentException(JShadeException.ERROR_003) ;
   }
   
   @Override
   public Dimension minimumLayoutSize(Container container) {
   Dimension   dimension   =   null ;
   
      return dimension ;
   }
   
   @Override
   public Dimension preferredLayoutSize(Container container) { 
   Dimension   dimension   =   null ;
   
      return dimension ;
   }
   
   @Override
   public void removeLayoutComponent(Component component) { }

   private void setContainingFrame(JFrame frame) {
       
      if ( !JShadeResources.isNull(frame) )
         containingFrame   =   frame ;
      else
         throw new IllegalArgumentException(JShadeException.ERROR_003) ;
   }
   
   private void setContentPanel(JPanel panel) {
       
      if ( !JShadeResources.isNull(panel) )
         contentPanel   =   panel ;
      else
         throw new IllegalArgumentException(JShadeException.ERROR_003) ;
   }
   
   private void setDraggableButton(JButton button) {
       
      if ( !JShadeResources.isNull(button) )
         draggableButton   =   button ;
      else
         throw new IllegalArgumentException(JShadeException.ERROR_003) ;
   }

}
