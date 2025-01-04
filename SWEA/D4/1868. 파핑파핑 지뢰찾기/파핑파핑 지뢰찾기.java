import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

class Point{
	int x, y;
	public Point(int x, int y) {
		this.x=x;
		this.y=y;
	}
}

class Solution
{
	final static int MAX_N = 300;
	static int N;
	static char[][] graph = new char[MAX_N][MAX_N];
	static int[][] directions = {{-1,-1}, {-1,0}, {-1,1}, {0,-1}, {0,1}, {1,-1}, {1,0}, {1,1}};
	
	public static void fillGraph() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(graph[i][j]=='.') {
					graph[i][j] = getNumber(i, j);
				}
			}
		}
	}
	public static char getNumber(int x, int y) {
		int cnt = 0;
		for(int[] dir : directions) {
			int nx = x + dir[0];
			int ny = y + dir[1];
			if(inMap(nx, ny) && graph[nx][ny]=='*') cnt++;
		}
		return (char) (cnt + '0');
	}
	public static boolean inMap(int x, int y) {
		return 0 <= x && x < N && 0 <= y && y < N;
	}
	public static int bfs(boolean[][] isVisited, int startX, int startY) {
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(startX, startY));
		isVisited[startX][startY]=true;
		while(!queue.isEmpty()) {
			Point point = queue.poll();
			int x = point.x;
			int y = point.y;
			
			for(int[] dir: directions) {
				int nx = x + dir[0];
				int ny = y + dir[1];
				if(inMap(nx, ny) && !isVisited[nx][ny]) {
					char v = graph[nx][ny];
					if(v=='0') {
						queue.add(new Point(nx, ny));						
						isVisited[nx][ny] = true;
					}
					else if('1'<=v && v<='8') {
						isVisited[nx][ny] = true;
					}
				}
			}

		}		
		return 1;
	}
	public static void main(String args[]) throws Exception
	{
        //System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for(int t=1; t<=T; ++t){
			N = Integer.parseInt(br.readLine());
			
			for(int i=0; i<N; i++) {
				String input = br.readLine();
				char[] inputChar = input.toCharArray();
				for(int j=0; j<N; j++) {
					graph[i][j] = inputChar[j];
				}
			}
			fillGraph();
			boolean[][] isVisited = new boolean[N][N];
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(graph[i][j]=='*') {
						isVisited[i][j]=true;
					}else {
						isVisited[i][j]=false;					
					}
				}
			}
			
			int ret = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(!isVisited[i][j] && graph[i][j]=='0') {  
						ret += bfs(isVisited, i, j);
					}
				}
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(isVisited[i][j]==false) {
						ret++;
					}
				}
			}
			
			System.out.println("#" + t + " " + ret);
		}
        br.close();
	}
}