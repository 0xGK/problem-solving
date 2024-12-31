import java.io.FileInputStream;
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
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();


		for(int t=1; t<=T; ++t){
			System.out.print("#" + t + " ");
			N = sc.nextInt();
			B = sc.nextInt();
			ret = Integer.MAX_VALUE;
			for(int i=0; i<N; i++) {
				H[i] = sc.nextInt();
			}
			backtrack(0, 0);
			System.out.println(ret-B);
			

		}
        sc.close();
	}
}