#include <stdio.h>

int main()
{
    // Write a program in C to display the first 10 natural numbers.

    int i, sum = 0;

    for (i = 1; i <= 10; i++)
    {
        printf("%d ", i);
        sum = sum + i;
    }
    printf("\n");
    printf("the sum is: %d\n", sum);
    return 0;
}