import java.io.*;
import java.util.*;


/*
 * board[row][col]: (row,col)에서 판매하고 있는 디저트의 종류
 * 1. 카페 사이에는 대각선으로 이동 가능
 * 	-> 반드시 돌아와야함
 * 	-> 좌상단에서 시작 && 우측 하단으로 시작하기.
 * 2. 투어 중 같은 숫자를 파는 카페가 있으면 안 됨
 * 		-> prune 조건이 될 수 있음
 * 3. 한 카페에서만 있는 것도 안 됨
 * 4. 갔던 길을 되돌아 오는 것도 안 됨
 * 
 * 
 * 
 * 
 */
public class Solution {
	static final int DESSERT_COUNT = 101;
	static int N;
	static int[][] board;
	static boolean[][] visited;
	static boolean[] selectedDessert;
	static int startRow, startCol;
	static int maxDessertCnt;
	static BufferedReader br;
	static StringTokenizer st;
	
	static int[][] dirs = {{1,1},{1,-1},{-1,-1},{-1,1}}; // 우하 -> 좌하 -> 좌상 -> 우상  
	
	public static void init() throws IOException{
		maxDessertCnt=0;
		N = Integer.parseInt(br.readLine().trim());
		board = new int[N][N];
		visited = new boolean[N][N];
		selectedDessert = new boolean[DESSERT_COUNT];
		
		for(int rowIdx=0; rowIdx<N; rowIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int colIdx=0; colIdx<N; colIdx++) {
				board[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
			}
		}
		
	}
	public static boolean isInBoard(int row, int col) {
		return row>=0 && row<N && col>=0 && col<N;
	}
	
	public static void dessertBacktrack(int row, int col, int curDirIdx, int cnt) {
		for(int dirIdx = curDirIdx; dirIdx<curDirIdx+2; dirIdx++) {
			// terminate
			if(dirIdx==4) return;
			
			int nxtRow = row + dirs[dirIdx][0];
			int nxtCol = col + dirs[dirIdx][1];
			
			if(!isInBoard(nxtRow, nxtCol) || visited[nxtRow][nxtCol]) continue;
			
			int dessert = board[nxtRow][nxtCol];
			
			// 이미 선택한 디저트인 경우
			if(selectedDessert[dessert]) {
				// prune: 만약 시작점으로 되돌아 온 경우
				if(nxtRow==startRow && nxtCol==startCol){
					maxDessertCnt = maxDessertCnt < cnt ? cnt : maxDessertCnt;
					return;
				}
				
				// 다음 케이스로 넘어가기
				continue;
			}
			
			// generate new branch
			visited[nxtRow][nxtCol] = true;
			selectedDessert[dessert] = true;
			dessertBacktrack(nxtRow, nxtCol, dirIdx, cnt+1);
			visited[nxtRow][nxtCol] = false;
			selectedDessert[dessert] = false;
		}
		
	}
	public static void playDessertGame() {
		for(startRow=0; startRow<N-1; startRow++) {
			for(startCol=1; startCol<N-1; startCol++) {
				selectedDessert[board[startRow][startCol]] = true;
				dessertBacktrack(startRow, startCol, 0, 1);
				selectedDessert[board[startRow][startCol]] = false;
			}
		}
		
		if(maxDessertCnt==0) {
			maxDessertCnt=-1;
		}
	}
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
         
        int T = Integer.parseInt(br.readLine());
        for(int testCase=1; testCase<=T; ++testCase) {
        	init();
        	playDessertGame();
            sb.append('#').append(testCase).append(' ').append(maxDessertCnt).append('\n');
             
        }
        System.out.println(sb);
         
    }
}
