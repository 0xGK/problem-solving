
import java.util.*;
import java.io.*;

/*
 * 1. backtrack으로 접근
 * 	- 진행 가능한 방향 4곳
 * 	- prune: nextStep에 이미 접근했던 값이 있다면, 그것을 사용하고 끊으면 됨
 *
 * 
 * 
 */
public class Solution {
	static final int MAX_N = 1001;
	static int N;
	static int[][] board = new int[MAX_N][MAX_N];
	static int[][] stepBoard = new int[MAX_N][MAX_N];
	static int maxDepth;
	static int lastDepth;
	static int[][] dirs = {{-1,0}, {1,0}, {0,-1}, {0,1}};
	static boolean[][] visited;
	
	public static void init() {
		visited = new boolean[N][N];
		maxDepth = 0;
	}
	
	public static boolean isPossible(int x, int y) {
		return x>=0 && y>=0 && x<N && y<N;
		
	}

	public static void backtrack(int x, int y) {
		// 4 방향으로 진행
		for(int[] dir : dirs) {
			int nx = x + dir[0];
			int ny = y + dir[1];
			
			if(!isPossible(nx, ny) || visited[nx][ny] || (board[nx][ny]-board[x][y]!=1)) continue;
			
			// prune
			if(stepBoard[nx][ny] != 1) {
				int tempStep = stepBoard[nx][ny] + lastDepth;
				lastDepth = lastDepth < tempStep ? tempStep: lastDepth;
				return;
			}
			
			visited[nx][ny] = true;
			lastDepth++;
			backtrack(nx, ny);
			visited[nx][ny] = false;				
		
		}
	}
	public static int findMaxDepth(StringBuilder sb) {
		int minRoomNumber = Integer.MAX_VALUE;
		for(int rowIndex=0; rowIndex<N; rowIndex++) {
			for(int colIndex=0; colIndex<N; colIndex++) {
				if(maxDepth == stepBoard[rowIndex][colIndex]) {
					minRoomNumber = minRoomNumber > board[rowIndex][colIndex] ? board[rowIndex][colIndex] : minRoomNumber;
				}
			}
		}
		return minRoomNumber;
	}
	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		
		int T = Integer.parseInt(br.readLine());
		for(int testCase=1; testCase<=T; testCase++) {
			sb.append("#").append(testCase).append(" ");
			N = Integer.parseInt(br.readLine());
			init();
			for(int rowIndex=0; rowIndex<N; rowIndex++) {
				st = new StringTokenizer(br.readLine());
				for(int colIndex=0; colIndex<N; colIndex++) {
					board[rowIndex][colIndex] = Integer.parseInt(st.nextToken());
					stepBoard[rowIndex][colIndex] = 1;
				}
				
			}
			
			for(int rowIndex=0; rowIndex<N; rowIndex++) {
				for(int colIndex=0; colIndex<N; colIndex++) {
					lastDepth=1;
					visited = new boolean[N][N];
					visited[rowIndex][colIndex] = true;
					backtrack(rowIndex, colIndex);
					stepBoard[rowIndex][colIndex] = lastDepth;
					maxDepth = maxDepth < lastDepth ? lastDepth : maxDepth; 
				}
			}

			int minRoomNumber = findMaxDepth(sb);
			sb.append(minRoomNumber).append(" ").append(maxDepth).append("\n");
		}
		System.out.println(sb);
	}
}
