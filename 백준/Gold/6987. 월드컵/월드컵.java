
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
    
    public static boolean isPossible() {    	
    	for(int countryIndex=0; countryIndex<COUNTRIES_NUMBER; countryIndex++) {
    		for(int resultIndex=0; resultIndex<3; resultIndex++) {
    			if(scoreBoard[countryIndex][resultIndex] != 0) {
    				return false;
    			}
    		}
    	}
    	return true;
    }
    
    public static void playGameBacktrack(int currentCountryIndex, int nextCountryIndex) {
    	if(flag) return;
        if (currentCountryIndex == COUNTRIES_NUMBER-1) {
        	flag = isPossible();
            return;
        }
        
        if (nextCountryIndex == COUNTRIES_NUMBER) {
            playGameBacktrack(currentCountryIndex + 1, currentCountryIndex + 2);
            return;
        }
        

        if (scoreBoard[currentCountryIndex][WIN] > 0 && scoreBoard[nextCountryIndex][LOOSE] > 0) {
            scoreBoard[currentCountryIndex][WIN]--;
            scoreBoard[nextCountryIndex][LOOSE]--;
            playGameBacktrack(currentCountryIndex, nextCountryIndex + 1);
            if(flag) return;
            scoreBoard[currentCountryIndex][WIN]++;
            scoreBoard[nextCountryIndex][LOOSE]++;
        }
        
        if (scoreBoard[currentCountryIndex][TIE] > 0 && scoreBoard[nextCountryIndex][TIE] > 0) {
            scoreBoard[currentCountryIndex][TIE]--;
            scoreBoard[nextCountryIndex][TIE]--;
            playGameBacktrack(currentCountryIndex, nextCountryIndex + 1);
            if(flag) return;
            scoreBoard[currentCountryIndex][TIE]++;
            scoreBoard[nextCountryIndex][TIE]++;
        }
        
        if (scoreBoard[currentCountryIndex][LOOSE] > 0 && scoreBoard[nextCountryIndex][WIN] > 0) {
            scoreBoard[currentCountryIndex][LOOSE]--;
            scoreBoard[nextCountryIndex][WIN]--;
            playGameBacktrack(currentCountryIndex, nextCountryIndex + 1);
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
    		playGameBacktrack(0, 1);
    		if(flag) {
    			sb.append(1);
    		}else {
    			sb.append(0);
    		}
    		sb.append(" ");
    	}
    	System.out.println(sb);
    }
}