#include<iostream>
#include<vector>
#include<algorithm>
#include<queue>
#define MAX_N 1001
#define MAX_VALUE (1<<30)
#define endl '\n'
using namespace std;
typedef struct {
	int dest;
	int dist;
}Node;

vector<Node> adj[MAX_N];
int dist[MAX_N];
int path[MAX_N];
bool visited[MAX_N];
int V, E, X;
// 내림차순 처럼 보이지만, sort랑 반대로 이게 min heap
// sort랑 반대라고 생각하자
struct compare {
	bool operator()(const Node &a, const Node &b) {
		return a.dist > b.dist;
	}
};

void dijkstra(int start) {
    priority_queue<Node, vector<Node>, compare> pq;

    // dist 배열 (무한대 값으로) 초기화
    for (int i = 1; i <= V; i++) {
        dist[i] = MAX_VALUE;
		visited[i] = false;
    }

    dist[start] = 0;
    pq.push({ start, 0 });
	path[start] = start;
    while (!pq.empty()) {
        Node cur = pq.top();
        pq.pop();
		int src = cur.dest;

        if (visited[cur.dest])
            continue;
        visited[cur.dest] = true;
        for (Node next : adj[src]) {
			int dest = next.dest;
            if (dist[dest] > next.dist + dist[src]) {
                dist[dest] = next.dist + dist[src];
				path[dest] = src;
				pq.push({ dest, dist[dest] });
            }
        }
    }
}

int main() {
	cin.tie(0); ios::sync_with_stdio(0);
	cin >> V >> E;

    for (int i = 0; i < E; i++) {
        int a, b, c;
		cin >> a >> b >> c;
		adj[a].push_back({ b,c });
    }


	int S, D; cin >> S >> D;
	dijkstra(S);
	cout << dist[D] << endl;
	
	vector<int> route;
	while (D != S) {
		//cout << path[D] << " ";
		route.push_back(D);
		D = path[D];
	}
	route.push_back(S);


	cout << route.size() << endl;
	for (int i = route.size() - 1; i >= 0; i--) {
		cout << route[i] << " ";
	}
	
	/*
	for (int i = 1; i <= V; i++) {
		cout << i << " " << path[i] << endl;
	}
	*/

}