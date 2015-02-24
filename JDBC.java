import java.sql.*;

// Terry Schmidt, Winter 2015, Database Technologies, CSC453

public class JDBC {
	
	  // JDBC driver name and database URL
	  static final String JDBC_DRIVER = "oracle.jdbc.OracleDriver";
	  static final String DB_URL = "jdbc:oracle:thin:@cdmoracledb.cti.depaul.edu:1521:def";
	  
	  // statement variables
	  static Connection conn = null;
  	  static Statement stmt = null;
  	  
		  public static void main(String[] args) throws Exception {
		    try {
		    	Class.forName(JDBC_DRIVER);
		    
		    	System.out.println("Connecting to database...");
		    	conn = DriverManager.getConnection(DB_URL, args[0], args[1]);
		    	System.out.println("Connected...");
		    	System.out.println("");
		    
		    	stmt = conn.createStatement();
		    	
		    	String createTableSQL = "CREATE TABLE vendors7 AS (SELECT * FROM vendors)";

		    	System.out.println("Executing vendors7 table creation...");
		    	stmt.executeUpdate(createTableSQL);
		    	System.out.println("Created vendors7 table in database...");
		    	System.out.println("");
		    	
		    	// Print table
		    	System.out.println("Printing table...");
		    	System.out.println("");
		    	
		    	ResultSet rs = stmt.executeQuery("SELECT VENDOR_ID, "
		    			+ "VENDOR_NAME, "
		    			+ "VENDOR_ADDRESS1, "
		    			+ "VENDOR_ADDRESS2, "
		    			+ "VENDOR_CITY, "
		    			+ "VENDOR_STATE, "
		    			+ "VENDOR_ZIP_CODE, "
		    			+ "VENDOR_PHONE, "
		    			+ "VENDOR_CONTACT_LAST_NAME, "
		    			+ "VENDOR_CONTACT_FIRST_NAME,"
		    			+ "DEFAULT_TERMS_ID, "
		    			+ "DEFAULT_ACCOUNT_NUMBER, "
		    			+ "VENDOR_CITY || VENDOR_STATE, " // first additional field
		    			+ "VENDOR_CONTACT_FIRST_NAME || ' ' || VENDOR_CONTACT_LAST_NAME as FULL_NAME " // second additional field
		    			+ "FROM vendors7");
		    	
		    	ResultSetMetaData rsmd = rs.getMetaData();
		    	
		    	int column2precision = rsmd.getPrecision(2); 
		   
		    	int columnCount = rsmd.getColumnCount();
		    	
		    	
		    	// Get column names
		    	String col1 = rsmd.getColumnName(1); String col2 = rsmd.getColumnName(2); String col3 = rsmd.getColumnName(3); String col4 = rsmd.getColumnName(4); String col5 = rsmd.getColumnName(5); String col6 = rsmd.getColumnName(6);
		    	String col7 = rsmd.getColumnName(7); String col8 = rsmd.getColumnName(8); String col9 = rsmd.getColumnName(9); String col10 = rsmd.getColumnName(10); String col11 = rsmd.getColumnName(11); String col12= rsmd.getColumnName(12);
		    	String col13 = rsmd.getColumnName(13); String col14 = rsmd.getColumnName(14);
		    	
		    	// Print column names (spaces for formatting)
		    	System.out.print(col1 + "                  ");
		    	System.out.print(col2 + "                            ");
		    	System.out.print(col3 + "                  ");
		    	System.out.print(col4 + "          ");
		    	System.out.print(col5 + "     ");
		    	System.out.print(col6 + "      ");
		    	System.out.print(col7 + "      ");
		    	System.out.print(col8 + "   ");
		    	System.out.print(col9 + "    ");
		    	System.out.print(col10 + "    ");
		    	System.out.print(col11 + "  ");
		    	System.out.print(col12 + "   ");
		    	System.out.print(col13 + "   ");
		    	System.out.print(col14 + "   ");
		    	
		    	System.out.println("");
		    	while (rs.next()) {
		    	      // Retrieve by column name
		    	      int VENDOR_ID = rs.getInt("VENDOR_ID");
		    	      String VENDOR_NAME = rs.getString("VENDOR_NAME");
		    	      String VENDOR_ADDRESS1 = rs.getString("VENDOR_ADDRESS1");
		    	      String VENDOR_ADDRESS2 = rs.getString("VENDOR_ADDRESS2");
		    	      String VENDOR_CITY = rs.getString("VENDOR_CITY");
		    	      String VENDOR_STATE = rs.getString("VENDOR_STATE");
		    	      String VENDOR_ZIP_CODE = rs.getString("VENDOR_ZIP_CODE");
		    	      String VENDOR_PHONE = rs.getString("VENDOR_PHONE");
		    	      String VENDOR_CONTACT_LAST_NAME = rs.getString("VENDOR_CONTACT_LAST_NAME");
		    	      String VENDOR_CONTACT_FIRST_NAME = rs.getString("VENDOR_CONTACT_FIRST_NAME");
		    	      int DEFAULT_TERMS_ID = rs.getInt("DEFAULT_TERMS_ID");
		    	      int DEFAULT_ACCOUNT_NUMBER = rs.getInt("DEFAULT_ACCOUNT_NUMBER");
		    	      String CITY_STATE = rs.getString("VENDOR_CITY||VENDOR_STATE");
		    	      String FULL_NAME = rs.getString("FULL_NAME");

		    	      // Display values
		    	      
		    	      // Formatting for VENDOR ID
		    	      if(VENDOR_ID < 10) {
		    	      System.out.print(VENDOR_ID + "         |");
		    	      }
		    	      if(VENDOR_ID >= 10 && VENDOR_ID <= 99) {
		    	    	  System.out.print(VENDOR_ID + "        |");
		    	      }
		    	      if(VENDOR_ID >= 100) {
		    	    	  System.out.print(VENDOR_ID + "       |");
		    	      }
		    	      
		    	      
		    	      // Formatting for VENDOR NAME
		    	      int spacesToAdd = column2precision - VENDOR_NAME.length();
		    	      for (int i = 0; i < spacesToAdd; i++) {
		    	    	  VENDOR_NAME = VENDOR_NAME + " ";
		    	      }
		    	      System.out.print(VENDOR_NAME  + "|");
		    	      
		    	      
		    	      // Formatting for ADDRESS1
		    	      int spacesToAdd2 = 31 - VENDOR_ADDRESS1.length();
		    	      for(int i = 0; i < spacesToAdd2; i++) {
		    	    	  VENDOR_ADDRESS1 = VENDOR_ADDRESS1 + " ";
		    	      }
		    	      System.out.print(VENDOR_ADDRESS1 + " |");
		    	      
		    	      
		    	      // Formatting for ADDRESS2
		    	      int spacesToAdd3 = 25 - VENDOR_ADDRESS2.length();
		    	      for(int i = 0; i < spacesToAdd3; i++) {
		    	    	  VENDOR_ADDRESS2 = VENDOR_ADDRESS2 + " ";
		    	      }
		    	      System.out.print(VENDOR_ADDRESS2  + " |");
		    	      
		    	      
		    	      // Formatting for VENDOR_CITY
		    	      int spacesToAdd4 = 16 - VENDOR_CITY.length();
		    	      for(int i = 0; i < spacesToAdd4; i++) {
		    	    	  VENDOR_CITY = VENDOR_CITY + " ";
		    	      }
		    	      System.out.print(VENDOR_CITY  + "|");
		    	      
		    	      
		    	      // Formatting for VENDOR_STATE
		    	      System.out.print(VENDOR_STATE  + "             |");
		    	      
		    	      // Formatting for VENDOR_ZIP_CODE
		    	      System.out.print(VENDOR_ZIP_CODE  + "                 |");
		    	      
		    	      // Formatting for VENDOR_PHONE
		    	      int spacesToAdd5 = 14 - VENDOR_PHONE.length();
		    	      for(int i = 0; i < spacesToAdd5; i++) {
		    	    	  VENDOR_PHONE = VENDOR_PHONE + " ";
		    	      }
		    	      System.out.print(VENDOR_PHONE  + " | ");
		    	      
		    	      
		    	      // Formatting for VENDOR_CONTACT_LAST_NAME
		    	      int spacesToAdd6 = 25 - VENDOR_CONTACT_LAST_NAME.length();
		    	      for(int i = 0; i < spacesToAdd6; i++) {
		    	    	  VENDOR_CONTACT_LAST_NAME = VENDOR_CONTACT_LAST_NAME + " ";
		    	      }
		    	      System.out.print(VENDOR_CONTACT_LAST_NAME  + " | ");
		    	      
		    	      
		    	      // Formatting for VENDOR_CONTACT_FIRST_NAME
		    	      int spacesToAdd7 = 25 - VENDOR_CONTACT_FIRST_NAME.length();
		    	      for(int i = 0; i < spacesToAdd7; i++) {
		    	    	  VENDOR_CONTACT_FIRST_NAME = VENDOR_CONTACT_FIRST_NAME + " ";
		    	      }
		    	      System.out.print(VENDOR_CONTACT_FIRST_NAME + " | ");
		    	      
		    	      
		    	      // Formatting for DEFAULT_TERMS_ID
		    	      System.out.print(DEFAULT_TERMS_ID  + "                | ");
		    	      
		    	      
		    	      // Formatting for DEFAULT_ACCOUNT_NUMBER
		    	      System.out.print(DEFAULT_ACCOUNT_NUMBER + "                   | ");
		    	      
		    	      
		    	      // Formatting for CITY_STATE
		    	      int spacesToAdd8 = 25 - CITY_STATE.length();
		    	      for(int i = 0; i < spacesToAdd8; i++) {
		    	    	  CITY_STATE = CITY_STATE + " ";
		    	      }
		    	      System.out.print(CITY_STATE + " | ");
		    	      
		    	      
		    	      // Fomatting for FULL_NAME
		    	      System.out.println(FULL_NAME);
		    	    }
		    	
		    	System.out.println("");
		    	System.out.println("Table printed.");
		    	System.out.println("");
		    	
		    } finally {
		    	//drop table
		    	System.out.println("Dropping vendors7...");
		    	String closeTableSQL = "DROP TABLE vendors7";
		    	stmt.executeUpdate(closeTableSQL);
		    	System.out.println("Vendors7 table dropped...");
		    	System.out.println("");
		    	
		    	
		    	//close connection
		    	System.out.println("Closing connection...");
		    	stmt.close();
			    conn.close();
			    System.out.println("Connection closed.");
		    }
	}
}
