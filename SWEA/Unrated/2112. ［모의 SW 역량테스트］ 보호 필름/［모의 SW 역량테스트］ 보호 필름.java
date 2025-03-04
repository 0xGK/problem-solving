
import java.io.*;
import java.util.*;
 
/* 
 * 보호 필름의 합격 기준 K
 * 각 세로축 기준 연속된 값이 K개 이상씩 나와야 통과함.
 * 
 * D <= 13
 * W <= 20
 * K <= D
 * 
 * 약품: 선택한 단면을 전부 A 또는 B로 변경 가능
 * 		-> D개의 단면 중 r개를 선택하여 A 또는 B로 변경하는 로직 생각 가능
 * 		-> 백트래킹을 활용한 조합 문제
 * 		-> worst case가 얼마인지 계산 필요
 * 
 * 1. isValid()
 *      -> 현재 상태에서 성능 검사를 통과하는지 확인하는 메서드 구현 필요
 *      -> 모든 세로 축에 대해서 k개의 연속인 값이 존재하는지 확인하는 메서드
 *      -> 시간복잡도: O(D*W)
 *      -> worst case: 13*20 = 260
 * 
 * 2. backtrack()
		-> 시간복잡도: O(2^D)
 *      -> worst case: nC0 + nC1 + nC2 + ... + nCk 
 *      			<= 13C0 + 13C1 + 13C2 + ... 13C13
 *      			= 2^13
 *      			= 8192
 *					-> not too much
 *      -> terminate: k개를 전부 선택한 경우
 *      -> prune: nCk에서 k를 0부터 시작해서, 처음으로 충족하는 경우가 발생한 경우가 최소
 *      		-> 나머지 branch는 전부 prune
 *      
 * 3. worst of worst case: 8192 * 260 = 2_129_920
 * 		-> enough to do
 * 
 * 
 */
public class Solution {
    static int D, W, K;
    static int[][] layers;
    static boolean flag;
    public static void init() {
        layers = new int[D][W];
    }
     
    public static boolean isValid() {
        int cnt = 0;
        for(int widthIndex=0; widthIndex<W; widthIndex++) {
            int prev = layers[0][widthIndex];
            cnt = 1;
            for(int depthIndex=1; depthIndex<D; depthIndex++) {
                if(prev == layers[depthIndex][widthIndex]) {
                    cnt++;
                }else {
                    prev = layers[depthIndex][widthIndex];
                    cnt = 1;
                }
                if(cnt==K) {
                    break;
                }
            }
            if(cnt!=K) {
                return false;
            }
        }
        return true;
    }
     
    public static void injectionBacktrack(int depthIndex, int remainedCount) {
        // prune
        if(flag) return;
         
        // terminate
        if(remainedCount==0) {
            if(isValid()) {
                flag = true;
            }
            return;
        }
        // terminate
        if(depthIndex==D) {
            return;
        }
         
        
        // generate branch
        injectionBacktrack(depthIndex+1, remainedCount);

        // 현재 배열을 임시 저장해고 백트랙시 복구 시키기
        // 실제 배열을 변경하지 않고, 
        //      변경시킬 index와 값을 사용해서 isValid() 메서드를 수정하면 최적화 가능할 듯
//        int[] tempLayer = new int[W];
         
        int[] tempLayer = Arrays.copyOf(layers[depthIndex], W);
        for(int widthIndex=0; widthIndex<W; widthIndex++) {
//            tempLayer[widthIndex] = layers[depthIndex][widthIndex];
            layers[depthIndex][widthIndex] = 0;
        }
        
        // generate branch
        injectionBacktrack(depthIndex+1, remainedCount-1);
         
        for(int widthIndex=0; widthIndex<W; widthIndex++) {
            layers[depthIndex][widthIndex] = 1;
        }
         
        // generate branch
        injectionBacktrack(depthIndex+1, remainedCount-1);
         
        // backtracking
        for(int widthIndex=0; widthIndex<W; widthIndex++) {
            layers[depthIndex][widthIndex] = tempLayer[widthIndex];
        }
    }
     
    public static void main(String[] args) throws Exception {
        //System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
          
        int T = Integer.parseInt(br.readLine());
        for(int testCase=1; testCase<=T; ++testCase) {
            sb.append("#").append(testCase).append(" ");
            st = new StringTokenizer(br.readLine());
            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            init();
            for(int depthIndex=0; depthIndex<D; depthIndex++) {
                st = new StringTokenizer(br.readLine());
                for(int widthIndex=0; widthIndex<W; widthIndex++) {
                    layers[depthIndex][widthIndex] = Integer.parseInt(st.nextToken());
                }
            }
            for(int kIdx=0; kIdx<=K; kIdx++) {
            	flag = false;
            	injectionBacktrack(0, kIdx);
            	if(flag) {
            		sb.append(kIdx).append("\n");
            		break;
            	}
            }
             
              
        }
        System.out.println(sb);
          
    }
}