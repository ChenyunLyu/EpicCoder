import java.io.*;

public class codeTest
{
	public static void main(String args[]) throws IOException
	{
		String ss = input( "Input String: " );
		codefunc encoder = new codefunc( ss , " " );
		ss = input( "Input code: " );
		codefunc decoder = new codefunc( " " , ss );
		
		System.out.println( encoder.cover );
		System.out.println( decoder.message );
		
	}

//IBIO INPUT INT =================================================
	static int inputInt(String prompt)
	{	int	result = 0;
		try
		{	result = Integer.valueOf(input(prompt).trim()).intValue() ;	}
		catch (Exception e)	{	System.out.println(">>> error in input"); result = 0;}
		return	result;
	}

	static int inputInt()
	{	return	inputInt("");	}

	static double inputDouble(String prompt)
	{	double	result = 0;
		try
		{	result = Double.valueOf(input(prompt).trim()).doubleValue() ;	}
		catch (Exception e)	{	result = 0;}
		return	result;
	}

	static double inputDouble()
	{	return	inputDouble("");	}
	
	static String input(String prompt)
	{	String	inputLine = "";
		System.out.print(prompt);
		try
		{	inputLine = (new java.io.BufferedReader(
							new java.io.InputStreamReader(System.in))).readLine();}
		catch (Exception e)
		{	String	err = e.toString();
			System.out.println(err);
			inputLine = "";
		}
		return	inputLine;
	}
}
