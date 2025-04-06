import java.io.*;
import java.util.*;

/*
 * 봉우리: 높이가 최대인 곳. 여러 개 있을 수 있음
 * 
 * 각 봉우리에서 시작하여, 4방향으로 수가 낮은 곳을 향해 이동할 때, 최대 깊이를 구해야 한다.
 * 
 * 이때, 공사 가능 깊이가 주어진다. 딱 한 곳만 정해서 깎을 때, 최대 깊이를 구하자!
 * 
 * 흠. N = 8
 * 전체 좌표에 대해서, K만큼 깎고, 모든 봉우리에서 진행?
 * 
 * [주의]
 * 필요한 경우 지형을 깎아 높이를 1보다 작게 만드는 것도 가능하다.
 * 	-> 사실 여기가 종착지가 될 것임.
 * 최대 공사 가능 깊이라는 것은, 0~K만큼 공사 가능하다는 것.
 * 
 * 1. 각 봉우리에서 출발
 * 	1-1. 각 봉우리는 공사칠 찬스가 있음. 값은 마음대로.
 * 	1-2. nxt로 이동할 때, 못가는 곳이었다면? 이동할 만큼으로 최소한의 값으로 깎고 들어가보자.
 * 	1-3. 깎지 않고 그냥도 들어가 봐야한다. 이것들을 전부 visited로 관리.
 * 
 */


public class Solution {
	static BufferedReader br;
	static StringTokenizer st;
	
	static final int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
	
	static int boardSize, maxConstructDepth;
	static int[][] board;
	static int[][] boardBackup;
	static boolean[][] visited;
	static int peekHeight;
	static int maxDepth;
	
	static class Peek{
		int row, col;
		int height;	// 안 쓸 수도 있음..
		public Peek(int row, int col, int height) {
			this.row = row;
			this.col = col;
			this.height = height;
		}
		
	}
	static List<Peek> peeks;
	public static void init() throws IOException{
		st = new StringTokenizer(br.readLine());
		boardSize = Integer.parseInt(st.nextToken());
		board = new int[boardSize][boardSize];
		
		peekHeight = 0;
		maxConstructDepth = Integer.parseInt(st.nextToken());
		
		for(int rowIdx=0; rowIdx<boardSize; rowIdx++) {
			st = new StringTokenizer(br.readLine());
			for(int colIdx=0; colIdx<boardSize; colIdx++) {
				board[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
				peekHeight = peekHeight < board[rowIdx][colIdx] ? board[rowIdx][colIdx] : peekHeight;
			}
		}
		peeks = new ArrayList<>();
		for(int rowIdx=0; rowIdx<boardSize; rowIdx++) {
			for(int colIdx=0; colIdx<boardSize; colIdx++) {
				if(peekHeight == board[rowIdx][colIdx]) {
					peeks.add(new Peek(rowIdx, colIdx, peekHeight));
				}
			}
		}
		maxDepth = 0;
	}
	
	
	public static boolean isInBoard(int row, int col) {
		return row>=0 && row<boardSize && col>=0 && col<boardSize;
	}
	
	// height: 현재 위치의 높이
	public static void backtrack(int row, int col, int height, int depth, boolean isConstructed) {
		// terminate...?
		// 이 연산을 계속...? 이것이 최선?
		maxDepth = maxDepth < depth ? depth : maxDepth;
		
		// generate new branch
		for(int[] dir : dirs) {
			int nxtRow = row + dir[0];
			int nxtCol = col + dir[1];
			if(!isInBoard(nxtRow, nxtCol)) continue;
			if(visited[nxtRow][nxtCol]) continue;
			
			int nxtHeight = board[nxtRow][nxtCol];
			
			// 공사 안 해도 됨!
			if(nxtHeight < height) {
				visited[nxtRow][nxtCol] = true;
				backtrack(nxtRow, nxtCol, nxtHeight, depth+1, isConstructed);
				visited[nxtRow][nxtCol] = false;
			}
			
			// 공사 해볼래?
			else if(!isConstructed) {
				int cuttingCnt = nxtHeight - height + 1;
				
				// 이 만큼 자를 수 있겠어?
				if(cuttingCnt <= maxConstructDepth){
					nxtHeight -= cuttingCnt;
					visited[nxtRow][nxtCol] = true;
					backtrack(nxtRow, nxtCol, nxtHeight, depth+1, true);
					visited[nxtRow][nxtCol] = false;
				}
			}
		}
	}
	
	public static void solve() {
		// 어디를 공사할까?
		for(Peek peek : peeks) {
			visited = new boolean[boardSize][boardSize];
			visited[peek.row][peek.col]= true; 
			backtrack(peek.row, peek.col, peek.height, 1, false);
		}
	}
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
         
        int T = Integer.parseInt(br.readLine());
        for(int testCase=1; testCase<=T; ++testCase) {
        	init();
        	solve();
            sb.append('#').append(testCase).append(' ').append(maxDepth).append('\n');
             
        }
        System.out.println(sb);
         
    }
}