#include<iostream>
#include<algorithm>
#include<vector>
#include<queue>
using namespace std;
const int MAX = 32001;
int inDegree[MAX];
vector<int> DAG[MAX];
int n, m;
// 위상 정렬
void topologySort() {
	queue<int> q;
	vector<int>ret;
	for (int i = 1; i <= n; i++) {
		if (inDegree[i] == 0) {
			q.push(i);
		}
	}
	for (int i = 1; i <= n; i++) {
		// cycle 발생
		if (q.empty()) return;

		int x = q.front(); q.pop();
		ret.push_back(x);
		for (int j = 0; j < DAG[x].size(); j++) {
			if (--inDegree[DAG[x][j]]==0) {
				q.push(DAG[x][j]);
			}
		}
	}
	for (int i = 0; i < n; i++) {
		cout << ret[i] << " ";
	}
}
int main() {
	cin.tie(0); ios::sync_with_stdio(0);
	cin >> n >> m;
	for (int i = 1; i <= n; i++) {
		inDegree[i] = 0;
	}
	for (int i = 0; i <m; i++) {
		int a, b; cin >> a >> b;
		DAG[a].push_back(b);
		inDegree[b]++;
	}
	topologySort();

	return 0;
}