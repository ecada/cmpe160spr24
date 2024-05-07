package Gensokyo;

import java.util.Scanner;
import java.util.Stack;

public class ParenthesisChecker {

	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine(); 
		boolean result = parenthesisCheck(input);
		System.out.println(result);
	}
	
	// This is the parenthesis checking function
	public static boolean parenthesisCheck(String expression) {
	    Stack<Character> parenthesisStack = new Stack<>();  // Creating our parenthesis stack 
	    char[] ch = expression.toCharArray();  // Turns your string into an array of chars inside your string
	    boolean flag = true;  // Our result, kept as a boolean flag
	    /* Mentioning the "alternative loop" way */
	    // Main for loop iterating the characters inside our string
	    for(int i = 0; i < ch.length; i++) {
	        if (!flag) {break;}
	    	char c = ch[i];  // Taking the character at the index i
	    	// If open parenthesis, it will be pushed onto the operators stack
	        if(c == '('){  
	        	parenthesisStack.push(c);
	        }
	        /* If closed parenthesis, pop the head of our stack (if exists)
	         * It should be '(' in a true parenthesis syntax 
	         * 
	         * (matching our open-closed parenthesis appropriately)
	         */
	        if(c == ')') {
	        	if ((!parenthesisStack.isEmpty()) && (parenthesisStack.peek() == '('))
	        		parenthesisStack.pop();
	        	else 
	        		flag = false;
	        }
	        /* Question: Do we need to do anything else?
	         * 
	         * Answer: See below
	         * */
	    }
	    /* Checking our stack, if not empty
	     * it means there are unmatched
	     * parenthesis, so our syntax is false.
	     */
	    if(!parenthesisStack.isEmpty()){
	    	flag = false;
	    }
	    // This will be our eventual result 
	    return flag;
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
// 3 * 5 / 7
// 2 + (((3 + 5)) + 7) + ((9)+(11)))






