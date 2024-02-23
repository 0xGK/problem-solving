#include<iostream>
#include<algorithm>
#include<vector>
#define MAX_N 901
const long long INF = 900'000'000;
using namespace std;

int W, H, G, X, Y, E;
int check[MAX_N];

typedef struct {
	int src;
	int dest;
	int cost;
}Edge;
vector<Edge> edges;
long long dist[MAX_N];
void init() {
	edges.clear();
	for (int i = 0; i < MAX_N; i++) {
		check[i] = 1;
		dist[i] = INF;
	}
}
void bellman() {
	dist[0] = 0;
	int N = W * H;

	for (int i = 0; i < N - 1; i++) {
		for (auto edge : edges) {
			if (dist[edge.src] == INF) continue;
			if (dist[edge.dest] > dist[edge.src] + edge.cost)
				dist[edge.dest] = dist[edge.src] + edge.cost;
		}
	}
	for (auto edge : edges) {
		if (dist[edge.src] == INF) continue;
		if (dist[edge.dest] > dist[edge.src] + edge.cost) {
			cout << "Never\n";
			return;
		}

	}
	if (dist[N - 1] == INF) {
		cout << "Impossible\n";
	}
	else {
		cout << dist[N - 1] << '\n';
	}

}

void Solve() {
	int dx[4] = { 1,-1,0,0 };
	int dy[4] = { 0,0,1,-1 };

	for (int y = 0; y < H; y++) {
		for (int x = 0; x < W; x++) {
			int src = W * y + x;
			if (check[src] == 2 || check[src] == 3 || src == W*H-1) continue;
			for (int j = 0; j < 4; j++) {
				int nx, ny;
				nx = x + dx[j];
				ny = y + dy[j];
				int dest = W * ny + nx;
				
				if (nx >= 0 && nx <= W - 1 && ny >= 0 && ny <= H - 1 && check[dest] != 2) {
					edges.push_back({ src, dest,1 });
				}
			}
		}
	}
	bellman();


}

int main() {
	cin.tie(0); ios::sync_with_stdio(0);
	while (1) {
		cin >> W >> H;
		init();
		if (W == 0 && H == 0) return 0;

		cin >> G;
		for (int i = 0; i < G; i++) {
			int x, y; cin >> x >> y;
			check[W * y + x] = 2;
		}
		cin >> E;
		for (int i = 0; i < E; i++) {
			int x, y, x_, y_, T;
			cin >> x >> y >> x_ >> y_ >> T;
			int cur = W * y + x;
			int nxt = W * y_ + x_;
			check[cur] = 3;
			edges.push_back({ cur, nxt, T });
		}
		Solve();


	}
	return 0;
}