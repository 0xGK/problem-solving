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
	int x, y; cin >> x >> y;
	int A, B;
	A = a * y + x * b;
	B = b * y;
	int gcd_value = gcd(A, B);
	cout << A/ gcd_value << ' ' << B/ gcd_value ;
}
int main() {
	cin.tie(0); ios::sync_with_stdio(0);
	run();

	return 0;
}