
import java.io.*;
import java.util.*;

public class Main {
	static final int COUNTRIES_NUMBER = 6;
	static final int WIN = 0;
	static final int TIE = 1;
	static final int LOOSE = 2;
	static int[][] scoreBoard = new int[6][3];
    static int possible;
    static boolean flag;

    public static void init(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        possible = 0;
        flag=false;
        
        for (int countryIndex = 0; countryIndex < 6; countryIndex++) {
        	scoreBoard[countryIndex][WIN] = Integer.parseInt(st.nextToken());
        	scoreBoard[countryIndex][TIE] = Integer.parseInt(st.nextToken());
        	scoreBoard[countryIndex][LOOSE] = Integer.parseInt(st.nextToken());
        }

    }
    
    public static void solution(int currentCountryIndex, int nextCountryIndex) {
    	if(flag) return;
        if (currentCountryIndex == 5) {
            int winSum = 0;
            int drawSum = 0;
            int loseSum = 0;
            
            for (int i = 0; i < 6; i++) {
                winSum += scoreBoard[i][WIN];
                drawSum += scoreBoard[i][TIE];
                loseSum += scoreBoard[i][LOOSE];
            }
            
            if (winSum ==0 && loseSum ==0 && drawSum== 0) {
                possible = 1;
                flag = true;
            }
            return;
        }
        
        if (nextCountryIndex == 6) {
            solution(currentCountryIndex + 1, currentCountryIndex + 2);
            return;
        }
        
        
        if (scoreBoard[currentCountryIndex][WIN] > 0 && scoreBoard[nextCountryIndex][LOOSE] > 0) {
            scoreBoard[currentCountryIndex][WIN]--;
            scoreBoard[nextCountryIndex][LOOSE]--;
            solution(currentCountryIndex, nextCountryIndex + 1);
            if(flag) return;
            scoreBoard[currentCountryIndex][WIN]++;
            scoreBoard[nextCountryIndex][LOOSE]++;
        }
        
        if (scoreBoard[currentCountryIndex][TIE] > 0 && scoreBoard[nextCountryIndex][TIE] > 0) {
            scoreBoard[currentCountryIndex][TIE]--;
            scoreBoard[nextCountryIndex][TIE]--;
            solution(currentCountryIndex, nextCountryIndex + 1);
            if(flag) return;
            scoreBoard[currentCountryIndex][TIE]++;
            scoreBoard[nextCountryIndex][TIE]++;
        }
        
        if (scoreBoard[currentCountryIndex][LOOSE] > 0 && scoreBoard[nextCountryIndex][WIN] > 0) {
            scoreBoard[currentCountryIndex][LOOSE]--;
            scoreBoard[nextCountryIndex][WIN]--;
            solution(currentCountryIndex, nextCountryIndex + 1);
            if(flag) return;
            scoreBoard[currentCountryIndex][LOOSE]++;
            scoreBoard[nextCountryIndex][WIN]++;
        }
    }
    
    public static void main(String[] args) throws Exception {
    	//System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
    	for(int index=0; index<4; index++) {
    		init(br);
    		solution(0, 1);
    		sb.append(possible).append(" ");
    	}
    	System.out.println(sb);
    }
}