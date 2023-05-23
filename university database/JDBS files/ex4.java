package com.univdb;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ex4{
	
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


    
    
    public static void cgpa(String id)
	{
    	PreparedStatement stmt = null;
    	PreparedStatement stmtcreate = null;
    	PreparedStatement insertStmt=null;
    	ResultSet rs = null;
		try {  
			
			stmtcreate = conn.prepareStatement("CREATE temp TABLE IF NOT EXISTS grademap (name VARCHAR(2),point int) ");
            stmtcreate.executeUpdate();
            String insertSql = "INSERT INTO grademap (name,point) VALUES (?, ?)";
            insertStmt = conn.prepareStatement(insertSql);
            
            List<String> myList = new ArrayList<String>();
            myList.add("A+");
            myList.add("A ");
            myList.add("A-");
            myList.add("B+");
            myList.add("B ");
            myList.add("B-");
            myList.add("C+");
            myList.add("C ");
            myList.add("C-");
            
            for (int i = 0; i < 9; i++) {
                insertStmt.setString(1, myList.get(i));
                insertStmt.setInt(2, 10 - i);
                insertStmt.executeUpdate();
            }
			
			stmt = conn.prepareStatement(" select a.id,sum(a.point*b.credits)/sum(b.credits) as cgpa from (select * from takes join grademap on takes.grade = grademap.name where takes.id = ? ) as a join course as b on a.course_id = b.course_id group by a.id ");
			stmt.setString(1,id);
			rs = stmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int colsize = rsmd.getColumnCount();
			for(int i = 1;i <= colsize;i++)
			{
				String colname = rsmd.getColumnName(i);
				System.out.print(colname);
				spacing(10 - colname.length());
			}
			
			System.out.println("");
	
			while(rs.next())
			{
	
				for(int i = 1; i<= colsize;i++)
				{
					String s = rs.getString(i);
					System.out.print(s);
					spacing(10 - s.length() );
				}
				System.out.println("");
			}
			System.out.println("");
			
			String dropTableSQL = "DROP table IF EXISTS grademap";
			try (Statement statement = conn.createStatement()) {
			    statement.executeUpdate(dropTableSQL);
			} catch (SQLException e) {
			    System.out.println("problem dropping table:" + e);
			}
			
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
		        if (insertStmt != null) {
		            rs.close();
		        }
		        if (stmtcreate != null) {
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
			 System.out.println("Enter noof id's you want to check :");
			 Scanner input = new Scanner(System.in);
			 int i = input.nextInt();
			 int count = 0;
			    while (count < i) {
			    	System.out.println("enter id:");
			        String k = input.next();
			        ex4.cgpa(k);
			        count++;
			        }
			    input.close();
			 
//			 ex4.cgpa(76672);
//			 ex4.cgpa(90567);
//			 ex4.cgpa(4582);
//			 ex4.cgpa(81258);
		   }
		   catch(SQLException sqle)
		   {
			   System.out.println("SQLException in conn :" + sqle);
		   }
	}

}
