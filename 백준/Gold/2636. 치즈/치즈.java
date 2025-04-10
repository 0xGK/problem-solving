import java.io.*;
import java.util.*;

/*
 * 
 * 
 * 공기와 접촉된 칸은 한 시간 지나면 녹아 없어짐
 * 치즈 구멍 속에는 공기가 없지만, 구멍을 둘러싼 치즈가 녹아서 구멍이 열리면, 구멍 속으로 공기가 들어감?
 * 
 * 바깥쪽 흰색(치즈가 없는 부분)에 대해서 bfs 순회
 * 		-> 닿아있는 치즈 영역은, 다음 시간에 녹아버릴 것임.
 */
public class Main {
	static int curCheeseCnt;
	static int nextCheeseCnt;
	static int totalTime;
	static int R, C;
	static int[][] cheeseBoard;
	static boolean[][] visited;
	static int[][] dirs = {{-1,0},{1,0},{0,-1},{0,1}};
	static BufferedReader br;
	static StringTokenizer st;
	
	static class Point{
		int row, col;
		public Point(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
	
	public static void init() throws IOException{
		st = new StringTokenizer(br.readLine().trim()); 
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		cheeseBoard = new int[R][C]; // 테두리는 0으로 채우기.
		curCheeseCnt=nextCheeseCnt=0;
		for(int rowIdx=0; rowIdx<R; rowIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			for(int colIdx=0; colIdx<C; colIdx++) {
				cheeseBoard[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
			}
		}
		
		 
	}
	
	public static boolean isInBoard(int row, int col) {
		return row>=0 && row<R && col>=0 && col<C;
	}
	public static boolean bfs(int time) {
		boolean isFinished = true;
		int tempCheeseCnt=0;
		visited = new boolean[R][C];
		Queue<Point> queue = new ArrayDeque<>();
		queue.add(new Point(0, 0));
		visited[0][0]=true;
		
		while(!queue.isEmpty()) {
			Point curPoint = queue.poll();
			
			for(int[] dir : dirs) {
				int nxtRow = curPoint.row + dir[0];
				int nxtCol = curPoint.col + dir[1];
				if(!isInBoard(nxtRow,nxtCol) || visited[nxtRow][nxtCol]) continue;
				// 치즈가 아니면서 이번 time은 아닌 것들
				if(cheeseBoard[nxtRow][nxtCol] != 1 && cheeseBoard[nxtRow][nxtCol] != time) {
					queue.add(new Point(nxtRow, nxtCol));
					visited[nxtRow][nxtCol] = true;					
				}else{
					cheeseBoard[nxtRow][nxtCol] = time;
					visited[nxtRow][nxtCol] = true;
					isFinished = false;
					tempCheeseCnt++;
				}
			
			}
		}
		curCheeseCnt = nextCheeseCnt;
		nextCheeseCnt = tempCheeseCnt;
		return isFinished;
	}
	
	public static int playCheeseGame() {
		for(int time=2;;time++) {
//	    	
//	    	for(int rowIdx=0; rowIdx<R; rowIdx++) {
//	    		for(int colIdx=0; colIdx<C; colIdx++) {
//	    			System.out.print(cheeseBoard[rowIdx][colIdx] + " ");
//	    		}
//	    		System.out.println();
//	    	}
//	    	System.out.println();
			if(bfs(time)) {
				return time-2;
			}
		}

	}
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
         
    	init();
    	int time = playCheeseGame();
//    	if(time<0) {
//    		time=0;
//    		curCheeseCnt=0;
//    	}
    	sb.append(time).append('\n').append(curCheeseCnt);
    	
    	


    	
    	System.out.println(sb);
    }
}
