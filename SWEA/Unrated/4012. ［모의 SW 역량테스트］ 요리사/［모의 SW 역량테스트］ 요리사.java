import java.io.*;
import java.util.*;


/*
 * @author: GK
 * 
 * 문제는 식재료를 어떤 식으로 나눌 것인지?
 * 	-> 경우의 수 nC(n/2)
 * 	-> 절반을 뽑으면 나머지 절반은 결정 됨. 점수 합산 loop을 한번 더
 * 	-> 최종 두 점수 합산의 차이가 최소가 되는 경우 찾기.
 * 
 * 계산 단순화
 * nCn/2: 결국 짝수임. 이중에서 또 절반만 결정하면, 나머지는 자동으로 정해지는 방식.
 * 	-> 한쪽 만들어 지면, 나머지 index는 생성 가능
 * 
 * ex) N:=8
 * 식재료: N/2:=4
 * 	-> 4개의 식재료가 있고, index가 4개가 있음.
 * 	-> index가 정해지면 2중 for loop으로 시너지를 합산.
 * 
 */



public class Solution {
	static int N, minTaste;
	static int[][] synergy;
	static int[] selectedArray;
	static int[] unSelectedArray;
	
	
	public static void init() {
		synergy = new int[N][N];
		selectedArray = new int[N/2];
		unSelectedArray = new int[N/2];
		minTaste = Integer.MAX_VALUE;
	}
	
	public static void makeUnSelectedArray() {
		int unSelectedIndex=0;
		int selectedIndex=0;
		for(int index=0; index<N; index++) {
			if(selectedIndex==N/2 ||  index != selectedArray[selectedIndex]) {
				unSelectedArray[unSelectedIndex++]=index;
			}else {
				selectedIndex++;
			}
			
		}
	}
	
	public static void combination(int elementIndex, int selectedIndex) {
		if(elementIndex > N) {
			return;
		}
		
		if(selectedIndex == N/2) {
			makeUnSelectedArray();
			int selectedTaste = getTaste(selectedArray);
			int unSelectedTaste = getTaste(unSelectedArray);
			
			int tempTaste = Math.abs(selectedTaste - unSelectedTaste);
			minTaste = minTaste > tempTaste ? tempTaste : minTaste;
			return;
		}
		
		selectedArray[selectedIndex] = elementIndex;
		combination(elementIndex+1, selectedIndex+1);
		combination(elementIndex+1, selectedIndex);
	}
	
	public static int getTaste(int[] indexArray) {
		int selectedTotalTaste=0;
		
		for(int rowIndex : indexArray) {
			for(int colIndex : indexArray) {
				selectedTotalTaste+=synergy[rowIndex][colIndex];
			}
		}
				
		return selectedTotalTaste;
	}
	
	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int testCase = 1; testCase <= T; testCase++) {
			sb.append("#").append(testCase).append(" ");
			N = Integer.parseInt(br.readLine());
			init();
			
			for(int inputRowIndex=0; inputRowIndex<N; inputRowIndex++) {
				st = new StringTokenizer(br.readLine());
				for(int inputColIndex=0; inputColIndex<N; inputColIndex++) {
					synergy[inputRowIndex][inputColIndex] = Integer.parseInt(st.nextToken());
				}	
			}
			
			// 아이디어
			// nCn/2: 결국 짝수임. 이중에서 또 절반만 결정하면, 나머지는 자동으로 정해지는 방식.
			selectedArray[0]=0;
			combination(1, 1);
			
//			combination(0, 0);			
			sb.append(minTaste).append("\n");
		}
		System.out.println(sb);
		
	}
}