#include<iostream>
#include<algorithm>

using namespace std;
int MAP[3];
int mindp[2][3];
int maxdp[2][3];
int n;

void run() {
	cin >> n;
	for (int i = 0; i < 3; i++) {
		mindp[0][i] = 0;
		maxdp[0][i] = 0;
		mindp[1][i] = 0;
		maxdp[1][i] = 0;
	}
	for (int i = 0; i < n; i++) {
		cin >> MAP[0] >> MAP[1] >> MAP[2];
		mindp[i%2][0] = min(mindp[(i + 1)%2][0] + MAP[0], mindp[(i + 1)%2][1] + MAP[0]);
		mindp[i % 2][1] = min(min(mindp[(i + 1)%2][0] + MAP[1], mindp[(i + 1)%2][1] + MAP[1]), mindp[(i + 1)%2][2] + MAP[1]);
		mindp[i % 2][2] = min(mindp[(i + 1)%2][1] + MAP[2], mindp[(i + 1)%2][2] + MAP[2]);

		maxdp[i % 2][0] = max(maxdp[(i + 1)%2][0] + MAP[0], maxdp[(i + 1)%2][1] + MAP[0]);
		maxdp[i % 2][1] = max(max(maxdp[(i + 1)%2][0] + MAP[1], maxdp[(i + 1)%2][1] + MAP[1]), maxdp[(i + 1)%2][2] + MAP[1]);
		maxdp[i % 2][2] = max(maxdp[(i + 1)%2][1] + MAP[2], maxdp[(i + 1)%2][2] + MAP[2]);


	}
	
	int min_val = min(min(mindp[(n-1)%2][0], mindp[(n - 1) % 2][1]), mindp[(n - 1) % 2][2]);
	int max_val = max(max(maxdp[(n - 1) % 2][0], maxdp[(n - 1) % 2][1]), maxdp[(n - 1) % 2][2]);

	cout << max_val << ' ' << min_val;
}
int main() {
	cin.tie(0); ios::sync_with_stdio(0);
	run();

	return 0;
}