import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int[] sequence;
	static int[][] memberResult;
	static boolean[] isVisited;
	static final int MEMBER_NUMBER = 9;
	static int startIndex, maxScore;
	
	public static void init() {
		sequence = new int[MEMBER_NUMBER];

		// 4번타자 순서 fix
		// initial startIndex+3 % MEMBER_NUMBER:= 0
		startIndex=MEMBER_NUMBER-3;
		sequence[0] = 1;
		isVisited = new boolean[MEMBER_NUMBER+1];
		isVisited[1]=true;
		
		
		memberResult = new int[N][MEMBER_NUMBER+1];
		maxScore = Integer.MIN_VALUE;
	}
	
	public static void playGame() {
		startIndex=MEMBER_NUMBER-3;
		int score=0;
		for(int round=0; round<N; round++) {
			int bitmask = 0;
			int outCount=0;
			while(outCount<3) {
				int playerNumber = sequence[startIndex%MEMBER_NUMBER];
				startIndex++;
				int result = memberResult[round][playerNumber];
				if(result == 0) {
					outCount++;
				}else if(result == 1){
					bitmask = (bitmask+1) << 1;
					
				}else if(result == 2){
					bitmask = (bitmask+1) << 2;
					
				}else if(result == 3){
					bitmask = (bitmask+1) << 3;
					
				}else if(result == 4){
					bitmask = (bitmask+1) << 4;
				}
				if(bitmask!=0) {
					int temp = ((1<<4) - 1) << 4;
					score += Integer.bitCount(bitmask&temp);
					bitmask = bitmask & ((1<<4)-1);
				}
			}
		}
		maxScore = maxScore < score ? score : maxScore;

	}
	
	public static void permutationBacktrack(int elementIndex) {
		if(elementIndex==MEMBER_NUMBER) {
			playGame();
			return;
		}
		
		for(int index = 1; index<=MEMBER_NUMBER; index++) {
			 if(isVisited[index]) continue;
			 isVisited[index] = true;
			 sequence[elementIndex] = index; 
			 permutationBacktrack(elementIndex+1);
			 isVisited[index] = false;
		}
		
	}

	
	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		init();
		
		for(int roundIndex=0; roundIndex<N; roundIndex++) {
			st = new StringTokenizer(br.readLine());
			for(int memberIndex=1; memberIndex<=MEMBER_NUMBER; memberIndex++) {
				memberResult[roundIndex][memberIndex] = Integer.parseInt(st.nextToken());
			}
		}

		permutationBacktrack(1);
		System.out.println(maxScore);
		
		
	}
}
