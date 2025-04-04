import java.io.*;
import java.util.*;
/*
 * prim 알고리즘으로 풀이
 * 모든 edge들에 대해서 진행. 
 */
public class Solution {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int V;
	static double E;
	static double[] X, Y;
	
	static double totalCost;
	static class Edge implements Comparable<Edge>{
		int dest;
		double cost;
		public Edge(int dest, double cost) {
			this.dest = dest;
			this.cost = cost;
		}
		@Override
		public int compareTo(Edge o) {
			return Double.compare(cost, o.cost);
		}
	}
	
	static boolean[] visited;
	static Queue<Edge> pq;
	
	static double[][] matrix;
	public static double getWeight(int src, int dest) {
		double dx = X[src] - X[dest];
		double dy = Y[src] - Y[dest];
		return dx*dx + dy*dy;
	}
	public static void init() throws IOException{
		V = Integer.parseInt(br.readLine());

		// X좌표와 Y좌표를 따로 저장
		X = new double[V];
		st = new StringTokenizer(br.readLine());
		for(int idx=0; idx<V; idx++) X[idx] = Double.parseDouble(st.nextToken());
		
		Y = new double[V];
		st = new StringTokenizer(br.readLine());
		for(int idx=0; idx<V; idx++) Y[idx] = Double.parseDouble(st.nextToken());
		
		E = Double.parseDouble(br.readLine());
		
		visited = new boolean[V];
		matrix = new double[V][V];
		for(int src=0; src<V-1; src++) {
			for(int dest=src+1; dest<V; dest++) {
				double cost = getWeight(src, dest);
				matrix[src][dest] = cost;
				matrix[dest][src] = cost;
			}
		}
		
	}
	
	static long prim() {
		pq = new PriorityQueue<>();
		pq.add(new Edge(0, 0));
		totalCost = 0;
		
		while(!pq.isEmpty()) {
			Edge node = pq.poll();
			int src = node.dest;
			if(visited[src]) continue;
			visited[src] = true;
			totalCost += node.cost;
			
			for(int dest = 0; dest < V; dest++) {
				if(visited[dest]) continue;
				pq.add(new Edge(dest, matrix[src][dest]));
			}
		}
		
		return Math.round(totalCost*E);
	}

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
		
        int T = Integer.parseInt(br.readLine());
        for(int testCase=1; testCase<=T; ++testCase) {
        	init();
            sb.append('#').append(testCase).append(' ').append(prim()).append('\n');
             
        }
        System.out.println(sb);
         
    }
}
