import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N, L;
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		int[] heightArray = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			heightArray[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(heightArray);
		
		for(int i=0; i<N; i++) {
			if(heightArray[i] <= L) {
				L++;
				continue;
			}else {
				break;
			}
		}
		System.out.println(L);
		
	}
	
}
