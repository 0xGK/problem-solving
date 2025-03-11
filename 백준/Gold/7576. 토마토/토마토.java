import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static int totalTime, targetCnt;
	static int rowSize, colSize;
	static int board[][];
	static int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
	static Queue<Point> points;
	
	static class Point{
		int rowIdx;
		int colIdx;
		int time;
		public Point(int rowIdx, int colIdx, int time) {
			this.rowIdx = rowIdx;
			this.colIdx = colIdx;
			this.time = time;
		}
	}
	public static void init() throws IOException{
		points = new ArrayDeque<>();
        st = new StringTokenizer(br.readLine());
        colSize = Integer.parseInt(st.nextToken());
        rowSize = Integer.parseInt(st.nextToken());
		board = new int[rowSize][colSize];
		for(int rowIdx=0; rowIdx<rowSize; rowIdx++) {
			st = new StringTokenizer(br.readLine());
			for(int colIdx=0; colIdx<colSize; colIdx++) {
				board[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
				if(board[rowIdx][colIdx]==1) {
					points.add(new Point(rowIdx,colIdx,1));
				}
				
			}
		}
	}
	public static boolean isInBoard(int rowIdx, int colIdx) {
		return rowIdx>=0 && rowIdx<rowSize && colIdx>=0 && colIdx<colSize;
	}
	public static int solve() {
		totalTime=-1;
		while(!points.isEmpty()) {
			Point curPoint = points.poll();
			for(int dir[] : dirs) {
				int nxtRow = curPoint.rowIdx + dir[0];
				int nxtCol = curPoint.colIdx + dir[1];
				totalTime = curPoint.time;
				if(!isInBoard(nxtRow, nxtCol)) continue;
				if(board[nxtRow][nxtCol]==0) {
					board[nxtRow][nxtCol] = totalTime+1;
					points.add(new Point(nxtRow,nxtCol, totalTime+1));
				}
			}
		}
		
		
		for(int rowIdx=0; rowIdx<rowSize; rowIdx++) {
			for(int colIdx=0; colIdx<colSize; colIdx++) {
				if(board[rowIdx][colIdx]==0) {
					return -1;
				}
				
			}
		}
		
		return totalTime-1;
	}
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));

    	init();
    	System.out.println(solve());
    }
}
