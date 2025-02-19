import java.io.*;
import java.util.*;
 
public class Solution {
	static final int MAX_N = 9;
	static int[] myCard;
	static int[] yourCard;
	static int[] playingCard;
	static boolean[] usingCard;
	static boolean[] leftCard;
	static int myWinCount;
	static int myLoseCount;
	
	
	static void play(int round) {
		if(round == 9) {
			int myScore = 0;
			int yourScore = 0;
			for(int i=0; i<MAX_N; i++) {
				if(myCard[i]>playingCard[i]) {
					myScore += myCard[i]+playingCard[i];
				}else {
					yourScore += myCard[i]+playingCard[i];
				}
			}
			
			if(myScore>yourScore) myWinCount++;
			else if(myScore<yourScore) myLoseCount++;
		}
		for(int i=0; i<MAX_N; i++) {
			if(!usingCard[i]) {
				usingCard[i]=true;
				playingCard[round] = yourCard[i];
				play(round+1);
				usingCard[i]=false;
			}
		}
		
	}
	
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
         
        int T = Integer.parseInt(br.readLine());
        for(int testCase=1; testCase<=T; ++testCase) {
            sb.append("#").append(testCase).append(" ");
            myCard = new int[MAX_N];
            yourCard = new int[MAX_N];
            playingCard = new int[MAX_N];
            usingCard = new boolean[MAX_N];
            leftCard = new boolean[MAX_N*2+1];
            myWinCount = 0;
            myLoseCount = 0;
            
            st = new StringTokenizer(br.readLine());
            for(int i=0; i<MAX_N; i++) {
            	myCard[i] = Integer.parseInt(st.nextToken());
            	leftCard[myCard[i]]=true;
            }
            int idx=0;
            for(int i=1; i<=MAX_N*2; i++) {
            	if(!leftCard[i]) {
            		yourCard[idx++] = i;
            	}
            }
            		
            play(0);
            sb.append(myWinCount).append(" ").append(myLoseCount).append("\n");
             
        }
        System.out.println(sb);
         
    }
}