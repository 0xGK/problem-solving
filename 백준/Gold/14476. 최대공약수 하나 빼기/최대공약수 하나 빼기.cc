#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int gcd(int a, int b) {
	if (b == 0) return a;
	return gcd(b, a % b);
}

int main() {
	vector<int> num;
	int n; cin >> n;
	vector<int> L_arr(n);
	vector<int> R_arr(n);

	for (int i = 0; i < n; i++) {
		int k; cin >> k;
		num.push_back(k);
	}

	L_arr[0] = num[0];
	R_arr[n - 1] = num[n-1];


	for (int i = 1; i <= n - 1; i++) {
		int L_val = L_arr[i - 1];
		L_arr[i] = gcd(max(L_val, num[i]), min(L_val, num[i]));
		int R_val = R_arr[n - i];
		R_arr[n - 1 - i] = gcd(max(R_val, num[n - 1 - i]), min(R_val, num[n - 1 - i]));
	}


	int ret = -1;
	int K = -1;

	for (int i = 0; i < n; i++) {
		if (i == 0) {
			ret = max(ret, R_arr[1]);
			K = num[i];
		}
		else if (i == n - 1) {
			if (ret < L_arr[n - 2]) {
				ret = L_arr[n - 2];
				K = num[i];
			}
		}
		else {
			if (ret < gcd(L_arr[i - 1], R_arr[i + 1])) {
				ret = gcd(L_arr[i - 1], R_arr[i + 1]);
				K = num[i];
			}
		}
	}

	if (K % ret == 0) {
		cout << -1;
	}
	else {
		cout << ret << " " << K;
	}

	return 0;
}