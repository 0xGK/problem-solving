import java.util.*;
import java.io.*;

/*
 *	@author GK
 *
 * 문제: 자연수 N과 M이 주어졌을 때, 1부터 N까지의 자연수 중 중복 없고 순서 있게 M개를 고른 수열
 * 	-> 치환:  n개의 자연수 중 m개를 순서 없이 뽑는 경우의 수 = nCm
 * 			
 * 백트레킹으로 접근
 * 		1. next depth로 넘어가기 전에 fix된 서열값 저장
 * 		2. 고른 수열은 오름차순
 * 			-> 순서를 fix
 * 			-> startIndex := startIndex+1 이런 식으로 사용한 index 고정
 * 		3. 마지막 depth에서 fixed 서열을 출력 버퍼에 저장
 */
public class Main {
	static int N, M;
	static boolean[] visited;
	static int[] sequence;
	static StringBuilder sb;
	
    static void combination(int depth, int startIndex) {
        if (depth == M) {
        	for(int elementIndex=0; elementIndex<M; elementIndex++) {
        		sb.append(sequence[elementIndex]).append(" ");
        	}
        	sb.append("\n");
            return;
        }

        for (int elementIndex = startIndex; elementIndex <= N; elementIndex++) {
        	sequence[depth] = elementIndex;
        	combination(depth + 1, elementIndex + 1);
        }
    }
    
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
    	sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        visited = new boolean[N+1];
        sequence = new int[M];
        
        combination(0, 1);
        System.out.println(sb);
        br.close();
    }
}