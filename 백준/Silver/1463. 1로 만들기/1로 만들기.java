import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Main {
	static int dp[];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int X = Integer.parseInt(br.readLine());
        dp = new int[X+1];
        if(X<2) {
        	System.out.println(0);
        }else {
        	dp[0]=0;
        	dp[1]=0;
        	for(int i=2; i<=X; i++) {
        		dp[i]=Integer.MAX_VALUE;
        	}
        	for(int i=2; i<=X; i++) {
        		if(i%2==0) {
        			dp[i] = dp[i] > dp[i/2] + 1 ? dp[i/2] + 1 : dp[i];  
        		}
        		if(i%3==0) {
        			dp[i] = dp[i] > dp[i/3] + 1 ? dp[i/3] + 1 : dp[i];
        		}
        		dp[i] = dp[i] > dp[i-1] + 1 ? dp[i-1] + 1 : dp[i];
        		
        	}
        	System.out.println(dp[X]);
        }
    }
}
