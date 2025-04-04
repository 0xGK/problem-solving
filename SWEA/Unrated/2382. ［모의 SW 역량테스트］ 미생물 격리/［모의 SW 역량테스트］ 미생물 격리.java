import java.io.*;
import java.util.*;
/*
 * 1. 미생물 군집이 이동 후 약품에 도달한 경우
 * 	1-1. 절반 죽고, 이동방향 반대
 * 	1-2. 수가 0이 되면 사라짐
 * 	1-3. 위치는 약품 위에서 존재. 즉, 약품으로 이동했을 경우만 미생물 수 조정
 * 2. 이동 후 여러 군집이 한 셀에 모이는 경우
 * 	2-1. 합쳐진 군집의 미생물 수는 미생물 수의 합
 * 	2-2. 이동 방향은 가장 많은 군집의 이동 방향
 * 	2-3. 합쳐지는 군집의 미생물 수가 같은 경우는 없음
 * 3. 문제 풀이 순서
 * 	3-1. 미생물 이동
 * 		3-1-1. 미생물 위치, 수, 방향 수정
 * 	3-2. 같은 위치의 미생물 합산
 * 		3-2-1. 방향은 가장 많은 수의 미생물이 결정
 * 		3-2-1. 미생물의 수는 합산
 */
public class Solution {
	static int N, M, K, totalNum;
	static int[][] dirs = {{0,0},{-1,0},{1,0},{0,-1},{0,1}}; // 상하좌우
	static int[] changeDir = {0, 2, 1, 4, 3};
	static List<Microbe> microbes;
	
	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb;
	
	// 미생물
	static class Microbe{
		int row, col, num, dir;

		public Microbe(int row, int col, int num, int dir) {
			this.row = row;
			this.col = col;
			this.num = num;
			this.dir = dir;
		}

	}
	
	public static void init() throws IOException{
		totalNum=0;
		st = new StringTokenizer(br.readLine().trim());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		microbes = new ArrayList<>();
		for(int microbIdx=0; microbIdx<K; microbIdx++) {
			st = new StringTokenizer(br.readLine().trim());
			int row = Integer.parseInt(st.nextToken());
			int col = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			microbes.add(new Microbe(row,col,num,dir));
		}
	}
	public static boolean isInMedicine(int row, int col) {
		return row==0 || row == N-1 || col == 0 || col == N-1;
	}
	public static void playMicrobeGame() {
		for(int playTime=0; playTime<M; playTime++) {
			// 미생물 이동
			for(Microbe microbe : microbes) {
				microbe.row += dirs[microbe.dir][0];
				microbe.col += dirs[microbe.dir][1];
				// 약품인 경우
				if(isInMedicine(microbe.row, microbe.col)) {
					microbe.dir = changeDir[microbe.dir];
					microbe.num/=2;
				}
			}
			
			// 미생물 합산
			// 리스트로 단순 순회는 비효율 적일 수도?
			for(int microbeIdx=0; microbeIdx<microbes.size(); microbeIdx++) {
				Microbe curMicrobe = microbes.get(microbeIdx);
				if(curMicrobe.num==0) continue;
				int curRow = curMicrobe.row;
				int curCol = curMicrobe.col;
				int maxNum = curMicrobe.num;
				// 현재 미생물과 동일한 위치인 녀석들 저장
				for(int nxtMicrobeIdx=microbeIdx+1; nxtMicrobeIdx<microbes.size(); nxtMicrobeIdx++) {
					Microbe nxtMicrobe = microbes.get(nxtMicrobeIdx);
					if(nxtMicrobe.num==0) continue;
					int nxtRow = nxtMicrobe.row;
					int nxtCol = nxtMicrobe.col;
					if(curRow==nxtRow && curCol==nxtCol) {
						curMicrobe.num += nxtMicrobe.num;
						// 방향 전환
						if(maxNum < nxtMicrobe.num) {
							curMicrobe.dir = nxtMicrobe.dir;
							maxNum = nxtMicrobe.num;
						}
						nxtMicrobe.num=0;
						
					}
				}
			}
			
			// 죽은 미생물, 리스트에서 제거
			microbes.removeIf(microbe -> microbe.num==0);
		}
		
		// 남아있는 미생물 수의 총 합 구하기
		for(int microbeIdx=0; microbeIdx<microbes.size(); microbeIdx++) {
			totalNum+=microbes.get(microbeIdx).num;
		}
	}
    public static void main(String[] args) throws Exception {
      //System.setIn(new FileInputStream("input.txt"));
      br = new BufferedReader(new InputStreamReader(System.in));
      sb = new StringBuilder();
      
        
      int T = Integer.parseInt(br.readLine().trim());
      for(int testCase=1; testCase<=T; ++testCase) {
          init();
          playMicrobeGame();
          sb.append('#').append(testCase).append(' ').append(totalNum).append('\n');
            
      }
      System.out.println(sb);
        
  }
}
