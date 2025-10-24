#include<stdio.h>

//Write a program in C to display the sum of the series [ 9 + 99 + 999 + 9999 ...]. //1 +11 + 111 + 1111 + .. n terms.

int main(){
    int i,j=0,term=5,sum=0;

    for(i=1;i<=term;i++){

        j=j * 10 +9; // j=j * 10 +1;
        sum =sum + j;                       
    }                       
    printf("the sum of the series is %d", sum);

                                                                    
        
    return 0;
}                                                    