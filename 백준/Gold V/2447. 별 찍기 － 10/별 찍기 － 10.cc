#include <iostream>
#include <algorithm>
using namespace std;
void print(int x, int y, int n) {
	if ((x / n) % 3 == 1 && (y / n) % 3 == 1) {
		cout << ' ';
	}
	else {
		if (n == 1) {
			cout << '*';
		}
		else {
			print(x, y, n / 3);
		}
	}
}
int main()
{
	cin.tie(0);
	ios::sync_with_stdio(0);
	int n;
	cin >> n;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			print(i, j, n);
		}
		cout << "\n";
	}
	return 0;
}