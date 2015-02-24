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
		    			+ "VENDOR_ADDRESS2 "
		    			+ "FROM vendors7");
		    	
		    	ResultSetMetaData rsmd = rs.getMetaData();
		    	
		    	int column2precision = rsmd.getPrecision(2); 
		   
		    	int columnCount = rsmd.getColumnCount();
		    	
		    	
		    	// Get column names
		    	String col1 = rsmd.getColumnName(1); String col2 = rsmd.getColumnName(2); String col3 = rsmd.getColumnName(3); String col4 = rsmd.getColumnName(4);
		    	
		    	// Print column names (spaces for formatting)
		    	System.out.print(col1 + "             ");
		    	System.out.print(col2 + "                                   ");
		    	System.out.print(col3 + "                            ");
		    	System.out.print(col4 + "          ");
		    	
		    	System.out.println("");
		    	while (rs.next()) {
		    	      // Retrieve by column name
		    	      int VENDOR_ID = rs.getInt("VENDOR_ID");
		    	      String VENDOR_NAME = rs.getString("VENDOR_NAME");
		    	      String VENDOR_ADDRESS1 = rs.getString("VENDOR_ADDRESS1");
		    	      String VENDOR_ADDRESS2 = rs.getString("VENDOR_ADDRESS2");

		    	      // Display values
		    	      
		    	      // Formatting for VENDOR ID
		    	
		    	    	  System.out.print(VENDOR_ID + "              ");
		    	      
		    	      
		    	      int spacesToAdd = 50 - VENDOR_NAME.length();
		    	      for(int i = 0; i < spacesToAdd; i++){
		    	    	  VENDOR_NAME = VENDOR_NAME + " ";
		    	      }
		    	      System.out.print(VENDOR_NAME);
		    	      
		    	      
		    	      int spacesToAdd2 = 50 - VENDOR_ADDRESS1.length();
		    	      for(int i = 0; i < spacesToAdd2; i++) {
		    	    	  VENDOR_ADDRESS1 = VENDOR_ADDRESS1 + " ";
		    	      }
		    	      System.out.print(VENDOR_ADDRESS1);
		    	      
		    	   
		    	      System.out.print(VENDOR_ADDRESS2);
		    	      
		    	     
		 
		    	      System.out.println("");
		    	      
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
