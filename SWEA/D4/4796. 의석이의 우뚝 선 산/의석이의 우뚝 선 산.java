import java.io.*;
import java.util.*;

/*
 * @author GK
 * 
 * 주의사항
 * 	- 두 지점이 같은 높이를 가지는 경우는 없다.
 * 		-> 높이 비교할 때, 등호는 신경쓰지 않아도 됨
 * 	- N은 3이상
 * 
 * 우뚝선 산: 증가->감소 역전이 일어나는 곳
 * 
 * 이미지화:
 *	\/ : 0
 *	/\ : 1
 *  \/\ : 1
 *  /\/ : 1
 *	/\/\ : 2
 *
 *	1. 좌측부터 시작하고, 증가와 감소를 탐지해야함.
 *		-> 증가 or 감소에 대한 flag를 두고, 초기 값은 감소로 설정
 *		-> 증가->감소 역전이 발생할 때마다 카운트
 *
 *
 *	2. 이제 우뚝선 산이 존재할 수 있는 구간의 개수를 출력해야함

 */


public class Solution{
	static final int MAX_N = 50050;
	static int N;
	static int[] mountainHeight = new int[50050];
	static long peakRangeCount;

	public static void getNumberOfPeakRange() {
		peakRangeCount = 0;
		
		// (flag = true) := 증가
		// (flag = false) := 감소
		// 증가를 탐지해야하므로, 초기에는 감소로 설정
		boolean flag = false;
		int upCount = 0;
		int downCount = 0;
		
		for(int index=0; index<N-1; index++) {
			// 감소인 경우
			if(flag == false) {
				// 감소였는데, 증가가 되는 경우
				if(mountainHeight[index] < mountainHeight[index+1]) {
					flag = true;
					
					// calculate
					peakRangeCount += upCount * downCount;

					// 다시 카운트 시작
					upCount = 1;
					downCount = 0;			
				}
				// 여전히 감소인 경우
				else {
					downCount++;
				}
			}
			// 증가인 경우
			else {
				// 증가였는데, 감소가 되는 경우
				//	-> peak!!
				if(mountainHeight[index] > mountainHeight[index+1]) {
					flag = false;
					downCount++;
				}
				//여전히 증가인 경우
				else {
					upCount++;
				}
			}

		}
		
		// calculate
		peakRangeCount += upCount * downCount;
		
	}
	
	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer st = new StreamTokenizer(br);
//		StreamTokenizer st = new StreamTokenizer(new InputStreamReader(System.in));
//		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
//		int T = Integer.parseInt(br.readLine().trim());
//		int T = sc.nextInt();
		st.nextToken();
		int T = (int) st.nval;
		
		
		for(int testCase=1; testCase<=T; ++testCase){
			sb.append("#").append(testCase).append(" ");
//			N = Integer.parseInt(br.readLine().trim());
//			N = sc.nextInt();
			st.nextToken();
			N = (int) st.nval;
//			st = new StringTokenizer(br.readLine().trim());
			for(int index=0; index<N; index++) {
//				mountainHeight[index] = Integer.parseInt(st.nextToken());
//				mountainHeight[index] = sc.nextInt();
				st.nextToken();
				mountainHeight[index] = (int) st.nval;
			}
			getNumberOfPeakRange();
			sb.append(peakRangeCount).append("\n");
			
		}
		System.out.println(sb);
//		sc.close();
	}
}
