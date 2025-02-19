import java.io.*;
import java.util.*;
/*
 */
public class Solution {
	final static int MAX_N = 10;
	
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
         
        int T = Integer.parseInt(br.readLine());
        for(int testCase=1; testCase<=T; ++testCase) {
            sb.append("#").append(testCase).append("\n");
            int N = Integer.parseInt(br.readLine());
            int[][] board = new int[MAX_N][MAX_N];
            
            // for odd case
            board[N/2][N/2]=N*N;
            int round = N-1;
            int i=0, j=0;
            int cnt=1;
            while(round>0) {
            	for(int r=0; r<round; r++) {
            		board[i][j++]=cnt++;
            	}
            	for(int r=0; r<round; r++) {
            		board[i++][j]=cnt++;
            	}
            	for(int r=0; r<round; r++) {
            		board[i][j--]=cnt++;
            	}
            	for(int r=0; r<round; r++) {
            		board[i--][j]=cnt++;
            	}
            	i++; j++;
            	round-=2;
            }
            for(i=0; i<N; i++) {
            	for(j=0; j<N; j++) {
            		sb.append(board[i][j]).append(" ");
            	}
            	sb.append("\n");
            }
            
            
        }
        System.out.println(sb);
         
    }
}