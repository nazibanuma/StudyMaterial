#include <stdio.h>

// Write a c program to to see all the perfect numbers from 1 to 300

int main()
{

    int i, j, num = 300, sum = 0;

    for (i = 1; i <= num; i++)
    {
        sum = 0;
        for (j = 1; j < i; j++)
        {

            if (i % j == 0)
            {
                sum = sum + j;
            }
        }

        if (sum == i)
        {

            printf("%d\t", i);
        }
    }
    return 0;
}