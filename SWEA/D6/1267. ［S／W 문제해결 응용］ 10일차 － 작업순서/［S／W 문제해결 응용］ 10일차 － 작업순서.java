import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;



/*
 * 
 * 위상 정렬 개념
 * inDegree를 고려하라.
 * 
 * 모든 노드들에 대해서 inDegree각각 추가
 * 그들의 
 * start 노드 queue에 추가
 * start 노드의 inDegree queue에 추가
 * 
 * 
 */
public class Solution {
	static int V, E;
	static int[] inDegree;
	static List<Integer>[] graph;
	
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	
	public static void init() throws IOException{
		st = new StringTokenizer(br.readLine().trim());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		inDegree = new int[V+1];
		graph = new ArrayList[V+1];
		
		for(int v=1; v<=V; v++) {
			graph[v] = new ArrayList<>();
		}
		
		st = new StringTokenizer(br.readLine().trim());
		for(int edgeIdx=0; edgeIdx<E; edgeIdx++) {
			int src = Integer.parseInt(st.nextToken());
			int dest = Integer.parseInt(st.nextToken());
			graph[src].add(dest);
			inDegree[dest]++;
		}
	}
	
	public static void solve() {
		Queue<Integer> queue = new ArrayDeque<>();
		for(int v=1; v<=V; v++) {
			if(inDegree[v]==0) {
				queue.add(v);
			}
		}
		
		while(!queue.isEmpty()) {
			int cur = queue.poll();
			sb.append(cur).append(" ");
			
			for(int next : graph[cur]) {
				inDegree[next]--;
				if(inDegree[next]==0) queue.add(next);
				
			}
		}
	}
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
         
        int T = 10;
        for(int testCase=1; testCase<=T; ++testCase) {
        	init();
            sb.append('#').append(testCase).append(' ');
            solve();
            sb.append('\n');
             
        }
        System.out.println(sb);
         
    }
}
