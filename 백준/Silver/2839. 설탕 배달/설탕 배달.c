#include<stdio.h>
#include<string.h>
int main() {
	int  n;
	int cnt = 0;
	scanf("%d", &n);
	for (int i = n/5; i >=0 ; i--) {
		for (int j = n / 3; j >= 0; j--) {
			if (n == i * 5 + j * 3) {
				printf("%d", i + j);
				cnt = 1;
				break;
			}
		}
		if (cnt == 1) break;
	}
	if (cnt == 0) printf("%d", -1);




	return 0;
}