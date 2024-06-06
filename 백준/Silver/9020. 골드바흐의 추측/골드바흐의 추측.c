#include<stdio.h>
int main() {

	int arr[10000] = { 0 };
	int cnt = 0, k = 0;
	int T, n;
	int a=0, b=0;
	for (int i = 2; i <= 100000; i++) {
		for (int j = 1; j*j <= i; j++) {
			if (i % j == 0) {
				cnt++;
			}
		}
		if (cnt == 1) {
			arr[k++] = i;
		}
		cnt = 0;
	}
	
	
	scanf("%d", &T);
	for (int N = 0; N < T; N++) {
		scanf("%d", &n);
		for (int i = 0; arr[i] < n; i++) {
			for (int j = 0; arr[j] < n; j++) {
				if (arr[i] + arr[j] == n) {
					if (arr[i] > arr[j]) break;
					a = arr[i];
					b = arr[j];
				}


			}
		}
		printf("%d %d\n", a,b);



	}




	return 0;
}