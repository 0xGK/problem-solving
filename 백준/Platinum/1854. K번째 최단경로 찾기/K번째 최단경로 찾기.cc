#include <iostream>
#include <vector>
#include <queue>

using namespace std;

typedef struct Node {
	int dest;
	int cost;
}Node;

int N, M, K;
vector< Node > adj[1002];
priority_queue<int> kth_pq[1002];
struct compare {
	bool operator()(const Node &a, const Node &b) {
		return a.cost > b.cost;
	}
};
void dijkstra() {
	priority_queue< Node, vector<Node>, compare > pq;
	pq.push({ 1, 0 });
	kth_pq[1].push(0);

	while (!pq.empty()) {
		auto cur = pq.top(); pq.pop();
		int src = cur.dest;

		for (auto next : adj[src]) {
			int dest = next.dest;
			int cost = next.cost + cur.cost;

			if (kth_pq[dest].size() < K) {
				kth_pq[dest].push(cost);
				pq.push({ dest, cost });
			}
			else if (kth_pq[dest].top() > cost) {
				kth_pq[dest].pop();
				kth_pq[dest].push(cost);
				pq.push({ dest, cost });
			}
		}
	}
}


int main()
{

	ios_base::sync_with_stdio(0);
	cin.tie(0), cout.tie(0);

	cin >> N >> M >> K;

	for (int i = 0; i < M; i++) {
		int u, v, d;

		cin >> u >> v >> d;

		adj[u].push_back({v,d});
	}

	dijkstra();

	for (int i = 1; i <= N; i++) {
		if (kth_pq[i].size() != K) cout << -1 << "\n";
		else cout << kth_pq[i].top() << "\n";
	}

	return 0;
}