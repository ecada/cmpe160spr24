package Gensokyo;

import java.util.Stack;
import java.util.Scanner;

public class ParenthesisChecker_Solution {
	
	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		String input = scanner.nextLine();
		boolean result = parenthesisCheck(input);
		System.out.println(result);
	}
	
	public static boolean parenthesisCheck(String expression) {
		Stack<Character> parenthesisStack = new Stack<>();
		char[] characters = expression.toCharArray();
		boolean flag = true;
		
		for (int i=0; i<characters.length; i++ ) {
			if (!flag) {break;}
			char c = characters[i];
			
			if (c == '(') {
				parenthesisStack.push(c);
			}
			if (c == ')') {
				if (!(parenthesisStack.isEmpty())) {
					parenthesisStack.pop();
				}
				else {
					flag = false;
				}				
			}
		}
		if (flag == false) {return flag;}
		
		if (!(parenthesisStack.isEmpty())) {
			flag = false;
		}
		return flag;
	
	}


}
