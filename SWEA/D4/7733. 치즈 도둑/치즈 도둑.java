import java.io.*;
import java.util.*;
/*
 * 각 칸에 치즈의 맛이 1~100사이의 숫자로 주어짐
 * 
 * 
 * 
 */
public class Solution {
	static int N, maxCheeseCnt, maxDay;
	static int[][] cheeseBoard;
	static boolean[][] visited;
	static int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
	static BufferedReader br;
	static StringTokenizer st;
	
	public static class Point{
		int row, col;
		public Point(int row, int col) {
			this.row=row;
			this.col=col;
		}
	}
	
	
	public static void init() throws IOException{
		maxCheeseCnt=1;
		maxDay=0;
		N = Integer.parseInt(br.readLine().trim());
		cheeseBoard = new int[N][N];
		for(int rowIdx=0; rowIdx<N; rowIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int colIdx=0; colIdx<N; colIdx++) {
				int tempTaste = Integer.parseInt(st.nextToken());
				cheeseBoard[rowIdx][colIdx] = tempTaste; 
				maxDay = maxDay < tempTaste ? tempTaste : maxDay;  
			}
		}
	}
	
	public static boolean isInBoard(int row, int col) {
		return row>=0 && row<N && col>=0 && col<N;
	}
	
	public static void bfsCheese(int rowIdx, int colIdx, int day) {
		Queue<Point> queue = new ArrayDeque<>();
		queue.offer(new Point(rowIdx, colIdx));
		visited[rowIdx][colIdx] = true;
		
		while(!queue.isEmpty()) {
			Point curPoint = queue.poll();
			for(int[] dir : dirs) {
				int nxtRow = curPoint.row + dir[0];
				int nxtCol = curPoint.col + dir[1];
				
				if(!isInBoard(nxtRow, nxtCol) || cheeseBoard[nxtRow][nxtCol]<=day || visited[nxtRow][nxtCol]) continue;

				queue.offer(new Point(nxtRow, nxtCol));
				visited[nxtRow][nxtCol] = true;
			}
			
			
		}
	}
	
	public static void playCheeseGame() {
		for(int day=1; day<=maxDay; day++) {
			int cheeseCnt=0;
			visited = new boolean[N][N];
			for(int rowIdx=0; rowIdx<N; rowIdx++) {
				for(int colIdx=0; colIdx<N; colIdx++) {
					if(cheeseBoard[rowIdx][colIdx]<=day || visited[rowIdx][colIdx]) continue;
					bfsCheese(rowIdx, colIdx, day);
					cheeseCnt++;
				}
			}
			maxCheeseCnt = maxCheeseCnt < cheeseCnt ? cheeseCnt : maxCheeseCnt;
		}
	}
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
         
        int T = Integer.parseInt(br.readLine());
        for(int testCase=1; testCase<=T; ++testCase) {
        	init();
        	playCheeseGame();
            sb.append('#').append(testCase).append(' ').append(maxCheeseCnt).append('\n');
        }
        System.out.println(sb);
         
    }
}
