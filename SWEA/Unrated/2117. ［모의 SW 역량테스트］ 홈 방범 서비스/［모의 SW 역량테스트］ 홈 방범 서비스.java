import java.io.*;
import java.util.*;
/*
 * 운영비용 := K^2 + (K-1)^2
 * 
 * 집들은 각각 M의 비용 지불할 것임.
 * 보안 회사 입장에서, 손해보지 않는 한 최대한 많은 집에 제공하려고 함.
 * 
 * 각 K에 대해서 가능한 많은 집을 집어넣어야 합니다.
 * 	이때, 비용의 최대값을 저장.
 * 
 * 모든 K에 대해서 진행하고 난 뒤에, Profit이 최대가 되는 값을 출력하자.
 * 
 * 
 * 
 */
public class Solution {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int boardSize, cost;
	static int[][] board;
	static int resultHouseCnt;
	
	
	public static void init() throws IOException{
		st = new StringTokenizer(br.readLine());
		boardSize = Integer.parseInt(st.nextToken());
		cost = Integer.parseInt(st.nextToken());
		board = new int[boardSize][boardSize];
		for(int rowIdx=0; rowIdx<boardSize; rowIdx++) {
			st = new StringTokenizer(br.readLine());
			for(int colIdx=0; colIdx<boardSize; colIdx++) {
				board[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
			}
		}
		resultHouseCnt=0;
	}
	
	public static boolean isInBoard(int row, int col) {
		return row>=0 && row<boardSize && col>=0 && col<boardSize; 
	}
	
	public static int getHouseCnt(int row, int col, int K) {
		int houseCnt = 0;
		int rangeTerm = K-1;
		int blankCnt = -(K-1);
		for(int rowIdx = row-rangeTerm; rowIdx <= row+rangeTerm; rowIdx++) {
			for(int colIdx = col-rangeTerm+Math.abs(blankCnt); colIdx <= col+rangeTerm-Math.abs(blankCnt); colIdx++) {
				if(!isInBoard(rowIdx, colIdx)) continue;
				if(board[rowIdx][colIdx]==1) houseCnt++;
			}
			blankCnt++;
		}
		return houseCnt;
	}

	public static void solve() {
		for(int k=1; k<=boardSize+1; k++) {
			int maxHouseCnt = 0;
			for(int rowIdx=0; rowIdx<boardSize; rowIdx++) {
				for(int colIdx=0; colIdx<boardSize; colIdx++) {
					int houseCnt = getHouseCnt(rowIdx, colIdx, k);
					maxHouseCnt = maxHouseCnt < houseCnt ? houseCnt : maxHouseCnt; 
				}
			}
			if(maxHouseCnt*cost >= k*k + (k-1)*(k-1)) {
				resultHouseCnt = resultHouseCnt < maxHouseCnt ? maxHouseCnt : resultHouseCnt;
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
            sb.append('#').append(testCase).append(' ').append(resultHouseCnt).append('\n');
        }
        System.out.println(sb);
    }
}