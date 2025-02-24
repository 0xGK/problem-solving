
import java.util.*;
import java.io.*;
/*
 * 1. 부분 분자열 길이에 맞게 count값 초기 설정
 * 2. sliding window로 우측으로 이동하면서 count값 조정하면서 가능한 경우의 수 카운트
 * 
 * 
 */
public class Main {
	static int sequenceLen, subSequenceLen, totalCount;
	static int minA, minC, minG, minT;
	static int countA, countC, countG, countT;
	
	
	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine().trim());
		sequenceLen = Integer.parseInt(st.nextToken());
		subSequenceLen = Integer.parseInt(st.nextToken());
		String sequence = br.readLine().trim();
		totalCount=countA=countC=countG=countT=0;
		st = new StringTokenizer(br.readLine().trim());
		minA = Integer.parseInt(st.nextToken());
		minC = Integer.parseInt(st.nextToken());
		minG = Integer.parseInt(st.nextToken());
		minT = Integer.parseInt(st.nextToken());
		
		for(int index=0; index<subSequenceLen; index++) {
			switch(sequence.charAt(index)) {
			case 'A':
				countA++;
				break;
			case 'C':
				countC++;
				break;
			case 'G':
				countG++;
				break;
			case 'T':
				countT++;
				break;
				
			}
				
		}
		if((countA>=minA) && (countC>=minC) && (countG>=minG) && (countT>=minT)) {
			totalCount++;
		}
		for(int index=0; index<sequenceLen-subSequenceLen; index++) {
			switch(sequence.charAt(index)) {
			case 'A':
				countA--;
				break;
			case 'C':
				countC--;
				break;
			case 'G':
				countG--;
				break;
			case 'T':
				countT--;
				break;
				
			}
			
			switch(sequence.charAt(index+subSequenceLen)) {
			case 'A':
				countA++;
				break;
			case 'C':
				countC++;
				break;
			case 'G':
				countG++;
				break;
			case 'T':
				countT++;
				break;
				
			}
			if((countA>=minA) && (countC>=minC) && (countG>=minG) && (countT>=minT)) {
				totalCount++;
			}
		}
		System.out.println(totalCount);
		
	}
}
