import java.util.*;
import java.io.*;

/*
 * 
5
4 0
4 30
4 47
5 31
5 62
 */
class Solution
{
	static int N, M;
	public static void main(String args[]) throws Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T;
		T=Integer.parseInt(br.readLine());

		for(int test_case = 1; test_case <= T; test_case++)
		{
			sb.append("#").append(test_case).append(" ");
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			int flag = (1<<N) -1 ;
			if((M&flag) == flag) {
				sb.append("ON\n");
			}else {
				sb.append("OFF\n");
			}
			
			
		}
		
		System.out.println(sb);
	}
}