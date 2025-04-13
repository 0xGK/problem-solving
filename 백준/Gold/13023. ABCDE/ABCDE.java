import java.util.*;
import java.io.*;

/*
 * 5개의 연속되;ㄴ
 * 
 * 
 * 
 * 
 */

public class Main {
	static int N, M;
	static List<Integer>[] graph;
	static boolean[] visited;
	static BufferedReader br;
	static StringTokenizer st;
	public static void init() throws IOException{
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList[N];
		
		for(int idx=0; idx<N; idx++) {
			graph[idx] = new ArrayList<>();
		}
		for(int inputIdx=0; inputIdx<M; inputIdx++) {
			st = new StringTokenizer(br.readLine());
			int src = Integer.parseInt(st.nextToken());
			int dest = Integer.parseInt(st.nextToken());
			graph[src].add(dest);
			graph[dest].add(src);
		}
	}
	
	static int isPossible;
	public static void searchBacktrack(int cur, int cnt) {
		if(isPossible==1) return;
		if(cnt==5) {
			isPossible=1;
			return;
		}
		for(int nxt : graph[cur]) {
			if(visited[nxt]) continue;
			visited[nxt] = true;
			searchBacktrack(nxt, cnt+1);
			if(isPossible==1) return;
			visited[nxt] = false;
		}
		
	}
	public static void solve() {
		for(int idx=0; idx<N; idx++) {
			if(graph[idx].isEmpty()) continue;
			
			// 해당 노드를 시작으로 연속으로 5개를 찾아봐야합니다.
			visited = new boolean[N];
			visited[idx] = true;
			isPossible=0;
			searchBacktrack(idx, 1);
			if(isPossible==1) break;
		}
	}
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
    	init();
    	solve();
    	System.out.println(isPossible);
    	
    }
}
