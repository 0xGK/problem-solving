import java.io.*;
import java.util.*;
 
public class Main {
	static int N, M;
	static boolean[] visited;
	static int[] sequence;
	
    static void backtrack(int depth, StringBuilder sb) {
        if (depth == M) {
        	for(int i=0; i<M; i++) {
        		sb.append(sequence[i]).append(" ");
        	}
        	sb.append("\n");

            return;
        }

        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                sequence[depth] = i;
                backtrack(depth + 1, sb);
                visited[i] = false;
            }
        }
    }
    
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
    	StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        visited = new boolean[N+1];
        sequence = new int[M];
        
        backtrack(0, sb);
        System.out.println(sb);
        br.close();
    }
}