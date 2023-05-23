package com.univdb;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ex5{
	
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


    
    
    public static void top(int k)
	{
    	PreparedStatement stmttop = null;
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
			
            
            
			stmttop = conn.prepareStatement(" select a.id,sum(a.point*b.credits)/sum(b.credits) as cgpa, RANK() OVER (ORDER BY sum(a.point*b.credits)/sum(b.credits) DESC) as ranking  from (select * from takes join grademap on takes.grade = grademap.name ) as a join course as b on a.course_id = b.course_id group by a.id limit ?");
			stmttop.setInt(1,k);
			rs = stmttop.executeQuery();
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
		        if (stmttop != null) {
		            stmttop.close();
		        }
		    } catch (SQLException e) {
		        // handle the exception
		    }
		}
		
	}
    

    public static void topdept(String q,int k)
    {
    	PreparedStatement stmttop = null;
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
			
            
       
            stmttop = conn.prepareStatement("select * from (select x.id as id,student.dept_name,cgpa,rank() over (order by cgpa desc) as departmental_rank "
					+ "                            from  (select a.id as id,sum(a.point*b.credits)/sum(b.credits) as cgpa"
					+ "                              from (select * from takes join grademap on takes.grade = grademap.name ) as a join course as b on a.course_id = b.course_id "
					+ "                               group by a.id ) as x join student on student.id = x.id  where dept_name = ? ) as final where final.departmental_rank <= ? ;");
            stmttop.setString(1,q);
            stmttop.setInt(2,k);
			
			rs = stmttop.executeQuery();
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
			
			String dropTableSQL = "DROP table IF EXISTS grademap";
			try (Statement statement = conn.createStatement()) {
			    statement.executeUpdate(dropTableSQL);
			} catch (SQLException e) {
			    System.out.println("problem dropping table:" + e);
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
		        if (insertStmt != null) {
		            rs.close();
		        }
		        if (stmtcreate != null) {
		            rs.close();
		        }
		        if (stmttop != null) {
		            stmttop.close();
		        }
		    } catch (SQLException e) {
		        // handle the exception
		    }
		}
		
	}
    
    
    public static void topcourse(String q,int k)
    {
    	PreparedStatement stmttop = null;
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
			
            
       
            stmttop = conn.prepareStatement("select * from (select course_id,x.id,cgpa,rank() over (order by cgpa desc) as ranking "
            		+ "                    from (select a.id,sum(a.point*b.credits)/sum(b.credits) as cgpa from (select * from takes join grademap on takes.grade = grademap.name ) as a join course as b on a.course_id = b.course_id group by a.id ) as x join takes on takes.id = x.id "
            		+ "                       where takes.course_id = ? ) as final where final.ranking <= ?  ;");
            stmttop.setString(1,q);
            stmttop.setInt(2,k);
			rs = stmttop.executeQuery();
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
		        if (stmttop != null) {
		            stmttop.close();
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
			 Scanner input = new Scanner(System.in);
			 System.out.println("Enter noof top students you want to check :");
			 int k = input.nextInt();
			 ex5.top(k);
			 
			 
			 System.out.println("Enter noof departments you want to check :");
			 int i = input.nextInt();
			 input.nextLine();
			 int count = 0;
			 System.out.println("enter department and noof top rankers:");
			    while (count < i) {
			        String dept = input.nextLine();
			        ex5.topdept(dept,k);
			        count++;
			        }
			    
			    System.out.println("Enter noof courses you want to check :");
				 i = input.nextInt();
				 input.nextLine();
				 count = 0;
				    while (count < i) {
				    	System.out.println("enter course_id and noof top rankers:");
				        String dept = input.nextLine();
				        ex5.topcourse(dept,k);
				        count++;
				        }
			    input.close();
			 
//			 ex5.top(5);
//			 
//			 ex5.topdept("Psychology",5);
//			 ex5.topdept("Elec. Eng.",5);
//			 ex5.topdept("Civil Eng.",5);
//			 
//			 ex5.topcourse(237,5);
//			 ex5.topcourse(349,5);
//			 ex5.topcourse(735,5);
//			 
		   }
		   catch(SQLException sqle)
		   {
			   System.out.println("SQLException in conn :" + sqle);
		   }
	}

}

