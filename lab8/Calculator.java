package Gensokyo;

import java.util.Scanner;
import java.util.Stack;

public class Calculator {
	public static void main(String args[]) {
		try {
			Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();            
			double result = evaluateInfixExpression(input);
			System.out.println(result);
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	// This is our evaluator function
	public static double evaluateInfixExpression(String expression) throws Exception {
		// Creating our stacks
	    Stack<Double> operands = new Stack<>(); // Stack of numbers
	    Stack<Character> operators = new Stack<>(); // Stack of operations
	    // Turns your string into an array of chars inside your string
	    char[] ch = expression.toCharArray();
	    // Alternatively, you can iterate your characters inside your string by a simple for loop like
	    /* for (int i=0; i<expression.length; i++) {
	     *     char c = expression.charAt(i);
	     *     
	     *     // The rest will be similar
	     * 
	     * }
	     */
	    // Main for loop iterating the characters inside our string
	    for(int i = 0; i < ch.length; i++) {
	        char c = ch[i];
	        // The "Digit" case
	        if(Character.isDigit(c)){ 
	        	/* If our number consists of more than one digit, then 
	        	 * the while loop below gives us the entire number, starting
	        	 * from that digit
	        	 * */
	            double num = 0;
	            /* Our i here is same as the i in our big for loop
	             * 
	             * Question: How is this possible?
	             * 
	             * Answer: Why not :')
	             * 
	             * */
	            while (i < ch.length && Character.isDigit(ch[i])) {
	                num = num * 10 + (ch[i] - '0');
	                i++;
	            }
	            /* As we have "i++" at the end of one iteration of our for loop 
	             * we should put this "i--" in order to "neutralize" our 
	             * iteration index i
	             * 
	             * A possible edge can be:
	             * "...+234*56..."
	             * 
	             * Not putting "i--" can go directly from 4 to 6,
	             * skipping the * sign in between 
	             * 
	             * */
	            i--;
	            // Pushing our number into the operands stack
	            operands.push(num);
	        }
	        // If open parenthesis, it will be pushed onto the operators stack
	        else if( c == '('){
	            operators.push(c);
	        }
	        // Seeing a closed parenthesis
	        else if(c == ')') {
	            /* Calculating between that closed parenthesis and 
	             * its corresponding open parenthesis (which should
	             * exist if parenthesis are written correctly)
	             * */
	        	
	        	/* (No precedence problem occur due to the 
	        	 * algorithm used in the "reading the operator"
	        	 * case) */
	            while(operators.peek() != '('){
	                double ans = calculate(operands, operators);
	                operands.push(ans);
	            }
	            /* Popping the corresponding '(' of our
	             * lastly read closed parenthesis
	             */
	            operators.pop();
	        }
	        // Seeing an operator
	        else if(isOperator(c)){
	        	/*  While there are operators inside operators stack and
	        	 *  the "recently read" operator has lower or equal precedence 
	        	 *  than the top of the stack 
	        	 *
	        	 *  Why?
	        	 * 
	        	 *  Consider the case 3 * 5 + 7
	        	 *  The operands stack has [3,5 
	        	 *  (rightmost part is the top of the stack, above it is 5)
	        	 *  The operators stack has [*
	        	 *  
	        	 *  When we see the + operation, we should first do
	        	 *  3*5, then add 2 to the result of 3*5 (which is 15)
	        	 *  
	        	 *  The below condition checks these precedence cases 
	        	 *  and does the calculations and pushes accordingly
	        	 *  */
	            while(!operators.isEmpty() && precedence(c) <= precedence(operators.peek())){
	                /* In our above example, this corresponds to:
	                 * Step 1: [3,5  // [*  
	                 * Step 2: [(empty)  // [(empty)  // calculate 3*5=15
	                 * Step 3: [15  // [(empty)
	                 * 
	                 * */
	            	double ans = calculate(operands, operators);
	                operands.push(ans);
	                /* Question: Where is the + operation? :) */
	            }
	            operators.push(c);
	            /* Now the lower precedence operation is pushed onto the stack
	             * 
	             * Step 4: [15  // [+
	             * 
	             * Answer: The "+" operation is pushed here, above part deals 
	             * only with high precedence parts
	             * 
	             * */
	        }
	    }
	    /* Question: Is there an edge case that we might be missing? 
	     * Answer: We did not do anything about empty spaces
	     * Follow-up question: But really do we need to handle them separately?
	     * Follow-up answer: Actually no, we implicitly handled them :') 
	     * */
	    
	    /* Now the main for loop has ended, in other words we've visited
	     * everything in our input string. The rest will be the calculations 
	     * 
	     * (Note that in this case, all precedences have been set and 
	     * all parenthesis' inside parts have been calculated, so no
	     * other casework is required for the rest :)  )
	     * */
	    // Calculating everything in our stack
	    while(!operators.isEmpty()){
	        double ans = calculate(operands, operators);
	        operands.push(ans);
	    }
	    // This will be our eventual result 
	    return operands.pop();
	}
	// Checks if that character is an operator or not
	private static boolean isOperator(char c) {
	    return (c=='+'||c=='-'||c=='/'||c=='*');
	}
	/* Function returning precedences of operations
	 * 
	 * 1) + and - have the same precedence
	 * 2) * and / have the same precedence
	 * 3) * has higher precedence than +
	 * 
	 * */
	private static int precedence(char c) {
	    switch (c){
	        // In both cases + and -, return 1
	        case '+':
	        case '-':
	            return 1;
	        // In both cases * and /, return 2
	        case '*':
	        case '/':
	            return 2;
	    }
	    return -1;
	}
	// Our function for doing numeric calculations
	private static double calculate(Stack<Double> operands, Stack<Character> operators) throws Exception {
	    double a = operands.pop();
	    double b = operands.pop();
	    /* Note that our prefix is indeed 
	     * b (operator) a
	     * 
	     * not a (operator) b
	     * 
	     * */
	    // Popping the operator
	    char operator = operators.pop();
	    /* Checking possible cases
	     * 
	     * One can also use lots of if-else s
	     * but this looks more "esthetic" 
	     * */
	    switch (operator) {
	        case '+':
	            return a + b;
	        case '-':
	            return b - a; // Remember the prefix ordering :)
	        case '*':
	            return a * b;
	        case '/':
	        	// Guess which edge case that is...
	            if (a == 0) {
	            	// Throwing exception
	                throw new Exception("Cannot divide by zero");
	            }
	            // "Peaceful" case where denominator != 0
	            return b / a; 
	    }
	    return 0;
	}
}
/* /////////////////////////////////////////////////////////////////////////  */
/* /////////////////////////////////////////////////////////////////////////  */
/* /////////////////////////////////////////////////////////////////////////  */

















/*    /////////////////////////////////////////////////////////////////////////
 *    
 *    /////////////////////////////////////////////////////////////////////////
 *    
 *    /////////////////////////////////////////////////////////////////////////
 * 
 *    /////////////////////////////////////////////////////////////////////////
 * 
 *    /////////////////////////////////////////////////////////////////////////
 * 
 *    /////////////////////////////////////////////////////////////////////////
 * 
 *    /////////////////////////////////////////////////////////////////////////
 * 
 *    /////////////////////////////////////////////////////////////////////////
 * 
 *    /////////////////////////////////////////////////////////////////////////
 * 
 *    /////////////////////////////////////////////////////////////////////////
 * 
 *    /////////////////////////////////////////////////////////////////////////
 * 
 *    /////////////////////////////////////////////////////////////////////////
 * 
 *    /////////////////////////////////////////////////////////////////////////
 * 
 */
//"     1 + ( 2*3+ (5-7)/(3-1*2)      ) * 9     "
//3 * 5 / 7
//2 + (((3 + 5)) + 7) + ((9)+(11)))



























