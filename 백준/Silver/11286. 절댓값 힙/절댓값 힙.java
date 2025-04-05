import java.util.*;
import java.io.*;

public class Main {
	static BufferedReader br;
	static Queue<Integer> absHeap;
	static int N;
	public static void init() throws IOException{
		N = Integer.parseInt(br.readLine());
		absHeap = new PriorityQueue<>(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				if(Math.abs(o1) == Math.abs(o2)) {
					return Integer.compare(o1, o2);
				}
				return Integer.compare(Math.abs(o1), Math.abs(o2));
			}
		});
	}

	public static void solve() throws IOException{

	}
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

    	init();
		while(N-- >0) {
			int value = Integer.parseInt(br.readLine());
			if(value == 0) {
				if(absHeap.isEmpty()) {
					sb.append(0).append('\n');
				}else {
					sb.append(absHeap.poll()).append('\n');
				}
			}else {
				absHeap.add(value);
			}
		}

    	
    	System.out.println(sb);
    }
}
