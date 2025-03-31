import java.io.*;
import java.util.*;
/*
 * N * N cell
 * 1개의 cell에는 core 또는 전선이 올 수 있음
 * 
 * 가장자리는 전원이 흐르고 있다.
 * core와 전원을 연결하는 전선은 직선으로만 설치 가능
 * 교차하면 안 됨
 * 
 * 전선 길이의 합이 최소가 되는 값을 구하라.
 * 
 * 
 * 백 트레킹으로 진행
 * 각각의 Core가 전선 뻗을 수 있는 경우는 4가지.
 * 	-> 이것을 실제 배열에 직선 방향으로 방문 처리
 * 		- boolean[][] visited를 사용해서, 직선 방향으로 true 값으로 방문 처리
 * 		- backtrack시에 false값으로 다시 원상 복구
 * 
 */
public class Solution {
	static BufferedReader br;
	static StringTokenizer st;
	static int[][] board;
	static boolean[][] visited;
	static int[][] dirs = {{-1,0},{0,1},{1,0},{0,-1}}; // 상 우 하 좌
	static int N, coreSize, minTotalLength;
	static int maxSelectedCoreCnt;
	
	// 벽에 붙은 경우는 prune 대상
	static class Core{
		int row, col;

		public Core(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	static List<Core> cores;
	
	// 실제 파워 위치는 아니고, 파워를 바로 받을 수 있는 위치인 경우
	public static boolean isInPower(int row, int col) {
		return row==0 || row==N-1 || col==0 || col==N-1;
	}
	public static void init() throws IOException{
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		visited = new boolean[N][N];
		cores = new ArrayList<>();
		coreSize=0;
		minTotalLength = Integer.MAX_VALUE;
		maxSelectedCoreCnt=0;
		
		for(int rowIdx=0; rowIdx<N; rowIdx++) {
			st = new StringTokenizer(br.readLine());
			for(int colIdx=0; colIdx<N; colIdx++) {
				board[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
				if(board[rowIdx][colIdx]==1) {
					visited[rowIdx][colIdx] = true;
					// 이미 파워에 닿아있는 녀석, 전선 설치 대상이 아님
					if(!isInPower(rowIdx, colIdx)) {
						cores.add(new Core(rowIdx,colIdx));
						coreSize++;						 
					}
				}
			}
		}
		
	}
	
	public static boolean isInBoard(int row, int col) {
		return row >= 0 && row < N && col >= 0 && col < N;
	}
	
	public static int getLineLength(Core core, int[] dir) {
		int nxtRow = core.row + dir[0];
		int nxtCol = core.col + dir[1];
		int cnt = 0;
		while(isInBoard(nxtRow, nxtCol)) {
			cnt++;
			if(visited[nxtRow][nxtCol]) {
				return 0;
			}
			nxtRow += dir[0];
			nxtCol += dir[1];
		}
		
		return cnt;
	}
	
	public static void fillLine(Core core, int[] dir, boolean flag) {
		int nxtRow = core.row + dir[0];
		int nxtCol = core.col + dir[1];

		while(isInBoard(nxtRow, nxtCol)) {
			visited[nxtRow][nxtCol] = flag;
			nxtRow += dir[0];
			nxtCol += dir[1];
		}
		
	}

	public static void backtrack(int coreCnt, int totalLength, int selectedCoreCnt) {
		// terminate
		if(coreCnt == coreSize) {
			if(maxSelectedCoreCnt == selectedCoreCnt) {
				minTotalLength = minTotalLength > totalLength ? totalLength : minTotalLength;				
			}else if(maxSelectedCoreCnt < selectedCoreCnt) {
				maxSelectedCoreCnt = selectedCoreCnt;
				minTotalLength = totalLength;
			}
			return;
		}
		
		
		// prune
		// 더 이상 남아있는 것들을 전부 선택해도, max 개수보다 적은 경우
		if(selectedCoreCnt + (coreSize - coreCnt) < maxSelectedCoreCnt) {
			return;
		}
		
		// generate new branch
		Core core = cores.get(coreCnt);
		for(int[] dir : dirs) {
			int lineLength = getLineLength(core, dir);
			if(lineLength > 0) {
				fillLine(core, dir, true);
				backtrack(coreCnt+1, totalLength+lineLength, selectedCoreCnt+1);
				fillLine(core, dir, false);
			}
		}
		backtrack(coreCnt+1, totalLength, selectedCoreCnt);
	}
	
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
         
        int T = Integer.parseInt(br.readLine());
        for(int testCase=1; testCase<=T; ++testCase) {
        	init();
        	backtrack(0, 0, 0);
            sb.append('#').append(testCase).append(' ').append(minTotalLength).append('\n');
             
        }
        System.out.println(sb);
         
    }
}