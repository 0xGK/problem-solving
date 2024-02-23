#include<iostream>
#include<algorithm>
#include<vector>
#include<queue>

using namespace std;
#define MAX 100001
#define LGN 17
// 2^16 (65,536) < N = 100,000 < 2^17 (131,072)
//LGN = 5; // 2^4 (16) < N = 18 < 2^5 (32)
// 워프할 예정

int parent[LGN+1][MAX];
int depth[MAX];
bool visited[MAX];
vector<int> adj[MAX];
int n;
void bfs(int start, int dep) {
	queue<int> q;
	q.push(start);
	depth[start] = dep;
	visited[start] = true;
	parent[0][1] = 1;

	while (!q.empty()) {
		int cur = q.front(); q.pop();
		for (auto next : adj[cur]) {
			if (!visited[next]) {
				visited[next] = true;
				parent[0][next] = cur;
				depth[next] = depth[cur] + 1;
				q.push(next);
			}
		}
	}

}
//루트 이상은 전부 루트로 나옴
void make_aces() {
	for (int K = 1; K <= LGN; K++) {
		for (int V = 1; V <= n; V++) {
			// K = 2, V = 17 parent[2][17] = parent[1][11]
			// 17의 2번 위 부모 = 17의 부모의 부모다
			// K = 1, V = 17 parent[1][17] = parent[0][14]
			parent[K][V] = parent[K - 1][parent[K - 1][V]];
		}
	}
}


int lca(int x, int y) {
	// y가 더 깊은 것으로 설정
	if (depth[x] > depth[y]) {
		int tmp = x;
		x = y;
		y = tmp;
	}
	// y의 depth를 x까지 끌어올리기
	for (int i = LGN; i >= 0; i--) {
		if (depth[y] - depth[x] >= (1 << i)) {
			y = parent[i][y];
		}
	}
	if (x == y) return x;
	// root 이상은 전부 root라서 parent가 같다가, 달라지는 곳을 찾아야함.
	for (int i = LGN; i >= 0; i--) {
		if (parent[i][x] != parent[i][y]) {
			x = parent[i][x];
			y = parent[i][y];
		}
	}
	return parent[0][x];


}

int main() {
	cin.tie(0); ios::sync_with_stdio(0);
	cin >> n;
	for (int i = 1; i < n; i++) {
		int a, b; cin >> a >> b;
		adj[a].push_back(b);
		adj[b].push_back(a);
	}
	for (int i = 1; i <= n; i++) {
		parent[0][i]=0;
		depth[i] = 0;
		visited[i] = false;
	}

	bfs(1, 0);
	make_aces();

	int m; cin >> m;
	for (int i = 0; i < m; i++) {
		int a, b; cin >> a >> b;
		cout << lca(a, b) << '\n';
	}
	return 0;
}