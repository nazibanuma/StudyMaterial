#include <stdio.h>

int main()
{

    int i, j, row =3;
    float sum = 0;

    for (i = 1; i <= row; i++)
    {

        for (j = 1; j <= i; j++)
        {
            sum = sum + (1.0 / j);
        }
    }
    printf("sum is: %f", sum);
    return 0;
}