import java.util.*;
import java.io.*;

/*
 * 1. 전체적인 순서는 1자리만 고정하고, 나머지는 팩토리얼
 * 	-> backtrack 활용한 permutation 사용
 * 	-> 순서 정해지면, 게임 플레이 
 * 
 * 2. 1~3루에 선수가 있거나 없거나
 * 	-> 비트마스킹으로 접근 가능
 * 		- 0000: no player
 * 		- 0010: 1루 주자
 * 		- ...
 * 	-> 비트마스크 값을 left shift 하면서 밀어버릴 수 있음
 * 	-> 4자리 보다 넘어가는 값을 주기적으로 계산해서 값 반영 가능
 * 
 * 
 */

public class Main {
	static int N;
	static int[] memberOrder;
	static int[][] memberResult;
	static boolean[] isVisited;
	static final int MEMBER_NUMBER = 9;
	static int startIndex, maxScore;
	
	public static void init() {
		memberOrder = new int[MEMBER_NUMBER];
		// 1번 선수 순서를 배열의 첫 자리에 fix
		//	-> 참조하는 startIndex를 MEMBER_NUMBER-3부터 시작하면, 4번째에 1번 타자 
		memberOrder[0] = 1;
		isVisited = new boolean[MEMBER_NUMBER+1];
		isVisited[1]=true;
		memberResult = new int[N][MEMBER_NUMBER+1];
		maxScore = Integer.MIN_VALUE;
	}
	
	public static void playGame() {
		// 4번 타자가, 1번 선수가 될 수 있도록 startIndex 조정
		startIndex=MEMBER_NUMBER-3;
		int score=0;
		for(int round=0; round<N; round++) {
			int bitmask = 0;
			int outCount=0;
			while(outCount<3) {
				int playerNumber = memberOrder[startIndex%MEMBER_NUMBER];
				startIndex++;
				int result = memberResult[round][playerNumber];
				if(result == 0) {
					outCount++;
				}else if(result == 1){
					// 홈에 선수 추가하고, 1칸씩 shift
					bitmask = (bitmask+1) << 1;
				}else if(result == 2){
					// 홈에 선수 추가하고, 2칸씩 shift					
					bitmask = (bitmask+1) << 2;
				}else if(result == 3){
					// 홈에 선수 추가하고, 3칸씩 shift					
					bitmask = (bitmask+1) << 3;
				}else if(result == 4){
					// 홈에 선수 추가하고, 4칸씩 shift
					bitmask = (bitmask+1) << 4;
				}
				if(bitmask!=0) {
					int temp = ~((1<<4) - 1);
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
			 memberOrder[elementIndex] = index; 
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
