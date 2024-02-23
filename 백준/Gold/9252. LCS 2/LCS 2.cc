#include<iostream>
#include<algorithm>
#include<string>
#define fastio cin.tie(0);cout.tie(0);ios::sync_with_stdio(0);
#define endl '\n'
#define MAX_N 1001
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
	for (int i = 1; i <= str1.size(); i++) {
		for (int j = 1; j <= str2.size(); j++) {
			if (str1[i-1] == str2[j-1]) {
				dp[i][j] = dp[i - 1][j - 1] + 1;
			}
			else {
				dp[i][j] = max(dp[i][j - 1], dp[i - 1][j]);
			}
		}
	}
	cout << dp[str1.size()][str2.size()] << endl;
	
	//for (int i = 1; i <= str1.size(); i++) {for (int j = 1; j <= str2.size(); j++) {cout << dp[i][j] << blank;}cout << endl;}
	
	string ans = "";
	int i = str1.size();
	int j = str2.size();
	int len = dp[i][j];
	while (len) {
		if (len-1 == max(dp[i - 1][j], dp[i][j - 1])) {
			ans = str1[i-1]+ans;
			i--; j--;
			len--;
		}
		else if (dp[i - 1][j] < dp[i][j - 1]) {
			j--;
		}
		else {
			i--;
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