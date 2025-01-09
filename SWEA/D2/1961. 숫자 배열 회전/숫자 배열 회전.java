import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

class Solution
{
	final static int MAX_N = 7;
	static int N;
	static int[][] rotated90Graph = new int[MAX_N][MAX_N];
	static int[][] rotated180Graph = new int[MAX_N][MAX_N];
	static int[][] rotated270Graph = new int[MAX_N][MAX_N];

	public static void main(String args[]) throws Exception
	{
        //System.setIn(new FileInputStream("src/res/input.txt"));
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for(int t=1; t<=T; ++t){
			N = Integer.parseInt(br.readLine());
			for(int x=0; x<N; x++){
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int y=0; y<N; y++){
					int a = Integer.parseInt(st.nextToken());
					rotated90Graph[y][N-1-x] = a;
					rotated180Graph[N-1-x][N-1-y] = a;
					rotated270Graph[N-1-y][x] = a;
				}

			}
			StringBuilder sb = new StringBuilder();
			sb.append("#").append(t).append("\n");
			for(int x=0; x<N; x++){
				for(int y=0; y<N; y++){
					sb.append(rotated90Graph[x][y]);
				}
				sb.append(" ");
				for(int y=0; y<N; y++){
					sb.append(rotated180Graph[x][y]);
				}
				sb.append(" ");
				for(int y=0; y<N; y++){
					sb.append(rotated270Graph[x][y]);
				}
				sb.append("\n");
			}
			System.out.print(sb);
		}
		
        br.close();
	}

	
}
