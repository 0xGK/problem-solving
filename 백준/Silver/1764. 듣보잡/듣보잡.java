
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	static String[] str1, str2;
	static List<String> resultStr;
	static int N, M, totalCnt;

	public static void solve() throws IOException{
		totalCnt=0;
		resultStr = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		str1 = new String[N];
		str2 = new String[M];
		for(int idx=0; idx<N; idx++) {
			str1[idx] = br.readLine();
		}
		
		for(int idx=0; idx<M; idx++) {
			str2[idx] = br.readLine();
		}
		
		Arrays.sort(str1);
		Arrays.sort(str2);
		int idx1=0;
		int idx2=0;
		while(idx1<str1.length && idx2<str2.length) {
			if(str1[idx1].equals(str2[idx2])) {
				totalCnt++;
				resultStr.add(str1[idx1]);
				idx1++;
				idx2++;
			}else if(str1[idx1].compareTo(str2[idx2])>0) {
				idx2++;
			}else {
				idx1++;
			}
		}

		sb.append(totalCnt).append('\n');
		for(String str : resultStr) {
			sb.append(str).append('\n');
		}
	}
    public static void main(String[] args) throws Exception {
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
    	solve();
    	System.out.println(sb);
    }
}
