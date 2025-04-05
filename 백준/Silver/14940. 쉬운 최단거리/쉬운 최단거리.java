import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int rowSize, colSize;
	static int startRow, startCol;
	static int[][] board;
	static int[][] dp;
	static int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
	static class Point{
		int row, col;

		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	public static void init() throws IOException{
		st = new StringTokenizer(br.readLine());
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		
		board = new int[rowSize][colSize];
		dp = new int[rowSize][colSize];
		for(int rowIdx=0; rowIdx<rowSize; rowIdx++) {
			st = new StringTokenizer(br.readLine());
			for(int colIdx=0; colIdx<colSize; colIdx++) {
				board[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
				if(board[rowIdx][colIdx]==2) {
					startRow = rowIdx;
					startCol = colIdx;
				}
			}
		}
		for(int rowIdx=0; rowIdx<rowSize; rowIdx++) {
			Arrays.fill(dp[rowIdx], Integer.MAX_VALUE);
		}
	}
	
	
	public static boolean isInBoard(int row, int col) {
		return row>=0 && row<rowSize && col>=0 && col<colSize;
	}
	public static void playGame() {
		dp[startRow][startCol] = 0;
		Queue<Point> queue = new ArrayDeque<>();
		queue.add(new Point(startRow, startCol));
		
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			int row = cur.row;
			int col = cur.col;
			
			for(int[] dir : dirs) {
				int nxtRow = row + dir[0];
				int nxtCol = col + dir[1];
				if(!isInBoard(nxtRow, nxtCol)) continue;
				if(board[nxtRow][nxtCol]==0) continue;
				
				if(dp[nxtRow][nxtCol] > dp[row][col] + 1) {
					dp[nxtRow][nxtCol] = dp[row][col] + 1;
					queue.add(new Point(nxtRow, nxtCol));
				}
			}
		}
		for(int rowIdx=0; rowIdx<rowSize; rowIdx++) {
			for(int colIdx=0; colIdx<colSize; colIdx++) {
				int distance = dp[rowIdx][colIdx]==Integer.MAX_VALUE ? 0 : dp[rowIdx][colIdx];
				if(distance==0 && board[rowIdx][colIdx]==1) {
					distance = -1;
				}
				System.out.print(distance + " ");
			}
			System.out.println();
		}
		
	}

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
         
    	init();
    	playGame();

    	
    	System.out.println(sb);
    }
}
