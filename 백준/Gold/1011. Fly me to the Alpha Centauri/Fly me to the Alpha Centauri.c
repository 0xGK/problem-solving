#include<stdio.h>
int main() {
	int T;
	long long x, y;
	long long n = 1;
	scanf("%d", &T);
	for (int i = 0; i < T; i++) {
		scanf("%lld %lld", &x, &y);
		while (n * n <= y - x) n++;
		n--;

		if (y - x == n * n) printf("%lld\n", 2 * n - 1);
		else if (y - x <= n * n + n) printf("%lld\n", 2 * n);
		else if (y - x <= n * n + 2 * n) printf("%lld\n", 2 * n + 1);
		n = 1;

	}
	return 0;
}