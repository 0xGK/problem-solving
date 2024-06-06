#include<stdio.h>
int isprime(int n) {
	for (int i = 2; i*i <= n; i++) {
		if (n % i == 0) return 0; //nonprime
	}
	if (n == 1) return 0;
	else return 1;

}
int main() {
	int n;
	int cnt = 0;
	
	scanf("%d", &n);
	while (n != 0) {
		for (int i = n+1; i <= 2 * n; i++) {
			cnt += isprime(i);
		}
		printf("%d\n", cnt);
		cnt = 0;
		scanf("%d", &n);
	}
	return 0;
}