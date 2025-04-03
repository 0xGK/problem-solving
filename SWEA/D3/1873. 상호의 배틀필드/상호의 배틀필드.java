import java.io.*;
import java.util.*;

/*
 * 
 * <Rule>
	문자	의미
	.	평지(전차가 들어갈 수 있다.)
	*	벽돌로 만들어진 벽
	#	강철로 만들어진 벽
	-	물(전차는 들어갈 수 없다.)
	^	위쪽을 바라보는 전차(아래는 평지이다.)
	v	아래쪽을 바라보는 전차(아래는 평지이다.)
	<	왼쪽을 바라보는 전차(아래는 평지이다.)
	>	오른쪽을 바라보는 전차(아래는 평지이다.)
	
	
	문자	동작
	U	Up : 전차가 바라보는 방향을 위쪽으로 바꾸고, 한 칸 위의 칸이 평지라면 위 그 칸으로 이동한다.
	D	Down : 전차가 바라보는 방향을 아래쪽으로 바꾸고, 한 칸 아래의 칸이 평지라면 그 칸으로 이동한다.
	L	Left : 전차가 바라보는 방향을 왼쪽으로 바꾸고, 한 칸 왼쪽의 칸이 평지라면 그 칸으로 이동한다.
	R	Right : 전차가 바라보는 방향을 오른쪽으로 바꾸고, 한 칸 오른쪽의 칸이 평지라면 그 칸으로 이동한다.
	S	Shoot : 전차가 현재 바라보고 있는 방향으로 포탄을 발사한다.
			-> 포탄은 벽돌 or 강철에 충돌하거나 맵 밖으로 직진
			-> 벽돌: 파괴되어 평지 됨
			-> 강철: 파괴되지 않음
 *
 * 2 <= H, W <= 20
 * 0 < N <= 100
 * 
 * 
 * 
 */

public class Solution {
	static final int UP = 0;
	static final int DOWN = 1;
	static final int LEFT = 2;
	static final int RIGHT = 3;
	static final int SHOOT = 4;
	static int N, H, W;
	static int curRow, curCol, curDir;
	static char[][] board;
	static int[] commandList;
	static StringTokenizer st;
	static int[][] dirs = {{-1,0}, {1,0}, {0,-1}, {0,1}};	// 상하좌우
	static char[] dirToChar = {'^', 'v', '<', '>'};
	
	public static void init(BufferedReader br) throws Exception{
		st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		board = new char[H][W];
		for(int heightIndex=0; heightIndex<H; heightIndex++) {
			String inputLine = br.readLine();
			for(int widthIndex=0; widthIndex<W; widthIndex++) {
				char curChar = inputLine.charAt(widthIndex);
				board[heightIndex][widthIndex] = curChar;
				
				// 시작지점의 탱크 위치와 방향 저장
				for(int dirIndex=0; dirIndex<4; dirIndex++) {
					if(curChar==dirToChar[dirIndex]) {
						curRow=heightIndex;
						curCol=widthIndex;
						curDir=dirIndex;
						break;
					}
				}
			}
		}
		
		N = Integer.parseInt(br.readLine());
		commandList = new int[N];
		String inputList = br.readLine();
		for(int index=0; index<N; index++) {
			char command = inputList.charAt(index);
			switch(command) {
			case 'U':
				commandList[index] = UP;
				break;
			case 'D':
				commandList[index] = DOWN;
				break;
			case 'L':
				commandList[index] = LEFT;
				break;
			case 'R':
				commandList[index] = RIGHT;
				break;
			case 'S':
				commandList[index] = SHOOT;
				break;
			}
		}
	}
	
	
	public static void playBattle(int command) {
		int nextRow, nextCol;
		if(command==SHOOT) {
			nextRow = curRow;
			nextCol = curCol;
			while(true) {
				nextRow += dirs[curDir][0];
				nextCol += dirs[curDir][1];
				
				if(nextRow<0 || nextCol<0 || nextRow>=H || nextCol>=W) return;
				
				// 강철인 경우
				if(board[nextRow][nextCol]=='#') {
					return;
				}
				
				// 벽돌인 경우
				if(board[nextRow][nextCol]=='*') {
					board[nextRow][nextCol]='.';
					return;
				}
				
				
			}
		}
		// Move인 경우
		// command:= 방향
		else {
			// 방향 전환
			board[curRow][curCol]=dirToChar[command];
			curDir = command;
			
			nextRow = curRow + dirs[command][0];
			nextCol = curCol + dirs[command][1];
			
			// 움직일 수 없는 경우, 방향만 바꾸고 종료
			if(nextRow<0 || nextCol<0 || nextRow>=H || nextCol>=W) return;
			
			// 평지인 경우만 이동 가능
			if(board[nextRow][nextCol]=='.') {
				board[nextRow][nextCol]=board[curRow][curCol];
				board[curRow][curCol]='.';
				curRow = nextRow;
				curCol = nextCol;
			}
		}
	}
	public static void printBoard(StringBuilder sb) {
		for(int rowIndex=0; rowIndex<H; rowIndex++) {
			for(int colIndex=0; colIndex<W; colIndex++) {
				sb.append(board[rowIndex][colIndex]);
			}
			sb.append('\n');
		}
	}
    public static void main(String[] args) throws Exception {
	    //System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
         
        int T = Integer.parseInt(br.readLine());
        for(int testCase=1; testCase<=T; ++testCase) {
        	init(br);
        	for(int commandIndex=0; commandIndex<N; commandIndex++) {
        		playBattle(commandList[commandIndex]);
        	}
        	sb.append("#").append(testCase).append(" ");
        	printBoard(sb);
        	
             
        }
        System.out.println(sb);
         
    }
}