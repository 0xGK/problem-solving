import java.io.*;
import java.util.*;

public class Solution{
	static int[] sequence;
	static int startIndex;
	static final int SEQUENCE_LEN = 8;
	
	public static void init() {
		sequence = new int[8];
		startIndex=0;
	}
	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		
		int T = 10;
		
		for(int testCase=1; testCase<=T; testCase++) {
			init();
			sb.append("#").append(testCase).append(" ");
			br.readLine();
			st = new StringTokenizer(br.readLine());
			for(int index=0; index<SEQUENCE_LEN; index++) {
				sequence[index] = Integer.parseInt(st.nextToken());
			}
			
			int value = 1;
			while(true) {
				sequence[startIndex%SEQUENCE_LEN] -= value;
				if(++value > 5) value = 1;
				
				if(sequence[startIndex%SEQUENCE_LEN] <=0) {
					break;
				}
				startIndex++;
				
			}
			for(int index=1; index<SEQUENCE_LEN; index++) {
				sb.append(sequence[(startIndex+index)%SEQUENCE_LEN]).append(" "); 
			}
			sb.append("0\n");
			
		}
		System.out.println(sb);
	}
}
