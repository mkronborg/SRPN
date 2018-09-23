  

/**
 * Performs calculations, based on operands and an operator, or converts a number in octal to decimal
 *
 * @author Maximilian Kronborg
 * @version 1.0
 */
public class Calculate
{

    final int MAX = Integer.MAX_VALUE; // Highest allowed integer value
    final int MIN = Integer.MIN_VALUE; // Lowest allowed integer value

    /**
     * Given a char, this method performs the prescribed operation on the two supplied operands
     * 
     * @param char operation
     * @param long first
     * @param long second
     */
    public long perform(char operation, long first, long second)
    {
        boolean calculated = true; // Boolean to keep track of whether an operation was performed, or whether it was not allowed
        long sum = 0; // a long can hold more than the maximum value of an int, and saturation will be considered after operation
        switch (operation)
        {
            case '+': 
            sum = first + second;
            break;

            case '-': 
            sum = first - second;
            break;

            case '*': 
            sum = first * second;
            break;

            case '/': 
            if (second == 0) // Dividing by 0 is not allowed
            {
                System.err.println("Divide by 0.");
                return ((long)MIN)-1;// out of bounds value indicates to StackHandler object that calculation was not performed
            }
            else
            {
                sum = first / second;
            }
            break;

            case '%': 
            if (second == 0) // Dividing by 0 is not allowed
            {
                System.err.println("Divide by 0.");
                return ((long)MIN)-1;// out of bounds value indicates to StackHandler object that calculation was not performed
            }
            else
            {
                sum = first % second;
            }
            break;

            case '^': 
            if (second < 0) // Negative power not allowed
            {
                System.err.println("Negative power.");
                return ((long)MIN)-1; // out of bounds value indicates to StackHandler object that calculation was not performed
            }
            else
            {
                sum = (long)Math.pow(first, second);
            }
            break;

            default: // If the operation is not recognized, then no operation is performed, and boolean is set to false
            calculated = false;
            break;
        }   
        if (calculated) 
            if (sum > MAX) // If results is greater than MAX or less than MIN allowed, then it is set to that
                return MAX;
            else if (sum < MIN)
            {
                return MIN;
            }
            else
                return sum;
        System.err.println("Unrecognised operator or operand \"" + operation + "\"."); // If code reaches here, then operator was not recognised
        return ((long)MIN)-1; // Out of bounds value indicates unsuccessful operation.
    }
    /**
     * @return long 
     *          Returns the value resulting from the specified operation, or a number outside the allowed
     *          range to indicate that the operation was not performed, such that the two operands are placed
     *          back into the stack
     */
    
    /**
     * Converts a supplied string from octal to decimal
     * 
     * @param String s
     *          Number that is known to be convertible to a long, and as such, known
     *          not to include anything but numbers, but which needs to be converted to octal due
     *          a leading 0
     */
    public long octal(String s)
    {
        long sum = 0; // Keeps track of cumulative value of digits in number
        int i = s.length() - 1; // Counter variable. 
        int stop = 0;
        boolean neg = false; // Checks if number is negative
        int value;
        if (s.charAt(0) == '-') // if leading character is '-' then it is skipped
        {
            neg = true;
            stop = 1; // A negative sign at the front is not read
        }
        while (i >= stop) // Traverses string
        {
            value = Integer.parseInt(s.substring(i, i+1));
            if (value > 7) // If a number greater than 7 is read in a digit, then the counter starts over with the remaining digits
            {
                sum = 0;
                s = s.substring(0, i);
            }
            else
                sum += value * Math.pow(8, s.length() - 1 - i); // Calculates value of number in a given popsition
            i--;
        }
        if (neg) // Returns negative of result if the string has a leading '-'
            sum *= -1;
        if (sum > MAX) // If results is greater than MAX or less than MIN allowed, then it is set to that
                return MAX;
            else if (sum < MIN)
            {
                return MIN;
            }
            else
                return sum;
    }
    /**
     * @return long
     *          Returns the resulting decimal value, or the maximum of minimu allowed value if the 
     *          decimal equivalent is too large.
     */

}

