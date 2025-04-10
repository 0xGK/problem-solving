import java.util.*;
import java.io.*;
/*
 * 1. (0,0)에서 시작해서 움직이되, 한번 방문한 알파벳을 도 방문할 수는 없다.
 * 2. 최대 몇 칸을 지나갈 수 있는 지 구하시오.
 * 		-> dfs: 각 breadth까지 오기 위해 마크했던 알파벳을 기억하라. 
 * 
 */
public class Main {
	static final int ALPHABET_COUNT = 26;
	static int R, C, maxDist;
	static char board[][];
	static boolean[] alphabetVisited;
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
		maxDist=0;
		st = new StringTokenizer(br.readLine().trim());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		board = new char[R][C];
		visited = new boolean[R][C];
		alphabetVisited = new boolean[ALPHABET_COUNT];
		for(int rowIdx=0; rowIdx<R; rowIdx++) {
			String inputLine = br.readLine().trim();
			for(int colIdx=0; colIdx<C; colIdx++) {
				board[rowIdx][colIdx] = inputLine.charAt(colIdx);
			}
		}
	}
	
	public static boolean isInBoard(int row, int col) {
		return row>=0 && row<R && col>=0 && col<C;
	}
	
	public static void backtrack(int row, int col, int dist) {
		maxDist = maxDist < dist ? dist : maxDist;
		
		for(int[] dir : dirs) {
			int nxtRow = row + dir[0];
			int nxtCol = col + dir[1];
			
			if(!isInBoard(nxtRow, nxtCol)) continue;
			
			char nxtAlphabet = board[nxtRow][nxtCol];
			if(visited[nxtRow][nxtCol] || alphabetVisited[nxtAlphabet-'A']) continue;
			
			visited[nxtRow][nxtCol] = true;
			alphabetVisited[nxtAlphabet-'A'] = true;
			backtrack(nxtRow, nxtCol, dist+1);
			visited[nxtRow][nxtCol] = false;
			alphabetVisited[nxtAlphabet-'A'] = false;
		}
		
		
	}
	public static void playAlphabetGame() {
		visited[0][0]=true;
		char alphabet = board[0][0];
		alphabetVisited[alphabet-'A']=true;
		backtrack(0, 0, 1);
	}
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
    	init();
    	playAlphabetGame();
    	sb.append(maxDist);
    	
    	System.out.println(sb);
    }
}
