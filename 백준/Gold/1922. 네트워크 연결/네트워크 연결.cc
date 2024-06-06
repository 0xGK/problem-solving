#include<iostream>
#include<algorithm>
#include<vector>
#include<queue>

using namespace std;
const int MAX = 1001;
int parent[MAX];

typedef struct Edge {
	int a;
	int b;
	int cost;
}Edge;

struct compare {
	bool operator()(Edge &a, Edge &b) {
		return a.cost > b.cost;
	}
};

priority_queue<Edge, vector<Edge>, compare> pq;

int getParent(int a) {
	if (parent[a] == a) return a;
	return parent[a] = getParent(parent[a]);
}
int isConnected(int a, int b) {
	int x = getParent(a);
	int y = getParent(b);
	return x == y;
}
void _union(int a, int b) {
	int x = getParent(a);
	int y = getParent(b);
	if (x < y) {
		parent[y] = x;
	}
	else {
		parent[x] = y;
	}

}


int n, m;
// 위상 정렬
int main() {
	cin.tie(0); ios::sync_with_stdio(0);
	cin >> n >> m;

	for (int i = 1; i <= n; i++) {
		parent[i] = i;
	}


	for (int i = 0; i < m; i++) {
		int a, b, c; cin >> a >> b >> c;
		Edge e = { a, b, c };
		pq.push(e);
	}
	int ret = 0;
	while(!pq.empty()){
		Edge e = pq.top();
		pq.pop();
		if (!isConnected(e.a, e.b)) {
			_union(e.a, e.b);
			ret += e.cost;
		}
	}
	cout << ret << endl;


	return 0;
}