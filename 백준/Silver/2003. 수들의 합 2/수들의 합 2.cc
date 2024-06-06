#include <iostream>
using namespace std;

int n, m;
int numbers[10001];
int cnt = 0;


int main() {
	cin.tie(0); ios::sync_with_stdio(0);
	cin >> n >> m;

	for (int i = 0; i < n; i++)
	{
		cin >> numbers[i];
	}
	int start, end, sum;

	for (int i = 0; i < n; i++)
	{
		start = end = i;
		sum = numbers[start];
		while (end<=n) {
			if (sum == m) {
				cnt++;
				break;
			}
			else if (sum < m) {
				sum += numbers[++end];
			}
			else {
				break;
			}
		}
	}

	cout << cnt;
	return 0;
}