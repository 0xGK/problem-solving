import java.io.*;
import java.util.*;
/*
 * N * N 배열에서 수들을
 * 
 * 
 * 
 * 
 * 
 */
public class Solution {
	static final int UP = 0;
	static final int DOWN = 1;
	static final int LEFT = 2;
	static final int RIGHT = 3;
	static int command;
	static int N;
	static int[][] board;
	static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	
	
	
	public static void init() {
		command = 0;
		board = new int[N][N];
	}
	
	
	public static void playGame() {
		if(command==LEFT) {
            for(int rowIndex=0; rowIndex<N; rowIndex++) {
            	// 2개씩 잡아서 비교!
            	for(int curIndex=0; curIndex<N-1; curIndex++) {
            		// swap current value
            		if(board[rowIndex][curIndex]==0) {
                		int nextIndex = curIndex+1;
                		for(; nextIndex<N; nextIndex++) {
                			if(board[rowIndex][nextIndex]!=0) {
                				board[rowIndex][curIndex] = board[rowIndex][nextIndex];
                				board[rowIndex][nextIndex] = 0;
                				break;
                			}
                		}
            		}
            		
            		// terminate
            		if(board[rowIndex][curIndex]==0) {
            			break;
            		}
            		
            		// getNextIndex
            		int nextIndex = curIndex+1;
            		for(; nextIndex<N; nextIndex++) {
            			if(board[rowIndex][nextIndex]!=0) {
            				break;
            			}
            		}
            		
            		// terminate
            		if(nextIndex==N) {
            			continue;
            		}
            		
            		// merge
            		if(board[rowIndex][curIndex] == board[rowIndex][nextIndex]) {
            			board[rowIndex][curIndex] *= 2; 
            			board[rowIndex][nextIndex] = 0;
            		}
            	}
            }
		}
		
		else if(command==RIGHT) {
            for(int rowIndex=0; rowIndex<N; rowIndex++) {
            	// 2개씩 잡아서 비교!
            	for(int curIndex=N-1; curIndex>0; curIndex--) {
            		// swap current value
            		if(board[rowIndex][curIndex]==0) {
                		int nextIndex = curIndex-1;
                		for(; nextIndex>=0; nextIndex--) {
                			if(board[rowIndex][nextIndex]!=0) {
                				board[rowIndex][curIndex] = board[rowIndex][nextIndex];
                				board[rowIndex][nextIndex] = 0;
                				break;
                			}
                		}
            		}
            		
            		// terminate
            		if(board[rowIndex][curIndex]==0) {
            			break;
            		}
            		
            		// getNextIndex
            		int nextIndex = curIndex-1;
            		for(; nextIndex>=0; nextIndex--) {
            			if(board[rowIndex][nextIndex]!=0) {
            				break;
            			}
            		}
            		
            		// terminate
            		if(nextIndex==-1) {
            			continue;
            		}
            		
            		// merge
            		if(board[rowIndex][curIndex] == board[rowIndex][nextIndex]) {
            			board[rowIndex][curIndex] *= 2; 
            			board[rowIndex][nextIndex] = 0;
            		}
            	}
            }
		}
		
		else if(command==UP) {
            for(int colIndex=0; colIndex<N; colIndex++) {
            	// 2개씩 잡아서 비교!
            	for(int curIndex=0; curIndex<N-1; curIndex++) {
            		// swap current value
            		if(board[curIndex][colIndex]==0) {
                		int nextIndex = curIndex+1;
                		for(; nextIndex<N; nextIndex++) {
                			if(board[nextIndex][colIndex]!=0) {
                				board[curIndex][colIndex] = board[nextIndex][colIndex];
                				board[nextIndex][colIndex] = 0;
                				break;
                			}
                		}
            		}
            		
            		// terminate
            		if(board[curIndex][colIndex]==0) {
            			break;
            		}
            		
            		// getNextIndex
            		int nextIndex = curIndex+1;
            		for(; nextIndex<N; nextIndex++) {
            			if(board[nextIndex][colIndex]!=0) {
            				break;
            			}
            		}
            		
            		// terminate
            		if(nextIndex==N) {
            			continue;
            		}
            		
            		// merge
            		if(board[curIndex][colIndex] == board[nextIndex][colIndex]) {
            			board[curIndex][colIndex] *= 2; 
            			board[nextIndex][colIndex] = 0;
            		}
            	}
            }
		}
		
		
		else if(command==DOWN) {
			for(int colIndex=0; colIndex<N; colIndex++) {
            	// 2개씩 잡아서 비교!
            	for(int curIndex=N-1; curIndex>0; curIndex--) {
            		// swap current value
            		if(board[curIndex][colIndex]==0) {
                		int nextIndex = curIndex-1;
                		for(; nextIndex>=0; nextIndex--) {
                			if(board[nextIndex][colIndex]!=0) {
                				board[curIndex][colIndex] = board[nextIndex][colIndex];
                				board[nextIndex][colIndex] = 0;
                				break;
                			}
                		}
            		}
            		
            		// terminate
            		if(board[curIndex][colIndex]==0) {
            			break;
            		}
            		
            		// getNextIndex
            		int nextIndex = curIndex-1;
            		for(; nextIndex>=0; nextIndex--) {
            			if(board[nextIndex][colIndex]!=0) {
            				break;
            			}
            		}
            		
            		// terminate
            		if(nextIndex==-1) {
            			continue;
            		}
            		
            		// merge
            		if(board[curIndex][colIndex] == board[nextIndex][colIndex]) {
            			board[curIndex][colIndex] *= 2; 
            			board[nextIndex][colIndex] = 0;
            		}
            	}
            }
		}
		
		
		
		
	}
	
	
    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
         
        int T = Integer.parseInt(br.readLine());
        for(int testCase=1; testCase<=T; ++testCase) {
            sb.append("#").append(testCase).append("\n");
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            init();
            
            switch(st.nextToken()) {
	            case "up":
	            	command = UP;
	            	break;
	            case "down":
	            	command = DOWN;
	            	break;
	            case "left":
	            	command = LEFT;
	            	break;
	            case "right":
	            	command = RIGHT;
	            	break;
            }
            
            for(int rowIndex=0; rowIndex<N; rowIndex++) {
            	st = new StringTokenizer(br.readLine());
            	for(int colIndex=0; colIndex<N; colIndex++) {
            		board[rowIndex][colIndex] = Integer.parseInt(st.nextToken());
            	}
            }
            
            playGame();
            
            //view
            for(int rowIndex=0; rowIndex<N; rowIndex++) {
            	for(int colIndex=0; colIndex<N; colIndex++) {
            		sb.append(board[rowIndex][colIndex]).append(" ");
            	}
            	sb.append("\n");
            }
            
            
            
             
        }
        System.out.println(sb);
        br.close();
         
    }
}