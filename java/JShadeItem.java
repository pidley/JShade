package org.codegaucho.pidley;

import java.awt.* ;
import javax.swing.* ;
import javax.swing.border.* ;

public class JShadeItem extends JShadeComponent {
private static final long serialVersionUID         =   -1599168358880154305L;

/**
 * Default transparency value
 */
private final   int      DEFAULT_TRANSPARENCY      =   175 ;
/**
 * Default background color with default transparency applied
 */
private final   Color    DEFAULT_BACKGROUNDCOLOR   =   new Color(0x03,0x03,0x03,DEFAULT_TRANSPARENCY) ;
/**
 * Default height
 */
private final   int      DEFAULT_HEIGHT            =   50 ;
/**
 * Default border.  Standard raised bevel border.  
 */
private final   Border   DEFAULT_BORDER            =   BorderFactory.createRaisedBevelBorder() ;
/**
 * Currently set border.  Initialized to DEFAULTBORDER.
 */
private         Border   itemBorder               =   DEFAULT_BORDER ;
/**
 * Currently set background color.  Initialized to DEFAULTBACKGROUNDCOLOR.
 */
private         Color    backgroundColor          =   DEFAULT_BACKGROUNDCOLOR ;
/**
 * Currently set transparency of the background color.  Initialized to DEFAULTTRANSPARENCY.
 */
private         int      backgroundTransparency   =   DEFAULT_TRANSPARENCY ;
/**
 * Currently set height of the item.  Initialized to DEFAULTHEIGHT
 */
private         int      height                   =   DEFAULT_HEIGHT ;

/**
 * Constructor creates a default <code>ShadeItem</code>
 * 
 * @throws InstantiationException when any method called by the constructor fails
 */
   public JShadeItem() throws InstantiationException {

      try {
         setMaximumSize(new Dimension(Toolkit.getDefaultToolkit().getScreenSize().width, getHeight())) ;
         setMinimumSize(new Dimension(100, getHeight())) ;
         setPreferredSize(new Dimension(100, getHeight())) ;
 
         setBackgroundColor(getBackgroundColor()) ;
         setItemBorder(getItemBorder()) ;
      } catch (Exception e) {
         throw new InstantiationException(JShadeException.ERROR_016) ;
      }
   }

/**
 * Returns current background color.
 * 
 * @return current background color
 */
   public final Color getBackgroundColor() {

      return backgroundColor ;
   }

/**
 * Returns current background transparency
 * 
 * @return current background transparency 
 */
   public final int getBackgroundTransparency() {

      return backgroundTransparency ;
   }

/**
 * Returns height
 * @return height 
 */   
   
  @Override
  public final int getHeight() {
      
     return height ;
  }
   
/**
 * Returns current <code>Border</code>
 * 
 * @return current border 
 */
   public final Border getItemBorder() {

      return itemBorder ;
   }

/**
 * Sets the <code>PShadeItem</code>'s background color to the specified color
 * 
 * @param color new rendering color
 * @throws JShadeException when <code>color</code> is null.
 */
   public final void setBackgroundColor(Color color) {

      if ( !JShadeResources.isNull(color) ) {
         try {
            backgroundColor   =   new Color(color.getRed(), color.getGreen(), color.getBlue(), backgroundTransparency) ;
            setBackground(backgroundColor) ;
         } catch (Exception e) {
            throw new JShadeException(JShadeException.ERROR_013, e) ;
         }
      } else {
         throw new IllegalArgumentException(JShadeException.ERROR_005) ;
      }
   }

/**
 * Sets the <code>PShadeItem</code>'s background color to the color specified by the
 * specified <code>red</code>, <code>green</code>, and <code>blue</code> values.
 * 
 * @param red Red color component value of the new rendering color
 * @param green Green color component value of the new rendering color
 * @param blue Blue color component value of the new rendering color
 * @throws JShadeException when any color component value is not in the range
 * >= 0 and < 256
 */   
   public final void setBackgroundColor(int red, int green, int blue) {

      if ( JShadeResources.isColorValid(red, green, blue) ) {
         try {
            backgroundColor   =   new Color(red, green, blue, backgroundTransparency) ;
            setBackground(backgroundColor) ;
            validate() ;
         } catch (Exception e) {
            throw new JShadeException(JShadeException.ERROR_013, e) ;
         }
      } else {
         throw new IllegalArgumentException(JShadeException.ERROR_005) ;
      }
   }

/**
 * Sets the transparency value of the <code>PShadeItem</code>'s background color to the
 * value specified.
 * 
 * @param transparency new rendered transparency
 * @throws JShadeException when the specified transparency value is not in the 
 * range >= 0 and < 256
 */   
   public final void setBackgroundTransparency(int transparency) {

      if ( JShadeResources.isTransparencyValid(transparency) ) {
         try {
            backgroundTransparency   =   transparency ;
            backgroundColor          =   new Color(backgroundColor.getRed(), backgroundColor.getGreen(), backgroundColor.getBlue(), backgroundTransparency) ;
            setBackground(backgroundColor) ;
            validate() ;
         } catch (Exception e) {
            throw new JShadeException(JShadeException.ERROR_014, e) ;
         }
      } else {
         throw new IllegalArgumentException(JShadeException.ERROR_006) ;
      }
   }

/**
 * Set the <code>JShadeItem</code>'s border to the specified border.
 * 
 * @param border new rendered border
 * @throws JShadeException when the specified border is null
 */
   public final void setItemBorder(Border border) {

      if ( !JShadeResources.isNull(border) ) {
         try {
            setBorder(border) ;
            validate() ;
         } catch (Exception e) {
            throw new JShadeException(JShadeException.ERROR_015, e) ;
         }
      } else {
         throw new IllegalArgumentException(JShadeException.ERROR_003) ;
      }
   }

}

