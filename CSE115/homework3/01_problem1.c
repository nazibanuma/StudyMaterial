#include <stdio.h>

int main()
{
    // Write a C program to read an integer from user and output its last and first digit.

    int iLAST, num, ifirst;

    printf("Enter an integer: ");
    scanf("%d", &num);

    iLAST = num % 10;
    printf("%d\n", iLAST);

    ifirst = num;
    while (ifirst > 10)
    {
        ifirst /= 10;
    }

    printf("%d", ifirst);
    return 0;
}

/*2899%10= 9
2899/10= 289*/