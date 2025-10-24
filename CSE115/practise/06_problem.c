#include <stdio.h>

// Write a c program to to count first 3 the perfect numbers

int main()
{

    int i=1, j, num = 3, sum = 0,count=0;

   while(count < num)
    {
        sum=0;
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
            count++;
        }
        i++;
    }
    return 0;
}