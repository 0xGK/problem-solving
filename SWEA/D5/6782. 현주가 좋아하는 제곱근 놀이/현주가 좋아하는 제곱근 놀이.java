import java.io.*;
import java.util.*;
/*
 * N>=2
 * N <= 1_000_000_000_000
 * sqrtN <= 1_000_000
 * N could be N+1
 * if(squareRoot(N) is Integer), then it could be squareRoot(N) 
 * 
 * 목표: make N to 2
 * 
 * =========================
 * 1. if N == sqrtN * sqrtN
 * 		-> N = sqrtN;
 * 	  else
 * 		-> N++;
 * 
 * ======= timeout(X)=======
 * 
 * 1. binarySearch: N이랑 가장
 * 
 * 
 */
public class Solution {
	static long N, nearestSqrtN;
	static int playCount;
	
    public static void playGame() {
        playCount = 0;
        
        while (N != 2) {
        	nearestSqrtN = (long)Math.sqrt(N); 
            if (nearestSqrtN * nearestSqrtN == N) {
                playCount++;
            } else {
            	nearestSqrtN++;
                playCount+=nearestSqrtN * nearestSqrtN-N+1;
            }
            N = nearestSqrtN;
        }
    }
	
    public static void main(String[] args) throws Exception {
      //System.setIn(new FileInputStream("input.txt"));
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringBuilder sb = new StringBuilder();
      StringTokenizer st;
        
      int T = Integer.parseInt(br.readLine());
      for(int testCase=1; testCase<=T; ++testCase) {
          sb.append("#").append(testCase).append(" ");
          N = Long.parseLong(br.readLine());
          playGame();
          sb.append(playCount).append("\n");
      }
      System.out.println(sb);
        
  }
}
