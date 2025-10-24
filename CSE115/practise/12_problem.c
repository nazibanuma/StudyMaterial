#include<stdio.h>

// Write a program in C to display the multiplier table vertically from 1 to n

int main(){

    int i,j,num,multitable;

    printf("the multiplication table of number: ");
    scanf("%d", &num);

    for(i=1;i<=num;i++){
        for(j=1;j<= num;j++){
            multitable = j * i;
            printf("%d * %d = %d", j, i, multitable);

        }
        printf("\n");
    }

        
    return 0;
}