import java.util.*;
import java.io.*;

/*
 * @author GK
 * 
 * [문제 조건]
 * 수의 변경이 빈번히 일어남.
 * 	-> index update
 * 		
 * 어떤 부분의 합을 구하려고 함
 * 	-> range query
 * 
 * segment tree로 구현
 *  -> range update는 없으므로, lazy propagation은 고려 X
 * 
 * [bottom-up Segment Tree]
 * 1. segTree 자료구조 저장
 * 	1-1. segTree의 treeSize와 base 구하기
 * 	1-2. segTree의 leaf node에 초기 데이터 추가
 * 	1-3. 초기 segTree의 leaf node의 부모 노드에 구간 합산 저장
 * 
 * 2. segTree update()
 * 	2-1. 해당 idx에 값을 value만큼 증감
 * 	2-2. 부모 노드로 올라가면서, value만큼 증감
 * 
 * 3. segTree query()
 * 	3-1. 구간 index를 좁혀가면서 값 합산
 * 
 */

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	
	static long[] segTree;
	static int numberSize, updateSize, querySize;
	static int treeSize;
	static int base;
	static StringBuilder sb;

	public static void init() throws IOException{
		st = new StringTokenizer(br.readLine());
		numberSize = Integer.parseInt(st.nextToken());
		updateSize = Integer.parseInt(st.nextToken());
		querySize = Integer.parseInt(st.nextToken());
		
		// bottom-up 방식으로, tree 크기를 미리 결정하고, base값 사용
		treeSize = 1;
		while(treeSize < numberSize) {
			treeSize <<= 1;
		}
		base = treeSize-1;
		
		segTree = new long[treeSize*2 + 1];
		
		for(int idx=1; idx<=numberSize; idx++) {
			 segTree[base+idx] = Long.parseLong(br.readLine());
		}
		
		// segTree 초기화
		for(int idx = base; idx>0; idx--) {
			segTree[idx] = segTree[idx*2]+segTree[idx*2+1];
		}
	}
	
	// root까지 올라가면서 갱신해야함.
	public static void update(int idx, long diff) {
		idx += base;
		
		while(idx>0) {
			segTree[idx] += diff;
			idx/=2;
		}
	}
	
	public static long query(int startIdx, int endIdx) {
		long value = 0;
		startIdx+=base;
		endIdx+=base;
		while(startIdx<=endIdx) {
			if((startIdx&1)==1) {
				value += segTree[startIdx++];
			}
			
			if((endIdx&1)==0) {
				value += segTree[endIdx--];
			}
			startIdx/=2;
			endIdx/=2;
		}
		return value;
	}

    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("input.txt"));
        br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
         
    	init();
    	for(int gameIdx=0; gameIdx<updateSize+querySize; gameIdx++) {
    		st = new StringTokenizer(br.readLine());
    		int command = Integer.parseInt(st.nextToken());
    		if(command==1) {
    			int idx = Integer.parseInt(st.nextToken());
    			long value = Long.parseLong(st.nextToken());
    			long diff = value - segTree[base+idx];
    			update(idx, diff);
    		}else {
    			int startIdx = Integer.parseInt(st.nextToken());
    			int endIdx = Integer.parseInt(st.nextToken());
    			sb.append(query(startIdx, endIdx)).append('\n');
    		}
    	}
    	
    	System.out.println(sb);
    }
}
