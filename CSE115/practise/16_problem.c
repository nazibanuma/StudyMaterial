// Write a C program to display the pattern as a pyramid with each row containing an odd number of asterisks.

#include <stdio.h>

int main()
{
    int i, j=1, rows = 5;

    for (i = 1; i <= rows; i++)
    {
        for(j=rows-i;j>=1;j--){
            printf(" ");
        }
        for(j=1;j <= 2*i -1;j++)
        {

            printf("*");
           
        }
       
        printf("\n");
    }
    return 0;
}