package com.univdb;

import java.sql.*;
import java.util.Scanner;

public class ex2 {
	
	
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
    
    
    public static void  prereq(String id)
	{
    	PreparedStatement stmt = null;
    	ResultSet rs = null;
		try 
		   {
			
			stmt = conn.prepareStatement("with recursive preq(course_id,prereq_id) as (select course_id,prereq_id from prereq "
					+ "  union select preq.course_id,prereq.prereq_id from preq,prereq where preq.prereq_id = prereq.course_id ) "
					+ " select preq.course_id,preq.prereq_id,course.title from preq join course on preq.prereq_id = course.course_id  where preq.course_id = ? ;"); 
			stmt.setString(1, id);
			rs = stmt.executeQuery(); 
			ResultSetMetaData rsmd = rs.getMetaData();
			int colsize = rsmd.getColumnCount();
			for(int i = 1;i <= colsize;i++)
			{
				String colname = rsmd.getColumnName(i);
				System.out.print(colname);
				spacing(20 - colname.length());
			}
			
			System.out.println("");
			while(rs.next())
			{
				for(int i = 1; i<= colsize;i++)
				{
					String s = rs.getString(i);
					System.out.print(s);
					spacing(20 - s.length() );
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
			 System.out.println("Enter noof course_id you want to check :");
			 Scanner input = new Scanner(System.in);
			 int i = input.nextInt();
			 int count = 0;
			    while (count < i) {
			    	System.out.println("enter course_id:");
			        String k = input.next();
			        ex2.prereq(k);
			        count++;
			        }
			    input.close();
			    
//             ex2.prereq(276);
//             ex2.prereq(647);
//             ex2.prereq(496);
		   }
		   catch(SQLException sqle)
		   {
			   System.out.println("SQLException in conn :" + sqle);
		   }
	}

}
