// Write a C program to compute the super-factorial of a given number 𝒏, 𝒔𝒇(𝒏) = 𝟏! ∗ 𝟐! ∗ 𝟑! ∗ ...𝒏!

#include <stdio.h>

int main()
{
    int i, n, m = 1;
    long long int total = 1;

    printf("Enter an integer: ");
    scanf("%d", &n);

    for (i = 1; i <= n; i++)
    {
        m *= i;
        total = m * i;
    }

    printf("the super factorial is %lld", total);

    return 0;
}
