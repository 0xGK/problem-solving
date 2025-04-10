

import java.util.*;
import java.io.*;
/*
 * 1. 궁수 3명의 위치를 조합으로 결정
 * 2. 결정된 위치에서, 동시에 시작인데, ^ 모양으로 target 구하기.
 * 		-> target 제거
 * 		-> kill 수 계산
 * 3. 적의 위치 이동
 * 		-> 맵 밖으로 벗어난 경우, 적은 사라짐.
 * 
 * 
 */
public class Main {
	static int rowSize, colSize, D, maxKillCnt;
	static int archerRow;
	static int[][] board;
	static int[][] playBoard;
	static boolean[][] isKilled;
	static int[] selectedArcher;
	static BufferedReader br;
	static StringTokenizer st;
	
	public static void init() throws IOException{
		maxKillCnt=0;
		st = new StringTokenizer(br.readLine().trim());
		rowSize = Integer.parseInt(st.nextToken());
		colSize = Integer.parseInt(st.nextToken());
		selectedArcher = new int[3];
		
		D = Integer.parseInt(st.nextToken());
		board = new int[rowSize][colSize];
		playBoard = new int[rowSize][colSize];
		
		for(int rowIdx=0; rowIdx<rowSize; rowIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int colIdx=0; colIdx<colSize; colIdx++) {
				playBoard[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	public static boolean isInBoard(int row, int col) {
		return row>=0 && row<rowSize && col>=0 && col<colSize;
	}
	
	public static boolean findTarget(int archerIdx, int distance) {
		int rowIdx = archerRow;
		int colIdx;
		
		
		// 완쪽 절반
		for(colIdx=archerIdx-(distance-1); colIdx < archerIdx; colIdx++, rowIdx--) {
			if(!isInBoard(rowIdx, colIdx)) continue;
			// target 찾으면 바로 종료하면 됨
			if(board[rowIdx][colIdx]==1) {
				isKilled[rowIdx][colIdx] = true;
				return true;
			}
			
		}
		
		// 오른쪽 절반
		for(; colIdx <= archerIdx+distance-1; colIdx++, rowIdx++) {
			if(!isInBoard(rowIdx, colIdx)) continue;
			if(board[rowIdx][colIdx]==1) {
				isKilled[rowIdx][colIdx] = true;
				return true;
			}
		}
		
		return false;
	}
	
	public static int playAttackGame() {
		int killCnt=0;
		// 맵 카피
		for(int rowIdx=0; rowIdx<rowSize; rowIdx++) {
			for(int colIdx=0; colIdx<colSize; colIdx++) {
				board[rowIdx][colIdx] = playBoard[rowIdx][colIdx];
			}
		}
		// 최대 rowSize만큼의 라운드 실행
		for(int round=0; round<rowSize; round++) {
			archerRow = rowSize - 1 - round;
			isKilled = new boolean[rowSize][colSize];
			// 궁수 선택
			for(int archerIdx : selectedArcher) {
				// 거리 1부터 시작해서 가장 가깝고, 왼족에 있는 target 찾기
				for(int distance=1; distance<=D; distance++) {
					if(findTarget(archerIdx, distance)) {
						// 찾아낸 경우 해당 아처는 종료. 다음 아처로
						break;
					}
				}
			}
			
			
			// 적 삭제
			for(int rowIdx=0; rowIdx<rowSize; rowIdx++) {
				for(int colIdx=0; colIdx<colSize; colIdx++) {
					if(isKilled[rowIdx][colIdx]) {
						board[rowIdx][colIdx]=0;
						killCnt++;
					}
				}
			}
			
			// 적 이동
//			for(int rowIdx=rowSize-1; rowIdx>0; rowIdx--) {
//				for(int colIdx=0; colIdx<colSize; colIdx++) {
//					board[rowIdx][colIdx] = board[rowIdx-1][colIdx];
//				}
//			}
//			for(int colIdx=0; colIdx<colSize; colIdx++) {
//				board[0][colIdx] = 0;
//			}
			
		}
		return killCnt;
		
	}
	public static void selectArcher(int elementIdx, int archerIdx) {
		// playGame
		if(archerIdx==3) {
			int killCnt = playAttackGame();
			maxKillCnt = maxKillCnt < killCnt ? killCnt : maxKillCnt;
			
			return;
		}
		
		// generate new branch
		for(int nxtIdx = elementIdx; nxtIdx<colSize; nxtIdx++) {
			selectedArcher[archerIdx] = nxtIdx;
			selectArcher(nxtIdx+1, archerIdx+1);
		}
		
		
	}
	
	public static void playGame() {
		// 궁수의 위치를 선택하는 조합
		selectArcher(0, 0);
	}
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
         
    	init();
    	playGame();
    	sb.append(maxKillCnt);
    	System.out.println(sb);
    }

}
