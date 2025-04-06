import java.io.*;
import java.util.*;
/*
 * 고객만족도 설문지
 * 	1. 접수 창구번호 <= N
 * 	2. 정비 창구번호 <= M
 * 
 * step1. 접수
 * step2. 정비
 * 
 * 총 K명ㅇ ㅣ반문했고, 1번부터 고객번호 받음
 * 
 * 도착시간이 각각 다름.
 * 
 * 빈 창구가 있는 경우, 빈 접수 창구에 감.
 * 없다면, 생길 대까지 기다림
 * 
 * [접수 우선순위]
 * 여러 고객이 기다리고 있다면, 고객번호가 낮은 순서대로 우선순위 -> for loop으로 해결
 * 빈 창구가 여러 곳이라면 접수 창구 번호가 작은 곳으로 -> for loop으로 해결
 * 
 * [정비 우선순위]
 * 먼저 기다리는 고객이 우선 -> queue로 해결
 * 동시에 접수를 완료해서 넘어왔다면, 이용했던 접수 창구 번호가 작은 고객이 우선 -> 접수대에서 뺄 때, for loop으로 해결
 * 빈 창구가 여러 곳이라면 창구 번호가 작은 곳으로. -> for loop으로 해결
 * 
 * 
 * [출력]
 * 지갑 분실한 고객과 같은 접수 창구와 정비 창구를 이용한 고객 번호의 합. 없다면 -1 출력
 */
public class Solution {
	static BufferedReader br;
	static StringTokenizer st;
	
	static int receptionSize, repairSize, customerSize, receptionNumber, repairNumber;
	static int[] receptionTime, repairTime, customerTime;
	static Queue<Integer> waitingReception;
	static Queue<Customer> waitingRepair;
//	static List<Integer> resultList;
	static int totalCnt;
	static boolean[] isSameNumber;
	
	
	static class Customer{
		int customerId;
		int finishTime;
		public Customer(int customerId, int finishTime) {
			this.customerId = customerId;
			this.finishTime = finishTime;
		}
	}
	static Customer[] receptionList, repairList;
		
	public static void init() throws IOException{
		st = new StringTokenizer(br.readLine());
		receptionSize = Integer.parseInt(st.nextToken());
		repairSize = Integer.parseInt(st.nextToken());
		customerSize = Integer.parseInt(st.nextToken());
		receptionNumber = Integer.parseInt(st.nextToken());
		repairNumber = Integer.parseInt(st.nextToken());
		
		receptionTime = new int[receptionSize+1];
		st = new StringTokenizer(br.readLine());
		for(int idx=1; idx<=receptionSize; idx++) {
			receptionTime[idx] = Integer.parseInt(st.nextToken());
		}
		
		repairTime = new int[repairSize+1];
		st = new StringTokenizer(br.readLine());
		for(int idx=1; idx<=repairSize; idx++) {
			repairTime[idx] = Integer.parseInt(st.nextToken());
		}

		customerTime = new int[customerSize+1];
		st = new StringTokenizer(br.readLine());
		// 여기서 idx = 고객 번호
		for(int customerId=1; customerId<=customerSize; customerId++) {
			customerTime[customerId] = Integer.parseInt(st.nextToken());
		}
		
		waitingReception = new ArrayDeque<>();
		waitingRepair = new ArrayDeque<>();
		receptionList = new Customer[receptionSize+1];
		repairList = new Customer[repairSize+1];
		totalCnt = 0;
		isSameNumber = new boolean[customerSize+1];
	}
	public static void solve() {
		int time = 0;
		int customerId = 1;
		int outCnt=0;
		while(outCnt<customerSize) {
			// 접수대에 도착한 손님!
			for(; customerId <= customerSize; customerId++) {
				if(customerTime[customerId] != time) break;
				waitingReception.add(customerId);
			}
			
			// 접수대에서 빠져주실래용
			// receptionList[receptionIdx] : 손님이 빠지는 시간!
			for(int receptionIdx=1; receptionIdx<=receptionSize; receptionIdx++) {
				if(receptionList[receptionIdx] != null
						&& receptionList[receptionIdx].finishTime <= time) {
					waitingRepair.add(receptionList[receptionIdx]);
					receptionList[receptionIdx] = null;
				}
			}
			
			// 접수대로 들어오세용
			for(int receptionIdx=1; receptionIdx<=receptionSize; receptionIdx++) {
				// 빈 접수대가 있습니다.
				if(receptionList[receptionIdx] == null) {
					// 대기 인원이 있습니다.
					if(!waitingReception.isEmpty()) {
						int waitingCustomerId = waitingReception.poll(); 
						receptionList[receptionIdx] = new Customer(waitingCustomerId, time+receptionTime[receptionIdx]);
						
						// 결과 확인
						if(receptionIdx == receptionNumber) {
							isSameNumber[waitingCustomerId] = true;
						}
					}
					// 대기 인원이 없군요.
					else {
						break;
					}
				}
			}
			
			
			
			
			// 수리대에서 빠져주실래용
			for(int repairIdx=1; repairIdx<=repairSize; repairIdx++) {
				if(repairList[repairIdx] != null
						&& repairList[repairIdx].finishTime <= time) {
					repairList[repairIdx] = null;
					outCnt++;
				}
			}
			
			// 수리대로 들어오세용
			for(int repairIdx=1; repairIdx<=repairSize; repairIdx++) {
				// 빈 수리대가 있습니다.
				if(repairList[repairIdx] == null) {
					// 대기 인원이 있습니다.
					if(!waitingRepair.isEmpty()) {
						Customer waitingCustomer = waitingRepair.poll();
						waitingCustomer.finishTime = time+repairTime[repairIdx];
						repairList[repairIdx] = waitingCustomer;
						
						// 결과 확인
						if(repairIdx == repairNumber) {
							if(isSameNumber[waitingCustomer.customerId]) {
								totalCnt+=waitingCustomer.customerId;
							}
						}
					}
					// 대기 인원이 없군요.
					else {
						break;
					}
				}
			}
			
			time++;
		}
		if(totalCnt==0) {
			totalCnt=-1;
		}
	}
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
         
        int T = Integer.parseInt(br.readLine());
        for(int testCase=1; testCase<=T; ++testCase) {
        	init();
        	solve();
            sb.append('#').append(testCase).append(' ').append(totalCnt).append('\n');
        }
        System.out.println(sb);
         
    }
}