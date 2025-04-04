import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N, M;
	static int[] parents;
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	
	
	public static void init() throws IOException{
		parents = new int[N+1];
		for(int idx=1; idx<N+1; idx++) {
			parents[idx]=idx;
		}
	}
	
	
	
	public static int find(int element) {
		// root인 경우
		if(parents[element] == element) return element;
		
		// 아닌 경우
		return parents[element] = find(parents[element]); // 경로 압축
	}
	public static void union(int element1, int element2) {
		int e1Root = find(element1);
		int e2Root = find(element2);
		
		// 이미 같은 집합인 경우
		if(e1Root == e2Root) {
			return;
		}
		
		parents[e2Root] = e1Root;
		
	}
	
	public static void solve() throws IOException{
		int command, e1, e2;
		for(int inputIdx=0; inputIdx<M; inputIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			command = Integer.parseInt(st.nextToken());
			e1 = Integer.parseInt(st.nextToken());
			e2 = Integer.parseInt(st.nextToken());
			if(command==0) {
				union(e1, e2);
			}else {
				if(find(e1) == find(e2)) {
					sb.append(1);
				}
				else {
					sb.append(0);
				}
			}
		}
		sb.append("\n");
	}
	
    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream("input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
         
        int T = Integer.parseInt(br.readLine().trim());
        for(int testCase=1; testCase<=T; ++testCase) {
        	sb.append("#").append(testCase).append(" ");
        	
    		st = new StringTokenizer(br.readLine().trim());
    		N = Integer.parseInt(st.nextToken());
    		M = Integer.parseInt(st.nextToken());
    		
        	init();
        	solve();
        }
        System.out.println(sb);
         
    }
}
