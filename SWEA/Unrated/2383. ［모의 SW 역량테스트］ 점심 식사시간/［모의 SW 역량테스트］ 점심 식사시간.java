import java.io.*;
import java.util.*;
/*
 * 
 * N <= 10
 * 사람의 수 <= 10
 * 계단의 길이 <= 10
 * 
 * [주의]
 * 계단 입구 = 2개로 고정
 * 
 * 이동 완료 시간: 모든 사람들이 계단을 내려가 아래층으로 이동 완료 시간
 * 
 * 계단 입구까지 이동 시간: 맨해튼 거리
 * 
 * 계단을 내려가는 시간: 1분 후 아래칸으로 이동. 
 * 	계단 위에는 동시에 최대 3명까지만 올라가 있을 수 있음.
 * 	이미 계들을 3명이 내려가고 있는 경우 그 중 한 명이 계단이 완전히 내려갈 때까지 대기
 * 	계단마다 길이 k가 주어지며 계단에 올라간 후 완전히 내려가는데 k분 걸림.
 * 
 * 최소 이동 완료 시간을 구하랏.
 * 
 * 모든 것은 정해져 있다...
 * 조합으로 풀자.
 * 
 * 
 * 
 * 
 * 
 * 
 */
public class Solution  {
	static BufferedReader br;
    static StringTokenizer st;
     
    static int N, personSize;
    static int[][] board;
    static int totalMinTime;
    static int[] stairTime;
     
    static int[][] timeToStairs;
    static int[] selectedStairIdx;  // 0 또는 1이 들어가겠죠?
     
    static List<Integer>[] stairQueue;
    static Queue<Integer> movingQueue;
     
    static int minTime;
     
    public static int getManhattanDistance(int[] first, int[] second) {
        return Math.abs(first[0]-second[0])+Math.abs(first[1]-second[1]);
    }
    public static void init() throws IOException{
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        List<int[]> personLocationList = new ArrayList<>();
        int[][] stairLocationList = new int[2][];
        stairTime = new int[2];
        int stairCnt=0;
        for(int rowIdx=0; rowIdx<N; rowIdx++) {
            st = new StringTokenizer(br.readLine());
            for(int colIdx=0; colIdx<N; colIdx++) {
                board[rowIdx][colIdx] = Integer.parseInt(st.nextToken());
                if(board[rowIdx][colIdx]==1) {
                    personLocationList.add(new int[] {rowIdx, colIdx});
                }
                else if(board[rowIdx][colIdx]>1) {
                    stairTime[stairCnt] = board[rowIdx][colIdx];
                    stairLocationList[stairCnt++] = new int[] {rowIdx, colIdx};
                }
            }
        }
        personSize = personLocationList.size();
         
        selectedStairIdx = new int[personSize];
        timeToStairs = new int[2][personSize];
         
        int personCnt=0;
        for(int[] personLocation : personLocationList) {
            for(int stairIdx=0; stairIdx<2; stairIdx++) {
                timeToStairs[stairIdx][personCnt] = getManhattanDistance(stairLocationList[stairIdx], personLocation);
            }
            personCnt++;
        }
        stairQueue = new ArrayList[2];
        minTime=Integer.MAX_VALUE;
    }
     
    public static void playStairGame() {
        stairQueue[0] = new ArrayList<>();
        stairQueue[1] = new ArrayList<>();
         
        for(int personIdx=0; personIdx<personSize; personIdx++) {
            int stairIdx = selectedStairIdx[personIdx];
            stairQueue[stairIdx].add(timeToStairs[stairIdx][personIdx]);
        }
        Collections.sort(stairQueue[0]);
        Collections.sort(stairQueue[1]);
         
        int maxTime = 0;
        for(int stairIdx=0; stairIdx<2; stairIdx++) {
            movingQueue = new ArrayDeque<>();
            int lastTime = 0;
             
            // 먼저 가능한 만큼 추가
            // 대기 1분 추가
            int personIdx = 0;
            for(;personIdx<3 && personIdx < stairQueue[stairIdx].size(); personIdx++) {
                movingQueue.add(stairQueue[stairIdx].get(personIdx) + stairTime[stairIdx] + 1);
            }
             
            while(personIdx < stairQueue[stairIdx].size()) {
                int curTime = movingQueue.poll();
                int nxtTime = stairQueue[stairIdx].get(personIdx++);
                 
                // 바로 들어가면 되는 경우
                // nxt가 대기 중이었던 경우임.
                if(curTime > nxtTime) {
                    movingQueue.add(curTime+stairTime[stairIdx]);
                }
                // 나중에 들어와야 하는 경우
                // 대기 1분 추가
                else {
                    movingQueue.add(nxtTime + stairTime[stairIdx]+1);
                }
            }
             
            while(!movingQueue.isEmpty()) {
                lastTime = movingQueue.poll();
            }
            maxTime = maxTime < lastTime ? lastTime : maxTime;
        }
        minTime = minTime > maxTime ? maxTime : minTime;
         
         
    }
     
    public static void combination(int selectedCnt) {
        if(selectedCnt==personSize) {
            playStairGame();
            return;
        }
         
        for(int stairIdx=0; stairIdx<2; stairIdx++) {
            selectedStairIdx[selectedCnt] = stairIdx;
            combination(selectedCnt+1);
        }
    }
     
     
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
         
        int T = Integer.parseInt(br.readLine());
        for(int testCase=1; testCase<=T; ++testCase) {
            init();
            combination(0);
            sb.append('#').append(testCase).append(' ').append(minTime).append('\n');
             
        }
        System.out.println(sb);
          
    }
}
