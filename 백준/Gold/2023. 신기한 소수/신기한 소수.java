import java.util.*;
import java.io.*;
/*
 * N <= 8
 * 각 자리수에 맞는 소스들을 찾아야 함..
 * dp 문제?
 * 왼쪽부터 각 자리가 모두 소수인 것들만 찾을 것임
 * 1자리부터 시작해서 소수인 경우에만 두 번재로 진행
 * ex) 1자리 소수 -> 2, 3, 5, 7
 * 
 * [소수판별]
 * N이 소수인지 알려면 2부터 sqrt(N)까지 값으로 나눈 나머지가 0인지 확인하면 됨
 * 
 * [아리스토테네스의 체] - (X)
 * 1 ~ N 값을 먼저 배열로 저장
 * for i in range(2, N)
 * 	if(visited[i]) continue;	 
 *  i를 제외한, i의 배수들 전부 방문 처리
 * 남는 애들이 전부 소수임.
 *문제에서 구하라는 것에 N은 8이니까 전부 구하는 것은 비효율 적임 
 *
 * 
 */
public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static int N;
	
	public static boolean isPrime(int number) {
		for(int i=2; i<= (int)Math.sqrt(number); i++) {
			if(number%i==0) {
				return false;
			}
		}
		if(number==1) return false;
		return true;
	}
	
	public static void init() throws IOException{
		N = Integer.parseInt(br.readLine());
	}
	
	public static void dfs(int selectedCnt, int number) {
		// terminate
		if(selectedCnt==N) {
			sb.append(number).append('\n');
			return;
		}
		
		// generate
		int n = number*10;
		for(int nextNumber = number*10+1; nextNumber<number*10+10; nextNumber++) {
			if(isPrime(nextNumber)) {
				dfs(selectedCnt+1, nextNumber);
			}
		}
	}
	
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
         
    	init();
    	dfs(0, 0);
    	
    	System.out.println(sb);
    }
}
