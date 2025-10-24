// C program to check Leap Year (a year is a leap year if – (i) it is divisible by 4 but not divisible by 100 OR (ii)
//it is divisible by 400)
#include<stdio.h>

int main(){
        int year;

        printf("Enter the year: ");
        scanf("%d", &year);

        if((year %4 ==0 && year%100 !=0) || (year%400==0))
        {
            printf("it is  leap year");
             }
        else
       {
            printf("the year is Not leap year");
             }


    return 0;
}