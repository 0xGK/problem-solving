#include<stdio.h>
int main(){
    
	int n1, n2,temp1,temp2;
	scanf("%d %d", &n1, &n2);
	temp1 = (n1 % 10)*100 + ((n1 % 100) / 10)*10 + n1 / 100;
	temp2 = (n2 % 10)*100 + ((n2 % 100) / 10)*10 + n2 / 100;
	if (temp1 > temp2) printf("%d", temp1);
	else printf("%d", temp2);

    return 0;
}