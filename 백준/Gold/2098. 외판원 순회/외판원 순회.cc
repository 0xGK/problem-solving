#include <cstdio>
#include <cstring>
#include <algorithm>
using namespace std;

int N, start, W[16][16], dp[16][65536];//1<<16 = 2^16개의 노드의 방문 여부를 비트로 표시.
const int IMPOSSIBLE = 1000000000;

/*
현재 외판원이 current번 마을에 있고, 방문한 마을 집합이 visited일 때
앞으로 남은 모든 마을을 방문하고 0번 마을로 돌아갈 때 드는 최소 비용
*/
int TSP(int current, int visited){
    /*
    기저 사례: 모든 마을을 방문한 경우,
    현재 마을에서 시작지점으로 갈 수 있으면 해당 비용을 리턴하고
    아닐 경우 불가능 값을 리턴한다.
    */
    if(visited == (1<<N)-1){// 모두 방문
        if(W[current][start]==0){
            //시작점으로 돌아갈 수 없다면
            return IMPOSSIBLE;
        }else{
            return W[current][start];
        }
    }

    int &ret = dp[current][visited];
    if(ret != -1){
        //-1이 아니라면 자명한 최소값(dpCost[][]) 바로 리턴.
        return ret; 
    } 

    ret = IMPOSSIBLE;
    // 아직 방문하지 않은 모든 도시들을 순회해 봄
    for(int i=0; i<N; i++){
        if(visited & (1<<i)) continue; // 방문한적이 있다면 
        if(W[current][i]==0) continue; // 다음 도시로 갈수가 없다면 
        //현재 노드에서 재귀적으로 다음 노드 방문.
        ret = min(ret, TSP(i, visited | (1<<i)) + W[current][i]);
    }
    return ret;
}

int main(){
    scanf("%d", &N);
    for(int i=0; i<N; i++)
        for(int j=0; j<N; j++)
            scanf("%d", &W[i][j]);
    memset(dp, -1, sizeof(dp));
    //0 : 0번 노드가 첫 시작노드 / 1 : 1은 2^0 -> 즉 시작 노드인 0번 노드를 방문했음을 표시)
    //어떤 도시에서 출발하던 비용은 같으므로 0번 노드를 첫 시작노드로 임의 설정.
    printf("%d\n", TSP(0, 1));

}