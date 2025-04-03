import java.util.*;
import java.io.*;
/*
 * 문제: 최소 비용을 찾아야함
 * 1. 모든 경우의 수를 고려해야 하는 상황
 * 2. Sorting해서 greedy하게?
 *  -> 3달 연속 이용권이 있기 때문에 불가능(X)
 * 3. 1-12월까지 순차적으로 backtracking하는 것이 좋을 듯
 *  -> index를 달의 값으로
 *  -> 파생 branch는 다음달 혹은 3달 후로 진행하면 됨
 *  -> terminate: 12월까지 계산한 경우
 *  -> prune: ticketPrice를 누적합으로 넘기면서, minTicketPrice보다 클 경우
 *  
 */
public class Solution {
    static final int MONTH_COUNT = 12;
    static int dayPrice, monthPrice, threeMonthPrice;
    static int minTicketPrice;
    static int[] monthPlan;
    static int[] dp;
    // 초기화 및 입력 저장
    public static void init(BufferedReader br) throws IOException{
        StringTokenizer st = new StringTokenizer(br.readLine());
        dayPrice = Integer.parseInt(st.nextToken());
        monthPrice = Integer.parseInt(st.nextToken());
        threeMonthPrice = Integer.parseInt(st.nextToken());
        minTicketPrice = Integer.parseInt(st.nextToken());
        
        monthPlan = new int[MONTH_COUNT+1];
        st = new StringTokenizer(br.readLine());
        for(int month=1; month<=MONTH_COUNT; month++) {
            monthPlan[month] = Integer.parseInt(st.nextToken());
        }
        dp = new int[MONTH_COUNT+1];
    }
     
    public static void solve() {
    	for(int month=1; month<3; month++) {
    		dp[month] = Math.min(monthPlan[month]*dayPrice, monthPrice) + dp[month-1];
    	}
    	for(int month=3; month<13; month++) {
    		dp[month] = Math.min(monthPlan[month]*dayPrice, monthPrice) + dp[month-1];
    		dp[month] = Math.min(dp[month], dp[month-3]+threeMonthPrice);
    	}
    	minTicketPrice = minTicketPrice > dp[12] ? dp[12] : minTicketPrice; 
    }
    public static void main(String[] args) throws Exception{
        //System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
         
        int T = Integer.parseInt(br.readLine());
        for(int testCase=1; testCase<=T; testCase++) {
            sb.append("#").append(testCase).append(" ");
            init(br);
            solve();
            sb.append(minTicketPrice).append("\n");
        }
         
        System.out.println(sb);
 
    }
}