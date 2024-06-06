#include <iostream>
#include <algorithm>
#include<string.h>
#include<cmath>
using namespace std;
int maxTree[300000];
int minTree[300000];
int treeSize;
int start;
//leaf to root
/*
	단일 업데이트일 때는 이게 빠름
	구간 업데이트에서는 lazy_propagation으로 노든 노드 업데이트 1번씩만 하기.

*/

int getMax(int left, int right) {
	int cur_max = 0;
	left += start;
	right += start;
	while (left <= right) {
		if (left % 2 == 1) {
			cur_max = max(maxTree[left++], cur_max);
		}
		if (right % 2 == 0) {
			cur_max = max(maxTree[right--], cur_max);
		}
		left /= 2;
		right /= 2;
	}
	return cur_max;
}
int getMin(int left, int right) {
	int cur_min = pow(2,30);
	left += start;
	right += start;
	while (left <= right) {
		if (left % 2 == 1) {
			cur_min = min(minTree[left++], cur_min);
		}
		if (right % 2 == 0) {
			cur_min = min(minTree[right--], cur_min);
		}
		left /= 2;
		right /= 2;
	}
	return cur_min;
}

void run() {
	int N, Q;
	cin >> N >> Q;

	treeSize = 1;
	while (treeSize < N) {
		treeSize *= 2;
	}
	memset(maxTree, 0, treeSize * 2 + 1);
	start = treeSize - 1;
	for (int i = 1; i <= N; i++) {
		cin >> maxTree[start + i];
		minTree[start + i] = maxTree[start + i];
	}

	for (int i = start; i != 0; i--) {
		maxTree[i] = max(maxTree[i * 2], maxTree[i * 2 + 1]);
		minTree[i] = min(minTree[i * 2], minTree[i * 2 + 1]);
	}

	for (int i = 0; i < Q; i++) {
		int x, y;
		cin >> x >> y;
		if (x > y) swap(x, y);
		cout << getMin(x, y) << " " << getMax(x, y) << '\n';
	}
}

int main()
{
	cin.tie(0);
	ios::sync_with_stdio(0);
	run();

	return 0;
}