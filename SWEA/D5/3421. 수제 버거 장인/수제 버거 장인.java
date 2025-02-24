import java.io.*;
import java.util.*;

/*
 * 1. backtrack으로 접근 
 * 		- 1~N까지 숫자들을 visited/unvisited로 변경하면서 bracktrack
 * 		- 금지된 조합에 대해서는 분기 끊기
 * 		- 매 조회마다 totalCount += 1
 * 		- terminate: elementIndex>N
 * 
 */

public class Solution {
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	static int totalCount, N, M;
	static boolean[] visited;
	static List<Integer>[] forbidden = new ArrayList[21];
	
	public static boolean checkForbidden(int elementIndex) {
		for(int next : forbidden[elementIndex]) {
			if(visited[next]==true) {
				return false;
			}
		}
		return true;
	}
	
	public static void buggurBacktrack(int elementIndex) {
		if(elementIndex>N) {
			totalCount++;
			return;
		}
		
		if(checkForbidden(elementIndex)) {
			visited[elementIndex] = true;
			buggurBacktrack(elementIndex+1);
		}
		
		visited[elementIndex] = false;
		buggurBacktrack(elementIndex+1);
	}
	
	public static void init() {
		totalCount=0;
		visited = new boolean[21];
		
		for(int i=0; i<21; i++) {
			forbidden[i] = new ArrayList<>();
		}
	}
	
	
	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int testCase=1; testCase<=T; testCase++) {
			init();
			sb.append("#").append(testCase).append(" ");
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			for(int mIndex=0; mIndex<M; mIndex++) {
				st = new StringTokenizer(br.readLine());
				int a, b;
				a = Integer.parseInt(st.nextToken());
				b = Integer.parseInt(st.nextToken());
				forbidden[a].add(b);
				forbidden[b].add(a);
			}
			
			buggurBacktrack(1);
			sb.append(totalCount).append("\n");
			
		}
		System.out.println(sb);
	}
	
}
