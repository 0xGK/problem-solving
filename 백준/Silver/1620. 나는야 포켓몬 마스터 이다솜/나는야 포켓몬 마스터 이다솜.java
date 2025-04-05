import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static int N, M;
	static Map<String, Integer> hashMap;
	static String[] names;
	public static void init() throws IOException{
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		names = new String[N+1];
		hashMap = new HashMap<>();
		
		for(int idx=1; idx<=N; idx++) {
			String name = br.readLine();
			hashMap.put(name, idx);
			names[idx] = name;
		}
	}
	public static void solve() throws IOException{
		for(int idx=0; idx<M; idx++) {
			String str = br.readLine();
			try {
				int id = Integer.parseInt(str);
				System.out.println(names[id]);
			}catch(NumberFormatException e) {
				System.out.println(hashMap.get(str));
			}
		}		
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
