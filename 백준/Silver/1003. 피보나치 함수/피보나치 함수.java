import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main {
    static int[][] dp;

    public static void main(String[] args) throws Exception {
    	//System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        // 최대 n을 찾기 위해 입력 먼저 저장
        int[] inputs = new int[T];
        int maxN = 0;
        for (int i = 0; i < T; i++) {
            inputs[i] = Integer.parseInt(br.readLine());
            maxN = Math.max(maxN, inputs[i]);
        }

        // DP 배열 초기화
        dp = new int[maxN + 1][2]; // 0: zeroCnt, 1: oneCnt
        dp[0][0] = 1; // fibonacci(0): 0이 1번, 1은 0번
        dp[0][1] = 0;
        if (maxN >= 1) {
            dp[1][0] = 0; // fibonacci(1): 0이 0번, 1은 1번
            dp[1][1] = 1;
        }

        for (int i = 2; i <= maxN; i++) {
            dp[i][0] = dp[i-1][0] + dp[i-2][0]; // 0의 횟수
            dp[i][1] = dp[i-1][1] + dp[i-2][1]; // 1의 횟수
        }

        for (int i = 0; i < T; i++) {
            int n = inputs[i];
            System.out.println(dp[n][0] + " " + dp[n][1]);
        }
    }
}
