import java.util.*;
import java.io.FileInputStream;

class Solution
{
	final static int MAX_N=15;
	static final int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
	static int N;
	static int[][] graph = new int[MAX_N][MAX_N];
	static boolean visited[][] = new boolean[MAX_N][MAX_N];
	static Point startPoint, endPoint;
	public static int bfs() {
		int ret = Integer.MAX_VALUE;
		Queue<Point> queue = new LinkedList<>();
		Point currentPoint = new Point(startPoint.x, startPoint.y, 0) ;
				
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				visited[i][j]=false;
			}
		}
		
		queue.add(currentPoint);
		visited[currentPoint.x][currentPoint.y] = true;
		
		while(!queue.isEmpty()) {
			currentPoint = queue.poll();
			int cx = currentPoint.x;
			int cy = currentPoint.y;
			int ctime = currentPoint.time;
			
			if(cx == endPoint.x && cy == endPoint.y) {
				ret = Math.min(ret, ctime);
			}
			for(int[] dir : directions) {
				int nx = cx+dir[0];
				int ny = cy+dir[1];
				if(inMap(nx,ny) && !visited[nx][ny] && graph[nx][ny]!=1) {
					if(graph[nx][ny]!=2 || (ctime%3 == 2)) {
						queue.add(new Point(nx, ny, ctime+1));
						visited[nx][ny]=true;
					}else {
						queue.add(new Point(cx, cy, ctime+1));
					}
				}
			}			
		}
		
		if(ret == Integer.MAX_VALUE) {
			return -1;
		}else {
			return ret;
		}
		
		
	}
	public static boolean inMap(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < N;
	}
	
	public static void main(String args[]) throws Exception
	{
//		System.setIn(new FileInputStream("res/input.txt"));
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		for(int test_case = 1; test_case <= T; test_case++)
		{
			N = sc.nextInt();
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					graph[i][j]=sc.nextInt();
				}
			}
			int sx, sy, ex, ey;
			sx = sc.nextInt();
			sy = sc.nextInt();
			ex = sc.nextInt();
			ey = sc.nextInt();
			startPoint = new Point(sx, sy, 0);
			endPoint = new Point(ex, ey, 0);
			
			int ret = bfs();
			System.out.println("#"+ test_case + " " + ret);
			
		}
		
	}
}

class Point{
	int x, y;
	int time;
	public Point(int x, int y, int time) {
		this.x=x;
		this.y=y;
		this.time=time;
	}
}