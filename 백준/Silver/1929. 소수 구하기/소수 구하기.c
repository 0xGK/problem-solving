#include<stdio.h>
int isprime(int n) {
	for (int i = 2; i*i <= n; i++) {
		if (n % i == 0) return 0;
	}
	if (n != 1) return n; //prime
	else return 0;

}


int main() {
	int M, N,cnt=0;
	scanf("%d %d", &M, &N);
	for (int i = M; i <= N; i++) {
		if (isprime(i) != 0) printf("%d\n", isprime(i));

	}

	return 0;
}