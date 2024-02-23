#include<iostream>
#include<algorithm>
using namespace std;
#define endl '\n'
#define MAX_N 100001
int dp[MAX_N];
int arr[MAX_N];
int N, M;

void Solve() {
	int N, M; cin >> N>>M;
	for (int i = 1; i <= N; i++) {
		cin >> arr[i];
	}
	dp[0] = 0;
	for (int i = 1; i <= N; i++) {
		dp[i] = dp[i - 1] + arr[i];
	}
	for (int i = 0; i < M; i++) {
		int a, b; cin >> a >> b;
		cout << dp[b] - dp[a - 1] << endl;
	}
	

}
int main() {
	cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
	Solve();
	return 0;
}