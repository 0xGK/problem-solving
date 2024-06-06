#include<iostream>
#include<algorithm>

using namespace std;

int gcd(int a, int b) {
	if (b == 0) return a;
	return gcd(b, a%b);
}
int lcm(int a, int b) {
	return (a*b) / gcd(a, b);
}

void run() {
	int a, b; cin >> a >> b;
	cout << gcd(a, b) << '\n' << lcm(a, b)<<'\n';
}
int main() {
	cin.tie(0); ios::sync_with_stdio(0);
	run();

	return 0;
}