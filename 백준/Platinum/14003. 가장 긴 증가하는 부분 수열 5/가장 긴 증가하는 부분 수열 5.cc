#include<iostream>
#include<algorithm>
#include<string>
using namespace std;
#define endl '\n'
#define MAX_N 1000001

int Len[MAX_N];
int arr[MAX_N];
int ans[MAX_N];
int index[MAX_N];
int n;

void Solve() {
	cin >> n;
	int idx = 0;
	for (int i = 1; i <= n; i++) {
		int tmp; cin >> tmp;
		arr[i] = tmp;
		if (idx == 0) {
			index[i] = 0;
			Len[idx++] = tmp;
		}
		else {
			if (Len[idx - 1] < tmp) {
				index[i] = idx;
				Len[idx++] = tmp;
			}
			else {
				int c_idx = lower_bound(Len, Len + idx, tmp) - Len;
				index[i] = c_idx;
				Len[c_idx] = tmp;
			}
		}
	}
	int t = 0;
	cout << idx << endl;
	for (int i = n; i >= 1; i--) {
		if (idx == index[i]+1) {
			ans[t++] = arr[i];
			idx--;
		}
	}
	for (int i = t - 1; i >= 0; i--) cout << ans[i] << ' ';

	/*
	for (int i = 0; i <= idx; i++) {
		cout << arr[index[i]] << " ";
	}
	*/

}
int main() {
	cin.tie(0); cout.tie(0); ios::sync_with_stdio(0);
	//freopen("input.txt", "r", stdin);
	Solve();

	return 0;
}