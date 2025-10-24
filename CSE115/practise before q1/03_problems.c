//Write a C program to enter month number and print number of days in month
#include<stdio.h>

int main(){
        int month;

        printf("Enter the month(1-12):");
        scanf("%d", &month);

        switch(month){
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
            printf("The month has 31 days");
            break;
            case 2:
            printf("The month has 28 or 29 days");
            break;
            case 4: case 6: case 9: case 11:
            printf("The month has 30 days");
            break;
            default:
            printf("invalid month number");
            break;
 }
    return 0;
}