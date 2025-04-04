import java.util.*;
import java.io.*;
/*
 * 계란으로 계란치기
 * 
 * 계란: 내구도, 무게
 * 
 * 계란치기
 * 1. 상대 계란의 무게만큼 내구도가 깎임
 * 2. 내구도가 0 이하가 되면 깨짐
 * 
 * 매우 복잡해 보임... N이 8이니까 그냥 8! 모든 경우의 수를 다 해버리자.
 * 
 */
public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int eggSize;
	static int[] durabilities, weights;
	static int[] selectedEggs;
	static boolean[] visited;
	static int maxBreakEggCnt;
	
	public static void init() throws IOException{
		eggSize = Integer.parseInt(br.readLine());
		durabilities = new int[eggSize];
		weights = new int[eggSize];
		for(int eggIdx=0; eggIdx<eggSize; eggIdx++) {
			st = new StringTokenizer(br.readLine());
			durabilities[eggIdx] = Integer.parseInt(st.nextToken());
			weights[eggIdx] = Integer.parseInt(st.nextToken());
		}
		selectedEggs = new int[eggSize];
		visited = new boolean[eggSize];
		maxBreakEggCnt=0;
	}
	
	// 중복이 가능한데요?
	public static void backtrack(int selectedCnt) {
		if(selectedCnt==eggSize) {
			int breakEggCnt=0;
			for(int eggIdx=0; eggIdx<eggSize; eggIdx++) {
				if(durabilities[eggIdx]<=0) breakEggCnt++;
			}
			maxBreakEggCnt = maxBreakEggCnt < breakEggCnt ? breakEggCnt : maxBreakEggCnt;
			return;
		}
		
		// 전 이미 깨졌는데요?
		if(durabilities[selectedCnt]<=0) {
			backtrack(selectedCnt+1);
			return;
		}

		// 전부다 깨져있는 건 아니죠?
		boolean isFinished = true;
		
		// 다른 친구들을 깨봅시다.
		for(int eggIdx=0; eggIdx<eggSize; eggIdx++) {
			if(eggIdx == selectedCnt || durabilities[eggIdx]<=0) continue;
			durabilities[selectedCnt] -= weights[eggIdx];
			durabilities[eggIdx] -= weights[selectedCnt];
			backtrack(selectedCnt+1);
			durabilities[selectedCnt] += weights[eggIdx];
			durabilities[eggIdx] += weights[selectedCnt];
			isFinished = false;
		}
		
		// 전부다 깨졌나 봅니다.
		if(isFinished) {
			backtrack(selectedCnt+1);
		}
		
	}
	
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
         
    	init();
    	backtrack(0);
    	sb.append(maxBreakEggCnt);
    	
    	System.out.println(sb);
    }
}
