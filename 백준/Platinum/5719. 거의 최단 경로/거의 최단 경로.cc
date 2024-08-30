#include <iostream>
#include <vector>
#include <queue>

#define MAX_N 501
#define INF 987654321

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

vector<Node> adjList[MAX_N];
vector<int> delList[MAX_N];

int dist[MAX_N];
int visited[MAX_N];

void dijkstra(int start) {
    priority_queue<Node, vector<Node>, compare> pq;

    dist[start] = 0;
    pq.push({start, 0});

    while (!pq.empty()) {
        Node cur = pq.top(); pq.pop();
        auto src=cur.dest;
        if (dist[src] < cur.cost)
            continue;

        for (Node next : adjList[src]) {
            if(next.cost == -1) continue; 

            int dest = next.dest;
            int cost = next.cost + cur.cost; // cur.cost이 값이 dist[src]이다.

            if (dist[dest] > cost) {
                dist[dest] = cost;
                pq.push({dest, cost});
                delList[dest].clear(); 
                delList[dest].push_back(src); 

            }
            else if(dist[dest] == cost){ 
                delList[dest].push_back(src);
            }
        }
    }
}

void del_bfs(int end) {
    queue<int> q;
    q.push(end);

    while (!q.empty()) {
        int cur = q.front(); q.pop();

        if(visited[cur] != 0) continue;
        visited[cur] = 1;

        for(auto src : delList[cur]){
            for(auto& edge : adjList[src]){
                if(edge.dest == cur){
                    edge.cost = -1;
                }
            }
            q.push(src);
        }

    }
}

void init(){
    for (int i = 0; i < MAX_N; i++) {
        adjList[i].clear();
        delList[i].clear();
        dist[i] = INF;
        visited[i] = 0;
    }
}

int main() {
    while(1){
        int N, M, S, E;
        scanf("%d %d", &N, &M);
        if(N==0 && M==0){
            break;
        }

        init();

        scanf("%d %d", &S, &E);
        for (int i = 0; i < M; i++) {
            int a, b, c;
            scanf("%d %d %d", &a, &b, &c);
            adjList[a].push_back({b, c});
        }

        dijkstra(S);
        del_bfs(E);

        for (int i = 0; i <= N; i++) {
            dist[i] = INF;
        }       
        dijkstra(S);

        if (dist[E] == INF) {
            printf("-1\n");
        }else{
            printf("%d\n", dist[E]);
        }
    }
}