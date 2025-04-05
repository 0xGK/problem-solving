import java.io.*;
import java.util.*;
/*
 * 2명의 일꾼이, 가로 길이 M 만큼 꿀을 채취
 * 
 * 하나의 벌통에서 꿀을 채취할 때, 한번에 도추 채취
 * Q. 채취한 꿀이 섞이게 되면 상품 가치가 덜어지므로, 채취한 꿀은 하나의 용기에 담아야 한다>???
 * 두 일꾼이 채취할 수 있는 꿀의 최대 양은 C이다.
 * 
 * 두 일꾼들이 채취한 꿀의 종류가 서로 다를 것임. 단순 합산을 하면 안 되고 List에 담아 둬야하고,
 * 수익을 계산할 때, 꿀의 양의 제곱 합을 구해야 함.
 * 
 * N <= 10
 * M <= 5
 * 10 <= C <= 30
 * 1<= 꿀의 양 <= 9
 * 
 * 
 */
public class Solution {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int N, M, C;
	static int[][] board;
	static int totalHoney;
	static int[] maxHoney;
	static int bRowIdx, bColIdx; 
	
	public static void init() throws IOException{
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		board = new int[N][N];
		for(int rowIdx=0; rowIdx<N; rowIdx++) {
			st = new StringTokenizer(br.readLine());
			for(int colIdx=0; colIdx<N; colIdx++) {
				board[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
			}
		}
		totalHoney=0;
		maxHoney = new int[N];
	}
	
	public static void backtrack(int selectedCnt, int honey, int honeySquare) {
		// terminate
		if(selectedCnt == M) {
			maxHoney[bRowIdx] = maxHoney[bRowIdx] < honeySquare ? honeySquare : maxHoney[bRowIdx];
			return;
		}
		
		// prune
		if(bColIdx+selectedCnt >= N) return;
		
		// generate new branch
		int curHoney = board[bRowIdx][bColIdx+selectedCnt];
		if(honey+curHoney<=C) {
			backtrack(selectedCnt+1, honey+curHoney, honeySquare+curHoney*curHoney);			
		}			
		backtrack(selectedCnt+1, honey, honeySquare);
	}
	
	
	public static void solve() {
		for(bRowIdx=0; bRowIdx<N; bRowIdx++) {
			for(bColIdx=0; bColIdx<N; bColIdx++) {
				backtrack(0, 0, 0);
			}
		}
		Arrays.sort(maxHoney);
		totalHoney = maxHoney[N-1] + maxHoney[N-2];
	}
	
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int testCase=1; testCase<=T; ++testCase) {
        	init();
        	solve();        	
            sb.append('#').append(testCase).append(' ').append(totalHoney).append('\n');
             
        }
        System.out.println(sb);
         
    }
}