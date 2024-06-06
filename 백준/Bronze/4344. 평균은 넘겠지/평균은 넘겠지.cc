#include<stdio.h>
/*
int main() {
	int cnt, level, len, i, sum, n;
	level = len = i = sum = 0;
	cnt = 1;
	char arr[80];
	scanf("%d", &n);
	while (level < n) {
		scanf("%s", arr);
		while (arr[i] != 0) {
			if (arr[i] == 'O') sum += cnt++;
			else cnt = 1;
			i++;
		}
		printf("%d\n", sum);
		sum = i=0;
		cnt = 1;
		level++;
	}
	return 0;
}
*/

int main() {
	int n, num,sum, cnt,arr[1000];;
	sum = cnt= 0;
	double avg;
	scanf("%d", &num);
	int k = 0;
	while(k<num){
		scanf("%d", &n);
		for (int j = 0; j < n; j++) {
			scanf("%d", &arr[j]); 
			sum += arr[j];
		}
		avg = (double)sum / n;
		for (int j = 0; j < n; j++) {
			if (arr[j] > avg) cnt++;
		}


		printf("%.3f%%\n", (double)cnt*100/n);



		avg=sum =cnt= 0;
		k++;
	}


	return 0;
}