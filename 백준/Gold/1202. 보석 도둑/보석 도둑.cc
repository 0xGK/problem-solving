#include<iostream>
#include<algorithm>
#include<queue>
#include<vector>

using namespace std;
vector<int> bag;
priority_queue<int> pq;
pair<int, int> gem[300000];
void run() {
	int N, K; cin >> N >> K;
	for (int i = 0; i < N; i++) {
		int M, V; cin >> M >> V;
		gem[i]=make_pair(M, V);

	}
	for (int i = 0; i < K; i++) {
		int C; cin >> C;
		bag.push_back(C);
	}
	sort(gem, gem + N);
	sort(bag.begin(), bag.end());

	long long sum = 0;
	int cnt = 0;
	for (int i = 0; i < K; i++) {
		while (cnt<N && gem[cnt].first<=bag[i]) {
			pq.push(gem[cnt].second); cnt++;
		}
		if (!pq.empty()) {
			sum+=pq.top();
			pq.pop();
		}
		
	}
	cout << sum;
}
int main() {
	cin.tie(0); ios::sync_with_stdio(0);
	run();
	return 0;
}
