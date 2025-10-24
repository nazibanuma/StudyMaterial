#include <stdio.h>

// Write a C program to display Pascal's triangle.

int main()
{

    int i, j, coeff = 1, rows = 5;

    for (i = 0; i < rows; i++)
    {
        for (j = rows - i ; j > 0; j--)
        {
            printf(" ");
        }
        for (j = 0; j <= i; j++)
        {
            if (j == 0 || j == i)
            {
                coeff = 1;
            }
            else
            {
                coeff = (coeff * (i - j + 1)) / j;
            }
            printf("%d ", coeff);
        }
        printf("\n");
    }

    return 0;
}