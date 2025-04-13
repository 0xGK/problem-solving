import java.util.*;
import java.io.*;
/*
 * 원숭이가 최소한의 동작으로 도착하고 싶다!
 * 
 * 움직임 방법
 * 	1. 상하좌우 1칸
 * 		-> 4가지
 * 	2. row, col을 2:1 or 1:2 이동
 * 		-> 2*2*2 = 8가지
 * 
 * 	3. 사실상 좌상단에서 우하단으로 가니까, 
 * 		2:1 or 1:2의 움직임은 우하단 방면만 고려해도 되지 않을까?
 * 
 * 	4. 조합으로 해보자.
 * 		말 음직임은 K번까지 이용 가능. 어떤 단계에서 쓸 지는 미지수.
 * 
 */
public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	
	static final int[][] monkeyDirs = {{-1,0},{1,0},{0,-1},{0,1}}; // 상하좌우
	static final int[][] horseDirs = {{2,1},{1,2},		// 우하단
									{-2,1},{-1,2},		// 우상단
									{1,-2},{2,-1},		// 좌하단
									{-1,-2},{-2,-1}};	// 좌상단
							
	static int rowSize, colSize, horseStepSize;
	static int minStep;
	
	static int[][] board;
	static boolean[][][] visited;
	static Queue<Monkey> queue;
	
	static class Monkey{
		int row, col, dist, horseStep;
		public Monkey(int row, int col, int dist, int horseStep) {
			this.row = row;
			this.col = col;
			this.dist = dist;
			this.horseStep = horseStep;
		}
	}
	
	public static void init() throws IOException{
		horseStepSize = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		colSize = Integer.parseInt(st.nextToken());
		rowSize = Integer.parseInt(st.nextToken());
		board = new int[rowSize][colSize];
		
		for(int rowIdx=0; rowIdx<rowSize; rowIdx++) {
			st = new StringTokenizer(br.readLine());
			for(int colIdx=0; colIdx<colSize; colIdx++) {
				board[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
			}
		}
		minStep = Integer.MAX_VALUE;
		visited = new boolean[horseStepSize+1][rowSize][colSize];
	}
	
	public static boolean isInBoard(int row, int col) {
		return row>=0 && row<rowSize && col>=0 && col<colSize;
	}
	
	public static void bfs() {
		queue = new ArrayDeque<>();
		queue.add(new Monkey(0,0,0,0));
		visited[0][0][0]=true;
		
		while(!queue.isEmpty()) {
			Monkey monkey = queue.poll();
			int row = monkey.row;
			int col = monkey.col;
			
			if(row==rowSize-1 && col==colSize-1) {
				minStep = minStep > monkey.dist ? monkey.dist : minStep;
				return;
			}
			
			if(monkey.horseStep < horseStepSize) {
				for(int[] horseDir : horseDirs) {
					int nxtRow = row + horseDir[0];
					int nxtCol = col + horseDir[1];
					if(!isInBoard(nxtRow, nxtCol) || board[nxtRow][nxtCol]==1 || visited[monkey.horseStep+1][nxtRow][nxtCol]) continue;
					visited[monkey.horseStep+1][nxtRow][nxtCol] = true;
					queue.add(new Monkey(nxtRow, nxtCol, monkey.dist+1, monkey.horseStep+1));
				}
			}
			
			for(int[] monkeyDir : monkeyDirs) {
				int nxtRow = row + monkeyDir[0];
				int nxtCol = col + monkeyDir[1];
				if(!isInBoard(nxtRow, nxtCol) || board[nxtRow][nxtCol]==1 || visited[monkey.horseStep][nxtRow][nxtCol]) continue;
				visited[monkey.horseStep][nxtRow][nxtCol] = true;
				queue.add(new Monkey(nxtRow, nxtCol, monkey.dist+1, monkey.horseStep));
			}
		}
	}
	
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
         
    	init();
    	bfs();
    	
    	System.out.println(minStep == Integer.MAX_VALUE ? -1 : minStep);
    }
}
