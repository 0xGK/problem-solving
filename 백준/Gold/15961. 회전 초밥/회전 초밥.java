import java.util.*;
import java.io.*;

/*
 * 1. 연속으로 k개 접시 선택시 -> 할인
 * 2. 쿠폰 사용 -> 초밥 하나 무료 제공. (벨트 위에 없다면, 만들어서 제공)
 * 
 * 목표: 가능한 다양한 초밥을 먹자!
 * 
 * 
 * 
 */

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int beltSize, sushiSize, K, coupon;	// N: 벨트 위 접시 수, d: 초밥의 가짓수, k: 연속해서 먹는 수, c: 쿠폰 번호 
	static int[] sushiList;
	static int[] backupSushiList;
	static int[] countList;
	static int sushiCnt;
	static int maxSushiCnt;
	
	public static void solve() throws IOException{
		st = new StringTokenizer(br.readLine());
		beltSize = Integer.parseInt(st.nextToken());
		sushiSize = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		coupon = Integer.parseInt(st.nextToken());
		countList = new int[sushiSize+1];
		backupSushiList = new int[K];
		sushiList = new int[K]; // 배열 엄청 큼..!!
		
		// 초기 카운트 먼저 설정
		sushiCnt=0;
		for(int idx=0; idx<K; idx++) {
			backupSushiList[idx] = sushiList[idx] = Integer.parseInt(br.readLine());
			if(countList[sushiList[idx]]++ == 0) {
				sushiCnt++;
			}
		}		
		maxSushiCnt = countList[coupon]==0 ? sushiCnt+1 : sushiCnt;
		
		
		for(int idx=0; idx<beltSize-K; idx++) {
			// out
			int outSushi = sushiList[idx%K];
			if(--countList[outSushi] == 0) {
				sushiCnt--;
			}
			
			// in
			int inSushi = Integer.parseInt(br.readLine());
			sushiList[idx%K] = inSushi;
			if(countList[inSushi]++ == 0) {
				sushiCnt++;
			}
			int totalCnt = countList[coupon]==0 ? sushiCnt+1 : sushiCnt;
			maxSushiCnt = maxSushiCnt < totalCnt ? totalCnt : maxSushiCnt;
		}
		
		for(int idx=beltSize-K, backIdx=0; idx<beltSize; idx++, backIdx++) {
			// out
			int outSushi = sushiList[idx%K];
			if(--countList[outSushi] == 0) {
				sushiCnt--;
			}
			
			// in
			int inSushi = backupSushiList[backIdx];
			if(countList[inSushi]++ == 0) {
				sushiCnt++;
			}
			int totalCnt = countList[coupon]==0 ? sushiCnt+1 : sushiCnt;
			maxSushiCnt = maxSushiCnt < totalCnt ? totalCnt : maxSushiCnt;
		}		
	}
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
    	solve();
    	System.out.println(maxSushiCnt);
    	
    }
}
