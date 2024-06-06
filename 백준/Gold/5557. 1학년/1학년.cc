#include<iostream>
#include<algorithm>
#include<string>
using namespace std;
#define endl '\n'
#define MAX_N 101
typedef unsigned long long ull;

ull dp[MAX_N][21];

void Solve() {
	for (int i = 0; i < MAX_N; i++) {
		for (int j = 0; j < 21; j++) {
			dp[i][j] = 0;
		}
	}
	int arr[101];
	int N; cin >> N;
	for (int i = 1; i <= N; i++) {
		cin >> arr[i];
	}
	dp[1][arr[1]]++;

	for (int i = 2; i < N; i++) {
		for (int j = 0; j <= 20; j++) {
			if (dp[i - 1][j] > 0) {
				int n1 = j + arr[i];
				int n2 = j - arr[i];
				if (n1 >= 0 && n1 <= 20) {
					dp[i][n1]+= dp[i - 1][j];
					//cout << i << "th: " << n1 << endl;
				}
				if (n2 >= 0 && n2 <= 20) {
					dp[i][n2]+= dp[i - 1][j];
					//cout << i << "th: " << n2 << endl;
				}
			}
		}
	}

	cout << dp[N - 1][arr[N]] << endl;
}
int main() {
	cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
	//freopen("input.txt", "r", stdin);
	Solve();
	return 0;
}