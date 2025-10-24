#include <stdio.h>

// Write a program in C to display the multiplication table for a given integer.

int main()
{

    int i, num, multitable;

    printf("the multiplication table of number: ");
    scanf("%d", &num);

    for (i = 1; i <= 10; i++)
    {
        multitable = num * i;
        printf("%d * %d = %d\n", num, i, multitable);
    }

    return 0;
}