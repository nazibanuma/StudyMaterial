// Write a C program to compute the quadruple factorial of a given number 𝒏, 𝒒(𝒏) = (𝟐𝒏)!/n!

#include <stdio.h>

int main()
{
    int i, n;
    long long int m = 1, d = 1;

    printf("Enter an integer: ");
    scanf("%d", &n);

    for (i = 1; i <= 2 * n; i++)
    {
        m *= i;
    }

    for (i = 1; i <= n; i++)
    {
        d *= i;
    }

    printf("the quadruple factorial is %lld", m / d);

    return 0;
}
