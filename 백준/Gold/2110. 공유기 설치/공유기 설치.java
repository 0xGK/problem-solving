import java.io.*;
import java.util.*;

/*
 * 두 경유기 사이의 최대 거리를 출력
 * 이때, 최대 거리가 1이상 1000000000 이하가 될 것임
 * 
 * 
 */
public class Main {
	static int N, C;
	static int[] houses;
	
	static BufferedReader br;
	
	public static void init() throws IOException{
		StringTokenizer st = new StringTokenizer(br.readLine()); 
		N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		houses = new int[N];
		for(int idx=0; idx<N; idx++) {
			houses[idx]=Integer.parseInt(br.readLine());
		}
		
		Arrays.sort(houses);
	}
	

	public static int getInstallCnt(int distance) {
		int count = 1;
		int prevLocation = houses[0];
		
		for(int i=1; i<houses.length; i++) {
			int curLocation = houses[i];
			
			if(curLocation - prevLocation >= distance) {
				count++;
				prevLocation = curLocation;
			}
		}
		
		return count;
	}
	
	public static void solve() {
		int left = 1;
		int right = houses[N-1] - houses[0]+1;
		int mid=0;
		
		// lowerbound: 목표 값보다 같거나 큰 값이 처음 나오는 곳
		while(left<right) {
			mid = (left+right)/2;
			// 목표 카운트가 부족한 경우, 거리를 줄여야 함.
			// 줄여야 하는데
			if(getInstallCnt(mid)<C) {
				right = mid;
			}
			// 목표 카운트랑 동일한 경우에도 계속 진행.
			// 최대한 큰 distance를 찾아야 하므로 증가시키기
			else {
				left = mid+1;
			}
		}
		
		System.out.println(right-1);
	}
	
	public static void main(String[] args) throws IOException{
		//System.setIn(new FileInputStream("input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		init();
		solve();
		
		
	}
}
