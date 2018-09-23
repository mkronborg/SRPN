 

import java.util.Stack; // Imported  to enable use of Stack for storing operands

/**
 * Deals with numbers and other inputs, and manages their interaction with the stack
 *
 * @author Maximilian Kronborg
 * @version 1.0
 */
public class StackHandler
{
    final int MAX = Integer.MAX_VALUE; // Highest allowed integer values
    final int MIN = Integer.MIN_VALUE; // Lowest allowed integer value
    RFunction random; // RFunction object, produces random numbers
    Calculate calc; // Calculate object, performs mathematical operations
    Stack <Integer> stack = new Stack<Integer>(); // Holds input operands read from BufferedReader, as well as results of operations on them
    public StackHandler()
    {
        random = new RFunction(); // Instantiating instance objects
        calc = new Calculate();
    }

    /**
     * Given a number, if it is within bounds, and the stack is not full, the number is pushed to the stack
     *
     * @param long number
     *              Number to push to stack
     */
    public void processNum(long number)
    {
        int num;
        if (number > MAX) // If number is greater than the MAX or MIN values of an integer, then it is set to that
            num = MAX;
        else if (number < MIN)
            num = MIN;
        else
            num = (int)number;

        if (stack.size() <= 22) // There seems to be some disagreement on this, as the SRPN.jar file I have will only take 23 elements,
        {						// but other students claim on their computers it is 24 elements...
            stack.push(num); // If stack is not full, number is pushed to the stack
        }
        else

            System.err.println("Stack overflow."); // if Stack is full, error message is displayed
    }
    
    /**
     * Given a string, calls the Calculate class to convert it from octal to decimal
     *
     * @param String s
     *              Number to be converted
     */
    public void octal(String s)
    {
        stack.push((int)calc.octal(s)); // Converst input from octal to decimal
    }

    /**
     * Given a character, the operation it prescribes is performed
     *
     * @param char c
     *              Character to be analysed
     */
    public void processChar(char c)
    {
        if (c == '=') // '=' prints last result, or the next element in the stack
        {
            returnResult(); // Prints value of next element in array
        }
        else if (c == 'r') // Prints a number cyclically from an array
        {
            processNum((long)random.randomNumber()); // pushes one of a list of numbers to stack
        }
        else if (c == 'd') // Prints contents of stack
        {
            printStack(); // prints contents of stack
        }
        else if (c == '#') // Prints contents of stack
        {
            return; // Ends processCommand function with no returned value
        }
        else if (c == ' ')
        {
            return; // Ends processCommand function with no returned value
        }
        else if (!Character.isLetter(c)) // if String is not a special operation,
										//then it must be an operator
        {
            if (stack.size() < 2)
                System.err.println("Stack underflow."); // If one or no operands are in the stack, an error message is printed
            else
            {
                int second = stack.pop(); // acquires two next operands
                int first = stack.pop();
                long response = calc.perform(c, first, second); // calls Calculate object to perform operation
                if (response >= MIN) // If reponse is lower than MIN, it means that operation was unsuccessful
                {
                    stack.push((int)response);
                    return;
                }
                stack.push(first); // If the program reaches this point then the operation was not completed, and operands are pllaced back in the stack
                stack.push(second);
            }
        }
        else
        {
            System.err.println("Unrecognised operator or operand \"" + c + "\"."); // If character was not recognised error message is printed
        }
        
    }

    /**
     * Prints the contents currently held in the stack
     */
    public void printStack()
    {
        if (! stack.empty())
            for (int i = 0; i < stack.size(); i++) // Loops through stack and prints elements
            {
                System.out.println(stack.get(i));
            }
        else
            System.out.println(); // prints empty line if stack is empty
    }

    /**
     * Prints the next number in the stack
     */
    public void returnResult()
    {
        if (! stack.empty())
            System.out.println(stack.peek());
        else
            System.err.println("Stack empty."); // If stack is empty then an error is printed
    }
}
