#include<iostream>
#include<algorithm>
using namespace std;
#define endl '\n'
#define MAX_N 501
int dp[MAX_N][MAX_N];
void Solve() {
	int n; cin >> n;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j <= i; j++) {
			int k; cin >> k;
			dp[i][j] = k;
		}
	}
	for (int i = 1; i < n; i++) {
		for (int j = 0; j <= i; j++) {
			dp[i][j] = max(dp[i][j] + dp[i - 1][j], dp[i][j] + dp[i - 1][j - 1]);
		}
	}
	int MAX = 0;
	for (int i = 0; i < n; i++) {
		MAX = max(MAX, dp[n - 1][i]);
	}
	cout << MAX<<endl;

}
int main() {
	cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
	Solve();
	return 0;
}