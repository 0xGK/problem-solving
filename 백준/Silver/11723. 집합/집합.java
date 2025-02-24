import java.util.*;
import java.io.*;
public class Main {
	static final int MAX_VALUE = 20;
	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int bitMap=0;
		int N = Integer.parseInt(br.readLine());
		
		for(int index=0; index<N; index++) {
			st = new StringTokenizer(br.readLine());
			String command = st.nextToken();
			if(command.equals("add")) {
				int value = Integer.parseInt(st.nextToken());
				bitMap |= (1<<value);
			}else if(command.equals("remove")) {
				int value = Integer.parseInt(st.nextToken());
				bitMap &= ~(1<<value);
				
			}else if(command.equals("check")) {
				int value = Integer.parseInt(st.nextToken());
				if((bitMap & (1<<value))!=0) {
					sb.append("1\n");
				}else {
					sb.append("0\n");
				}
				
			}else if(command.equals("toggle")) {
				int value = Integer.parseInt(st.nextToken());
				bitMap ^= (1<<value);
			}else if(command.equals("all")) {
				bitMap = (1<<(MAX_VALUE+1))-1;
			}else if(command.equals("empty")) {
				bitMap = 0;
				
			}
		}
		System.out.println(sb);
		
	}
}
