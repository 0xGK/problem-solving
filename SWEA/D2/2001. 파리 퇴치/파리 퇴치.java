import java.io.*;
import java.util.*;

/*
 * @author GK
 * 
 * 문제 치환: N * N 배열에서 M * M 영역의 값의 합의 최댓값 구하기
 * 1. M * M 영역 내부 값의 합 구하기
 * 		-> 누적합을 미리  구해놓고, rowIndex와 colIndex 조정하면서 구하기.
 * 		-> cumulativeSequence(endColIndex) - cumulativeSequence(startColIndex-1): startColIndex부터 endColIndex까지 수의 합
 * 2. N * N 배열 전체 순회하면서, maxScore갱신
 * 
 */
public class Solution {
	static int N, M;
	static int[][] cumulativeSequence;	// 누적합
	static int[][] map;		// 전체 맵
	static int maxScore;	// 최대 점수
	
	// 초기화
	public static void init() {
		cumulativeSequence = new int[N+1][N+1];
		map = new int[N+1][N+1];
		maxScore=0;
	}
	
	// 주어진 좌표에서 M*M 영역 내부 수의 합
	public static int getScore(int startRowIndex, int startColIndex) {
		int endRowIndex = startRowIndex + M - 1;
		int endColIndex = startColIndex + M - 1;
		int score=0;
		
		for(int rowIndex=startRowIndex; rowIndex<=endRowIndex; rowIndex++) {
			score += cumulativeSequence[rowIndex][endColIndex] - cumulativeSequence[rowIndex][startColIndex-1];
		}
		return score;
	}
    public static void main(String[] args) throws Exception {
    	//System.setIn(new FileInputStream("input.txt"));
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	StringBuilder sb = new StringBuilder();
    	StringTokenizer st;
    	
    	int T = Integer.parseInt(br.readLine());
        for(int testCase=1; testCase<=T; ++testCase) {
            sb.append("#").append(testCase).append(" ");
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            init();
            for(int rowIndex=1; rowIndex<=N; rowIndex++) {
            	st = new StringTokenizer(br.readLine());
            	for(int colIndex=1; colIndex<=N; colIndex++) {
            		// 누적합 저장
            		cumulativeSequence[rowIndex][colIndex] = cumulativeSequence[rowIndex][colIndex-1] + Integer.parseInt(st.nextToken());
            	}
            }
			for(int rowIndex=1; rowIndex<=N-M+1; rowIndex++) {
				for(int colIndex=1; colIndex<=N-M+1; colIndex++) {
					int score = getScore(rowIndex, colIndex);
					maxScore = score > maxScore ? score : maxScore;
				}
			}

            sb.append(maxScore).append("\n");
            
        }
		System.out.println(sb);
	}
}
