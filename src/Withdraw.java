import java.sql.Connection;
import java.util.Scanner;

public class Withdraw 
{
	public Withdraw(Connection con,long anum,Scanner input)throws Exception 
	{
		System.out.println("Enter amount to withdraw:");
		long amount=input.nextLong();
		if(amount<=Balance.balance(con, anum)) {
			amount=Balance.balance(con, anum)-amount;
		new Deposite(con,anum,amount);
		System.out.println("Withdraw Sucessful");
		}else
		{
			System.out.println("Insufficient amount");
		}
	}
	public Withdraw(Connection con,long anum,long amount)throws Exception 
	{
		amount=Balance.balance(con, anum)-amount;
		new Deposite(con,anum,amount);
	}

}
