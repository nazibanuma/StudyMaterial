#include <stdio.h>
/*Write a C program using function that reads a floating point number n and an integer d and then
prints the rounded value of n up to d decimal places. E.g. for n=5.678 and d = 2; it should print 5.68*/

int main()
{
    int d;
    float n;

    printf("enter an integer and float number : ");
    scanf("%d%f", &d, &n);

    printf("%.*f", d, n);

    return 0;
}