import java.util.*;
import java.io.*;

/*
 * 단순히 mCn을 구하는 조합 문제로 치환할 수 있습니다.
 * 
 */
public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	
	
	static int[][] C;
	
	
	public static void init() throws IOException{
		C = new int[31][31];
		for(int i=0; i<31; i++) {
			C[i][0] = C[i][i] = 1;
		}
		for(int n=2; n<31; n++) {
			for(int k=1; k<n; k++) {
				C[n][k] = C[n-1][k-1] + C[n-1][k];
			}
		}
	}
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
         
    	init();
    	int T = Integer.parseInt(br.readLine());
    	for(int testCase=1; testCase<=T; testCase++) {
    		st = new StringTokenizer(br.readLine());
    		int k = Integer.parseInt(st.nextToken());
    		int n = Integer.parseInt(st.nextToken());
    		sb.append(C[n][k]).append('\n');
    	}
    	System.out.println(sb);
    }
}
