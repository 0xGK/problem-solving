#include <iostream>
#include <vector>
#include <algorithm>

#define max_log 20
#define max_size 987654321

int N, K;
std::vector<std::vector<std::pair<int, int>>> tree;
std::vector<std::vector<int>> sparse;
std::vector<std::vector<int>> minL;
std::vector<std::vector<int>> maxL;
std::vector<int> depth;

void dfs(int parent, int cur, int dep, int road_length)
{
	depth[cur] = dep;
	sparse[cur][0] = parent;
	minL[cur][0] = road_length;
	maxL[cur][0] = road_length;

	for (int i = 0; i < tree[cur].size(); i++)
	{
		if (tree[cur][i].first != parent)
		{
			dfs(cur, tree[cur][i].first, dep + 1, tree[cur][i].second);
		}
	}
	return;
}

void Memoization()
{
	for (int j = 1; j < max_log; j++)
	{
		for (int i = 2; i <= N; i++)
		{
			if (sparse[i][j - 1] != 0)
			{
				int prev = sparse[i][j - 1];
				sparse[i][j] = sparse[prev][j - 1];

				minL[i][j] = std::min(minL[i][j - 1], minL[prev][j - 1]);
				maxL[i][j] = std::max(maxL[i][j - 1], maxL[prev][j - 1]);
			}
		}
	}
}

std::pair<int, int> LCA(int a, int b)
{
	int mins = max_size;
	int maxs = -max_size;

	if (depth[a] != depth[b])
	{
		if (depth[a] < depth[b])
		{
			std::swap(a, b);
		}

		int diff = depth[a] - depth[b];

		for (int i = 0; diff > 0; i++) // diff가 한칸 오른쪽으로 이동해서 현재 조사하는 자릿수의 크기가 전 단계의 2배 만큼 증가했으므로 i도 2^1 만큼 증가시킴 1,2,4,8,16....
		{
			if (diff % 2 == 1) // 마지막 자리가 1이면 ex 1001, 1101, 0011....
			{
				mins = std::min(mins, minL[a][i]);
				maxs = std::max(maxs, maxL[a][i]);
				a = sparse[a][i];
			}

			diff = diff >> 1;      // diff = diff >> 1 옆으로 한칸씩 이동 ex) 1001 -> 0100, 1101 -> 0110, 0011 -> 0001....
		}
	}

	//탐색을 max_log 부터 역순으로 하는 이유? -> 어느 높이든 도달할 수 있기 때문 참고 : https://devowen.com/274
	if (a != b)
	{
		for (int i = max_log - 1; i >= 0; i--)
		{
			if (sparse[a][i] != sparse[b][i])
			{
				mins = std::min(mins, std::min(minL[a][i], minL[b][i]));
				maxs = std::max(maxs, std::max(maxL[a][i], maxL[b][i]));

				a = sparse[a][i];
				b = sparse[b][i];
			}
		}
	mins = std::min(mins, std::min(minL[a][0], minL[b][0]));
	maxs = std::max(maxs, std::max(maxL[a][0], maxL[b][0]));
	}


	return { mins, maxs };
}

int main()
{
	std::ios::sync_with_stdio(false);
	std::cin.tie(nullptr);
	std::cout.tie(nullptr);

	std::cin >> N;
	tree.assign(N + 1, std::vector<std::pair<int, int>>());
	sparse.assign(N + 1, std::vector<int>(20, 0));
	minL.assign(N + 1, std::vector<int>(20, 1000001));
	maxL.assign(N + 1, std::vector<int>(20, 0));
	depth.assign(N + 1, 0);

	for (int i = 0; i < N - 1; i++)
	{
		int a, b, c;
		std::cin >> a >> b >> c;

		tree[a].push_back({ b, c });
		tree[b].push_back({ a, c });
	}

	dfs(0, 1, 0, 0);

	minL[1][0] = max_size;

	Memoization();

	std::cin >> K;

	for (int i = 0; i < K; i++)
	{
		int a, b;
		std::cin >> a >> b;

		auto res = LCA(a, b);
		std::cout << res.first << " " << res.second << '\n';
	}

	return 0;
}
