import java.io.FileInputStream;
import java.util.*;

class Solution
{
	final static int MAX_N = 15;
	static int N, M;
	static int[][] graph = new int[MAX_N][MAX_N];

	static int getPlus(int x, int y){
		int start_x = x - (M - 1);
		int end_x = x + (M - 1);
		int start_y = y - (M - 1);
		int end_y = y + (M - 1);
		if(start_x<0) start_x=0;
		if(end_x>=N) end_x=N-1;
		if(start_y<0) start_y=0;
		if(end_y>=N) end_y=N-1;
		int sum=-graph[x][y];
		for(int i = start_x; i<=end_x; i++){
			sum+=graph[i][y];
		}
		for(int j = start_y; j<=end_y; j++){
			sum+=graph[x][j];
		}
		return sum;
	}
	static int getX(int x, int y){
		int start_x = x - (M - 1);
		int end_x = x + (M - 1);
		int start_y = y - (M - 1);
		int end_y = y + (M - 1);	
		int sum=-graph[x][y];
		for(int i=start_x, j=start_y; i<=end_x && j<=end_y; i++, j++){
			if(i<0 || i>=N || j<0 || j>=N) continue;
			sum+=graph[i][j];
		}
		for(int i=start_x, j=end_y; i<=end_x && j>=start_y; i++, j--){
			if(i<0 || i>=N || j<0 || j>=N) continue;
			sum+=graph[i][j];
		}
		return sum;
	}
	
	public static void main(String args[]) throws Exception
	{
        //System.setIn(new FileInputStream("res/input.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		// sc.nextLine();

		for(int t=1; t<=T; ++t){
			System.out.print("#" + t + " ");
			N = sc.nextInt();
			M = sc.nextInt();
			for(int i=0; i<N; i++){
				for(int j=0; j<N; j++){
					graph[i][j] = sc.nextInt();
				}

			}
			int ret=0;
			for(int i=0; i<N; i++){
				for(int j=0; j<N; j++){
					int tmp = Math.max(getPlus(i,j), getX(i,j));
					ret = Math.max(ret, tmp);
				}
			}
			System.out.println(ret);

		}
        sc.close();
	}
}