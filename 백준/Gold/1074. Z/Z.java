import java.io.*;
import java.util.*;
/*
 * 모든 것들은 이미 다 정해져 있음
 * r,c가 큰 박스부터 생각해서 몇 번째 박스에 속해있는지를 계산해야함
 * 
 * 
 * 
 */
public class Main {
	static int N, r, c;
	static int[] power;
	static int length, totalCount;
	public static void playGame() {
		while(N>0) {
			length = power[--N];
			int boxCount;
			if(r<length && c<length) {
				boxCount=0;
			}else if(r<length && c>=length) {
				boxCount=1;
				c-=length;
			}else if(r>=length && c<length) {
				boxCount=2;
				r-=length;
			}else{
				boxCount=3;
				r-=length;
				c-=length;
			}
			
			totalCount+=boxCount*length*length;
		}
		
		
	}

	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
				totalCount=0;
		power = new int[15];
		power[0]=1;
		for(int powerIndex=1; powerIndex<15; powerIndex++) {
			power[powerIndex] = power[powerIndex-1]*2; 
		}	
		playGame();
		System.out.println(totalCount);
	}
}
