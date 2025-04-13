import java.util.*;
import java.io.*;
/*
 * 단순 구현으로 DFS를 하자!
 * 
 * 1. (0,1)부터 출발해서 (N-1, N-1)까지 도달할 수 있는 모든 경우의 수를 구하자
 * 	1-1. 현재 방향에 따라 진행할 수 있는 방법이 정해져 있음
 * 	 
 * 
 */
public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int boardSize;
	static int[][] board;
	
	static final int HORIZONTAL = 0;
	static final int VERTICAL = 1;
	static final int DIAGONAL = 2;

	static int totalCnt;
	
	static boolean[][][] isMove;
	
	
	public static void init() throws IOException{
		boardSize = Integer.parseInt(br.readLine());
		board = new int[boardSize][boardSize];
		for(int rowIdx=0; rowIdx<boardSize; rowIdx++) {
			st = new StringTokenizer(br.readLine());
			for(int colIdx=0; colIdx<boardSize; colIdx++) {
				board[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
			}
		}
		totalCnt=0;
		isMove = new boolean[3][boardSize][boardSize];
		
		for(int rowIdx=0; rowIdx<boardSize-1; rowIdx++) {
			for(int colIdx=0; colIdx<boardSize-1; colIdx++) {
				if(board[rowIdx+1][colIdx] == 0) {
					isMove[VERTICAL][rowIdx][colIdx] = true;
				}
				if(board[rowIdx][colIdx+1] == 0) {
					isMove[HORIZONTAL][rowIdx][colIdx] = true;
				}
				if(board[rowIdx+1][colIdx] == 0 && board[rowIdx][colIdx+1] == 0 && board[rowIdx+1][colIdx+1] == 0) {
					isMove[DIAGONAL][rowIdx][colIdx] = true;
				}
			}
		}
		// 끝부분 처리
		for(int rowIdx=0; rowIdx<boardSize-1; rowIdx++) {
			if(board[rowIdx+1][boardSize-1] == 0) {
				isMove[VERTICAL][rowIdx][boardSize-1] = true;
			}
		}
		for(int colIdx=0; colIdx<boardSize-1; colIdx++) {
			if(board[boardSize-1][colIdx+1] == 0) {
				isMove[HORIZONTAL][boardSize-1][colIdx] = true;
			}
		}
		
	}
	public static boolean isInBoard(int row, int col) {
		return row>=0 && row<boardSize && col>=0 && col<boardSize;
	}
	public static void dfs(int row, int col, int dir) {
		if(row==boardSize-1 && col== boardSize-1) {
			totalCnt++;
			return;
		}
		
		// 저는 가로입니다.
		if(dir==HORIZONTAL) {
			// 오른쪽으로 이동 가능한가요?
//			if(!isInBoard(row, col+1) || board[row][col+1]==1) return;
			if(isMove[HORIZONTAL][row][col])
				dfs(row, col+1, HORIZONTAL);
			
			// 대각선으로도 이동 가능한가요?
//			if(!isInBoard(row+1, col) || board[row+1][col]==1 || board[row+1][col+1]==1) return;
			if(isMove[DIAGONAL][row][col])
				dfs(row+1, col+1, DIAGONAL);
		}
		// 저는 세로입니다.
		else if(dir==VERTICAL) {
			// 아래로 이동 가능한가요?
//			if(!isInBoard(row+1, col) || board[row+1][col]==1) return;
			if(isMove[VERTICAL][row][col])
				dfs(row+1, col, VERTICAL);
			
			// 대각선으로도 이동 가능한가요?
//			if(!isInBoard(row, col+1) || board[row][col+1]==1 || board[row+1][col+1]==1) return;
			if(isMove[DIAGONAL][row][col])
				dfs(row+1, col+1, DIAGONAL);
			
		}
		// 저는 대각선 입니다.
		else if(dir==DIAGONAL) {
			// 오른쪽으로 이동 가능한가요?
//			if(isInBoard(row, col+1) && board[row][col+1]==0) {
//				dfs(row, col+1, HORIZONTAL);				
//			}
			if(isMove[HORIZONTAL][row][col])
				dfs(row, col+1, HORIZONTAL);
			
			// 아래로 이동 가능한가요?
//			if(!isInBoard(row+1, col) || board[row+1][col]==1) return;
			if(isMove[VERTICAL][row][col])
				dfs(row+1, col, VERTICAL);
			
			// 대각선으로도 이동 가능한가요?
//			if(!isInBoard(row, col+1) || board[row][col+1]==1 || board[row+1][col+1]==1) return;
//			dfs(row+1, col+1, DIAGONAL);
			if(isMove[DIAGONAL][row][col])
				dfs(row+1, col+1, DIAGONAL);
		}
	}
		

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
    	init();
    	dfs(0,1,HORIZONTAL);
    	System.out.println(totalCnt);
    }
}
