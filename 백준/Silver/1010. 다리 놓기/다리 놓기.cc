#include <iostream>
#include <algorithm>
#define MAX_N 31
using namespace std;
typedef unsigned long long ull;
ull C[MAX_N][MAX_N];
int main()
{
	ios::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);
	for (int i = 0; i < MAX_N;i++) {
		C[i][0] = C[i][i] = 1;
	}
	for (int i = 2; i < MAX_N; i++) {
		for (int j = 1; j < MAX_N; j++) {
			C[i][j] = (C[i - 1][j - 1] + C[i - 1][j]);
		}
	}
	int TC; cin >> TC;
	for (int tc = 1; tc <= TC; ++tc) {
		int N, K;
		cin >> K>>N;
		cout << C[N][K] << endl;


	}


	return 0;
}
