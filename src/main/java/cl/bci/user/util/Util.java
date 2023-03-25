package cl.bci.user.util;

import java.sql.Timestamp;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {
	
	/**
	 * 
	 * @param regx
	 * @param data
	 * @return true or false
	 */
	public static boolean matcherData( String regx, String data ) {
		
        Pattern pattern = Pattern.compile(regx);
        Matcher matcher = pattern.matcher( data );
		return matcher.matches();
		
	}
	
	/**
	 * 
	 * @return Timestamp
	 */
	public static Timestamp getTime() {
		Date date = new Date();
		Timestamp ts = new Timestamp( date.getTime() );
		return ts;
	}

}
