#include <stdio.h>

// Write a c program to to see all the prime numbers from 1 to 300

int main()
{

    int i, j, num = 300, isPrime = 1;

    for (i = 1; i <= num; i++)
    {
        isPrime = 1;
        if (i == 1)
        {
            isPrime = 0;
        }
        for (j = 2; j <= i / 2; j++)
        {

            if (i % j == 0)
            {
                isPrime = 0;
                break;
            }
        
        }
        if(isPrime == 1)    
            {
                printf("%d\t", i);
            }
       
    }

    return 0;
}