#include<stdio.h>
int main() {
	int i,T, k, n;
	int arr[15][15] = { 0 };
	for (int i = 1; i < 15; i++) arr[0][i] = i;

	for (int i = 1; i < 15; i++) {
		for (int j = 1; j < 15; j++) {
			for (int k = 1; k <= j; k++) arr[i][j] += arr[i-1][k];
		}
	}

	scanf("%d", &T);
	for (int i = 0; i < T; i++) {
		scanf("%d %d", &k, &n);
		printf("%d\n", arr[k][n]);

	}




	return 0;
}