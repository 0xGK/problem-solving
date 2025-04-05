import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	
	static Queue<Integer> minHeap;
	static Queue<Integer> maxHeap;
	static Map<Integer, Integer> hashMap;
	static int N;
	
	public static void init() throws IOException{
		N = Integer.parseInt(br.readLine());
		minHeap = new PriorityQueue<>();
		maxHeap = new PriorityQueue<>(Collections.reverseOrder());
		hashMap = new HashMap<>();
	}
	public static long getMinValue() {
		while(!minHeap.isEmpty()) {
			int value = minHeap.poll();
			int cnt = hashMap.getOrDefault(value,0);
			if(cnt==0) continue;
			
			if(cnt>1){
				hashMap.put(value, hashMap.get(value)-1);
			}else if(cnt==1){
				hashMap.remove(value);
			}
			return value;
		}
		return Long.MAX_VALUE;
	}
	
	public static long getMaxValue() {
		while(!maxHeap.isEmpty()) {
			int value = maxHeap.poll();
			int cnt = hashMap.getOrDefault(value,0);
			if(cnt==0) continue;
			
			if(cnt>1){
				hashMap.put(value, hashMap.get(value)-1);
			}else if(cnt==1){
				hashMap.remove(value);
			}
			return value;
		}
		return Long.MAX_VALUE;
	}
	public static void solve() throws IOException{
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			char command = st.nextToken().charAt(0);
			if(command == 'I') {
				int value = Integer.parseInt(st.nextToken());
				minHeap.add(value);
				maxHeap.add(value);
				hashMap.put(value, hashMap.getOrDefault(value, 0)+1);
			}else {
				int type = Integer.parseInt(st.nextToken());
				
				if(hashMap.size()==0) continue;
				if(type==-1) {
					getMinValue();
				}else {
					getMaxValue();
				}
			}
		}
		
	}
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("input.txt"));
    	
        br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int testCase=1; testCase<=T; testCase++) {
        	init();
        	solve();
        	if(hashMap.size()==0) {
        		sb.append("EMPTY\n");
        	}else {
        		long value = getMaxValue();
        		sb.append(value).append(' ');
        		if(hashMap.size()>0) value = getMinValue();
        		sb.append(value).append('\n');
        	}
        }
    	System.out.println(sb);
    }
}
