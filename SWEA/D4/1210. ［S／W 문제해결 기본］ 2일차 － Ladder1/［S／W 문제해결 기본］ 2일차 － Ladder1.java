import java.io.*;
import java.util.*;
 
public class Solution {
    static final int MAX_N = 100;
    static int[][] board;
    static int[] prevLadder;
    static int[] nextLadder;
     
    static int nextDir(int x, int y) {
        if(y-1>=0 && board[x][y-1]==1) {
            return prevLadder[y];
        }
        else if(y+1<MAX_N && board[x][y+1]==1) {
            return nextLadder[y];
        }
        return y;
    }
     
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
         
        int T = 10;
        for(int testCase=1; testCase<=T; ++testCase) {
            br.readLine();
            sb.append("#").append(testCase).append(" ");
             
            int end=0;
            board = new int[MAX_N][MAX_N];
            for(int i=0; i<MAX_N; ++i) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<MAX_N; ++j) {
                    // 0인 값은 굳이 안 넣어도 됨. 그러나 check하는 것이 더 오래 걸리지 않을까?
                    board[i][j]=Integer.parseInt(st.nextToken());
                    if(board[i][j]==2) end=j;
                }
            }
             
            nextLadder = new int[MAX_N];
            prevLadder = new int[MAX_N];
            int prev = 0;
            for(int next=1; next<MAX_N; ++next) {
                if(board[0][next]==1) {
                    nextLadder[prev]=next;
                    prevLadder[next]=prev;
                    prev=next;
                }
            }

             
            for(int i=MAX_N-2; i>=0; --i) {
                end = nextDir(i, end);
            }
            sb.append(end).append("\n");
             
        }
        System.out.println(sb);
         
    }
}