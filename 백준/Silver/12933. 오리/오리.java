import java.util.*;
import java.io.*;

public class Main{
	static BufferedReader br;
//	static StringTokenizer st;
//	public static void init() throws IOException{
//	}
	static String quack = "quack";
	static int cnt = 0;
	static boolean[] visited;
	static String str;
	public static void init() throws IOException{
		str = br.readLine();
		visited = new boolean[str.length()];
	}
	public static int findUack(int startIdx) {
		int quackIdx=0;
		int idx = startIdx;
		for(;idx<str.length() && quackIdx<quack.length(); idx++) {
			if(visited[idx]) continue;
			if(str.charAt(idx) == quack.charAt(quackIdx)) {
				visited[idx] = true;
				quackIdx++;
			}
		}
		if(quackIdx==quack.length()) {
			return idx;
		}
		return -1;
	}
	public static boolean findQ () {
		boolean flag = true;
		while(flag) {
			flag = false;
			for(int idx=0; idx<str.length(); idx++) {
				if(visited[idx]) continue;
				if(str.charAt(idx)=='q') {
					flag = true;
					int nxtIdx = findUack(idx);
					if(nxtIdx == -1) return false;
					idx = nxtIdx-1;
				}
			}
			if(flag)
				cnt++;
		}
		for(int idx = 0; idx < str.length(); idx++) {
			if(!visited[idx]) return false;
		}
		
		return true;
	}
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        
        init();
    	if(findQ()) {
    		System.out.println(cnt);
    	}else {
    		System.out.println(-1);
    	}
    }
}
