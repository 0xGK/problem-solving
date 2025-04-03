import java.io.*;
import java.util.*;

/*
 * 주의 사항
 * 		- 농장 크기는 항상 홀수
 * 		- 수확은 항상 정사각형 마름모 형태
 * 
 * 
 * 1. 배열의 rowIndex가 증가함에 따라 계산 영역이 달라짐
 * 2. N=5일 경우, 계산 영역은 1 2 3 2 1
 * 		- 가운데 영역은 항상 계산할 예정
 * 			-> 즉, 0 1 2 1 0 이라는 숫자 배열을 만들어 내야함
 * 			-> 만들어낸 숫자를 gap이라고 한다면, half-gap부터 half+gap까지 숫자들을 합산하면 됨
 * 
 * Example
 * (N:= 5, half:= N/2 = 2)
 * 
 * rowIndex	|	abs(rowIndex - half)	|	half - abs(rowIndex - half)
 * 		0	| 			2				|			0
 * 		1	| 			1				|			1
 * 		2	| 			0				|			2
 * 		3	| 			1				|			1
 * 		4	| 			2				|			0
 * 
 */


public class Solution {
	static int boardSize, half, sum;
	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int testCase=1; testCase<=T; ++testCase) {
			sb.append("#").append(testCase).append(" ");
			boardSize = Integer.parseInt(br.readLine());
			half = boardSize/2;
			sum=0;
			for(int rowIndex=0; rowIndex<boardSize; ++rowIndex) {
				String sequence = br.readLine();
				int gap = half - Math.abs(rowIndex-half);
				for(int colIndex = half-gap; colIndex<=half+gap; ++colIndex) {
					sum+=(sequence.charAt(colIndex) - '0');
				}
			}
			sb.append(sum).append("\n");
		}
		System.out.println(sb);
		
				
	}
}
