// Write a program in C to find the sum of the series [ 1-X^2/2!+X^4/4!- .........].

#include <stdio.h>
#include <math.h>

int main()
{
    int i, j, x = 2, k, fact = 1, term = 4;
    float sum = 1;

    if(term>1){
    for (i = 2, j = 2; i <= term; i++, j += 2)

    {
        fact = 1;

        for (k = 1; k <= j; k++)
        {
            fact = fact * k;
        }

        if (i % 2 == 0)
        {
            sum = sum - (pow(x, j) / fact);
        }
        else
        {
            sum = sum + (pow(x, j) / fact);
        }
    }
}
    printf("%f", sum);

    return 0;
}