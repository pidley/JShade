package org.codegaucho.pidley;

import java.awt.Color ;

enum Alignment {
    TOP_DOWN, BOTTOM_UP
}

class JShadeResources {

   public static boolean isColorValid(Color color) {
   
      return ( (!isNull(color)) ? true : false  ) ;
   }
       
   public static boolean isColorValid(int red, int green, int blue) {
   
      return ( (isColorComponentValid(red) && isColorComponentValid(green) && isColorComponentValid(blue)) ? true : false  ) ;
   }
   
   public static boolean isColorComponentValid(int colorComponentValue) {
   
      return ( ((colorComponentValue >= 0) && (colorComponentValue <= 255)) ? true : false ) ;
   }
   
   public static boolean isNull(Object object) {
       
      return ( (object == null) ? true : false ) ;
   }
   
   public static boolean isTextValid(String text) {
   
      return ( (!isNull(text) && (text.length() > 0)) ? true : false ) ;     
   }
   
   public static boolean isTextValid(String text, int maximumLength) {
   
      return ( (isTextValid(text) && (text.length() <= maximumLength)) ? true : false ) ;
   }
   
   public static boolean isTransparencyValid(int transparency) {
       
      return ( ((transparency >= 0) && (transparency <= 255)) ? true : false ) ;
   }
}
