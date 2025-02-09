import java.util.*;
import java.io.*;

class Solution {
	static StringBuilder sb = new StringBuilder();
	
	static class Node{
		int id;
		int size;
		Node leftChild;
		Node rightChild;
		
		public Node(int id) {
			this.id=id;
			this.size=1;
			leftChild=null;
			rightChild=null;
		}
	}
	static int N, V, u, v;
	static int cnt;
	static Node[] nodes = new Node[10001];
	static int[] parent = new int[10001];
	static int[] depth = new int[10001];
	
	
	static void init() {
		for(int i=1; i<10001; i++) {
			nodes[i] = new Node(i);
			
		}
	}
	
	static int setDepth(Node node, int d) {
		if(node==null) {
			return 0;
		}
		depth[node.id] = d;
		node.size+=setDepth(node.leftChild, d+1);
		node.size+=setDepth(node.rightChild, d+1);
		return node.size;
	}
	
	static int getTreeSize(Node node) {
		
		return 1;
	}
	
	static int findParent(Node n1, Node n2) {
		// Set same depth
		while(depth[n1.id] != depth[n2.id]) {
			if(depth[n1.id] < depth[n2.id]) {
				n2 = nodes[parent[n2.id]];
			}
			else {
				n1 = nodes[parent[n1.id]];
			}
		}
		
		while(parent[n1.id]!=parent[n2.id]) {
			n2 = nodes[parent[n2.id]];
			n1 = nodes[parent[n1.id]];
		}
		
		return parent[n1.id];
	}
	public static void main(String[] args) throws Exception{
//		System.setIn(new FileInputStream("input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int testCase = 1; testCase<=T; ++testCase) {
			sb.append("#").append(testCase).append(" ");
			
			init();
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			V = Integer.parseInt(st.nextToken());
			u = Integer.parseInt(st.nextToken());
			v = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			
			for(int i=0; i<V; ++i) {
				int pId = Integer.parseInt(st.nextToken());
				int cId = Integer.parseInt(st.nextToken());
				parent[cId] = pId;
				
				if(nodes[pId].leftChild==null) {
					nodes[pId].leftChild=nodes[cId];
				}else {
					nodes[pId].rightChild=nodes[cId];
				}
			}
			
			setDepth(nodes[1], 1);
			
			int pId = findParent(nodes[u], nodes[v]);
			sb.append(pId).append(" ").append(nodes[pId].size).append("\n");
		}
		System.out.println(sb);
				
	}
}
