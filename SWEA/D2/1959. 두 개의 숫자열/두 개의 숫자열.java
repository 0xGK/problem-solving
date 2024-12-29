import java.util.Scanner;
import java.io.FileInputStream;
import java.util.Arrays;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		sc.nextLine();

		for(int t=1; t<=T; ++t){
			System.out.print("#"+t+" ");
			int N = sc.nextInt();
			int M = sc.nextInt();
			int B = Math.max(N, M);
			int S = Math.min(N, M);
			sc.nextLine();

			String input1 = sc.nextLine();
			String input2 = sc.nextLine();

			String[] parts1 = input1.split(" ");
			String[] parts2 = input2.split(" ");

			int[] intArray1 = new int[B];
			int[] intArray2 = new int[B];

			if(N<M){
				for(int i=0; i< N; i++){
					intArray1[i] = Integer.parseInt(parts1[i]) ;
				}
				for(int i=0; i< M; i++){
					intArray2[i] = Integer.parseInt(parts2[i]) ;
				}
			}else{
				for(int i=0; i< M; i++){
					intArray1[i] = Integer.parseInt(parts2[i]) ;
				}
				for(int i=0; i< N; i++){
					intArray2[i] = Integer.parseInt(parts1[i]) ;
				}
			}


			int ret = -123456789;
			for(int i=0; i<=B-S; i++){
				int sum = 0;
				for(int j=0; j<S; j++){
					sum += intArray1[j]*intArray2[i+j];
				}
				ret = Math.max(ret, sum);
			}
			System.out.println(ret);
		}
		
		
        sc.close();
	}
}