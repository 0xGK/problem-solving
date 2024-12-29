import java.io.FileInputStream;
import java.util.*;

class Solution
{
	final static int MAX_N = 7;
	static int N;
	static int[][] graph = new int[MAX_N][MAX_N];
	static int[][] rotated90Graph = new int[MAX_N][MAX_N];
	static int[][] rotated180Graph = new int[MAX_N][MAX_N];
	static int[][] rotated270Graph = new int[MAX_N][MAX_N];

	static void viewMap(int[][] viewGraph){
		for(int i=0; i<N; i++){
			for(int j=0; j<N; j++){
				System.out.print(viewGraph[i][j]+" ");
			}
			System.out.println();
		}
	}

	static void rotation90(){
		int nx, ny;
		for(int x=0; x<N; x++){
			for(int y=0; y<N; y++){
				nx = y;
				ny = N-1-x;
				rotated90Graph[nx][ny] = graph[x][y];
			}
		}
	}
	static void rotation180(){
		int nx, ny;
		for(int x=0; x<N; x++){
			for(int y=0; y<N; y++){
				nx = N-1-x;
				ny = N-1-y;
				rotated180Graph[nx][ny] = graph[x][y];
			}
		}
	}
	static void rotation270(){
		int nx, ny;
		for(int x=0; x<N; x++){
			for(int y=0; y<N; y++){
				nx = N-1-y;
				ny = x;
				rotated270Graph[nx][ny] = graph[x][y];
			}
		}
	}

	public static void main(String args[]) throws Exception
	{
        //System.setIn(new FileInputStream("res/input.txt"));
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		// sc.nextLine();

		for(int t=1; t<=T; ++t){
			System.out.println("#"+t);
			N = sc.nextInt();
			for(int i=0; i<N; i++){
				for(int j=0; j<N; j++){
					graph[i][j] = sc.nextInt();
				}

			}
			rotation90();
			rotation180();
			rotation270();
			// viewMap(graph);
			// viewMap(rotated90Graph);
			// viewMap(rotated180Graph);
			// viewMap(rotated270Graph);

			for(int x=0; x<N; x++){
				for(int y=0; y<N; y++){
					System.out.print(rotated90Graph[x][y]);
				}
				System.out.print(" ");
				for(int y=0; y<N; y++){
					System.out.print(rotated180Graph[x][y]);
				}
				System.out.print(" ");
				for(int y=0; y<N; y++){
					System.out.print(rotated270Graph[x][y]);
				}
				System.out.println();
			}

		}
		
		
        sc.close();
	}

	
}