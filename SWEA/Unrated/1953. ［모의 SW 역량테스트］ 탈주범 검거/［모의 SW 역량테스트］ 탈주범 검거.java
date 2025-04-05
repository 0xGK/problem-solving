import java.io.*;
import java.util.*;
/*
 * 주어진 시간 L.
 * L-1만큼 이동 가능
 */
public class Solution {
	static BufferedReader br;
	static StringTokenizer st;
	
	static final int[][][] dirs = {{},
							{{-1,0},{1,0},{0,-1},{0,1}},
							{{-1,0},{1,0}},
							{{0,-1},{0,1}},
							{{-1,0},{0,1}},
							{{1,0},{0,1}},
							{{1,0},{0,-1}},
							{{-1,0},{0,-1}}};
	
	
	static int rowSize, colSize, startRow, startCol, escapeTime;
	static int[][] board;
	static boolean[][] visited;
	static int totalCnt;
	
	static class Position{
		int row, col, time;
		public Position(int row, int col, int time) {
			this.row = row;
			this.col = col;
			this.time = time;
		}
	}
	static Queue<Position> queue;
	
	static boolean[][] horizontalAnswer;
	static boolean[][] verticalAnswer;
	public static void makeAnswer() {
		// 가로 연속인 경우의 터널 구조물 타입 (left, right)
		// (1 or 3 or 4 or 5, 1 or 3 or 6 or 7)
		horizontalAnswer = new boolean[8][8];
		int[] leftList = {1,3,4,5};
		int[] rightList = {1,3,6,7};
		for(int left : leftList) {
			for(int right : rightList) {
				horizontalAnswer[left][right] = true;
			}
		}
		
		// 세로 연속인 경우 (up, down)
		// (1 or 2 or 5 or 6, 1 or 2 or 4 or 7)
		verticalAnswer = new boolean[8][8];
		int[] upList = {1,2,5,6};
		int[] downList = {1,2,4,7};
		for(int up : upList) {
			for(int down : downList) {
				verticalAnswer[up][down] = true;
			}
		}
		
	}
	
	public static void init() throws IOException{
		st = new StringTokenizer(br.readLine());
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		startRow = Integer.parseInt(st.nextToken());
		startCol = Integer.parseInt(st.nextToken());
		escapeTime = Integer.parseInt(st.nextToken());
		board = new int[rowSize][colSize];
		for(int rowIdx=0; rowIdx<rowSize; rowIdx++) {
			st = new StringTokenizer(br.readLine());
			for(int colIdx=0; colIdx<colSize; colIdx++) {
				board[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
			}
		}
		totalCnt=0;
	}
	
	
	public static boolean isInBoard(int row, int col) {
		return row>=0 && row<rowSize && col>=0 && col<colSize;
	}
	
	public static boolean isConnected(int row, int col, int nxtRow, int nxtCol) {
		// 가로 연속 or 세로 연속인 상황임
		if(row == nxtRow) {
			// swap
			col = col < nxtCol ? col : nxtCol;
			int left = board[row][col];
			int right = board[row][col+1];
			return horizontalAnswer[left][right];
		}else {
			//swap
			row = row < nxtRow ? row : nxtRow;
			int up = board[row][col];
			int down = board[row+1][col];
			return verticalAnswer[up][down];
		}
	}
	
	public static void solve() {
		queue = new ArrayDeque<>();
		visited = new boolean[rowSize][colSize];
		
		queue.add(new Position(startRow, startCol, escapeTime));
		visited[startRow][startCol] = true;
		totalCnt++;
		while(!queue.isEmpty()) {
			Position cur = queue.poll();
			int time = cur.time;
			if(time==1) return;
			
			int row = cur.row;
			int col = cur.col;
			int dirIdx = board[row][col];
			
			for(int[] dir : dirs[dirIdx]) {
				int nxtRow = row + dir[0];
				int nxtCol = col + dir[1];
				if(!isInBoard(nxtRow, nxtCol) || visited[nxtRow][nxtCol] || !isConnected(row,col,nxtRow,nxtCol)) continue;
				queue.add(new Position(nxtRow, nxtCol, time-1));
				visited[nxtRow][nxtCol] = true;
				totalCnt++;
			}
		}
	}
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
         
        makeAnswer();
        int T = Integer.parseInt(br.readLine());
        for(int testCase=1; testCase<=T; ++testCase) {
        	init();
        	solve();
            sb.append('#').append(testCase).append(' ').append(totalCnt).append('\n');
        }
        System.out.println(sb);
         
    }
}