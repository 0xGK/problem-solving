#include<iostream>
#include<vector>
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
vector<Node> re_adj[MAX_N];
int dist[MAX_N];
int re_dist[MAX_N];
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
				pq.push({ dest, dist[dest] });
            }
        }
    }
}

void re_dijkstra(int start) {
	priority_queue<Node, vector<Node>, compare> pq;
	for (int i = 1; i <= V; i++) {
		re_dist[i] = MAX_VALUE;
		visited[i] = false;
	}

	re_dist[start] = 0;
	pq.push({ start, 0 });

	while (!pq.empty()) {
		Node cur = pq.top();
		pq.pop();
		int src = cur.dest;

		if (visited[cur.dest])
			continue;
		visited[cur.dest] = true;
		for (Node next : re_adj[src]) {
			int dest = next.dest;
			if (re_dist[dest] > next.dist + re_dist[src]) {
				re_dist[dest] = next.dist + re_dist[src];
				pq.push({ dest, re_dist[dest] });
			}
		}
	}
}


int main() {
	cin.tie(0); ios::sync_with_stdio(0);
	cin >> V >> E >> X;

    for (int i = 0; i < E; i++) {
        int a, b, c;
		cin >> a >> b >> c;
		adj[a].push_back({ b,c });
		re_adj[b].push_back({ a,c });
    }
	dijkstra(X); 
	re_dijkstra(X);

	int longest_time = 0;
	for (int i = 1; i <= V; i++) {
		int ist_dist = dist[i] + re_dist[i];
		if (longest_time < ist_dist) {
			longest_time = ist_dist;
		}
	}
	cout << longest_time << endl;



}