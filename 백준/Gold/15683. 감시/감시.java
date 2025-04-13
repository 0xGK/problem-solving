import java.util.*;
import java.io.*;
/*
 * [문제 조건]
 * 사무실 크기: N * M
 * N, M <= 8
 * cctv의 최대 개수: 8
 * 
 * 0: 빈 칸
 * 1~5: cctv
 * 6: 벽
 * #: 감시할 수 있는 영역
 * - cctv는 벽을 통과할 수 없음
 * - cctv는 cctv를 통과할 수 있음
 * 
 * [문제]
 * 사각지대의 최소 크기를 구하시오.
 * 
 * [cctv]
 * 1: 경우의 수=4
 * 2: 경우의 수=2
 * 3: 경우의 수=4 
 * 4: 경우의 수=4
 * 5: 경우의 수=1
 * 
 * [완전탐색]
 * cctv 수의 최대 = 8
 * 4^8 = 2^16
 * 	   = 1024 * 64 괜찮을 듯
 *  => backtracking 으로 구현 하자!
 * 
 * 
 */
public class Main{
	static final int UP = 0;
	static final int RIGHT = 1;
	static final int DOWN = 2;
	static final int LEFT = 3;
	static final int WALL = 6;
	static int minEmptyCnt;
	static int rowSize, colSize;
	static int[][] board;
	static int[][] tempBoard;
	static BufferedReader br;
	static StringTokenizer st;
	static List<CCTV> CCTVs;
	static int cctvCnt;
	static int[] selectedDir;
	// index: cctv 번호. value: 가능한 방향의 수
	static int[] cctvDirNumbers = {0,4,2,4,4,1};
	static int[][] dirs = {{-1,0},{0,1},{1,0},{0,-1}};
	
	static class CCTV{
		int row;
		int col;
		int type;
		public CCTV(int row, int col, int type) {
			this.row = row;
			this.col = col;
			this.type = type;
		}
	}
	
	public static void init() throws IOException{
		CCTVs = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		board = new int[rowSize][colSize];
		for(int rowIdx=0; rowIdx<rowSize; rowIdx++) {
			st = new StringTokenizer(br.readLine());
			for(int colIdx=0; colIdx<colSize; colIdx++) {
				board[rowIdx][colIdx]=Integer.parseInt(st.nextToken());
				// cctv인 경우
				if(board[rowIdx][colIdx]>0 && board[rowIdx][colIdx]<6) {
					CCTVs.add(new CCTV(rowIdx, colIdx, board[rowIdx][colIdx]));
				}
			}
		}
		cctvCnt = CCTVs.size();
		selectedDir = new int[cctvCnt];
		minEmptyCnt = Integer.MAX_VALUE;
	}
	
	
	public static int[][] getTempBoard() {
		int[][] tempBoard = new int[rowSize][colSize];
		for(int rowIdx=0; rowIdx<rowSize; rowIdx++) {
			for(int colIdx=0; colIdx<colSize; colIdx++) {
				tempBoard[rowIdx][colIdx]=board[rowIdx][colIdx];
			}
		}
		return tempBoard;
	}
	
	public static boolean isInBoard(int row, int col) {
		return row>=0 && row<rowSize && col>=0 && col<colSize;
	}
	public static void fillByDir(CCTV cctv, int dirIdx) {
		int row = cctv.row;
		int col = cctv.col;
		int[] dir = dirs[dirIdx];
		
		while(true) {
			row += dir[0];
			col += dir[1];
			
			if(!isInBoard(row, col) || tempBoard[row][col]==WALL) return;
			
			tempBoard[row][col] = 7;
		}
	}
	
	public static int getEmptyCnt() {
		int cnt=0;
		for(int rowIdx=0; rowIdx<rowSize; rowIdx++) {
			for(int colIdx=0; colIdx<colSize; colIdx++) {
				if(tempBoard[rowIdx][colIdx]==0) {
					cnt++;
				}
			}
		}
		return cnt;
	}
	
	public static void cctvBacktrack(int selectedCnt) {
		if(selectedCnt == cctvCnt) {
			tempBoard = getTempBoard();
			for(int cctvIdx=0; cctvIdx<cctvCnt; cctvIdx++) {
				CCTV cctv = CCTVs.get(cctvIdx);
				int type = cctv.type;
				int dirIdx = selectedDir[cctvIdx];
				
				if(type==1) {
					fillByDir(cctv, dirIdx);
				}else if(type==2) {
					fillByDir(cctv, dirIdx);
					fillByDir(cctv, dirIdx+2);
				}else if(type==3) {
					fillByDir(cctv, dirIdx);
					fillByDir(cctv, (dirIdx+1)%4);
				}else if(type==4) {
					fillByDir(cctv, dirIdx);
					fillByDir(cctv, (dirIdx+1)%4);
					fillByDir(cctv, (dirIdx+2)%4);
				}else if(type==5) {
					fillByDir(cctv, dirIdx);
					fillByDir(cctv, dirIdx+1);
					fillByDir(cctv, dirIdx+2);
					fillByDir(cctv, dirIdx+3);
				}
			}
			
			int cnt = getEmptyCnt();
			minEmptyCnt = minEmptyCnt > cnt ? cnt : minEmptyCnt;
			return;
		}
		
		int type = CCTVs.get(selectedCnt).type;
		for(int typeIdx=0; typeIdx<cctvDirNumbers[type]; typeIdx++) {
			selectedDir[selectedCnt] = typeIdx;
			cctvBacktrack(selectedCnt+1);
		}
	}
	public static void solve() {
		cctvBacktrack(0);
	}
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
         
    	init();
    	solve();
    	System.out.println(minEmptyCnt);
    }
}
