import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int rowSize, colSize, operationSize, operation;
	static int[][] board, resultBoard;
	static int[] operations;
	public static void init() throws IOException{
		st = new StringTokenizer(br.readLine());
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		board = new int[rowSize][colSize];
		operationSize = Integer.parseInt(st.nextToken());
		operations = new int[operationSize];
		
		
		for(int rowIdx=0; rowIdx<rowSize; rowIdx++) {
			st = new StringTokenizer(br.readLine());
			for(int colIdx=0; colIdx<colSize; colIdx++){
				board[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
			}
		}
		st = new StringTokenizer(br.readLine());
		for(int operationIdx=0; operationIdx<operationSize; operationIdx++) {
			operations[operationIdx] = Integer.parseInt(st.nextToken());
		}
	}
	public static void solve(int operation) {
		// 상하 대칭
		rowSize = board.length;
		colSize = board[0].length;
		if(operation==1) {
			resultBoard = new int[rowSize][colSize];
			for(int r=0; r<rowSize; r++) {
				for(int c=0; c<colSize; c++){
					int nr = (rowSize - 1) - r;
					int nc = c;
					resultBoard[nr][nc] = board[r][c];
				}
			}
		}
		// 좌우 대칭
		else if(operation==2) {
			resultBoard = new int[rowSize][colSize];
			for(int r=0; r<rowSize; r++) {
				for(int c=0; c<colSize; c++){
					int nr = r;
					int nc = (colSize - 1) - c;
					resultBoard[nr][nc] = board[r][c];
				}
			}
		}
		// 오른쪽 90도 회전
		else if(operation==3) {
			resultBoard = new int[colSize][rowSize];
			for(int r=0; r<rowSize; r++) {
				for(int c=0; c<colSize; c++){
					int nr = c;
					int nc = (rowSize-1)-r;
					resultBoard[nr][nc] = board[r][c];
				}
			}
		}
		// 왼쪽 90도 회전
		else if(operation==4) {
			resultBoard = new int[colSize][rowSize];
			for(int r=0; r<rowSize; r++) {
				for(int c=0; c<colSize; c++){
					int nr = (colSize-1)-c;
					int nc = r;
					resultBoard[nr][nc] = board[r][c];
				}
			}
		}
		// 4등분 시계방향
		else if(operation==5) {
			resultBoard = new int[rowSize][colSize];
			// 1->2
			for(int r=0; r<rowSize/2; r++) {
				for(int c=0; c<colSize/2; c++){
					int nr = r;
					int nc = c+colSize/2;
					resultBoard[nr][nc] = board[r][c];
				}
			}
			// 2->3
			for(int r=0; r<rowSize/2; r++) {
				for(int c=colSize/2; c<colSize; c++){
					int nr = r+rowSize/2;
					int nc = c;
					resultBoard[nr][nc] = board[r][c];
				}
			}
			// 3->4
			for(int r=rowSize/2; r<rowSize; r++) {
				for(int c=colSize/2; c<colSize; c++){
					int nr = r;
					int nc = c-colSize/2;
					resultBoard[nr][nc] = board[r][c];
				}
			}
			// 4->1
			for(int r=rowSize/2; r<rowSize; r++) {
				for(int c=0; c<colSize/2; c++){
					int nr = r-rowSize/2;
					int nc = c;
					resultBoard[nr][nc] = board[r][c];
				}
			}
		}
		else if(operation==6) {
			resultBoard = new int[rowSize][colSize];
			// 1->4
			for(int r=0; r<rowSize/2; r++) {
				for(int c=0; c<colSize/2; c++){
					int nr = r+rowSize/2;
					int nc = c;
					resultBoard[nr][nc] = board[r][c];
				}
			}
			// 4->3
			for(int r=rowSize/2; r<rowSize; r++) {
				for(int c=0; c<colSize/2; c++){
					int nr = r;
					int nc = c+colSize/2;
					resultBoard[nr][nc] = board[r][c];
				}
			}
			// 3->2
			for(int r=rowSize/2; r<rowSize; r++) {
				for(int c=colSize/2; c<colSize; c++){
					int nr = r-rowSize/2;
					int nc = c;
					resultBoard[nr][nc] = board[r][c];
				}
			}
			// 2->1
			for(int r=0; r<rowSize/2; r++) {
				for(int c=colSize/2; c<colSize; c++){
					int nr = r;
					int nc = c-colSize/2;
					resultBoard[nr][nc] = board[r][c];
				}
			}	
		}
		board = resultBoard;

	}
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
         
    	init();
    	for(int Idx=0; Idx<operationSize; Idx++) {
    		solve(operations[Idx]);
    	}
		for(int rowIdx=0; rowIdx<resultBoard.length; rowIdx++) {
			for(int colIdx=0; colIdx<resultBoard[0].length; colIdx++){
				System.out.print(resultBoard[rowIdx][colIdx]+" ");
			}
			System.out.println();
		}
    	
    	System.out.println(sb);
    }
}
