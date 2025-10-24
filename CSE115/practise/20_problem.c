#include <stdio.h>
#include <math.h>

// 1 + r + r2 + ... + rn

int main()
{
    int i, n=2, r = 2, x, sum = 1;

    for (i = 1; i <= n; i++)
    {
        x = pow(r, i);
        sum = sum + x;
    }
    printf("%d",sum);

    return 0;
}