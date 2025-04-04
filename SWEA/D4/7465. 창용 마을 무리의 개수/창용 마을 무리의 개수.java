import java.io.*;
import java.util.*;

public class Solution {
	static int N, M;
	static int groupCnt;
	static int[] parents;
	
	static BufferedReader br;
	static StringTokenizer st;
	
	public static void init() throws IOException{
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		parents = new int[N+1];
		for(int idx=1; idx<N+1; idx++) {
			parents[idx]=idx;
		}
		groupCnt = N;
	}
	
	public static int find(int e) {
		if(parents[e]==e) return e;
		return parents[e] = find(parents[e]);
	}
	public static void union(int e1, int e2) {
		int root1 = find(e1);
		int root2 = find(e2);
		
		if(root1 == root2) {
			return;
		}
		
		groupCnt--;
		parents[root2] = root1;
	}
	
	public static void solve() throws IOException{
		for(int inputIdx=0; inputIdx<M; inputIdx++) {
			st = new StringTokenizer(br.readLine());
			int e1 = Integer.parseInt(st.nextToken());
			int e2 = Integer.parseInt(st.nextToken());
			union(e1, e2);
		}
	}
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
         
        int T = Integer.parseInt(br.readLine());
        for(int testCase=1; testCase<=T; ++testCase) {
        	init();
        	solve();
            sb.append('#').append(testCase).append(' ').append(groupCnt).append('\n');
             
        }
        System.out.println(sb);
         
    }
}
