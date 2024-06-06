#include<iostream>
#include<algorithm>
#include<string>
#define fastio cin.tie(0);cout.tie(0);ios::sync_with_stdio(0);
#define endl '\n'
#define MAX_N 4001
#define blank ' '
using namespace std;

int dp[MAX_N][MAX_N];


void Solve() {

	string str1, str2;
	cin >> str1 >> str2;
	int N = max(str1.size(), str2.size());
	for (int i = 0; i <= N; i++) {
		dp[i][0] = dp[0][i] = 0;
	}
	int ans = 0;
	for (int i = 1; i <= str1.length(); i++) {
		for (int j = 1; j <= str2.length(); j++) {
			if (str1[i-1] == str2[j-1]) {
				dp[i][j] = dp[i - 1][j - 1] + 1;
			}
			ans = max(ans, dp[i][j]);
		}
	}
	cout << ans << endl;
}

int main() {
	fastio;
	//freopen("input.txt", "r", stdin);
	Solve();

	return 0;
}