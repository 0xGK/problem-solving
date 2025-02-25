import java.io.*;
import java.util.*;

/*
 * 	1. 수열을 고정시키고, 연산자의 위치를 변경시키며 최대 최소값 갱신
 *  2. 왼쪽에서부터 연산 순서가 강제되므로, backtrack으로 값을 넘겨주는 식으로 구현하면 좋을 듯
 * 
 *  주의사항:
 *  	- 연산자 우선순위 고려x 왼쪽에서 오른쪽으로 차례대로 계산
 *	 	- 12! is too much!....
 * 			-> 연산자 별로 카운트 따로 관리 
 * 
 */
public class Solution {
	static int N;
	static int[] sequence;	// 수식에 사용되는 수열
	static int[] operationCount;
	static int maxValue, minValue;
	
	
	public static void init() {
		sequence = new int[N];
		operationCount = new int[4];
		maxValue = Integer.MIN_VALUE;
		minValue = Integer.MAX_VALUE;
	}
	
	
	public static int calculateOperation(int firstInt, int secondInt, int operation) {
		switch(operation) {
		case 0:
			return firstInt+secondInt;
		case 1:
			return firstInt-secondInt;
		case 2:
			return firstInt*secondInt;
		case 3:
			return firstInt/secondInt;
		default:
			return -1;
		}
	}
		
	
	public static void permutationBacktrack(int operationIndex, int value) {
		if(operationIndex == N) {
			maxValue = maxValue < value ? value : maxValue;
			minValue = minValue > value ? value : minValue;
			return;
		}
		
		for(int index=0; index<4; index++) {
			if(operationCount[index]>0) {
				operationCount[index]--;
				int nextValue = calculateOperation(value, sequence[operationIndex], index);
				permutationBacktrack(operationIndex+1, nextValue);
				operationCount[index]++;
			}
		}
		
	}
	
	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int testCase = 1; testCase <= T; testCase++) {
			sb.append("#").append(testCase).append(" ");
			N = Integer.parseInt(br.readLine());
			init();
			
			st = new StringTokenizer(br.readLine());
			for(int index=0; index<4; index++) {
				operationCount[index] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for(int index=0; index<N; index++) {
				sequence[index] = Integer.parseInt(st.nextToken());
			}
			
			permutationBacktrack(1, sequence[0]);
			
			sb.append(maxValue - minValue).append("\n");
		}
		System.out.println(sb);
		
	}
}