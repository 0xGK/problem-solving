#include<stdio.h>
int main(){
	int a,n,count=0;
	scanf("%d",&n);
	a=n;
	
	while(1){
		n= n%10*10     +     (n/10 + n%10)%10;
		count++;
		
		if(a==n) break;
	}
	printf("%d",count);
	return 0;
}