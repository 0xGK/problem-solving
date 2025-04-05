import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int rowSize, colSize;
	static int startRow, startCol;
	static int[][] board;
	static int[][] result;
	static boolean[][] visited;
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
		result = new int[rowSize][colSize];
		visited = new boolean[rowSize][colSize];
		for(int rowIdx=0; rowIdx<rowSize; rowIdx++) {
			st = new StringTokenizer(br.readLine());
			for(int colIdx=0; colIdx<colSize; colIdx++) {
				board[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
				if(board[rowIdx][colIdx]==0) {
					visited[rowIdx][colIdx] = true;
				}
				else if(board[rowIdx][colIdx]==2) {
					startRow = rowIdx;
					startCol = colIdx;
				}
			}
		}
	}
	public static boolean isInBoard(int row, int col) {
		return row>=0 && row<rowSize && col>=0 && col<colSize;
	}
	public static void playGame() {
		Queue<Point> queue = new ArrayDeque<>();
		queue.add(new Point(startRow, startCol));
		visited[startRow][startCol] = true;
		
		while(!queue.isEmpty()) {
			Point cur = queue.poll();
			int row = cur.row;
			int col = cur.col;
			
			for(int[] dir : dirs) {
				int nxtRow = row + dir[0];
				int nxtCol = col + dir[1];
				if(!isInBoard(nxtRow, nxtCol) || visited[nxtRow][nxtCol]) continue;
				
				visited[nxtRow][nxtCol] = true;
				result[nxtRow][nxtCol] = result[row][col] + 1;
				queue.add(new Point(nxtRow, nxtCol));
			}
		}

		
	}

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
         
    	init();
    	playGame();
		for(int rowIdx=0; rowIdx<rowSize; rowIdx++) {
			for(int colIdx=0; colIdx<colSize; colIdx++) {
				if(!visited[rowIdx][colIdx] && board[rowIdx][colIdx]==1) {
					sb.append(-1).append(' ');
				}else {
					sb.append(result[rowIdx][colIdx]).append(' ');
				}
			}
			sb.append('\n');
		}
    	
    	System.out.println(sb);
    }
}
