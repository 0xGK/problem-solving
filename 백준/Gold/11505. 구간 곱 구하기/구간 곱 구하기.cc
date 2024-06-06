#include <iostream>
#include <algorithm>
#include<string.h>
#include<cmath>
using namespace std;
typedef unsigned long long ull;
ull tree[3000000];
int treeSize;
int start;
void update(int x, ull y) {
	int idx = x + start;
	tree[idx] = y;
	while (idx > 1) {
		if ((idx % 2) == 0) {
			tree[idx / 2] = (tree[idx] * tree[idx + 1]) % 1000000007;
		}
		else {
			tree[idx / 2] = (tree[idx] * tree[idx - 1]) % 1000000007;
		}
		idx /= 2;
	}

}
int getMul(int left, int right) {
	int ret = 1;
	left += start;
	right += start;
	while (left <= right) {
		if (left % 2 == 1) {
			ret = (ret*tree[left]) % 1000000007;
			left++;
		}
		if (right % 2 == 0) {
			ret = (ret*tree[right]) % 1000000007;
			right--;
		}
		left /= 2;
		right /= 2;
	}
	return ret;
}

void run() {
	int N, M, K;
	cin >> N >> M >> K;

	treeSize = 1;
	while (treeSize < N) {
		treeSize *= 2;
	}
	memset(tree, 0, treeSize * 2 + 1);

	start = treeSize - 1;

	for (int i = 1; i <= N; i++) {
		cin >> tree[start + i];
	}

	for (int i = start; i != 0; i--) {
		tree[i] = (tree[i * 2] * tree[i * 2 + 1]) % 1000000007;
	}

	for (int i = 0; i < M + K; i++) {
		int a; cin >> a;
		if (a == 1) {
			int b; ull c; cin >> b >> c;
			update(b, c);
		}
		else if (a == 2) {
			int b, c; cin >> b >> c;
			cout << getMul(b, c) << '\n';
		}
	}
}

int main()
{
	cin.tie(0);
	ios::sync_with_stdio(0);
	run();

	return 0;
}