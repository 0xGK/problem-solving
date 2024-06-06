#include<iostream>
#include<algorithm>
#include<vector>
#include<queue>

using namespace std;

const int MAX = 501;
int time[MAX];
int inDegree[MAX];
int hist[MAX];
vector<int> adj[MAX];

int main() {
	cin.tie(0); ios::sync_with_stdio(0);
	int n; cin >> n;
	
	for (int i = 1; i <= n; i++) {
		inDegree[i] = 0;
		hist[i] = 0;
	}
	for (int i = 1; i <= n; i++) {
		cin >> time[i];
		while (1) {
			int k; cin >> k;
			if (k < 0) break;
			adj[k].push_back(i);
			inDegree[i]++;
		}
	}
	queue<int> q;
	for (int i = 1; i <= n; i++) {
		if (inDegree[i] == 0) q.push(i);
	}

	while (!q.empty()) {
		int cur = q.front(); q.pop();
		for (auto next : adj[cur]) {
			if (hist[cur] + time[cur] > hist[next]) {
				hist[next] = hist[cur] + time[cur];
			}
			if (--inDegree[next] == 0) {
				q.push(next);
			}
		}
	}

	for (int i = 1; i <= n; i++)
	{
		cout << hist[i] + time[i] << '\n';
	}

	return 0;
}