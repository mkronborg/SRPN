 

import java.io.*; // Imported to enable use of BufferedReader
/**
 * @author Maximilian Kronborg
 * @version 2.0
 * 
 * Program class for an SRPN calculator.
 * Reads input and passes it to the StackHandler class for processing
 */

public class SRPN {
    StackHandler handler; // Deals with singular characters, or numbers, read from input
    /**
     * Recieves a String of input, and converts it to a number, operation, or a mix
     * 
     * @param String s
     *              A line read by BufferedReader, to be converted to useful input for mathematical computation 
     */
    public void processCommand(String s) 
    {
        if (s.length() < 1) // If the String is empty then it is ignored
            return;
        try{
            long n = Long.parseLong(s); // Attempts to cast string to a long
            if ((s.charAt(0) == '0') || (s.length() > 1 && s.charAt(1) == '0' && s.charAt(0) == '-'))
            {
                handler.octal("" + n); // Octal number passed to StackHandler withoutthe leading zero
            }
            else
            handler.processNum(n);
        }
        catch (NumberFormatException e) // If String cannot be converted into a long, it must be characters
        {
            if (s.length() == 1) // If there is only one char, then the StackHandler handles it
            {
                handler.processChar(s.charAt(0));
            }
            else
            {
                if (s.equals("rachid"))
                    System.out.println("Rachid is the best unit lecturer."); // Obviously
                else
                    decomposeString(s); // if String has more than one character, it must be broken down into separate characters or numbers
            }
        }

    }

    /**
     * Takes a string that is known to have more than one character, and which cannot be parsed
     * as long, breaks it down into numbers and operations, and calls processCommand()
     * 
     * @param String s
     *              The String to be broken down into numbers and operands
     */
    public void decomposeString(String s)
    {
        String number = ""; // Number to append read characters to if they are digits
        int i = 0; // Counter variable
        String c; // Each read char is placed in this String
        while (i < s.length())
        {
            c = s.substring(i, i+1); // c is set to be a String equal to the current char under consideration
            int max = 10; // Most allowed digits in a decimal number
            if (s.charAt(0) == '0')
            {
                max = 12; // if the number is octal, then it can hold more digits before reaching the highest allowed value
                for (int count = 1; s.charAt(count) == '0'; count++)
                {
                    max++; //For each zero in front of the octal number it can hold another digit
                }
            }
            if (number.length() > max) // If number is bigger than allowed, the it will be the maximum value, so remaining digits are skipped
            {
                while (i < s.length() - 1 && Character.isDigit(s.charAt(i))) // loops through string
               {
                   i++;
                }
            }
            if (Character.isDigit(s.charAt(i)))
            { // If either the current char is a digit, or it is a '-' and the next digit is a number, it is 
                number += c;
            }
            else if  (i + 1 < s.length() && s.charAt(i) == '-' && Character.isDigit(s.charAt(i+1))) // If a character is '-' and the next character is a number, then it is read as a number
            {
                processCommand(number); // previous read number is processed first
                number = "-" + s.charAt(i+1); // New number is started, and since it is known that the next character is a number, it is appended immediately
                i++; // i is incremeted as next character has already been added
            }
            else
            {
                if (!number.equals("")) // There is no point in calling a method for an empty string
                    processCommand(number);
				 if (c.equals("#")) // This character means whatever comes next should be ignored.
                    return;
                processCommand(c); // Since character is not a number, or  a '-' in front of a number, it is processed
                if (i < s.length() - 1)// If c is not the last character, then s becomes the remaining characters
                {
                    s = s.substring(i+1, s.length()); // number, and current char is processed, so s becomes the remaining characters
                    i = -1; // as i is later increented, it starts at -1, and as such,becomes 0
                    //line = s; // line is set equal to s for next iteration
                    number = ""; //number is reset
                }
            }
            if (i == s.length() - 1) // if the last character was read, then the  number is processed
                processCommand(number);
            i++; // Counter variable incremented
        }
    }

    /**
     * Constructor, instantiates instance variable
     */
    public SRPN()
    {
        handler = new StackHandler(); // Instantiates StackHandler object
    }

    /**
     * Starter code, modified slightly
     * Reads a line of input and passes it as a string to the processCommand() method
     */
    public static void main(String[] args) 
    {
        SRPN srpn = new SRPN();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try 
        {
            //Keep on accepting input from the command-line
            while(true) {
                String command = reader.readLine(); // Reads a new line from input

                //Close on an End-of-file (EOF) (Ctrl-D on the terminal)
                if(command == null) 
                {
                    //Exit code 0 for a graceful exit
                    System.exit(0);
                }

                //Otherwise, (attempt to) process the character
                srpn.processCommand(command);          
            }
        } catch(IOException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
}
