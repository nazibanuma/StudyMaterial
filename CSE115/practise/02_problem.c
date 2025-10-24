#include <stdio.h>

int main()
{

    // Write a program in C to display n terms of natural numbers and their sum and their average

    int i, num, sum = 0, average = 0;

    printf("The first n natural number is :");
    scanf("%d", &num);

    for (i = 1; i <= num; i++)
    {
        printf("%d ", i);
        sum = sum + i;
    }
    printf("\n");
    printf("the sum is: %d\n", sum);

    printf("the average is: %d", sum / num);

    return 0;
}