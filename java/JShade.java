package org.codegaucho.pidley ;

import java.awt.* ;
import java.awt.event.*;
import javax.swing.*;
import java.util.* ;

public class JShade extends JShadeComponent {

private final int              DEFAULT_BUTTONHEIGHT          =   25 ;
private final String           DEFAULT_BUTTON_LABEL_TEXT     =   "....." ;
private final int              DEFAULT_CONTENTTRANSPARENCY   =   127 ;
private final Color            DEFAULT_CONTENTPANELCOLOR     =   new Color(0x00,0x00,0xAA, DEFAULT_CONTENTTRANSPARENCY) ;
private final int              DEFAULT_SCROLLBARWIDTH        =   9 ;
private final Alignment        DEFAULT_ORIENTATION           =   Alignment.BOTTOM_UP ;
private final int              VGAP                          =   3 ;

private String                 buttonLabelText               =   DEFAULT_BUTTON_LABEL_TEXT ;
private JButton                draggableButton               =   null ;
private JFrame                 containingFrame               =   null ;
private JPanel                 contentPanel                  =   null ;
private ArrayList<JShadeItem>  itemCollection                =   null ;
private int                    buttonHeight                  =   DEFAULT_BUTTONHEIGHT ;
private Color                  contentPanelColor             =   DEFAULT_CONTENTPANELCOLOR ;
private int                    contentPanelTransparency      =   DEFAULT_CONTENTTRANSPARENCY ;
private int                    verticalGap                   =   VGAP ;
private int                    scrollBarWidth                =   DEFAULT_SCROLLBARWIDTH ;
private Alignment              contentOrientation            =   DEFAULT_ORIENTATION ;
private boolean                enabled                       =   true ;

   private class ScrollContentPanel extends JPanel {
   
      ScrollContentPanel() {
          
         try {
            setOpaque(false) ;
            setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS)) ;
         } catch (Exception e) {
            throw new JShadeException(JShadeException.ERROR_001, e) ;
         }
      }
      
      void addItem(JShadeItem item) {
         
         if ( !JShadeResources.isNull(item) ) {
            try {
               add(item) ;
               add(Box.createRigidArea(new Dimension( 0, verticalGap )) ) ;
            } catch (Exception e) {
               throw new JShadeException(JShadeException.ERROR_002, e) ;
            }
         } else 
            throw new IllegalArgumentException(JShadeException.ERROR_003) ;
      }
   }
   
   private class ContentPanelAdjustmentHandler implements AdjustmentListener {

      @Override
      public void adjustmentValueChanged(AdjustmentEvent e) {

         getContentPanel().repaint() ;
      }
   }

   private class JShadePanelComponentHandler implements ComponentListener {
   
      @Override
      public void componentHidden(ComponentEvent e) { }

      @Override
      public void componentShown(ComponentEvent e) { }

      @Override
      public void componentMoved(ComponentEvent e) { }

      @Override
      public void componentResized(ComponentEvent e) {
      Point   location    =   null ;
      int     yLocation   =   0   ;

         location    =   getDraggableButton().getLocation() ;
         yLocation   =   ( returnRatio() >= 1.0 ) ? ( getContainingFrame().getContentPane().getHeight() - getButtonHeight()) : ( location.y ) ;
         getDraggableButton().setBounds(location.x, yLocation, getContainingFrame().getContentPane().getWidth() , getButtonHeight());
         getContentPanel().setBounds(0,0,getContainingFrame().getContentPane().getWidth(), yLocation) ;
      }
      
      private double returnRatio() {
      Point location   =   null ;

         location   =   getDraggableButton().getLocation() ;      
         return ((location.y + getDraggableButton().getHeight()) * 1.0) / getContainingFrame().getContentPane().getHeight() ;          
      }
   }

   private class JShadePanelWindowHandler implements WindowListener {
      
      @Override
      public void windowActivated(WindowEvent e) {
      JPanel   glassPane   =   null ;
      Point    location    =   null ;

         location    =   getDraggableButton().getLocation() ;
         glassPane   =   (JPanel)getContainingFrame().getGlassPane() ;
         glassPane.setLayout(new JShadeLayout(getContainingFrame(), getDraggableButton(), getContentPanel())) ;
         getDraggableButton().setBounds(location.x, location.y, getContainingFrame().getContentPane().getWidth() , getDraggableButton().getHeight());
         
         glassPane.add(getContentPanel()) ;
         glassPane.add(getDraggableButton());
         glassPane.setVisible(true) ;
      }
       
      @Override
      public void windowClosed(WindowEvent e) {}
       
      @Override
      public void windowClosing(WindowEvent e) {} 
       
      @Override
      public void windowDeactivated(WindowEvent e) {} 
       
      @Override
      public void windowDeiconified(WindowEvent e) {} 
       
      @Override
      public void windowIconified(WindowEvent we) { }

      @Override
      public void windowOpened(WindowEvent e) { }         
       
   }
   
   private class JShadePanelMouseHandler implements MouseListener, MouseMotionListener {
   private boolean   inDrag        =   false;
   private int       startDragX    =   0 ;
   private int       startDragY    =   0 ; 

      @Override
      public void mouseEntered(MouseEvent e) { }
   
      @Override
      public void mouseExited(MouseEvent e) { }
   
      @Override
      public void mousePressed(MouseEvent e) {
   
         setStartDragX(e.getX());
         setStartDragY(e.getY());
      }
   
      @Override
      public void mouseReleased(MouseEvent e) {
   
         setInDrag(( getInDrag() ) ? false : true) ;
      }
   
      @Override
      public void mouseClicked(MouseEvent e) { }
   
      @Override
      public void mouseDragged(MouseEvent e) {
      int   newX     =   0 ;
      int   newY     =   0 ;

         if ( getEnabled() ) {
            newX   =   getDraggableButton().getX() + (e.getX() - getStartDragX());
            newY   =   getDraggableButton().getY() + (e.getY() - getStartDragY());
            if ( (newY < (getContainingFrame().getRootPane().getHeight() - getButtonHeight())) && (newY > 0) )  {
               getDraggableButton().setLocation(0, newY);
               getContentPanel().setBounds(0,0,getContainingFrame().getContentPane().getWidth(), newY) ;
               getContainingFrame().validate() ;
            }
            
            setInDrag(true);
         }
      }
   
      @Override
      public void mouseMoved(MouseEvent arg0) { }
      
      private boolean getInDrag() {
         
         return inDrag ;
      }
      
      private int getStartDragX() {
             
         return startDragX ;
      }

      private int getStartDragY() {
             
         return startDragY ;
      }
      
      private void setInDrag(boolean drag) {
      
         inDrag   =   drag ;
      }
      
      private void setStartDragX(int x) {
          
         if ( x >= 0 ) 
            startDragX   =   x ;
         else
            throw new IllegalArgumentException() ;
      }
      
      private void setStartDragY(int y) {
          
         if ( y >= 0 ) 
            startDragX   =   y ;
         else
            throw new IllegalArgumentException() ;
      }

   }

   public JShade(JFrame frame) throws InstantiationException {

      if ( !JShadeResources.isNull(frame) ) {
         try {
            createJShadeComponents() ;
            connectToFrame(frame) ;
         } catch (Exception e) {
            throw new JShadeException(JShadeException.ERROR_009, e) ;
         }
      } else {
         throw new InstantiationException(JShadeException.ERROR_003) ;
      }
   }
   
   public void addItem(JShadeItem item) {

      if ( !JShadeResources.isNull(item) ) {
         try {
            if ( JShadeResources.isNull(getItemCollection()) )
               setItemCollection(new ArrayList()) ;
            getItemCollection().add(item) ;
            render(getContentOrientation()) ;
         } catch (Exception e) {
            throw new JShadeException(JShadeException.ERROR_002, e) ;
         }
      } else
         throw new IllegalArgumentException(JShadeException.ERROR_003) ;
   }

   private void connectToFrame(JFrame frame) {
      
      if ( !JShadeResources.isNull(frame) )
         try {
            setContainingFrame(frame);
            getContainingFrame().setLocationRelativeTo(null) ;
            getContainingFrame().addWindowListener(new JShadePanelWindowHandler()) ;
            getContainingFrame().addComponentListener(new JShadePanelComponentHandler()) ; 
         } catch (Exception e) {
            throw new JShadeException(JShadeException.ERROR_007, e) ;
         }
      else
         throw new IllegalArgumentException(JShadeException.ERROR_003) ;
   }
   
   private JPanel createContentPanel() {
   JPanel   panel   =   null ;

      try {
         panel   =   new JPanel() ;
         panel.setLayout(null) ;
         panel.setBackground(getContentPanelColor());
         panel.setOpaque(true);
         panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS)) ;
      } catch (Exception e) {
         throw new JShadeException(JShadeException.ERROR_004, e) ;
      }
      return panel ;
   }

   private JButton createDraggableButton() {
   JButton                   button                   =   null ;
   JShadePanelMouseHandler   shadePanelMouseHandler   =   null ;

      try {
         shadePanelMouseHandler   =   new JShadePanelMouseHandler() ;
         button                   =   new JButton(getButtonLabelText()) ;
         button.addMouseListener(shadePanelMouseHandler);
         button.addMouseMotionListener(shadePanelMouseHandler);
      } catch (Exception e) {
         throw new JShadeException(JShadeException.ERROR_004, e) ;
      }
      
      return button ;
   }

   private void createJShadeComponents() {
   
      try {
         setContentPanel(createContentPanel()) ;
         setDraggableButton(createDraggableButton()) ;
      } catch (Exception e) {
         throw new JShadeException(JShadeException.ERROR_008, e) ;
      }
   }
   
   private JPanel createScrollContentPanel() {
   JPanel scrollContent   =   null ;
   
      try {
         scrollContent   =   new JPanel() ;
         //scrollContent.setBackground(contentPanelColor) ;
         scrollContent.setOpaque(false) ;
         scrollContent.setLayout(new BoxLayout(scrollContent, BoxLayout.PAGE_AXIS)) ;
      } catch (Exception e) {
         throw new JShadeException(JShadeException.ERROR_004, e) ;
      }
      
      return scrollContent ;
   }
   
   private JScrollPane createScrollPane(JPanel scrollContent) {
   JScrollPane   scrollPane      =   null ;
   
      if ( !JShadeResources.isNull(scrollContent) ) {
         try {
            scrollPane   =   new JScrollPane(scrollContent) ;
            scrollPane.setOpaque(false) ;
            scrollPane.getViewport().setOpaque(false) ;
            scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(scrollBarWidth, Integer.MAX_VALUE));
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS) ;
            scrollPane.getVerticalScrollBar().addAdjustmentListener(new ContentPanelAdjustmentHandler()) ;
            scrollPane.getHorizontalScrollBar().setPreferredSize(new Dimension(Integer.MAX_VALUE, scrollBarWidth));
            scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED) ;
         } catch (Exception e) {
            throw new JShadeException(JShadeException.ERROR_004, e) ;
         }
      } else {
         throw new IllegalArgumentException(JShadeException.ERROR_003) ;
      } 
      return scrollPane ;
   }
   ;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
   private int getButtonHeight() {
       
      return buttonHeight ;
   }
   
   private String getButtonLabelText() {
      
      return buttonLabelText ;
   }
   
   private JFrame getContainingFrame() {
   
      return containingFrame ;
   }
   
   private Color getContentPanelColor() {
      
      return contentPanelColor ;
   }
   
   private JPanel getContentPanel() {
   
      return contentPanel ;
   }
   
   private Alignment getContentOrientation() {
   
      return contentOrientation ;
   }
   
   private JButton getDraggableButton() {
   
      return draggableButton ;
   }
   
   private boolean getEnabled() {
   
      return enabled ;
   }
   
   private ArrayList<JShadeItem>  getItemCollection() {
       
      return itemCollection ;
   }
   
   public void setButtonText(String text) {

      if ( JShadeResources.isTextValid(text, 25) ) {
         try {
            buttonLabelText   =   text ;
            getDraggableButton().setText(text) ;
            getDraggableButton().validate() ;
         } catch (Exception e) {
            throw new JShadeException("Error setting button text", e) ;
         }
      } else {
         throw new IllegalArgumentException(JShadeException.ERROR_003) ;
      }
   }

   private void setContainingFrame(JFrame frame) {
       
      if ( !JShadeResources.isNull(frame) )
         containingFrame   =   frame ;
      else
         throw new IllegalArgumentException(JShadeException.ERROR_003) ;
   }
   
   public void setContentPanelColor(Color color) {

      if ( JShadeResources.isColorValid(color) ) {
         contentPanelColor   =   color ;
         revalidate() ;
      } else {
         throw new IllegalArgumentException(JShadeException.ERROR_005) ;
      }
   }

   public void setContentTransparency(int transparency) {

      if ( JShadeResources.isTransparencyValid(transparency) ) {
         contentPanelTransparency   =   transparency ;
         revalidate() ;
      } else {
         throw new IllegalArgumentException(JShadeException.ERROR_006) ;
      }
   }

   /*
  public void setContentOrientation(int orientation) {

      if ( (orientation == TOP_DOWN) || (orientation == BOTTOM_UP) ) {
         contentOrientation   =   orientation ;
      } else {
         throw new IllegalArgumentException() ;
      }
   }
   */
   public void setContentOrientation(Alignment orientation) {
       
      contentOrientation   =   orientation ;
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
   
   @Override
   public void setEnabled(boolean enable) {

      try {
         enabled   =   enable ;
         getDraggableButton().setEnabled(enable) ;
         getDraggableButton().revalidate() ;
      } catch (Exception e) {
         throw new JShadeException(JShadeException.ERROR_010, e) ;
      }
   }

   private void setItemCollection(ArrayList collection) {
       
      if ( !JShadeResources.isNull(collection) )
         try {
            itemCollection   =   collection ;
         } catch (Exception e) {
            throw new JShadeException(JShadeException.ERROR_011, e) ;
         }
      else
         throw new IllegalArgumentException(JShadeException.ERROR_003) ;
   }
   
   public void setScrollBarWidth(int width) {

      if ( width >= 0 ) {
         try {
            scrollBarWidth   =   width ;
            revalidate() ;
         } catch (Exception e) {
            throw new JShadeException("Error setting scrollbar width", e) ;
         }
      } else {
         throw new IllegalArgumentException() ;
      }
   }

   public void setShadePanelLabel(String label) {

   }

   /**
    * Renders the <code>PShade</code>
    * @param alignment orientation of the items displayed within the <code>PShade</code>
    */
  /*
   private void render(int alignment) {
   JPanel        scrollContent      =   null ;
   JPanel        tPanel             =   null ;
   JScrollPane   scrollPane         =   null ;

      contentPanel.removeAll() ;
      scrollContent   =   createScrollContentPanel() ;

      if ( itemCollection.size() > 0 ) {
         if ( alignment == TOP_DOWN ) {
            for ( int i = 0 ; i < itemCollection.size() ; i++) {
               scrollContent.add(itemCollection.get(i)) ;
               scrollContent.add(Box.createRigidArea(new Dimension( 0, verticalGap )) );
            }
         } else {
            tPanel   =   new JPanel() ;
            tPanel.setOpaque(false) ;
            scrollContent.add(tPanel) ;
            for ( int i = (itemCollection.size() - 1) ; i >= 0 ; i--) {
               scrollContent.add(itemCollection.get(i)) ;
               scrollContent.add(Box.createRigidArea(new Dimension( 0, verticalGap )) );
            }
         }
      } 

      scrollPane   =   createScrollPane(scrollContent) ;
      contentPanel.add(scrollPane) ;
      contentPanel.validate();
   }
*/
   private void render(Alignment alignment) {
   ArrayList<JShadeItem>  itemCollection     =   null ;
   JPanel                 scrollContent      =   null ;
   JPanel                 tPanel             =   null ;
   JScrollPane            scrollPane         =   null ;
   ScrollContentPanel     scrollContent2     =   null ;
   
      try {
         getContentPanel().removeAll() ;
         itemCollection   =   getItemCollection() ;
         scrollContent2   =   new ScrollContentPanel() ;

         if ( itemCollection.size() > 0 ) {
            if ( alignment == Alignment.TOP_DOWN ) {
               for ( int i = 0 ; i < itemCollection.size() ; i++) {
                  scrollContent2.addItem(itemCollection.get(i)) ;
               }
            } else {
               tPanel   =   new JPanel() ;
               tPanel.setOpaque(false) ;
               scrollContent2.add(tPanel) ;
               for ( int i = (itemCollection.size() - 1) ; i >= 0 ; i--) {
                  scrollContent2.addItem(itemCollection.get(i)) ;
               }
            }
         } 

         scrollPane   =   createScrollPane(scrollContent2) ;
         getContentPanel().add(scrollPane) ;
         getContentPanel().validate();
      } catch (Exception e) {
         throw new JShadeException(JShadeException.ERROR_012, e) ;
      }
   }
}
