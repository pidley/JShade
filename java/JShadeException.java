package org.codegaucho.pidley;

/**
 * Exception thrown from the body of all classes of the <coce>org.pidley.Shade</code>
 * package when functionality specific to member methods fail.
 *
 * @author R. L. E C K M A N
 * @version 1.0
 */
public class JShadeException extends RuntimeException {
public static final String ERROR_001 = "001: JShadeComponent not properly instantiated" ;
public static final String ERROR_002 = "002: Item was not added to Scroll Content Panel" ;    
public static final String ERROR_003 = "003: Argument to method contained null value" ;    
public static final String ERROR_004 = "004: JShadeComponent not properly created" ;    
public static final String ERROR_005 = "005: Invalid color value provided" ;  
public static final String ERROR_006 = "006: Invalid transparency value provided" ;  
public static final String ERROR_007 = "007: Cannot connect JShade to containing JFrame" ;  
public static final String ERROR_008 = "008: Cannot create JShade;  JShadeComponent error" ;
public static final String ERROR_009 = "009: Cannot create JShade" ;  
public static final String ERROR_010 = "010: JShade not enabled" ; 
public static final String ERROR_011 = "011: Item collection not valid" ;  
public static final String ERROR_012 = "012: Error rendering JShade" ;  
public static final String ERROR_013 = "013: Color creation failed" ;
public static final String ERROR_014 = "014: Transparency creation failed" ;
public static final String ERROR_015 = "015: Border assignment failed" ;
public static final String ERROR_016 = "016: Instantiation failed" ;
public static final String ERROR_017 = "017: Layout creation failed" ;


   JShadeException() {

      super("ShadePanel exception") ;
   }

   JShadeException(String exceptionMessage) {

      super(exceptionMessage) ;
   }

   JShadeException(String exceptionMessage, Throwable cause) {

      super(exceptionMessage, cause) ;
   }

   JShadeException(Throwable cause) {

      super(cause) ;
   }

}
