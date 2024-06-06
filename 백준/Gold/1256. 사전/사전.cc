#include <iostream>
#include <algorithm>
#include<vector>
#define MAX_N 201
#define MAX 1'000'000'000
using namespace std;
typedef unsigned long long ull;
ull C[MAX_N][MAX_N];
int main()
{
	ios::sync_with_stdio(false);
	cin.tie(nullptr);
	cout.tie(nullptr);
	int N, M;
	ull K;
	cin >> N>>M>>K;

	for (int i = 0; i < MAX_N;i++) {
		C[i][0] = C[i][i] = 1;
	}
	for (int i = 2; i < MAX_N; i++) {
		for (int j = 1; j < MAX_N; j++) {
			C[i][j] = (C[i - 1][j - 1] + C[i - 1][j]);
			if (C[i][j] > MAX)
				C[i][j] = MAX;
		}
	}
	if (C[N+M][M] < K) {
		cout << -1 << endl;
		return 0;
	}
	vector<char> str;

	while (M + N > 0) {
		if (C[M+N - 1][M] < K) {
			str.push_back('z');
			K -= C[M+N - 1][M];
			M--;
		}
		else {
			str.push_back('a');
			N--;
		}
	}
	for (auto s : str) {
		cout << s;
	}
	return 0;
}
