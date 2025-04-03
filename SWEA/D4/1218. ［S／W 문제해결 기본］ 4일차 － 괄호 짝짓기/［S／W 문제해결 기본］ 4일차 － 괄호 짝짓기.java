import java.util.*;
import java.io.*;

/*
 * @author GK
 * 
 * stack 구조 활용 문제
 * 1. 열린 괄호들은 전부 stack에 넣기
 * 2. 닫힌 괄호들은 stack의 peek와 비교해서 pop할지 결정
 * 		2-1. 만약 닫힌 괄호가 stack의 peek와 다르면 impossible
 * 
 */
public class Solution{
	static int N;
	static Stack<Character> stack;
	
	public static void main(String[] args) throws Exception {
    	//System.setIn(new FileInputStream("input.txt"));
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringBuilder sb = new StringBuilder();
    	
    	int T = 10;
        for(int testCase=1; testCase<=T; ++testCase) {
            sb.append("#").append(testCase).append(" ");
            N = Integer.parseInt(br.readLine());
            stack = new Stack<>();
            String sequence = br.readLine();
            for(int index=0; index<sequence.length(); index++) {
            	char currentChar = sequence.charAt(index);
            	if(currentChar=='{' || currentChar=='[' || currentChar=='<' || currentChar=='(') {
            		stack.add(currentChar);
            	}else {
            		char peekChar = stack.peek();
            		if((peekChar=='{' && currentChar=='}') || (peekChar=='[' && currentChar==']') || (peekChar=='<' && currentChar=='>') || (peekChar=='(' && currentChar==')')) { 
            			stack.pop();
            		}else {
            			break;
            		}
            		
            		
            	}
            }
            if(stack.isEmpty()) {
            	sb.append(1).append("\n");
            }else {
            	sb.append(0).append("\n");
            }
        }
		System.out.println(sb);
	}
}
