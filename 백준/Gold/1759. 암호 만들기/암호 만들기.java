import java.io.*;
import java.util.*;

/*
 * 암호: 서로 다른 L개의 알파벳 소문자들로 구성
 * 최소 한 개의 모음과 최소 두 개의 자음으로 구성
 * 암호는 오름차순
 * 문자열의 개수: C개
 * 이 중 적어도 한 개는 모음
 * 중복되는 것 없음
 * 
 * 1. 문제 치환: C개중 L개를 순서 없이 뽑는 모든 경우의 수. 이때, L개 중 모음은 적어도 1개.
 * 2. 조합을 구하는 백트레킹으로 구현
 * 		terminate: selectedCount == L
 * 	2-1. 입력 문자열을 사전 순으로 정렬한 다음, 순서대로(사전 순으로) 선택 유무 결정
 *  2-2. 모음 선택 cnt를 증감 시키고, terminate 조건에서, cnt==0인 경우는 skip
 * 
 */
public class Main {
	static int selectedSize, elementSize;
	static char[] sequence;
	static char[] selectedSequence;
	static int vowelCnt, consonantCnt;
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	public static void init() throws IOException{
		vowelCnt=consonantCnt=0;
		st = new StringTokenizer(br.readLine().trim());
		selectedSize = Integer.parseInt(st.nextToken());
		selectedSequence = new char[selectedSize];
		elementSize = Integer.parseInt(st.nextToken());
		sequence = new char[elementSize];
		
		st = new StringTokenizer(br.readLine().trim());
		for(int sequenceIdx=0; sequenceIdx<elementSize; sequenceIdx++) {
			sequence[sequenceIdx] = st.nextToken().charAt(0);
		}
		Arrays.sort(sequence);
	}
	public static boolean isVowel(char c) {
		return (c=='a' || c=='e' || c=='i' || c=='o' || c=='u');
	}
	public static void backtrack(int elementIdx, int selectedIdx) {
		// terminate
		if(selectedIdx==selectedSize) {
			// 모음을 적어도 1개, 자음은 2개 사용한 경우
			if(vowelCnt>0 && consonantCnt>1) {
				for(int resultIdx=0; resultIdx<selectedSize; resultIdx++) {
					sb.append(selectedSequence[resultIdx]);
				}
				sb.append('\n');
			}
			return;
		}

		// terminate
		if(elementIdx==elementSize) {
			return;
		}
		
		

		char alphabet = sequence[elementIdx];
		// 모음인 경우
		if(isVowel(alphabet)) {
			selectedSequence[selectedIdx]=alphabet;
			vowelCnt++;
			backtrack(elementIdx+1, selectedIdx+1);
			vowelCnt--;
			backtrack(elementIdx+1, selectedIdx);
		}else {
			selectedSequence[selectedIdx]=alphabet;
			consonantCnt++;
			backtrack(elementIdx+1, selectedIdx+1);
			consonantCnt--;
			backtrack(elementIdx+1, selectedIdx);
		}
		
		
		
	}
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
         
    	init();
    	backtrack(0,0);
    	
    	System.out.println(sb);
    }
}
