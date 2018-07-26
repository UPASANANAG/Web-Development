package HRManagement;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.PreparedStatement;

public class Company 
{
	public static void main(String[] args) throws Exception 
	{
		BufferedReader ob=new BufferedReader(new InputStreamReader(System.in));
		
		
		System.out.println("Welcome:");
		System.out.println("Enter User Name");
		String uname=ob.readLine();
		System.out.println("Enter password:");
		String pass=ob.readLine();
		
		User_login user=loginTest(uname,pass);
		if(user==null)
		{
			System.out.println("Please try again");
		}
		else
		{
			Scanner k=new Scanner(System.in);
			System.out.println("Welcome = "+user.getName());
			while(true){
			System.out.println("1.Create another user:");
			System.out.println("2.Generate Resources:");
			System.out.println("3.Approved");
			System.out.println("4.Recruited");
			System.out.println("5.Delete User");
			System.out.println("6.Delete Request");
			System.out.println("7.LogOut");
			System.out.println("Enter Choice");
			int choice=k.nextInt();
			
			switch(choice)
			{
			    case 1:
			     createUser();
			    	break;
			    case 2:
			    	generateResources();
			    	break;
			    case 3:
			    	String desg=user.getDesn();
			    	if(desg.equalsIgnoreCase("HR"))
					{
			    	approveResource();
					}
			    	else
			    	{
						System.out.println("You are not elligible to choose the option");
					}
			    	break;
			    case 4:
			    	String dept=user.getDesn();
					System.out.println(dept);
					if(dept.equalsIgnoreCase("HR"))
					{
						recruitResource();
					}
					else
					{
						System.out.println("You are not elligible to use this option");
					}
			    	break;
			    case 7:
			    	logOut();
			    	break;
			    case 5:
			    	String dept2=user.getDesn();
					System.out.println(dept2);
					if(dept2.equalsIgnoreCase("SystemUser"))
					{
					deleteUser();
					}
					else
					{
						System.out.println("You are not elligible to use this option");
					}
			    	break;
			    	
			    	
			    case 6:
			    	String dept1=user.getDesn();
					System.out.println(dept1);
					if(dept1.equalsIgnoreCase("HR"))
					{
			    	deleteRequest();
					}
					else
					{
						System.out.println("You are not elligible to use this option");
					}
			    	break;
				default:
					System.out.println("Wrong choice");
			}
			
			}
		}
	}
	private static void deleteRequest() {
		// TODO Auto-generated method stub
		Connection con3 = null;
		try{
		 con3 = getCon();
		 Statement stmt = con3.createStatement();
		 ResultSet rs = stmt.executeQuery("Select * from RESOURCES where STATUS='SUBMITTED' ");
		 System.out.println("Proceed to delete an UserRequest-->");
		 while(rs.next())
			{   
				
			 System.out.println(rs.getInt(1)+"\t"+rs.getString(3)+"\t"+rs.getString(4)+"\t"+rs.getString(4));
				Resource_generate resource = new Resource_generate(); 
				resource.setRec_ID(rs.getInt(1));
				resource.setSkills(rs.getString(3)); 
				resource.setExperience(rs.getString(4));
				resource.setStatus(rs.getString(5));
				
				
			
	}
		 System.out.println("Enter the ID you want to delete from the system:");
		 BufferedReader ob=new BufferedReader(new InputStreamReader(System.in));
		 int id=Integer.parseInt(ob.readLine());
		 int count3 = stmt.executeUpdate("delete from RESOURCES  where REC_ID='"+id+"'");
		 System.out.println("User Request deleted successfully");
		 } catch(Exception e){
			 e.printStackTrace();
		 }finally{
			 try {
				con3.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		
			
			
		 }
		
		
		
	
	private static void deleteUser() {
		// TODO Auto-generated method stub
		Connection con2 = null;
		try{
		 con2 = getCon();
		 Statement stmt = con2.createStatement();
		 ResultSet rs = stmt.executeQuery("Select * from USERS");
		 System.out.println("Proceed to delete an User-->");
		 while(rs.next())
			{   
				System.out.println(rs.getString(1)+"\t"+rs.getInt(2)+"\t"+rs.getString(4));
				User_login u=new User_login();
				u.setDesn(rs.getString(4));
				u.setId(rs.getInt(2));
				u.setName(rs.getString(1));
			
	}
		 System.out.println("Enter the ID you want to delete from the system:");
		 BufferedReader ob=new BufferedReader(new InputStreamReader(System.in));
		 int id=Integer.parseInt(ob.readLine());
		 int count1 = stmt.executeUpdate("delete from users  where ID='"+id+"'");
		 System.out.println("User Succesfully deleted from the system");
		 } catch(Exception e){
			 e.printStackTrace();
		 }finally{
			 try {
				con2.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		
			
			
		 }
		
	private static void logOut() {
		// TODO Auto-generated method stub
		System.out.println("Log Out Successful");
		System.exit(0);
		
	}
	private static void approveResource() {
		// TODO Auto-generated method stub
		Connection con1 = null;
		try{
		 con1 = getCon();
		 Statement stmt = con1.createStatement();
		 ResultSet rs = stmt.executeQuery("Select * from RESOURCES where STATUS='SUBMITTED' ");
		 int n=0;
		 while(rs.next())
			{
			 n++;
				System.out.println(rs.getInt(1)+"\t"+rs.getString(3)+"\t"+rs.getString(4)+"\t"+rs.getString(5));
			
			 
			Resource_generate resource = new Resource_generate(); 
			resource.setRec_ID(rs.getInt(1));
			resource.setSkills(rs.getString(3)); 
			resource.setExperience(rs.getString(4));
			resource.setStatus(rs.getString(5));
			resource.setDate(rs.getString(6));
			
	
			}
		 
		 if(n==0)
		 {
			 System.out.println("No data");
		 }
		 else
		 {
		 System.out.println("Enter the ID you want to approve:");
		 BufferedReader ob=new BufferedReader(new InputStreamReader(System.in));
		 int id=Integer.parseInt(ob.readLine());
		// System.out.println("Enter the Joining Date:");
		// String date=ob.readLine();
		 int a = stmt.executeUpdate("update resources set status='APPROVED' where REC_ID="+id+"");
		 }
		 } catch(Exception e){
			 e.printStackTrace();
		 }finally{
			 try {
				con1.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		
		
			
			
		 }

		
	
	private static void recruitResource() {
		// TODO Auto-generated method stub
		Connection con1 = null;
		try{
		 con1 = getCon();
		 Statement stmt = con1.createStatement();
		 ResultSet rs = stmt.executeQuery("Select * from RESOURCES where STATUS='APPROVED'");
		 int l =0;
		 while(rs.next())
			{   l++;
				System.out.println(rs.getInt(1)+"\t"+rs.getString(3)+"\t"+rs.getString(4)+"\t"+rs.getString(5));
			
			 
			Resource_generate resource = new Resource_generate(); 
			resource.setRec_ID(rs.getInt(1));
			resource.setSkills(rs.getString(3)); 
			resource.setExperience(rs.getString(4));
			resource.setStatus(rs.getString(5));
			}
	
			 if(l==0)
			 {
				 System.out.println("No data");
			 }
			 else
			 {
		 
		 System.out.println("Enter the ID you want to recruit:");
		 BufferedReader ob=new BufferedReader(new InputStreamReader(System.in));
		 int id=Integer.parseInt(ob.readLine());
		 System.out.println("Enter the Joining Date:");
	     String date=ob.readLine();
		  System.out.println("Enter the Name:");
	     String name=ob.readLine();
		 
		 int count = stmt.executeUpdate("update resources set NAME='"+name+"',EXP_JOIN_DATE='"+date+"', status='APPROVED' where REC_ID="+id);
		 }
			}catch(Exception e){
			 e.printStackTrace();
		 }finally{
			 try {
				con1.close();
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		
			
			
		 }
		
	
	private static void generateResources() {
		Connection con = null;
		try{
		 con = getCon();
		 Statement stmt = con.createStatement();
		Scanner scp = new Scanner(System.in);
		System.out.println("Enter skills required");
		String rskill = scp.nextLine();
		System.out.println("Enter experience:");
		String exp = scp.nextLine();
		System.out.println("Enter STATUS:");
		String sta = scp.nextLine();
		
		String sql1 = "INSERT INTO RESOURCES (SKILLS, EXPERIENCE,STATUS)" +
		        "VALUES ('"+rskill+"','"+exp+"','"+sta+"')";
		stmt.executeUpdate(sql1);
		//con.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private static void createUser() 
	{
		// TODO Auto-generated method stub
		Connection con = null;
		try{
		 con = getCon();
		 Statement stmt = con.createStatement();
		Scanner sc1 = new Scanner(System.in);
		Scanner sc2= new Scanner(System.in);
		System.out.println("Enter new user");
		String username = sc1.nextLine();
		System.out.println("Enter user ID");
		int new_id = sc1.nextInt();
		System.out.println("Enter user password");
		String pwd = sc2.nextLine();
		System.out.println("Enter designation");
		String desgn = sc2.nextLine();
		String sql = "INSERT INTO users (NAME, ID, PASSWORD, DESN)" +
		        "VALUES ('"+username+"','"+new_id+"','"+pwd+"','"+desgn+"' )";
		stmt.executeUpdate(sql);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	


	


	public static User_login loginTest(String uname, String pass) throws SQLException 
	{
		Connection con=getCon();


		PreparedStatement ps=con.prepareStatement("select * from users where name=? and PASSWORD=? ");
		ps.setString(1, uname);
		ps.setString(2, pass);
		ResultSet rs=ps.executeQuery();
		if(rs.next())
		{
			User_login u=new User_login();
			u.setDesn(rs.getString(4));
			u.setId(rs.getInt(2));
			u.setName(rs.getString(1));
			u.setPassword(rs.getString(3));
			
			return u;
		}
		else
			System.out.println("Not done");
	
		return null;
	}


	public static Connection getCon()
	{
		Connection con=null;
		try
		{
			 con = DriverManager.getConnection("jdbc:mysql://localhost/employee", "root","1234");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return con;
	}
	
}

		 
