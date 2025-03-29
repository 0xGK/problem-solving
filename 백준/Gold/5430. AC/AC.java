import java.io.*;
import java.util.*;


/*
 * R: 뒤집기
 * 	배열에 있는 수를 뒤집는 함수
 * D: 버리기
 * 	첫 번쨰 수를 버리는 함수.
 * 	배열이 empty일 경우 에러 발생
 * 
 */
public class Main {
	static int N, head, tail;
	static boolean isRightDir;

	static String command;
	static StringBuilder sb;

	static ArrayList<Integer> ll;

	public static void solve() {
		int startIdx = 0;
		int endIdx = N-1;
		for(int index=0; index<command.length(); index++) {
			if(command.charAt(index)=='R') {
				isRightDir = isRightDir ? false : true;
			}else {
				if(startIdx>endIdx) {
					sb.append("error\n");
					return;
				}
				if(isRightDir) {
					startIdx++;
				}else {
					endIdx--;
				}
			}
		}
		
		if(startIdx<=endIdx) {
			sb.append('[');
			if(isRightDir) {
				for(int index=startIdx; index<endIdx; index++) {
					sb.append(ll.get(index)).append(',');
				}
				sb.append(ll.get(endIdx));
			}else {
				for(int index=endIdx; index>startIdx; index--) {
					sb.append(ll.get(index)).append(',');
				}
				sb.append(ll.get(startIdx));
			}
			
			sb.append("]\n");
		}else {
			sb.append("[]\n");
		}
		
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		
		for(int testCase = 1; testCase<=T; testCase++) {
			isRightDir=true;
			command = br.readLine();
			N = Integer.parseInt(br.readLine());
			String input = br.readLine();
			input = input.substring(1, input.length()-1);
			ll = new ArrayList<>();

			st = new StringTokenizer(input, ",");
			for(int index=0; index<N; index++) {
				ll.add(Integer.parseInt(st.nextToken()));
			}
			
			solve();
			
		}
		System.out.println(sb);
		
	}
}
