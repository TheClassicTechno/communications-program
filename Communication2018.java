
/**
* BPA Java Programming Contest : Communications
*
* @author - contestant number goes here
*
* @version January 2017
*
*/
import java.io.FileNotFoundException;
import java.io.*;
import java.util.Scanner;
public class Communication2018
{ //declare variables
	static int messageNum, checkTotal, messageLength;
	static String message, originalMessage;
	public static void main(String[] args) throws FileNotFoundException
	{
		try // try to open the input file named in the command line
		{
		Scanner sc = new Scanner(new File("communications.txt"));
		// get the number of Starfleet messages to verify
		int numMessages = sc.nextInt();
		// process all messages
			for(int i = 0; i<numMessages;i++)
			{
			// get the message number, check total and length
			messageNum = sc.nextInt();
			checkTotal = sc.nextInt();
			messageLength = sc.nextInt();
			// get the message
			originalMessage = sc.nextLine();
			// process and verify the message
			message = cleanMessage();
			// build and print output
			printConfirmation();
			}
		}catch(FileNotFoundException e) //if file not found display error message
		{
		System.out.println(" Input file not found");
		}
	}
	// This method cleans extra spaces off the ends and removes the " over"
	//and returns a cleaned messager for processing
	
	public static String cleanMessage()
	{
	//remove beginning and ending spaces in message
		originalMessage = originalMessage.trim();
		//check to see if message ends in " over". if so remove "over"
		message = "";
		if(originalMessage.substring(originalMessage.length() - 5).equals(" over"))
		message = originalMessage.substring(0,originalMessage.length() - 5);
		else
		message = originalMessage;
		return message;
	}
	// This method verifies that the check total matches the sum of the chars in the message
	//- use cleaned message
	public static boolean verifyCheckTotal()
	{
		int sum = 0;
		for(int i = 0;i < message.length(); i++)
		{
		sum += message.charAt(i);
		}
		// System.out.println( "check = "+ checkTotal + " sum = " + sum);
		if(sum == checkTotal)
		{
		return true;
		}
		return false;
	}
	//This method verifies the length of the message matches length transmitted
	//- use cleaned message
	public static boolean verifyLength() //return true if length received = input length
	{
		if(messageLength == message.length())
		return true;
		return false;
	}
	// This method encodes the original message
	public static String encodeMessage()
	{ //replace in proper sequence using Strings
		originalMessage = originalMessage.replace("f","B");
		originalMessage = originalMessage.replace("F","P");
		originalMessage = originalMessage.replace("e","A");
		originalMessage = originalMessage.replace(" ","e");
		originalMessage = originalMessage.replace("t",">?/");
		
		return originalMessage;
	}
	// This method prepares and formats the output
	public static void printConfirmation()
	{
		System.out.printf("transmission %03d ",messageNum); //must print 3-digits
		String temp = " confirmed"; //default message
		if(!verifyLength()) //lengths NOT equal - use cleaned message
		{
			temp = " length error";
		}
		if(!verifyCheckTotal()) //totals NOT equal - use cleaned message
		{
			if(temp.equals(" confirmed"))
			temp = " check total error";
			else
			temp = temp + ", check total error"; //adds comma if needed
		}
		// ORIGINAL message ends in " over" ?
		if(!originalMessage.substring(originalMessage.length() - 5).equals(" over"))
		{
			if(temp.equals(" confirmed"))
				temp = " incomplete transmission";
			else
				temp = temp + ", incomplete transmission"; //adds comma if needed
		}
		System.out.println(temp); //adds confirmation/error message
		// send converted original message + over + blank line
		System.out.println(encodeMessage() + " over\n"); //end transmission with over
	}
}