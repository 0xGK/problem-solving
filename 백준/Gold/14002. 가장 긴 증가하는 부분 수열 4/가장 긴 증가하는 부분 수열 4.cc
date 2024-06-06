#include<iostream>
#include<algorithm>
#include<string>
using namespace std;
#define endl '\n'
#define MAX_N 1001

int dp[MAX_N];
int arr[MAX_N];
int hist[MAX_N];
int n;

void printPath(int idx, int n) {
	if (n == 0) return;
	printPath(hist[idx], --n);
	cout << arr[idx] << " ";
}
void Solve() {
	cin >> n;
	for (int i = 1; i <= n; i++) {
		cin >> arr[i];
	}
	int last = 1;
	int Length = 1;
	for (int i = 1; i <= n; i++) {
		dp[i] = 1;
		for (int j = 1; j < i; j++) {
			if (arr[j] < arr[i] && dp[j] + 1 > dp[i]) {
				dp[i] = dp[j] + 1;
				hist[i] = j;
				if (Length < dp[i]) {
					last = i;
					Length = dp[i];
				}
			}
		}
	}
	cout << Length << endl;
	printPath(last, Length);

}
int main() {
	cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
	//freopen("input.txt", "r", stdin);
	Solve();
	return 0;
}