import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;

public class Deposite
{
	Deposite(Connection con,long anum,Scanner input)throws Exception
	{
		System.out.println("Enter Amount to Deposit:");
		long amount=input.nextLong();
		if(amount>0) 
		{
		long bal=Balance.balance(con, anum);
		amount+=bal;
		String qry="update Account set balence="+amount+" where accnum="+anum;
		Statement st=con.createStatement();
		int res=st.executeUpdate(qry);
		if(res==1)
		{
			System.out.println("Amount Deposited SuccesFully");
		}
		}
		else
		{
			System.out.println("Enter Sufficient amount to Deposit");
		}
			
		
	}
	Deposite(Connection con,long anum,long amount)throws Exception
	{
		String qry="update Account set balence="+amount+" where accnum="+anum;
		Statement st=con.createStatement();
		st.executeUpdate(qry);
	
	
		
	}
}
