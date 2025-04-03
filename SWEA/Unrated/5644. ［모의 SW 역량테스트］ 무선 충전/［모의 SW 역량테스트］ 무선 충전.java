//package swea;

import java.io.*;
import java.util.*;
/*
 * BC의 개수 <=8
 * => boolean[][] visited를 8개 두자.
 * => 각 BC의 영역에 해당하는지 확인하는 용도.
 * => 사용자의 위치에서 해당 BC를 사용 가능한지 O(1)에 확인하기 위함
 * 
 * 1. charger를 power 순으로 내림차순 정렬
 * 	1.1. visited에 접근할 순서가 power가 큰 순으로 가능하게
 * 2. 각 경로의 위치에서 가능한 charger 수집
 * 	2.1. 수집한 charger에 대해서 power가 큰 순으로 하나씩 선택
 * 	2.2. userA와 userB 중에 누가 선택할지 비교
 * 
 */
public class Solution {
	// M: 사용자의 이동 정보
    // A: BC의 개수
    static int M, A;
    static int maxCharge;
    static boolean[][][] chargeArea;
    static int[][] dirs = {{0,0},{-1,0},{0,1},{1,0},{0,-1}};
    static int[] userA;
    static int[] userB;
    static int[] traceA;
    static int[] traceB;
    static boolean[] visitedA, visitedB;
    
    static StringTokenizer st; 
     
    
    // power 순으로 내림차순 정렬하기 위해 Comparable 상속
    static class Charger implements Comparable<Charger>{
        int row;
        int col;
        int range;
        int power;
         
        public Charger(int row, int col, int range, int power) {
            this.row = row;
            this.col = col;
            this.range = range;
            this.power = power;
        }
 
        @Override
        public int compareTo(Charger o) {
            return o.power - this.power;
        }
    }
    static Charger[] chargerList;
     
    public static void init(BufferedReader br) throws Exception{
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        A = Integer.parseInt(st.nextToken());
         
        maxCharge=0;
        userA = new int[] {0, 0};
        userB = new int[] {9, 9};
         
        //사용자 A의 이동 정보
        traceA = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int index=0; index<M; index++) {
            traceA[index] = Integer.parseInt(st.nextToken());
        }
         
        //사용자 B의 이동 정보      
        traceB = new int[M];
        st = new StringTokenizer(br.readLine());
        for(int index=0; index<M; index++) {
            traceB[index] = Integer.parseInt(st.nextToken());
        }
         
        chargeArea = new boolean[A][10][10];
        chargerList = new Charger[A];
        visitedA = new boolean[A];
        visitedB = new boolean[A];
         
        // charger 입력
        for(int BCIndex=0; BCIndex<A; BCIndex++) {
            st = new StringTokenizer(br.readLine());
            int col = Integer.parseInt(st.nextToken()) - 1;
            int row = Integer.parseInt(st.nextToken()) - 1;
            int range = Integer.parseInt(st.nextToken());
            int power = Integer.parseInt(st.nextToken());
            chargerList[BCIndex] = new Charger(row,col,range,power);
        }
         
        Arrays.sort(chargerList);
         
        //배터리 정보
        // make charge area
        for(int BCIndex=0; BCIndex<A; BCIndex++) {
            int row = chargerList[BCIndex].row;
            int col = chargerList[BCIndex].col;
            int range = chargerList[BCIndex].range;
            int step=0;
            for(int rowIndex=row-range; rowIndex<=row+range; rowIndex++) {
                int gap = range - Math.abs(range-step);
                for(int colIndex=col-gap; colIndex<=col+gap; colIndex++) {
                    if(rowIndex<0 || rowIndex>=10 || colIndex<0 || colIndex>=10) continue;
                    chargeArea[BCIndex][rowIndex][colIndex]=true;
                }
                step++;
            }
        }
    }
     
     
    public static void playChargeGame() {
        for(int BCIndex=0; BCIndex<A; BCIndex++) {
            visitedA[BCIndex] = chargeArea[BCIndex][userA[0]][userA[1]];
            visitedB[BCIndex] = chargeArea[BCIndex][userB[0]][userB[1]]; 
        }
         
        boolean isSelectA = false;
        boolean isSelectB = false;
        for(int BCIndex=0; BCIndex<A; BCIndex++) {
            // 선택이 끝난 경우
            if(isSelectA && isSelectB) break;
             
            // 둘다 같은 것을 가졌을 경우 누가 가져갈 것인가?
            if(!isSelectA && !isSelectB && visitedA[BCIndex] && visitedB[BCIndex]) {
                int nextPowerA = 0;
                int nextPowerB = 0;
                for(int nextBCIndex=BCIndex+1; nextBCIndex<A; nextBCIndex++) {
                    if(visitedA[nextBCIndex]) {
                        nextPowerA=chargerList[nextBCIndex].power;
                        break;
                    }
                }
                for(int nextBCIndex=BCIndex+1; nextBCIndex<A; nextBCIndex++) {
                    if(visitedB[nextBCIndex]) {
                        nextPowerB=chargerList[nextBCIndex].power;
                        break;
                    }
                }
                if(nextPowerA<nextPowerB) {
                    maxCharge+=chargerList[BCIndex].power+nextPowerB;
                }else {
                    maxCharge+=chargerList[BCIndex].power+nextPowerA;
                }
                break;
                 
            }
            // A가 아직 고르지 않은 경우
            else if(!isSelectA && visitedA[BCIndex]) {
                isSelectA = true;
                maxCharge+=chargerList[BCIndex].power;
            }
            // B가 아직 고르지 않은 경우
            else if(!isSelectB && visitedB[BCIndex]) {
                isSelectB = true;
                maxCharge+=chargerList[BCIndex].power;
            }
        }
    }
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
         
          
        int T = Integer.parseInt(br.readLine());
        for(int testCase=1; testCase<=T; ++testCase) {
            sb.append("#").append(testCase).append(" ");
            init(br);
            playChargeGame();
            for(int playCount=0; playCount<M; playCount++) {
                int direction = traceA[playCount];
                userA[0] += dirs[direction][0];
                userA[1] += dirs[direction][1];
                 
                direction = traceB[playCount];
                userB[0] += dirs[direction][0];
                userB[1] += dirs[direction][1];
                 
                playChargeGame();
            }
            sb.append(maxCharge).append('\n');
             
        }
        System.out.println(sb);
          
    }
}