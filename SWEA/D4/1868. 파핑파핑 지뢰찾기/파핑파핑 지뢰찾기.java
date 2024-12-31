import java.io.FileInputStream;
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
	static char[][] solutionGraph = new char[MAX_N][MAX_N];
	static int[] dx = {-1,0,1};
	static int[] dy = {-1,0,1};
	
	public static void viewGraph(char[][] map) {
		System.out.println();
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	public static void viewBoolean(Boolean[][] map) {
		System.out.println();
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	public static void fillGraph(char[][] map) {
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j]=='.') {
					map[i][j] = (char) (getNumber(map, i, j) + '0');
				}
			}
		}
	}
	public static int getNumber(char[][] map, int x, int y) {
		int cnt = 0;
		for(int i = x-1; i<= x+1; i++) {
			for(int j = y-1; j <= y+1; j++) {
				if(!inMap(i, j)) continue;
				if(i==x && j==y) continue;
				if(map[i][j]=='*') cnt++;
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
	public static int bfs(char[][] graph, Boolean[][] isVisited, int startX, int startY) {
		Queue<Point> queue = new LinkedList<>();
		queue.add(new Point(startX, startY));
		isVisited[startX][startY]=true;
//		cnt++;
		while(!queue.isEmpty()) {
			Point point = queue.poll();
			int x = point.x;
			int y = point.y;
			
			for(int i=0; i<3; i++){
				for(int j=0; j<3; j++) {
					
					int nx = x + dx[i];
					int ny = y + dy[j];
					if(inMap(nx, ny) && !isVisited[nx][ny]) {
						if(solutionGraph[nx][ny]=='0') {
							isVisited[nx][ny] = true;
							queue.add(new Point(nx, ny));						
						}
						else if('1'<=solutionGraph[nx][ny] && solutionGraph[nx][ny]<='8') {
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
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		/*
		 * bfs or dfs로 0인 부분을 true로 만들기 위해 몇 번 필요한지
		 * 0과 인접한 부분의 숫자들도 모두 true
		 * 나머지 숫자들 true로 카운트
		 */

		for(int t=1; t<=T; ++t){
			System.out.print("#" + t + " ");
			N = sc.nextInt();
			sc.nextLine();
			
			for(int i=0; i<N; i++) {
				String input = sc.nextLine();
				char[] inputChar = input.toCharArray();
				for(int j=0; j<N; j++) {
					graph[i][j] = inputChar[j];
					solutionGraph[i][j] = inputChar[j];
				}
			}
			fillGraph(solutionGraph);
//			viewGraph(solutionGraph);
			Boolean[][] isVisited = new Boolean[N][N];
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(solutionGraph[i][j]=='*') {
						isVisited[i][j]=true;	// 지뢰는 조사하지 않을 것
					}else {
						isVisited[i][j]=false;					
					}
				}
			}
			
			int ret = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(!isVisited[i][j] && solutionGraph[i][j]=='0') {
						int tmp = bfs(graph, isVisited, i, j); 
//						System.out.println("("+i+", "+j+"): "+tmp);
						ret += tmp;
					}
				}
			}
//			viewBoolean(isVisited);
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(isVisited[i][j]==false) {
						ret++;
					}
				}
			}
			
			System.out.println(ret);
		}
        sc.close();
	}
}