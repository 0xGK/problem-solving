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
	static int[] dx = {-1,0,1};
	static int[] dy = {-1,0,1};
	
	public static void fillGraph() {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(graph[i][j]=='.') {
					graph[i][j] = (char) (getNumber(i, j) + '0');
				}
			}
		}
	}
	public static int getNumber(int x, int y) {
		int cnt = 0;
		for(int i = x-1; i<= x+1; i++) {
			for(int j = y-1; j <= y+1; j++) {
				if(!inMap(i, j)) continue;
				if(i==x && j==y) continue;
				if(graph[i][j]=='*') cnt++;
			}
		}
		return cnt;
	}
	public static Boolean inMap(int x, int y) {
		if(0 <= x && x < N && 0 <= y && y < N) {
			return true;
		}
		return false;
	}
	public static int bfs(Boolean[][] isVisited, int startX, int startY) {
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(startX, startY));
		isVisited[startX][startY]=true;
		while(!queue.isEmpty()) {
			Point point = queue.poll();
			int x = point.x;
			int y = point.y;
			
			for(int i=0; i<3; i++){
				for(int j=0; j<3; j++) {
					int nx = x + dx[i];
					int ny = y + dy[j];
					if(inMap(nx, ny) && !isVisited[nx][ny]) {
						if(graph[nx][ny]=='0') {
							isVisited[nx][ny] = true;
							queue.add(new Point(nx, ny));						
						}
						else if('1'<=graph[nx][ny] && graph[nx][ny]<='8') {
							isVisited[nx][ny] = true;
						}
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
		/*
		 * bfs or dfs로 0인 부분을 true로 만들기 위해 몇 번 필요한지
		 * 0과 인접한 부분의 숫자들도 모두 true
		 * 나머지 숫자들 true로 카운트
		 */

		for(int t=1; t<=T; ++t){
			System.out.print("#" + t + " ");
			N = Integer.parseInt(br.readLine());
			
			for(int i=0; i<N; i++) {
				String input = br.readLine();
				char[] inputChar = input.toCharArray();
				for(int j=0; j<N; j++) {
					graph[i][j] = inputChar[j];
				}
			}
			fillGraph();
			Boolean[][] isVisited = new Boolean[N][N];
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(graph[i][j]=='*') {
						isVisited[i][j]=true;	// 지뢰는 조사하지 않을 것
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
			
			System.out.println(ret);
		}
        br.close();
	}
}