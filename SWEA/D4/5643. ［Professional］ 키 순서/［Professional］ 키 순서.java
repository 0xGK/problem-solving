import java.io.*;
import java.util.*;
/*
 * DAG 그래프
 * 
 * 각 학생에 대해서 수행
 * 선택된 학생 기준으로
 * 1. out 방향으로 visited 처리
 * 2. 남은 node들을 기준으로, 선택된 학생으로 들어올 수 있는지 확인.
 * 
 */
public class Solution {
	static BufferedReader br;
	static StringTokenizer st;
	static int N, M;
	static List<List<Integer>> graph;
	static int[] inDegree;
	static Queue<Integer> queue;
	static List<Integer> result;
	static boolean[] visited;
	public static void init() throws IOException{
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
//		inDegree = new int[N+1];
		graph = new ArrayList<>();
		for(int i=0; i<=N; i++) {
			graph.add(new ArrayList<>());
		}
		for(int idx=0; idx<M; idx++) {
			st = new StringTokenizer(br.readLine());
			int src = Integer.parseInt(st.nextToken());
			int dest = Integer.parseInt(st.nextToken());
			graph.get(src).add(dest);
//			inDegree[dest]++;
		}
		result = new ArrayList<>();
	}
	
	public static boolean canBeReach(int src, int dest) {
		queue = new ArrayDeque<>();
		queue.add(src);
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			
			for(int nxt : graph.get(cur)) {
				if(nxt == dest) return true;
				if(visited[nxt]) continue;
				queue.add(nxt);
			}
		}
		return false;
	}
	
	public static boolean figure(int student) {
		queue = new ArrayDeque<>();
		visited = new boolean[N+1];
		queue.add(student);
		visited[student] = true;
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			for(int nxt : graph.get(cur)) {
				if(visited[nxt]) continue;
				visited[nxt] = true;
				queue.add(nxt);
			}
		}
		
		for(int idx=1; idx<=N; idx++) {
			if(!visited[idx]) {
				// 이놈들이 이제, student로 들어갈 수 있는지 확인해야합니다.
				// 그런데 이게, visited 처리를 해도 될까요? -> 안됩니다.
				if(!canBeReach(idx, student)) {
					return false;
				}
			}
		}
		return true;
		
	}
	
	public static int solve() {
		int cnt = 0;
		for(int cur=1; cur<=N; cur++) {
			if(figure(cur)) cnt++;
			
			
		}
		return cnt;
	}
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
         
        int T = Integer.parseInt(br.readLine());
        for(int testCase=1; testCase<=T; ++testCase) {
        	init();
            sb.append('#').append(testCase).append(' ').append(solve()).append('\n');
             
        }
        System.out.println(sb);
         
    }
}