#include<stdio.h>

//Write a program in C to read 10 numbers from the keyboard and find their sum and average.

int main(){

    int i, num=10,sum=0,average;

    for(i=1;i<=num;i++){
        printf("enter any integer: \n",i);
        scanf("%d",&i);

        sum= sum+i;

    }
    average =sum/10;
    printf("the sum of first 10 integers is: %d\n",sum);
    printf("the average value is %d",average);
        
    return 0;
}