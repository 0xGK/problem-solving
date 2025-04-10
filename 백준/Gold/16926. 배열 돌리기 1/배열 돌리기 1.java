
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * 1. 한 줄씩 뜯어서 처리하기
 * 	1.1 네 변을 한 줄씩 뜯어서 새로운 배열에 저장
 * 
 * 2. 뜯은 배열을 startIndex부터 시작해서 원래 배열에 다시 저장
 * 	2.1 startIndex를 Rotation 횟수만큼 더하고, 길이에 대한 나머지로 시작
 */
public class Main {
	static int height, width, numberOfRotation;
	static int[][] board;
	static int[] linearArray;

	public static void init() {
		board = new int[height][width];
		linearArray = new int[2*(height+width)];
	}
	
	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		height = Integer.parseInt(st.nextToken());
		width = Integer.parseInt(st.nextToken());
		numberOfRotation = Integer.parseInt(st.nextToken());
		
		init();
		for(int rowIndex=0; rowIndex<height; rowIndex++) {
			st = new StringTokenizer(br.readLine());
			for(int colIndex=0; colIndex<width; colIndex++) {
				board[rowIndex][colIndex] = Integer.parseInt(st.nextToken());
			}
		}
		int maxRound = height > width ? width : height;
		
		for(int startIndex=0; startIndex<maxRound/2; startIndex++) {
			int linearIndex=0;

			int rowEndIndex = height-1-startIndex;
			int colEndIndex = width-1-startIndex;
			
			for(int colIndex = startIndex; colIndex<colEndIndex; colIndex++) {
				linearArray[linearIndex++] = board[startIndex][colIndex]; 
			}
			for(int rowIndex = startIndex; rowIndex<rowEndIndex; rowIndex++) {
				linearArray[linearIndex++] = board[rowIndex][colEndIndex];
			}
			for(int colIndex = colEndIndex; colIndex>startIndex; colIndex--) {
				linearArray[linearIndex++] = board[rowEndIndex][colIndex]; 
			}
			for(int rowIndex = rowEndIndex; rowIndex>startIndex; rowIndex--) {
				linearArray[linearIndex++] = board[rowIndex][startIndex];
			}
			
			
			int linearStartIndex=numberOfRotation;
			for(int colIndex = startIndex; colIndex<colEndIndex; colIndex++) {
				board[startIndex][colIndex] = linearArray[(linearStartIndex++)%linearIndex];
			}
			for(int rowIndex = startIndex; rowIndex<rowEndIndex; rowIndex++) {
				board[rowIndex][colEndIndex] = linearArray[(linearStartIndex++)%linearIndex];
			}
			for(int colIndex = colEndIndex; colIndex>startIndex; colIndex--) {
				board[rowEndIndex][colIndex] = linearArray[(linearStartIndex++)%linearIndex];
			}
			for(int rowIndex = rowEndIndex; rowIndex>startIndex; rowIndex--) {
				board[rowIndex][startIndex] = linearArray[(linearStartIndex++)%linearIndex];
			}

			
		}
		for(int i=0; i<height; i++) {
			for(int j=0; j<width; j++) {
				sb.append(board[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.println(sb);
		
		
	}
}
