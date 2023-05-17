

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Balance
{
	public  static long balance(Connection con,long anum)throws Exception
	{		
				long balance=0;
				Statement st1=con.createStatement();
		   		String qry1="select balence from Account where accnum="+anum;
		   		ResultSet result1=st1.executeQuery(qry1);
		   		while(result1.next())
		   		{
		   			balance=result1.getLong(1);
		   		}
		   		return balance;
	}

}

