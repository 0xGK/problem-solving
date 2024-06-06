#include<iostream>
#include<algorithm>
#include<string>
using namespace std;
#define endl '\n'
#define MAX_N 301
int dp[3][MAX_N];
int N, M;

void Solve() {
	int N; cin >> N;
	int arr[MAX_N];
	for (int i = 1; i <= N; i++) {
		cin >> arr[i];
	}
	for (int cnt= 1; cnt <= 2; cnt++) {
		for (int i = 0; i <= N; i++) {
			dp[cnt][i] = 0;
		}
	}

	dp[1][1] = arr[1];
	for (int i = 2; i <= N; i++) {
		dp[1][i] = max(dp[1][i - 2], dp[2][i - 2]) + arr[i];
		dp[2][i] = dp[1][i-1]+arr[i];
	}
	int ret;
	ret = max(dp[1][N], dp[2][N]);

	//test
	/*
	for (int cnt = 1; cnt < 3; cnt++) {
		for (int i = 1; i <= N; i++) {
			cout << dp[cnt][i] << " ";
		}
		cout << endl;
	}
	*/


	cout << ret << endl;

}
int main() {
	cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
	//freopen("input.txt", "r", stdin);
	Solve();
	return 0;
}