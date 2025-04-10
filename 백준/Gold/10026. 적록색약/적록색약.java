import java.util.*;
import java.io.*;

/*
 * 
 * 
 * 
 * 
 */
public class Main {
	static int N;
	static char[][] board;
	static boolean[][] visited;
	static int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}}; // 상하좌우
	static BufferedReader br;

	static class Point{
		int row, col;
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	public static void init() throws IOException{
		N = Integer.parseInt(br.readLine().trim());
		board = new char[N][N];
		
		for(int rowIdx=0; rowIdx<N; rowIdx++) {
			String inputLine = br.readLine().trim();
			for(int colIdx=0; colIdx<N; colIdx++) {
				board[rowIdx][colIdx] = inputLine.charAt(colIdx);
			}
		}
	}
	
	public static boolean isInBoard(int row, int col) {
		return row>=0 && row<N && col>=0 && col<N;
	}
	
	public static void fillSingleColor(int row, int col, char color) {
		Queue<Point> queue = new ArrayDeque<>();
		queue.add(new Point(row, col));
		visited[row][col]=true;
		
		while(!queue.isEmpty()) {
			Point curPoint = queue.poll();
			for(int[] dir : dirs) {
				int nxtRow = curPoint.row + dir[0];
				int nxtCol = curPoint.col + dir[1];
				
				if(!isInBoard(nxtRow, nxtCol)) continue;
				
				if(visited[nxtRow][nxtCol] || board[nxtRow][nxtCol] != color) continue;
				
				visited[nxtRow][nxtCol] = true;
				queue.add(new Point(nxtRow, nxtCol));
			}
		}
	}
	public static void fillRGColor(int row, int col) {
		Queue<Point> queue = new ArrayDeque<>();
		queue.add(new Point(row, col));
		visited[row][col]=true;
		
		while(!queue.isEmpty()) {
			Point curPoint = queue.poll();
			for(int[] dir : dirs) {
				int nxtRow = curPoint.row + dir[0];
				int nxtCol = curPoint.col + dir[1];
				
				if(!isInBoard(nxtRow, nxtCol)) continue;
				
				if(visited[nxtRow][nxtCol] || board[nxtRow][nxtCol] == 'B') continue;
				
				visited[nxtRow][nxtCol] = true;
				queue.add(new Point(nxtRow, nxtCol));
			}
		}
	}
	
	public static void playRGBGame() {
		// Green을 먼저 visited 처리
		// -> 이후 2가지 버전 생성 가능
		// 1) G / RB
		// 2) G / R / B
		int greenCnt=0;
		int redCnt=0;
		int blueCnt=0;
		int redGreenCnt=0;
		visited = new boolean[N][N];
		// 초록색 먼저
		char color = 'G';
		for(int rowIdx=0; rowIdx<N; rowIdx++) {
			for(int colIdx=0; colIdx<N; colIdx++) {
				if(visited[rowIdx][colIdx]) continue;
				if(board[rowIdx][colIdx]==color) {
					greenCnt++;
					fillSingleColor(rowIdx, colIdx, color);
				}
			}
		}
		
		// 빨강
		color = 'R';
		for(int rowIdx=0; rowIdx<N; rowIdx++) {
			for(int colIdx=0; colIdx<N; colIdx++) {
				if(visited[rowIdx][colIdx]) continue;
				if(board[rowIdx][colIdx]==color) {
					redCnt++;
					fillSingleColor(rowIdx, colIdx, color);
				}
			}
		}
		// 파랑
		color = 'B';
		for(int rowIdx=0; rowIdx<N; rowIdx++) {
			for(int colIdx=0; colIdx<N; colIdx++) {
				if(visited[rowIdx][colIdx]) continue;
				if(board[rowIdx][colIdx]==color) {
					blueCnt++;
					fillSingleColor(rowIdx, colIdx, color);
				}
			}
		}
		
		// 적록색약
		visited = new boolean[N][N];
		for(int rowIdx=0; rowIdx<N; rowIdx++) {
			for(int colIdx=0; colIdx<N; colIdx++) {
				if(visited[rowIdx][colIdx]) continue;
				if(board[rowIdx][colIdx]!='B') {
					redGreenCnt++;
					fillRGColor(rowIdx, colIdx);
				}
			}
		}
		
		
//		System.out.println(redCnt);
//		System.out.println(greenCnt);
//		System.out.println(blueCnt);
//		System.out.println(redBlueCnt);
		
		System.out.println((redCnt+blueCnt+greenCnt)+" " + (redGreenCnt+blueCnt));
		
	}
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
         
    	init();
    	playRGBGame();

    }
}
