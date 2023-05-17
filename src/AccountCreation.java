import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;



public class AccountCreation
{
	AccountCreation(Connection con,Scanner sc)throws Exception
	{
	System.out.println("**Account Creation Section**");
	System.out.println("Enetr your First Name:");
	String fname=sc.next();
	System.out.println("Enetr your Lastst Name:");
	String lname=sc.next();
	 System.out.println("Enter you 10 digit Mobile Number:");
	long mnum=sc.nextLong();
	System.out.println("Enter you 12 digit Adhar Number:");
	long adharnum=sc.nextLong();
	long accnum=(long)(Math.random()*100000000000000L+1);
	String qry="insert into Account values(?,?,?,?,?)";
	PreparedStatement ps=con.prepareStatement(qry);
	ps.setLong(1,accnum);
	ps.setString(2, fname+lname);
	ps.setLong(3, mnum);
	ps.setLong(4, adharnum);
	ps.setLong(5, 0);
	int rs=ps.executeUpdate();
	if(rs==1)
	{
		System.out.println("Account Added Succecsfully");
		System.out.println("Your Account Number is:"+accnum);
		System.out.println("please Note your Account number for Futur uses!!!");
		
	}
	}

}
