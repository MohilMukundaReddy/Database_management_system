package com.univdb;

import java.sql.*;
import java.util.Scanner;   

public class ex1{
	
	private static final String url = "jdbc:postgresql://localhost/univdb";
    private static final String user = "postgres";
    private static final String password = "Password@123";
    private static Connection conn = null;
    
    public static void spacing(int n)
	{
		for(int i = 0;i<n;i++)
		{
			System.out.print(" ");
		}
	}

//    the error is caused by the use of a parameterized table name in the query,
//    as PostgreSQL does not support passing table names as parameters in a prepared statement.
//    Instead, you need to concatenate table name to the query string and then execute the statement.
    
    
    public static void  NamesOfTable(String str,int k)
	{
    	PreparedStatement stmt = null;
    	ResultSet rs = null;
		try {  
			stmt = conn.prepareStatement("SELECT * FROM " + str + " LIMIT ?");
			stmt.setInt(1,k);
			rs = stmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int colsize = rsmd.getColumnCount();
			for(int i = 1;i <= colsize;i++)
			{
				String colname = rsmd.getColumnName(i);
				System.out.print(colname);
				spacing(15 - colname.length());
			}
			
			System.out.println("");
			while(rs.next())
			{
				for(int i = 1; i<= colsize;i++)
				{
					String s = rs.getString(i);
					System.out.print(s);
					spacing(15 - s.length() );
				}
				System.out.println("");
			}
			System.out.println("");
    	}
		
		catch(SQLException sqle)
		{
			 System.out.println("SQLException :" + sqle);
		}
		finally {
		    try {
		        if (rs != null) {
		            rs.close();
		        }
		        if (stmt != null) {
		            stmt.close();
		        }
		    } catch (SQLException e) {
		        // handle the exception
		    }
		}
		
	}
    
	
	public static void main(String[] args) {
		
		 try
		   {
			 conn = DriverManager.getConnection(url,user,password);
			 System.out.println("Enter noof tables you need :");
			 Scanner input = new Scanner(System.in);
			 int i = input.nextInt();
			 int count = 0;
			    while (count < i) {
			    	System.out.println("enter name of table and noof rows:");
			        String tablename = input.next();
			        int k = input.nextInt();
			        ex1.NamesOfTable(tablename,k);
			        count++;
			        }
			    input.close();
//			 ex1.NamesOfTable("department",10);
//			 ex1.NamesOfTable("section",10);
//			 ex1.NamesOfTable("takes",10);
		   }
		   catch(SQLException sqle)
		   {
			   System.out.println("SQLException in conn :" + sqle);
		   }
		
		
		 
	}

}
