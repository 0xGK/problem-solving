import java.util.*;
import java.io.*;


class Solution {
	static int N, M;
	static LinkedList<Integer> passwords;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		for(int testCase=1; testCase<=10; testCase++) {
			sb.append("#").append(testCase).append(" ");
			N = Integer.parseInt(br.readLine());
			passwords = new LinkedList<>();
			
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				passwords.add(Integer.parseInt(st.nextToken()));
			}
			
			M = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
			
			int x, y;
			for(int m=0; m<M; m++) {
				char command = st.nextToken().charAt(0);
				if(command == 'I') {
					x = Integer.parseInt(st.nextToken());
					y = Integer.parseInt(st.nextToken());
					LinkedList<Integer> tmpPasswords = new LinkedList<>();
					for(int i=0; i<y; i++) {
						tmpPasswords.add(Integer.parseInt(st.nextToken()));
					}
					
					passwords.addAll(x,tmpPasswords);
					
				}else if(command == 'D') {
					x = Integer.parseInt(st.nextToken());
					y = Integer.parseInt(st.nextToken());
					for(int i=0; i<y; i++) {
						passwords.remove(x-1);
					}
					
					
				}else if(command == 'A') {
					y = Integer.parseInt(st.nextToken());
					LinkedList<Integer> tmpPasswords = new LinkedList<>();
					for(int i=0; i<y; i++) {
						tmpPasswords.add(Integer.parseInt(st.nextToken()));
					}
					passwords.addAll(tmpPasswords);
					
					
				}
				
				
			}
			for(int i=0; i<10; i++) {
				sb.append(passwords.get(i)).append(" ");
			}
			sb.append("\n");
			
		}
		System.out.println(sb);
		
		
		
		
	}
}
