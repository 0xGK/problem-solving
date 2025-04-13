import java.util.*;
import java.io.*;
/*
 * 이웃한 두 집은 색이 같지 않아야 합니다.
 * 각 줄에 입력은, RGB의 비용
 * 
 * dp[i][color] = i번째 집까지 비용의 최솟값. 이때 color가 중요합니다
 * 
 *  i번째, color에 저장할 최소 비용은, 직전 집의 서로 다른 컬러의 집들 중 최소값을 선택해서 결정
 *  dp[i][color] = houseCost[i][color] + 
						Math.min(dp[i-1][(color+1)%3], dp[i-1][(color+2)%3] );
 * 
 * 
 */
public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int N;
	static int[][] dp;
	static int[][] houseCost;

	public static void init() throws IOException{
		N = Integer.parseInt(br.readLine());
		dp = new int[N+1][3];
		houseCost = new int[N+1][3];
		
		for(int houseIdx=1; houseIdx<=N; houseIdx++) {
			st = new StringTokenizer(br.readLine());
			for(int color=0; color<3; color++) {
				houseCost[houseIdx][color] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int color=0; color<3; color++) dp[1][color]=houseCost[1][color];
	}
	public static int solve() {
		for(int houseIdx=2; houseIdx<=N; houseIdx++) {
			for(int color=0; color<3; color++) {
				// 다른 컬러
				dp[houseIdx][color] = houseCost[houseIdx][color] + 
						Math.min(dp[houseIdx-1][(color+1)%3], dp[houseIdx-1][(color+2)%3] );
			}
		}
		return Math.min(Math.min(dp[N][0], dp[N][1]), dp[N][2]);
	}
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
    	init();
    	System.out.println(solve());
    }
}
