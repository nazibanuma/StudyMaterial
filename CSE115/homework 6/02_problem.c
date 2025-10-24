#include <stdio.h>

// Write a C program to compute and print the sum of palindrome numbers between m and n

int main()
{
    int i, m, n, sum = 0;

    while (1)
    {
        printf("enter the value of m and n: ");
        scanf("%d%d", &m, &n);

        if (m > n)
        {
            printf("m cannot be greater than n, input again");
        }
        else
        {
            break;
        }
    }
    for (i = m; i <= n; i++)
    {

        int num1 = i, reverse = 0, rem;

        while (num1 != 0)
        {
            rem = num1 % 10;
            reverse = reverse * 10 + rem;
            num1 /= 10;
        }

        if (i == reverse)
        {
            sum = sum + i;
        }
    }
    printf("the sum is:%d", sum);

    return 0;
}A