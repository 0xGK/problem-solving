import java.io.*;
import java.util.*;
public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int V, E;
	
	static List<Edge>[] edges;
	static boolean[] visited;
	static Queue<Edge> pq;
	static long totalWeight;
	
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
		V = Integer.parseInt(br.readLine());
		E = Integer.parseInt(br.readLine());
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
         
    	init();
    	prim();

    	
    	System.out.println(totalWeight);
    }
}
