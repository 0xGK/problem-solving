#include <iostream>
#include <algorithm>
#include<vector>
#define MAX_N 2501
using namespace std;
typedef unsigned long long ull;
double C[MAX_N][MAX_N];



int main()
{
	ios::sync_with_stdio(false);cin.tie(nullptr);cout.tie(nullptr);
	for (int i = 1; i < MAX_N; i++) {
		C[i][0] = C[i][i] = 1;
	}
	for (int i = 2; i < MAX_N; i++) {
		for (int j = 1; j < MAX_N; j++) {
			C[i][j] = C[i - 1][j - 1] + C[i - 1][j];
		}
	}



	int K,N, M;
	cin >> M;
	int color_cnt[MAX_N];
	for (int i = 1; i <= M; i++) {
		cin >> color_cnt[i];
	}

	cin >> K;
	N = 0;
	double ans = 0;
	for (int i = 1; i <= M; i++) {
		ans += C[color_cnt[i]][K];
		N += color_cnt[i];
	}

	double ret = ans/(double)C[N][K];
	cout.precision(11);
	cout << ret;
	return 0;
}
