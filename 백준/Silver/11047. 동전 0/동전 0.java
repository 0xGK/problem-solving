import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static int N, cost;
	static int[] values;
	public static void init() throws IOException{
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		cost = Integer.parseInt(st.nextToken());
		values = new int[N];

		for(int idx=0; idx<N; idx++) {
			values[idx] = Integer.parseInt(br.readLine());
		}
	}
	public static void solve() {
		int cnt=0;
		for(int idx=N-1; idx>=0; idx--) {
			int price = values[idx];
			
			if(cost/price>0) {
				cnt += cost/price;
				cost = cost%price;
			}
		}
		System.out.println(cnt);
	}
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
         
    	init();
    	solve();

    	
    	System.out.println(sb);
    }
}
