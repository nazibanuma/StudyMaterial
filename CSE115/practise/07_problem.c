#include <stdio.h>

// Write a c program to to see all the palindrome numbers from 1 to 300

int main()
{

    int i, j, num = 300, reverse, digit;

        for (i = 1; i <= num; i++)

    {
        reverse = 0;
        j = i;

        while (j != 0)
        {

            digit = j % 10;
            reverse = reverse * 10 + digit;
            j /= 10;
        }

        if (reverse == i)
        {

            printf("%d\t", i);
        }
    }
    return 0;
}