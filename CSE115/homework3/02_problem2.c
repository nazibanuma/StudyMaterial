// Write a C program to check whether an input number is a perfect number or not.
#include <stdio.h>

int main()
{
    int num, sum = 0;

    printf("Enter a positive integer: ");
    scanf("%d", &num);

    if (num <= 0)
    {
        printf("check your integer\n");
        return 0;
    }

    for (int i = 1; i <= num / 2; i++)
    {
        if (num % i == 0)
        {
            sum += i;
        }
    }

    if (sum == num)
    {
        printf("%d is a Perfect Number.\n", num);
    }
    else
    {
        printf("%d is NOT a Perfect Number.\n", num);
    }

    return 0;
}