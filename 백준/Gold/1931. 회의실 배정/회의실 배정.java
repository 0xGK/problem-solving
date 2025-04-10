import java.util.*;
import java.io.*;

/*
 * 1. 그리디로 접근
 * 	1.1. 정렬
 * 		1.1.1 end 값이 빠른 순으로 정렬
 * 		1.1.2. end가 같다면 start가 빠른 순으로 정렬
 * 
 * 	1.2. 순서가 결정됨
 * 		1.2.1 배열로 관리할 필요 없이, lastMeeting만 갱신하면서, 개수 count
 * 
 * 
 */

public class Main {
	static int N;
	static Meeting[] meetings;
	
	
	public static class Meeting implements Comparable<Meeting>{
		int start;
		int end;
		public Meeting(int start, int end) {
			this.start = start;
			this.end = end;
		}
		
		@Override
		public int compareTo(Meeting other) {
			// end 기준 오름차순
			// this.end < other.end
			// 만약 같다면, start 기준 오름차순
			if(this.end==other.end) {
				return this.start - other.start;
			}
			
			return this.end - other.end;
		}
		
//		@Override
//		public String toString() {
//			// TODO Auto-generated method stub
//			return "(" + start + ", " + end + ")";
//		}
	}
	
	
	
	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine());
		
		meetings = new Meeting[N];
		
		for(int inputIndex = 0; inputIndex<N; inputIndex++) {
			st = new StringTokenizer(br.readLine());
			meetings[inputIndex] = new Meeting(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		Arrays.sort(meetings);
		
		Meeting lastMeeting = meetings[0];
		int cnt=1;
		
		for(int i=1; i<N; i++) {
			if(lastMeeting.end <= meetings[i].start){
				lastMeeting = meetings[i];
				cnt++;
			}
		}
		System.out.println(cnt);
		
	}
}
