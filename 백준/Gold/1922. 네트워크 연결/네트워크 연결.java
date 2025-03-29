import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int V, E;
	
	static Edge[] edges;
	static long totalWeight;
	
	static class Edge implements Comparable<Edge>{
		int src;
		int dest;
		int weight;
		public Edge(int src, int dest, int weight) {
			this.src = src;
			this.dest = dest;
			this.weight = weight;
		}
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(weight, o.weight);
		}
	}
	
	static int[] parents;
	public static void init() throws IOException{
		V = Integer.parseInt(br.readLine());
		E = Integer.parseInt(br.readLine());
		edges = new Edge[E];
		parents = new int[V+1];
		for(int idx=1; idx<V+1; idx++) {
			parents[idx] = idx;
		}
		for(int inputIdx=0; inputIdx<E; inputIdx++) {
			st = new StringTokenizer(br.readLine());
			int src = Integer.parseInt(st.nextToken());
			int dest = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			edges[inputIdx] = new Edge(src, dest,weight);
		}
		totalWeight = 0;
	}
	public static int find(int e) {
		if(parents[e] == e) return e;
		return parents[e] = find(parents[e]);
	}
	public static boolean union(int e1, int e2) {
		int root1 = find(e1);
		int root2 = find(e2);
		if(root1 == root2) return false;
		parents[root1] = root2;
		return true;
	}
	public static void kruskal() {
		Arrays.sort(edges);
		for(Edge edge : edges) {
			if(union(edge.src, edge.dest)) {
				totalWeight += edge.weight;
			}
		}
	}
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
         
    	init();
    	kruskal();

    	
    	System.out.println(totalWeight);
    }
}
