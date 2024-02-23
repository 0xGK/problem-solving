#include<iostream>
#include<algorithm>
#include<queue>
#define fastio cin.tie(0);cout.tie(0);ios::sync_with_stdio(0);
#define endl '\n'
#define blank ' '
#define MAX_N 501
using namespace std;

int N;
int matrix[MAX_N][2];
//dp[i][j]: i~j번째까지 matrix의 곱의 연산 수
int dp[MAX_N][MAX_N];

void Solve() {
	cin >> N;
	for (int i = 1; i <= N; i++) {
		cin >> matrix[i][0];
		cin >> matrix[i][1];
	}
	for (int i = 0; i <= N; i++) {
		for (int j = 0; j <= N; j++) {
			dp[i][j] = 0;
		}
	}
	for (int i = 1; i < N; i++) {
		dp[i][i + 1] = matrix[i][0] * matrix[i][1] * matrix[i+1][1];
	}
	for (int m = 2; m <= N; m++) {
		for (int i = 1; i <= N - m; i++) {
			int j = i + m;
			for (int k = i; k < j; k++) {
				int cal = dp[i][k] + dp[k + 1][j] + (matrix[i][0] * matrix[k][1] * matrix[j][1]);
				if (!dp[i][j] || dp[i][j] > cal) {
					dp[i][j] = cal;
			}
			}
		}
	}
	cout << dp[1][N] << endl;
}

int main() {
	fastio;
	//freopen("input.txt", "r", stdin);
	Solve();

	return 0;
}