#include<iostream>
#include<algorithm>
#include<string>
using namespace std;
#define endl '\n'
#define MAX_N 101
#define down 0
#define right 1
#define impossible 0
#define possible 1
#define MOD 100000
typedef unsigned long long ull;

ull dp[2][2][MAX_N][MAX_N];

void Solve() {
	for (int i = 0; i < MAX_N; i++) {
		for (int j = 0; j < MAX_N; j++) {
			dp[0][0][i][j] = dp[0][1][i][j] = dp[1][0][i][j] = dp[1][1][i][j] = 0;
		}
	}
	int W, H; cin >> W >> H;

	for (int i = 1; i <= W; i++) {
		dp[possible][right][i][1] = 1;
	}
	for (int i = 1; i <= H; i++) {
		dp[possible][down][1][i] = 1;
	}



	for (int i = 2; i <= W; i++) {
		for (int j = 2; j <= H; j++) {
			dp[possible][right][i][j] = (dp[possible][right][i - 1][j] + dp[impossible][right][i - 1][j]) % MOD;
			dp[impossible][right][i][j] = dp[possible][down][i - 1][j];
			dp[possible][down][i][j] = (dp[possible][down][i][j-1] + dp[impossible][down][i][j-1]) % MOD;
			dp[impossible][down][i][j] = dp[possible][right][i][j-1];
		}
	}

	ull ret = (dp[possible][right][W][H]+ dp[impossible][right][W][H]+ dp[possible][down][W][H]+ dp[impossible][down][W][H]) % MOD;
	cout << ret << endl;


}
int main() {
	cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
	//freopen("input.txt", "r", stdin);
	Solve();
	return 0;
}