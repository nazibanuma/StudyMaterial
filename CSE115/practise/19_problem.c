#include <stdio.h>

int main()
{
    int i, j, fact = 1, num = 3;
    float termnew, sum = 0;

    for (i = 1; i <= num; i++)
    {
        fact = 1;
        for (j = 1; j <= i; j++){
            fact *= j;
        }
        termnew = (float)i / fact;
        sum = sum + termnew;
    }
    printf("%f", sum);
    return 0;
}