#include<stdio.h>
int main(){
	int n;
	scanf("%d",&n);


		for(int j=0; j<n; j++){
			for(int k=0; 2*k<n; k++){
				printf("* ");
			}
			printf("\n");			
			for(int k=0; 2*k<n-1; k++){
				printf(" *");
			}
			printf("\n");

		}




	return 0;
}