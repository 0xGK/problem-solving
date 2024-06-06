#include<stdio.h>
int main() {
	int M, N,cnt=0,pnum=0,sum=0,min;
	scanf("%d %d", &M, &N);
	for (int i = M; i <= N; i++) {
		for (int j = 1; j < i; j++) {
			if (i % j == 0) cnt++;
		}
		if (cnt == 1) { 
			pnum++;
			sum += i;
		}
		if (cnt==1 &&pnum == 1) min = i;
		cnt = 0;

	}
	if (pnum == 0) printf("%d", -1);
	else { 
		printf("%d\n", sum);
		printf("%d", min);
	}

	return 0;
}