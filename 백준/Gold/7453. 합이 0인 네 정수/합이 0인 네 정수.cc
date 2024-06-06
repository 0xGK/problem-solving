#include <iostream>
#include <cstring>
#include <algorithm>
#define MAX 4000

using namespace std;

int main()
{
	cin.tie(0); ios::sync_with_stdio(false);

	int N = 0;
	cin >> N;

	int A[MAX];
	int B[MAX];
	int C[MAX];
	int D[MAX];

	for (int i = 0; i < N; i++)
	{
		cin >> A[i] >> B[i] >> C[i] >> D[i];
	}

	int AB[MAX*MAX], CD[MAX*MAX];

	// AB 배열 합 구하기 
	int i = 0;
	for (int a = 0; a < N; a++)
	{
		for (int b = 0; b < N; b++)
		{
			AB[i] = A[a] + B[b];
			i++;
		}
	}

	// CD 배열 합 구하기 
	int j = 0;
	for (int c = 0; c < N; c++)
	{
		for (int d = 0; d < N; d++)
		{
			CD[j++] = C[c] + D[d];
		}
	}

	// AB, CD 배열 정렬하기
	sort(AB, AB + (N*N));
	sort(CD, CD + (N*N));


	long long cnt = 0;
	int ab = 0, cd = N * N - 1;

	while (ab < N * N && cd >= 0)
	{
		int sum = AB[ab] + CD[cd];
		if (sum == 0)
		{
			long long ab_cnt = 0, cd_cnt = 0;

			int ab_val = AB[ab];
			while (ab < N * N && AB[ab] == ab_val)
			{
				ab++;
				ab_cnt++;
			}

			int cd_val = CD[cd];
			while (cd >= 0 && CD[cd] == cd_val)
			{
				cd--;
				cd_cnt++;
			}
			cnt += ab_cnt * cd_cnt;
		}
		else if (sum > 0)
		{
			cd--;
		}
		else
		{
			ab++;
		}
	}

	cout << cnt << endl;
	return 0;
}