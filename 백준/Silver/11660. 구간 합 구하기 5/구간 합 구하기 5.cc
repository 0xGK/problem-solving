#include<iostream>
#include<algorithm>
using namespace std;
#define endl '\n'
#define MAX_N 1025
int dp[MAX_N][MAX_N];
int arr[MAX_N][MAX_N];
int N, M;

void Solve() {
	int N, M; cin >> N>>M;
	for (int i = 1; i <= N; i++) {
		for (int j= 1; j <= N; j++) {
			cin >> dp[i][j];
		}
	}
	for (int i = 0; i <= N; i++) {
		dp[i][0] = dp[0][i] = 0;
	}

	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= N; j++) {
			dp[i][j] = dp[i][j]+ dp[i][j - 1]+ dp[i-1][j]- dp[i-1][j - 1];
		}
	}
	for (int i = 0; i < M; i++) {
		int x1, y1, x2, y2; cin >> x1 >> y1 >> x2 >> y2;
		cout << dp[x2][y2] - dp[x2][y1 - 1]- dp[x1 - 1][y2]+ dp[x1 - 1][y1 - 1] << endl;
	}
	

}
int main() {
	cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
	Solve();
	return 0;
}