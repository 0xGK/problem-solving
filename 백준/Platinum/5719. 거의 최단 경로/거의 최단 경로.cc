#include <iostream>
#include <cstring>
#include<cstdio>
#include<vector>
#include<queue>
#include<string.h>

#define VMAX 500
#define EMAX 10000
#define MAX_VALUE 500500

using namespace std;
typedef struct  Node{
    int dest;
    int cost;
}Node;

struct compare{
    bool operator()(const Node &a, const Node &b){
        return a.cost > b.cost;
    }
};

vector<Node> adjList[VMAX+5];
vector<int> delList[VMAX+5];

int dist[VMAX+5];
int visited[505];

void dijkstra(int start) {
    priority_queue<Node, vector<Node>, compare> PQ;

    dist[start] = 0;
    PQ.push({start, 0});

    while (!PQ.empty()) {
        Node now = PQ.top();

        PQ.pop();
        if (dist[now.dest] < now.cost)
            continue;

        for (Node next : adjList[now.dest]) {
            // 최단거리로 지워진 간선은 건너뛴다.
            if(next.cost == -1) continue; 

            // cost 가 더 작을 때만 갱신하고 PQ큐에 넣음
            if (dist[next.dest] > next.cost + now.cost) {
                dist[next.dest] = next.cost + now.cost;
                PQ.push({next.dest, dist[next.dest]});
                // 새로운 최단거리가 있음으로 이전 것을 초기화한다.
                delList[next.dest].clear(); 
                // 새로운 최단거리 노드를 넣는다.
                delList[next.dest].push_back(now.dest); 

            }
            // 같은 비용이라면, 최소거리 경로가 여러개 라는 의미이다.
            else if(dist[next.dest] == next.cost + now.cost){ 
                delList[next.dest].push_back(now.dest);
            }
        }
    }
}

void del_bfs(int start) {

    queue<int> Q;
    Q.push(start);

    while (!Q.empty()) {
        int now = Q.front();
        Q.pop();

        if(visited[now] != 0){
            continue;
        }
        visited[now] = 1;

        for(int i=0;i<delList[now].size();i++){
            int next = delList[now][i];

            for (int j = 0; j < adjList[next].size(); j++) {
                if(adjList[next][j].dest == now){
                    adjList[next][j].cost = -1;
                }
            }
            Q.push(next);
        }
    }
}

int main() {
    while(1){
        int N, M, S, E;
        scanf(" %d %d", &N, &M);
        if(N==0&&M==0){
            break;
        }

        scanf(" %d %d", &S, &E);

        // 인접리스트 초기화
        for (int i = 0; i < N + 1; i++) {
            adjList[i].clear();
            delList[i].clear();
            dist[i] = MAX_VALUE;
            visited[i] = 0;
        }

        for (int i = 0; i < M; i++) {
            int a, b, c;
            scanf(" %d %d %d", &a, &b, &c);
            adjList[a].push_back({b, c});
        }

        dijkstra(S);

        del_bfs(E);

        for (int i = 0; i < N + 1; i++) {
            dist[i] = MAX_VALUE;
        }       
        dijkstra(S);

        if (dist[E] == MAX_VALUE) {
            printf("-1\n");
        }else{
            printf("%d\n", dist[E]);
        }
    }
}