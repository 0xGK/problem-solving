#include<stdio.h>
int main() {
	int T, n, cnt = 0,pnum=0;
	scanf("%d", &T);
	for (int i = 0; i < T; i++) {
		scanf("%d", &n);
		for (int j = 1; j < n; j++) {
			if (n % j == 0) cnt++;
		}
		if (cnt == 1) pnum++;
		cnt = 0;

	}
	printf("%d", pnum);



	return 0;
}