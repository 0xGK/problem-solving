import java.io.*;
import java.util.*;

public class Main {
	
	static int N;
	static int[] sequence;
	
	public static void swap(int leftIndex, int rightIndex) {
		int temp = sequence[leftIndex];
		sequence[leftIndex]=sequence[rightIndex];
		sequence[rightIndex]=temp;
	}
	
	public static void reverse(int start, int end) {
		while(start<end) {
			swap(start, end);
			start++;
			end--;
		}
	}
	public static boolean nextPermutation() {
		int i = N-1;
		while(i>0 && sequence[i-1] >= sequence[i]) {
			i--;
		}
		// i: top
		// i-1: target to swap
		if(i==0) {
			return false;
		}
		
		int j = N-1;
		// j: index to swap
		while(sequence[i-1] >= sequence[j]) {
			j--;
		}
		// swap
		swap(i-1, j);
		
		
		//reverse
		reverse(i, N-1);
		
		return true;
	}
	
	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		sequence = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int inputIndex=0; inputIndex<N; inputIndex++) {
			sequence[inputIndex] = Integer.parseInt(st.nextToken());
		}
		
		
		if(!nextPermutation()) {
			System.out.println(-1);
		}else {
			for(int index=0; index<N; index++) {
				System.out.print(sequence[index] + " ");
			}
		}
				
				
	}
}
