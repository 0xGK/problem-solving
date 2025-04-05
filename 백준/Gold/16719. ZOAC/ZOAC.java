import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	static String str;
	static boolean[] visited;
	
	public static void init() throws IOException{
		str = br.readLine();
		visited = new boolean[str.length()];
	}
	
	public static int getMinIndex(int startIdx, int endIdx) {
		int minIdx = -1;
		char minChar = 'Z'+1;
		for(int idx=startIdx; idx<endIdx; idx++) {
			if(visited[idx]) continue;
			char currentChar = str.charAt(idx);
			if(currentChar < minChar) {
				minChar = currentChar;
				minIdx = idx;
			}
		}
		return minIdx;
	}
	
	public static void printStr() {
		for(int idx=0; idx<str.length(); idx++) {
			if(visited[idx]) {
				sb.append(str.charAt(idx));
			}
		}
		sb.append('\n');
	}
	
	public static void dfs(int startIdx, int endIdx) {
		int minIdx = getMinIndex(startIdx, str.length());
		if(minIdx == -1) return;
		visited[minIdx] = true;
		printStr();
		dfs(minIdx+1, endIdx);
		dfs(startIdx, minIdx);
	}
	
	
	
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
    	init();
    	dfs(0, str.length());

    	
    	System.out.println(sb);
    }
}
