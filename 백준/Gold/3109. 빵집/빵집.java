import java.io.*;
import java.util.*;

/*
 * 주의사항
 * 	- 파이프라인의 최대 수는 R과 동일함
 * 		-> maxPipeline := R
 * 
 * start에서 end까지 가는데, 끝에 닿는 순간 cnt++고, 뒤로 돌아가면 cnt-- 임
 * 이때 문제는 end일 때만 해당하는 것
 * 즉 end-1인 경우에 end로 들어갈 때, cnt++
 * 	-> 빠져나올 때 cnt--를 해줘야함.
 * 
 *  => 아.. 잠깜만 동시에 진행을 못함..
 *  => rowIndex를 0~R-1인 값을 같이 넘겨주기
 *  => colIndex == C-1 인 경우, cnt++ 

 * backtrack을 R개의 row만큼 각각 실행할 것인데, 우선순위로 가장 위쪽부터 뻗을 수 있게 진행
 */
public class Main {
	static int R, C;
//	static boolean[][] board;
	static boolean[][] visited;
	static int totalCount;
	static boolean flag;
	
	
	
	// 초기 입력 및 초기화
	public static void init(BufferedReader br) throws IOException{
		totalCount = 0;
		
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
//		board = new boolean[R][C];
		visited = new boolean[R][C];
		
		for(int rowIndex=0; rowIndex<R; rowIndex++) {
			String inputLine = br.readLine();
			for(int colIndex=0; colIndex<C; colIndex++) {
				if(inputLine.charAt(colIndex)=='x') {
					visited[rowIndex][colIndex] = true;
				}
			}
		}
	}
	
	public static boolean isPossible(int r) {
		return r>=0 && r<R;
	}
	
	public static void backtrack(int rowIndex, int colIndex) {
		if(flag) return;
		if(colIndex == C-1) {
			totalCount++;
			flag = true;
			return;
		}
		
		for(int nextRowIndex = rowIndex - 1; nextRowIndex <= rowIndex + 1; nextRowIndex++) {
			if(!isPossible(nextRowIndex) || visited[nextRowIndex][colIndex+1]) continue;
			
			visited[nextRowIndex][colIndex+1] = true;
			backtrack(nextRowIndex, colIndex+1);
			if(flag) return;
			//visited[nextRowIndex][colIndex+1] = false;
		}
		
		
	}
	
	
	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		init(br);
		
		
		for(int i=0; i<R; i++) {
			flag = false;
			backtrack(i, 0);
		}
		System.out.println(totalCount);
		
	}
}
