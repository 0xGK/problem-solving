#include<iostream>
#include<algorithm>
#include<string.h>
using namespace std;
int A[500001], B[500001], H[500001];

void run() {
	int n, h;
	cin >> n >> h;
	memset(A, 0, h+1);
	memset(B, 0, h+1);
	for (int i = 0; i < n; i++) {
		int k; cin >> k;
		if ((i % 2) == 0) {
			A[k]++;
		}
		else {
			B[k]++;
		}
	}

	for (int i = h - 1; i > 0; i--) {
		A[i] += A[i+1];
		B[i] += B[i+1];
	}
	for (int i = 1; i <= h; i++) {
		H[i] = A[i] + B[h - i + 1];
	}

	sort(H + 1, H + h + 1);
	
	cout << H[1] << " ";
	cout << upper_bound(H + 1, H + h + 1, H[1]) - lower_bound(H + 1, H + h + 1, H[1]);


}


int main() {
	cin.tie(0); ios::sync_with_stdio(0);
	run();

	return 0;
}