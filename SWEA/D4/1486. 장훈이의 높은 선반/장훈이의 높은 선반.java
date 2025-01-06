import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

class Solution
{
	final static int MAX_N = 20;
	static int N, B;
	static int[] H = new int[MAX_N];
	static int ret = Integer.MAX_VALUE;

	
	public static void backtrack(int index, int curSum) {
		if(curSum>=B) {
			ret = Math.min(ret, curSum);
		}
		if(index==N) {
			return;
		}
		
		backtrack(index+1, curSum+H[index]);
		backtrack(index+1, curSum);
	}
	
	public static void main(String args[]) throws Exception
	{
        //System.setIn(new FileInputStream("res/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());


		for(int t=1; t<=T; ++t){
			System.out.print("#" + t + " ");
			String[] line = br.readLine().split(" ");
			N = Integer.parseInt(line[0]);
			B = Integer.parseInt(line[1]);
			ret = Integer.MAX_VALUE;
			String[] line2 = br.readLine().split(" ");
			for(int i=0; i<N; i++) {
				H[i] = Integer.parseInt(line2[i]);
			}
			backtrack(0, 0);
			System.out.println(ret-B);
			

		}
        br.close();
	}
}