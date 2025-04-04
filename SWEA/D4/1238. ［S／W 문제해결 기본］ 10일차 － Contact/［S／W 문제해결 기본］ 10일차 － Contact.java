import java.io.*;
import java.util.*;
/*
 * 연락 인원 <= 100
 * 1~100
 * 
 * 중간에 비어있는 번호가 있을 수 있음.
 * 가장 마지막에 연락 받는 사람 중 번호가 가장 큰 값을 출력.
 * 
 * 
 * 
 */
public class Solution {
	static final int MAX_PEOPLE_COUNT = 101;
	static BufferedReader br;
	static StringTokenizer st;
	static List<Integer>[] graph;
	static int N, start, maxDist, maxNumber, prevMaxNumber;
	static class Node{
		int cur;
		int dist;
		public Node(int cur, int dist) {
			this.cur = cur;
			this.dist = dist;
		}
	}
	
	public static void init() throws IOException{
		maxDist = -1;
		maxNumber = 0;
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		start = Integer.parseInt(st.nextToken());
		
		for(int idx=1; idx<MAX_PEOPLE_COUNT; idx++) {
			graph[idx] = new ArrayList<>();
		}
		
		st = new StringTokenizer(br.readLine());
		for(int idx=0; idx<N/2; idx++) {
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			graph[from].add(to);
		}
	}
	
	
	public static void solve() throws IOException{
		Queue<Node> queue = new ArrayDeque<>();
		boolean[] visited = new boolean[MAX_PEOPLE_COUNT];
		queue.add(new Node(start, 0));
		visited[start]=true;
		prevMaxNumber = 0;
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			int cur = node.cur;
			int dist = node.dist;

			if(maxDist < dist) {
				maxDist = dist;
				prevMaxNumber = maxNumber;
				maxNumber= 0;
			}
			
			for(int nxt: graph[cur]) {
				if(visited[nxt]) continue;
				visited[nxt] = true;
				queue.add(new Node(nxt, dist+1));
				// 지금 넣는 이 노드가 갱신 가능성 있는 경우임
				if(maxDist < dist+1) {
					//System.out.println("다핫");
					maxDist = dist;
					maxNumber = maxNumber < nxt ? nxt : maxNumber;
				}
				
			}
		}
		
	}
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        graph = new ArrayList[MAX_PEOPLE_COUNT];
        int T = 10;
        for(int testCase=1; testCase<=T; ++testCase) {
        	init();
        	solve();
            sb.append('#').append(testCase).append(' ').append(prevMaxNumber).append('\n');
             
        }
        System.out.println(sb);
         
    }
}
