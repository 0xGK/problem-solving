import java.io.*;
import java.util.*;


/*
 * 회사에서 출발하여 N명의 고객을 모두 방문하고 집으로 돌아오는 최단거리 경로 찾기
 * 출력값: 최단거리
 * 2 <= N <= 10
 * 
 */
public class Solution {
	static int N, minDistance;
	static StringTokenizer st;
	static int[] xList;
	static int[] yList;
	static boolean[] visited;
	static int[][] distance;
	static int homeX, homeY, companyX, companyY;
	
	public static int getDistance(int firstIndex, int secondIndex) {
		return Math.abs(xList[firstIndex]-xList[secondIndex])+Math.abs(yList[firstIndex]-yList[secondIndex]);
	}
	
	public static void init(BufferedReader br) throws Exception {
		minDistance = Integer.MAX_VALUE;
		N = Integer.parseInt(br.readLine());
		
		visited = new boolean[N+2];
		xList = new int[N+2];
		yList = new int[N+2];
		
		
		st = new StringTokenizer(br.readLine());
		
		// Company
		xList[0] = Integer.parseInt(st.nextToken());
		yList[0] = Integer.parseInt(st.nextToken());
		
		// Home
		xList[N+1] = Integer.parseInt(st.nextToken());
		yList[N+1] = Integer.parseInt(st.nextToken());
		
		for(int index=1; index<N+1; index++) {
			xList[index] = Integer.parseInt(st.nextToken());
			yList[index] = Integer.parseInt(st.nextToken());
		}
		
		distance = new int[N+2][N+2];
		// make distance
		for(int firstIndex=0; firstIndex<N+2; firstIndex++) {
			for(int secondIndex=0; secondIndex<N+2; secondIndex++) {
				distance[secondIndex][firstIndex] = distance[firstIndex][secondIndex] = getDistance(firstIndex, secondIndex);
			}
		}
		
	}
		
	public static void backtrack(int elementIndex, int selectedIndex, int curDistance) {
		// prune
		if(curDistance>=minDistance) {
			return;
		}
		
		// terminate
		if(elementIndex==N) {
			curDistance+=distance[selectedIndex][N+1];
			minDistance = minDistance > curDistance ? curDistance : minDistance;
			return;
		}
		
		// generate new branch
		for(int nextIndex=1; nextIndex<N+1; nextIndex++) {
			if(visited[nextIndex]) continue;
			visited[nextIndex]=true;
			backtrack(elementIndex+1, nextIndex, curDistance+distance[selectedIndex][nextIndex]);
			visited[nextIndex]=false;
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
            backtrack(0, 0, 0);
            sb.append(minDistance).append('\n');
        }
        System.out.println(sb);
         
    }
}