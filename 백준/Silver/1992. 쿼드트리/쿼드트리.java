import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int board[][];
	static StringBuilder sb;
	
	public static void init(BufferedReader br) throws Exception {
		sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		for(int rowIndex=0; rowIndex<N; rowIndex++) {
			String inputLine = br.readLine();
			for(int colIndex=0; colIndex<N; colIndex++) {
				board[rowIndex][colIndex] = inputLine.charAt(colIndex)-'0';
			}
		}
	}
	
	public static void compress(int rowStart, int colStart, int length) {
		if(length==0) {
			return;
		}
		boolean isAllZero = true;
		boolean isAllOne = true;
		for(int rowIndex = rowStart; rowIndex<rowStart+length; rowIndex++) {
			for(int colIndex = colStart; colIndex<colStart+length; colIndex++) {
				if(board[rowIndex][colIndex]==0) {
					isAllOne = false;
				}else {
					isAllZero = false;
				}
			}
		}
		
		if(isAllZero) {
			sb.append(0);
		}else if(isAllOne) {
			sb.append(1);
		}else {
			int nextLength = length/2;
			sb.append("(");
			compress(rowStart, colStart, nextLength);
			compress(rowStart, colStart+nextLength, nextLength);
			compress(rowStart+nextLength, colStart, nextLength);
			compress(rowStart+nextLength, colStart+nextLength, nextLength);
			sb.append(")");
		}
		
	}
	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		init(br);
		compress(0, 0, N);

		System.out.println(sb);
		
		
		
	}
}
