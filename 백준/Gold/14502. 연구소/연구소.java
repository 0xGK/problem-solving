import java.util.*;
import java.io.*;
/*
 * 벽 3개를 추가하자.
 * 1: 벽
 * 2: 바이러스
 * 	-> 상하좌우로 퍼진다.
 * 	-> bfs로 인접한 모든 곳에 퍼짐
 * 
 * 안전한 영역의 수를 구하자.
 * 
 * 
 */
public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int rowSize, colSize;
	static int[][] board;
	static int[][] backupBoard;
	static List<Integer> emptyList;
	static List<Integer> virusList;
	static int[] selectedList;
	static int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
	static int minVirusCnt;
	public static void init() throws IOException{
		st = new StringTokenizer(br.readLine());
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		board = new int[rowSize][colSize];
		backupBoard = new int[rowSize][colSize];
		emptyList = new ArrayList<>();
		virusList = new ArrayList<>();
		for(int rowIdx=0; rowIdx<rowSize; rowIdx++) {
			st = new StringTokenizer(br.readLine());
			for(int colIdx=0; colIdx<colSize; colIdx++) {
				backupBoard[rowIdx][colIdx] = board[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
				if(board[rowIdx][colIdx]==0) {
					emptyList.add(rowIdx*colSize+colIdx);
				}else if(board[rowIdx][colIdx]==2) {
					virusList.add(rowIdx*colSize+colIdx);
				}
			}
		}
		selectedList = new int[3];
		minVirusCnt = Integer.MAX_VALUE;
	}
		
	public static boolean isInBoard(int row, int col) {
		return row>=0 && row<rowSize && col>=0 && col<colSize;
	}
	public static int getVirusArea() {
		int virusCnt=0;
		
		// 벽 설치
		for(int extraWall : selectedList) {
			board[extraWall/colSize][extraWall%colSize] = 1;
		}
		
		// 바이러스 전파
		for(int virus : virusList) {
			Queue<Integer> q = new ArrayDeque<>();
			q.add(virus);
			while(!q.isEmpty()) {
				int cur = q.poll();
				int row = cur/colSize;
				int col = cur%colSize;
				
				for(int[] dir : dirs) {
					int nxtRow = row + dir[0];
					int nxtCol = col + dir[1];
					
					if(!isInBoard(nxtRow, nxtCol)) continue;
					if(board[nxtRow][nxtCol]!=0) continue;
					board[nxtRow][nxtCol] = 2;
					q.add(nxtRow*colSize+nxtCol);
					virusCnt++;
				}
			}
		}
		
//		System.out.println(virusCnt);
		// 백업
		for(int rowIdx=0; rowIdx<rowSize; rowIdx++) {
			for(int colIdx=0; colIdx<colSize; colIdx++) {
				board[rowIdx][colIdx] = backupBoard[rowIdx][colIdx];
			}
		}
		return virusCnt;
	}
	public static void backtrack(int selectedCnt, int selectedIdx) {
		// terminate
		if(selectedCnt==3) {
//			System.out.println(Arrays.toString(selectedList));
			int cnt = getVirusArea();
//			System.out.println(emptyList.size() -3-cnt);
//			System.out.println(cnt);
			minVirusCnt = minVirusCnt > cnt ? cnt : minVirusCnt; 
			return;
		}
		
		// prune
		
		// generate new branch
		for(int idx=selectedIdx; idx<emptyList.size(); idx++) {
			selectedList[selectedCnt] = emptyList.get(idx);
			backtrack(selectedCnt+1, idx+1);
		}
	}
	public static void solve() {
		backtrack(0,0);
	}
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
//        StringBuilder sb = new StringBuilder();
         
    	init();
    	solve();
//    	sb.append(emptyList.size() - 3 - minVirusCnt);
//    	System.out.println(sb);
    	System.out.println(emptyList.size() - 3 - minVirusCnt);
    }
}
