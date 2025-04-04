import java.io.*;
import java.util.*;

/*
 * 최소신장트리 문제
 * Kruskal로 풀이
 * Union - Find로 서소로 집합 만들기
 * Edge 노드들을 List에 넣고, weight 기준으로 오름차순 정렬하여,
 * 최소 weight 순서대로 하나씩 연결
 * 
 */
public class Solution {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int V, E;
	
	static List<Edge>[] edges;
	static long totalWeight;
	static Queue<Edge> pq;
	static boolean[] visited;
	
	static class Edge implements Comparable<Edge>{
		int dest;
		int weight;
		public Edge(int dest, int weight) {
			this.dest = dest;
			this.weight = weight;
		}
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(weight, o.weight);
		}
	}
	
	public static void init() throws IOException{
		st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		edges = new ArrayList[V+1];
		visited = new boolean[V+1];
		for(int idx=1; idx<=V; idx++) edges[idx] = new ArrayList<>();
		
		for(int inputIdx=0; inputIdx<E; inputIdx++) {
			st = new StringTokenizer(br.readLine());
			int src = Integer.parseInt(st.nextToken());
			int dest = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edges[src].add(new Edge(dest, weight));
			edges[dest].add(new Edge(src, weight));
		}
		totalWeight = 0;
	}

	public static void prim() {
		pq = new PriorityQueue<>();
		pq.add(new Edge(1,0));
		
		while(!pq.isEmpty()) {
			Edge edge = pq.poll();
			int src = edge.dest;
			
			if(visited[src]) continue;
			visited[src] = true;
			totalWeight += edge.weight;
			
			for(Edge nextEdge : edges[src]) {
				int dest = nextEdge.dest;
				if(visited[dest]) continue;
				pq.add(nextEdge);
			}
		}
	}
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
         
        int T = Integer.parseInt(br.readLine());
        for(int testCase=1; testCase<=T; ++testCase) {
        	init();
        	prim();
            sb.append('#').append(testCase).append(' ').append(totalWeight).append('\n');
             
        }
        System.out.println(sb);
         
    }
}
