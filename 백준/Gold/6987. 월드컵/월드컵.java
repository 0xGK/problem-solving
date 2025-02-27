

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	/*
	 * 1. 팀 0과 1을 비교하는 것부터 시작한다.
	 * 2. 다음 팀과 경기를 했을때 이기는 경우, 지는경우 , 비기는 경우를 모두 고려한다.
	 * 3. 다 돌았을때 모든 result가 0이 되어 있다면 가능하다.
	 * 
	 */
	
    static int possible; //가능한지
    static int[][] gameResult = new int[6][3];

    public static void init(BufferedReader br) throws NumberFormatException, IOException {
    	
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine().trim());
        
        possible = 0;
        
        for (int team = 0; team < 6; team++) {
        	for (int game = 0; game < 3; game++) {
        		gameResult[team][game] = Integer.parseInt(st.nextToken());
        	}
        }

    }
    
    public static void solution(int team, int next) {
    	//3. 다 돌았을때 모든 result가 0이 되어 있다면 가능하다.
        if (team == 5) {
            int winSum = 0;
            int drawSum = 0;
            int loseSum = 0;
            
            for (int i = 0; i < 6; i++) {
                winSum += gameResult[i][0];
                drawSum += gameResult[i][1];
                loseSum += gameResult[i][2];
            }
            
            if (winSum ==0 && loseSum ==0 && drawSum== 0) {
                possible = 1;
            }
            return;
        }
        
        if (next == 6) {
            solution(team + 1, team + 2);
            return;
        }
        
        
        //2. next 팀과 경기를 했을때 이기는 경우, 지는경우 , 비기는 경우를 모두 고려한다.
        // 팀 team이 팀 next에게 승리하는 경우
        if (gameResult[team][0] > 0 && gameResult[next][2] > 0) {
            gameResult[team][0]--;
            gameResult[next][2]--;
            solution(team, next + 1);
            gameResult[team][0]++;
            gameResult[next][2]++;
        }
        
        // 팀 team과 팀 next가 비기는 경우
        if (gameResult[team][1] > 0 && gameResult[next][1] > 0) {
            gameResult[team][1]--;
            gameResult[next][1]--;
            solution(team, next + 1);
            gameResult[team][1]++;
            gameResult[next][1]++;
        }
        
        // 팀 team이 팀 next에게 패배하는 경우
        if (gameResult[team][2] > 0 && gameResult[next][0] > 0) {
            gameResult[team][2]--;
            gameResult[next][0]--;
            solution(team, next + 1);
            gameResult[team][2]++;
            gameResult[next][0]++;
        }
    }
    
    public static void main(String[] args) throws NumberFormatException, IOException {
    	//System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	for(int index=0; index<4; index++) {
    		
    		init(br);
    		
    		//1. 팀 0과 1을 비교하는 것부터 시작한다.
    		solution(0, 1);
    		
    		// 결과 출력
    		System.out.print(possible + " ");
    	}
    }
}