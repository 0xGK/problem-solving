#include<iostream>
#include<algorithm>
#include<string>
using namespace std;
#define endl '\n'
#define MAX_N 1024
int dp[MAX_N][MAX_N];
int N, M;

void Solve() {
	int N, M; cin >> N >> M;
	for (int i = 0; i < MAX_N; i++) {
		dp[i][0] = dp[0][i] = 0;
	}
	for (int i = 1; i <= N; i++) {
		string str; cin >> str;
		for (int j = 0; j < str.size(); j++) {
			dp[i][j+1] = str[j] - 48;
		}
	}

	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= M; j++) {
			int size = min(dp[i - 1][j - 1], min(dp[i - 1][j], dp[i][j - 1]));
			if (size>0 && dp[i][j] == 1){
				dp[i][j] = size + 1;
			}
		}
	}
	int cnt=0;

	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= M; j++) {
			if (dp[i][j] > cnt) {
				cnt = dp[i][j];
			}
		}
	}


	cout << cnt * cnt <<endl;

	//view
	/*
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= M; j++) {
			cout << dp[i][j] << " ";
		}
		cout << endl;
	}
	*/
}
int main() {
	cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
	//freopen("input.txt", "r", stdin);
	Solve();
	return 0;
}