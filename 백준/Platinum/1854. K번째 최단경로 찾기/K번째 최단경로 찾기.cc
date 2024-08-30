#include<iostream>
#include<algorithm>
#include<vector>
#include<queue>

using namespace std;
#define MAX_N 1002

typedef struct Node{
    int dest;
    int cost;
}Node;

struct compare {
	bool operator()(const Node &a, const Node &b) {
		return a.cost > b.cost;
	}
};

vector<Node> graph[MAX_N];
priority_queue<int> kth_pq[MAX_N];
int N, M, K;

void init(){
    cin >> N >> M >> K;
    for(int i = 0; i < M; i++){
        int a, b, c;
        cin >> a >> b >> c;
        graph[a].push_back({b, c});
    }
}

void dijkstra(int start){
    priority_queue<Node, vector<Node>, compare> pq;
    pq.push({start, 0});
    kth_pq[1].push(0);
    while(!pq.empty()){
        Node cur = pq.top(); pq.pop();
        for(auto next : graph[cur.dest]){
            int dest = next.dest;
            int cost = next.cost + cur.cost;
            if(kth_pq[dest].size() < K){
                kth_pq[dest].push(cost);
                pq.push({dest, cost});
            }else if(kth_pq[dest].top() > cost){
                kth_pq[dest].pop();
                kth_pq[dest].push(cost);
                pq.push({dest, cost});
            }
        }
    }
}

void solve(){
    dijkstra(1);
    for(int i = 1; i <= N; i++){
        if(kth_pq[i].size() < K) cout << -1 << '\n';
        else cout << kth_pq[i].top() << '\n';
    }

}

int main(){
    ios::sync_with_stdio(0); cin.tie(0); cout.tie(0);
    // freopen("input.txt", "r", stdin);
    init();
    solve();
    return 0;
}