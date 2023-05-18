import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Bank {

	public static void main(String[] args)throws Exception
	{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","admin");
		System.err.println("***Welcome To OnlineBanking***");
		Scanner input=new Scanner(System.in);
		while(true)
		{
			System.out.println("1.Existing Customer \n2.New Customer\n3.Exit");
			System.out.println("Enter your choice:");
			int choice=0;
			choice=input.nextInt();
			switch(choice)
			{
			case 1:{
						System.out.println("Enter your Account Number To Continue:");
				   		long anumber=input.nextLong();
				   		Statement st=con.createStatement();
				   		String qry="select count(*)from Account where accnum="+anumber;
				   		ResultSet result=st.executeQuery(qry);
				   		int cn=0;
				   		while(result.next())
				   		{
				   			cn=result.getInt(1);
				   		}
				   		if(cn==1) 
				   		{
				   			System.out.print("Welcome ");
				   			ResultSet rs=st.executeQuery("select custname from Account where accnum="+anumber);
				   			while(rs.next())
				   			{
				   				System.out.print(rs.getString(1));
				   			}
				   		while(true) 
				   		{
				   			System.out.println("\n1.Deposit\n2.Withdraw\n3.Balance Enquery\n4.Transfer Funds\n5.Delete Account\n6.Back to Main menu");
				   			System.out.println("Enter your Choice");
				   			int choice2=input.nextInt(); 
					   		int exit=0;
					   		switch(choice2)
					   		{
					   		case 1:new Deposit(con,anumber,input);
					   				break;
					   		case 2:new Withdraw(con,anumber,input);
					   				break;
					   		case 3:long bal=Balance.balance(con,anumber);
					   				System.out.println("Your Account Balance is:"+bal);
					   				break;
					   		case 4:new Transfer(con,anumber,input);
					   				break;
					   		case 5:System.out.println("Deleting Your Account will remove all of your data(to continue press 1)");
					   				if(input.nextInt()==1)
					   				{
					   				Statement st1=con.createStatement();
					   				int res=st1.executeUpdate("delete from Account where accnum="+anumber);
					   				if(res==1)
					   					System.out.println("Account deleted Succesfuly");
					   				exit++;
					   				}
					   				break;
					   		case 6:exit++;
					   				break;
					   		default :System.out.println("Invalid Option");
					   				break;
					   		}
					   		if(exit==1)
					   		{
					   			break;
					   		}
				   		}
				   		}
				   		else
				   		{
				   			System.out.println("Account Number Not Exit!!--Please enter correct Account Number");
				   			System.out.println("Forget Account number(press 1):");
				   			if(input.nextInt()==1)
				   			{
				   				System.out.println("Enter your 12 digit adhaarnumber:");
				   				long aNum=input.nextLong();
				   				System.out.println("Enter Registered mobile number:");
				   				long mNum=input.nextLong();
				   				Statement st1=con.createStatement();
				   	ResultSet rs=st1.executeQuery("select accnum from Account where mobnum="+mNum+" AND adhaarnum="+aNum);
					   			int count=0;
				   				while(rs.next())
					   			{
					   				System.out.println("Your Account Number is "+rs.getLong(1));
					   				count++;
					   			}
				   				if(count==0)
				   				System.out.println("Invalid details!");
				   			}
				   		}
					}
					break;
			case 2:new AccountCreation(con,input);
				   break;
			case 3: System.exit(0);
					break;
			default :System.out.println("Invalid Option");
				break;
			}
		}
	}
}
