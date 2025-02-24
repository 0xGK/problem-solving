import java.util.*;
import java.io.*;

public class Main {
	
	static int N, minTasteGap;
	static int[] sour, bitter;
	static boolean[] visited;
	
	public static void backtrack(int elementIndex) {
		if(elementIndex==N) {
			int totalSour=1;
			int totalBitter=0;
			for(int index=0; index<N; index++) {
				if(visited[index]) {
					totalSour*=sour[index];
					totalBitter+=bitter[index];
				}
			}
			int tasteGap = Math.abs(totalSour - totalBitter);
			if(totalBitter>0) {
				minTasteGap = Math.min(tasteGap, minTasteGap); 
			}
			
			return;
		}
		
		visited[elementIndex] = true;
		backtrack(elementIndex+1);
		visited[elementIndex] = false;
		backtrack(elementIndex+1);
	}
	
	public static void init() {
		minTasteGap=Integer.MAX_VALUE;
		sour = new int[N];
		bitter = new int[N];
		visited = new boolean[N];
	}
	
	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		init();
		for(int index=0; index<N; index++) {
			st = new StringTokenizer(br.readLine());
			sour[index] = Integer.parseInt(st.nextToken());
			bitter[index] = Integer.parseInt(st.nextToken());
		}
		backtrack(0);
		System.out.println(minTasteGap);
		
	}
}
