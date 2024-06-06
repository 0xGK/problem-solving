#include <iostream>
#include <algorithm>

using namespace std;
int dp[12][12];
int main()
{
	ios::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);
	int N, K;

	cin >> N>>K;
	for (int i = 0; i <= 10;i++) {
		dp[i][0] = dp[i][i] = 1;
	}
	for (int i = 2; i <= 11; i++) {
		for (int j = 1; j <= 11; j++) {
			dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
		}
	}
	cout << dp[N][K] << endl;


	return 0;
}
