import java.util.*;
import java.io.*;

class Solution {
	static StringBuilder sb = new StringBuilder();
	
	static class Node{
		int id;
		char alphabet;
		Node leftChild;
		Node rightChild;
	}
	static int N;
	static int cnt;
	static Node[] nodePool = new Node[101];
	
	static void init() {
		for(int i=1; i<101; i++) {
			nodePool[i] = new Node();
		}
	}
	
	static Node getNode(int id) {
		return nodePool[id];
	}
	
	static void inorderTraversal(Node node) {
		if(node==null) return;
		
		inorderTraversal(node.leftChild);
		sb.append(node.alphabet);
		inorderTraversal(node.rightChild);
	}
	
	public static void main(String[] args) throws Exception{
//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		
		for(int testCase = 1; testCase<=10; ++testCase) {
			sb.append("#").append(testCase).append(" ");
			
			init();
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			
			for(int n=0; n<N; ++n) {
				st = new StringTokenizer(br.readLine());
				int id = Integer.parseInt(st.nextToken());
				Node node = getNode(id);
				node.id = id;
				node.alphabet = st.nextToken().charAt(0);
				
				if(st.hasMoreTokens()) {
					int nextId = Integer.parseInt(st.nextToken());
					node.leftChild = getNode(nextId);
				}
				if(st.hasMoreTokens()) {
					int nextId = Integer.parseInt(st.nextToken());
					node.rightChild = getNode(nextId);
				}
			}
			inorderTraversal(getNode(1));
			sb.append("\n");
			
		}
		System.out.println(sb);
				
	}
}
