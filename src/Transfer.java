import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Transfer 
{
 Transfer(Connection con,long anum,Scanner input)throws Exception
	{
		System.out.println("**Welcome to Transfer Section**");
		System.out.println("Enter Destination account Number to transfer:");
		long destAccNum=input.nextLong();
		if(anum==destAccNum)
		{
			System.out.println("Transfer to same account number is not Posiible!");
		}
		else {
		Statement st=con.createStatement();
   		String qry="select count(*)from Account where accnum="+destAccNum;
   		ResultSet result=st.executeQuery(qry);
   		int cn=0;
   		while(result.next())
   		{
   			cn=result.getInt(1);
   		}
   		if(cn==1) 
   		{
   			System.out.print("The Account Holder name is ");
  ResultSet rs=st.executeQuery("select custname from Account where accnum="+destAccNum);
   			while(rs.next())
   			{
   				System.out.print(rs.getString(1));
   			}
   			System.out.println("\nProceed with Transfer Funds(press 1):");
   			int choice=input.nextInt();
   			if(choice==1)   				
   			{
   				System.out.println("Enter Amount to transfer:");
   				long amount=input.nextLong();
   				if(amount<=Balance.balance(con, anum))
   				{
   					new Withdraw(con,anum,amount);
   					amount=Balance.balance(con,destAccNum)+amount;
   				new Deposit(con,destAccNum,amount);
   				System.out.println("Transfer successful");
   				}
   				else
   				{
   					System.out.println("Account doesn't have sufficient amount to transfer");
   				}
   			}
   		}
   		else 
   		{
   			System.out.println("Account Number is invalid!");
   		}
		}
	}
}
