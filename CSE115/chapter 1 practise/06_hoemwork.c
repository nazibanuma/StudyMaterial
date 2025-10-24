//Print the sum of the series:1+2+...+n; read n from user 

#include<stdio.h>

int main(){
        float n,sum;
        printf("Enter the value of n: ");
        scanf("%f", &n);
        
        //the formula is 1^2+2^2+...+n^2=(n(n-1)(2n-a)/6)
        sum = (n * (n-1)/2);

        printf("the sum is %f",sum);

    return 0;
}