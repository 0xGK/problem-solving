import java.io.*;
import java.util.*;

/*
 * @author GK
 * 
 * 1. nextPermutation logic 사용해서 모든 조합 계산
 * 	-> 000001 => 000010 => 000100 => ...  
 * 	-> 000011 => 000101 => 000110 => ...
 * 	-> ...
 * 
 * 	1.1. swap과 reverse 메소드 유지하되, 내부 로직 단순화 가능
 * 		-> 0과 1 사이의 변화만 있음
 * 
 * 2. 각 순열에서 칼로리와 선호도 계산하여 최대값 갱신
 * 
 * 
 */
public class Solution{

	static int numberOfIngredient;
	static int maxCalorie;
	static int maxPreference;
	static int[] preferenceArray;
	static int[] calorieArray;
	static int[] selectedArray;

	static void init(){
		preferenceArray = new int[numberOfIngredient];
		calorieArray = new int[numberOfIngredient];
		selectedArray = new int[numberOfIngredient];
		maxPreference = Integer.MIN_VALUE;
	}
	static void swap(int leftIdx, int rightIdx) {
//		int temp = selectedArray[leftIdx];
//		selectedArray[leftIdx] = selectedArray[rightIdx];
//		selectedArray[rightIdx] = temp;
		
		// 로직상 단순화 가능
		selectedArray[leftIdx]=1;
		selectedArray[rightIdx]=0;
	}
	static void reverse(int start, int end, int cnt) {		
//		while(start<end) {
//			swap(start, end);
//			start++;
//			end--;
//		}
		
		// 로직상 단순화 가능
		for(int idx = start; idx<=cnt; idx++) {
			selectedArray[idx]=0;
		}
		for(int idx = cnt+1; idx<=end; idx++) {
			selectedArray[idx]=1;
		}
	}
	static boolean hasNext() {
		int i = numberOfIngredient - 1;

		while (i > 0 && selectedArray[i] <= selectedArray[i - 1]) {
			i--;
		}

		if (i == 0)
			return false;

		int j = numberOfIngredient - 1;

		while (selectedArray[i-1] >= selectedArray[j]) {
			j--;
		}

		swap(i-1, j);

		reverse(i, numberOfIngredient - 1, j);
		
		return true;
	}



	static void calcPreference() {
		int totalCalorie = 0;
		int totalPreference = 0;
		for (int idx = 0; idx < numberOfIngredient; idx++) {
			if (selectedArray[idx] == 1) {
				totalCalorie += calorieArray[idx];
				if (totalCalorie > maxCalorie) {
					return;
				}
				totalPreference += preferenceArray[idx];
			}
		}

		maxPreference = maxPreference < totalPreference ? totalPreference : maxPreference; 
	}

	public static void calcAllPossibleCombination() {
		for (int selectedCount = 1; selectedCount <= numberOfIngredient; selectedCount++) {
			selectedArray = new int[numberOfIngredient];
			for (int idx = 0; idx < selectedCount; idx++) {
				selectedArray[numberOfIngredient - 1 - idx] = 1;
			}

			do {
				calcPreference();
			} while (hasNext());
		}
	}
	
	public static void main(String[] args) throws Exception {
		//System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int testCase = 1; testCase <= T; testCase++) {
			sb.append("#").append(testCase).append(" ");
			StringTokenizer st = new StringTokenizer(br.readLine());
			numberOfIngredient = Integer.parseInt(st.nextToken());
			maxCalorie = Integer.parseInt(st.nextToken());

			init();

			for (int idx = 0; idx < numberOfIngredient; idx++) {
				st = new StringTokenizer(br.readLine());
				preferenceArray[idx] = Integer.parseInt(st.nextToken());
				calorieArray[idx] = Integer.parseInt(st.nextToken());
			}

			
			calcAllPossibleCombination();

			sb.append(maxPreference).append("\n");
		}

		System.out.println(sb);
		br.close();
	}
}