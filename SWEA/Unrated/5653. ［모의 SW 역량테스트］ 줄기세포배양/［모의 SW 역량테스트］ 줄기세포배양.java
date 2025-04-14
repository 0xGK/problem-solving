import java.io.*;
import java.util.*;

/*
 * @author GK
 * 
 * 0. 생명력 : X
 * 	-> X 시간 동안 비활성 상태이고 X 시간이 지나는 순간 활성 상태가 된다.
 * 	-> 활성 상태가 되면 X시간동안 살 수 있고, 이후에 죽게 됨
 * 	-> 죽은 상태로 그리드 셀을 차지함
 * 
 * 1. 활성화된 줄기세포 
 * 	1-1. 비활성 상태인 녀석을 번식함
 * 	1-2. 이미 줄기세포 존재하는 곳에는 추가 번식 안 함
 * 	1-3. 2개 이상 동시에 번식하려는 경우 생명력 수치가 높은 줄기 세포가 혼자 차지합니다.
 * 	1-4. 생명력 수치 기준으로 정렬해서, 앞에서부터 번식 처리
 * 
 * 2. 배양 용기는 무한히 크다고 가정함.
 * 	2-1. 최개 러닝 시간에 맞게, board를 크게 작성
 * 	2-2. board[rowSize][colSize]
 * 		2-2-1. rowSize: N + 2*K
 * 		2-2-2. colSize: M + 2*K
 * 
 * 3. runningTime을 1부터 주어진 제한 시간까지 증가시키면서,
 * 	3-1. 세포 List에서 죽은 세포 또는 번식 세포를 관리합니다.
 * 		3-1-1. 초기 세포 또는 번식을 통해 세포를 추가합니다.
 * 		3-1-2. 수명을 다한 세포는 List에서 제거합니다.
 * 	3-2. 주어진 시간 동안 추가/삭제를 끝낸 다음 List의 크기를 출력합니다.
 * 
 */


public class Solution {
	static BufferedReader br;
	static StringTokenizer st;

	static int rowSize, colSize, totalRunningTime;
	static int[][] board; 
	static List<Cell> cells;
	static final int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}}; // 상하좌우
	
	static class Cell implements Comparable<Cell>{
		int row, col;
		int lifePower;
		int activeTime;
		int dyingTime;
		public Cell(int row, int col, int lifePower, int activeTime, int dyingTime) {
			this.row = row;
			this.col = col;
			this.lifePower = lifePower;
			this.activeTime = activeTime;
			this.dyingTime = dyingTime;
		}
		
		@Override
		public int compareTo(Cell o) {
			return Integer.compare(o.lifePower, this.lifePower);
		}

		// 디버깅용
		@Override
		public String toString() {
			return "Cell [row=" + row + ", col=" + col + ", lifePower=" + lifePower + ", activeTime=" + activeTime
					+ ", dyingTime=" + dyingTime + "]";
		} 
	}
	
	public static void init() throws IOException{
		st = new StringTokenizer(br.readLine());
		int inputRowSize = Integer.parseInt(st.nextToken());
		int inputColSize = Integer.parseInt(st.nextToken());
		totalRunningTime = Integer.parseInt(st.nextToken());
		rowSize = inputRowSize + 2*totalRunningTime;
		colSize = inputColSize + 2*totalRunningTime;
		cells = new ArrayList<>();
		
		board = new int[rowSize][colSize];
		for(int rowIdx=totalRunningTime; rowIdx<totalRunningTime+inputRowSize; rowIdx++) {
			st = new StringTokenizer(br.readLine());
			for(int colIdx=totalRunningTime; colIdx<totalRunningTime+inputColSize; colIdx++) {
				board[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
				int lifePower = board[rowIdx][colIdx];
				
				// 세포입니다.
				if(lifePower>0) {
					int activeTime = lifePower;
					int dyingTime = 2*lifePower;
					cells.add(new Cell(rowIdx, colIdx, lifePower, activeTime, dyingTime));
				}
			}
		}
	}
	
	// 세포 번식
	public static void propagation(Cell cell, int runningTime) {
		int row = cell.row;
		int col = cell.col;
		int lifePower = cell.lifePower;
		for(int[] dir: dirs) {
			int nxtRow = row+dir[0];
			int nxtCol = col+dir[1];
			
			if(board[nxtRow][nxtCol]==0) {
				board[nxtRow][nxtCol] = lifePower;
				cells.add(new Cell(nxtRow, nxtCol, lifePower, runningTime+lifePower, runningTime+2*lifePower));
			}
		}
		
	}
	public static void solve() {
		for(int runningTime = 1; runningTime<=totalRunningTime; runningTime++) {
			// 생명력 순으로 내림차순 정렬
			Collections.sort(cells);
			
			// 생명력 높은 순으로 번식 또는 삭제
			for(int cellIdx=0; cellIdx<cells.size(); cellIdx++) {
				Cell cell = cells.get(cellIdx);
				// 번식할 사람?
				if(cell.activeTime+1 == runningTime) {
					propagation(cell, runningTime);
				}
				
				// 가실 때가 되었습니다.
				if(cell.dyingTime == runningTime) {
					cells.remove(cellIdx);
					cellIdx--;
				}
			}
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
            sb.append('#').append(testCase).append(' ').append(cells.size()).append('\n');
        }
        System.out.println(sb);
    }
}