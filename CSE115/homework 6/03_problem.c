#include <stdio.h>

int main()
{
    int i=0, n, count=0;

    while (1)
    {
        printf("enter the value of n : ");
        scanf("%d", &n);

        if (n<0)
        {
            printf("n cant be a negative number, input again");
        }
        else
        {
            break;
        }
    }
    while(count!=n)
    {

        int num1 = i, reverse = 0, rem;


        while (num1 != 0)
        {
            rem = num1 % 10;
            reverse = reverse * 10 + rem;
            num1 /= 10;
        }

        if (i == reverse)
        {
            printf("%d ", i);
            count++;// count= total how many palindrome we have found till now
        } //n = how many WE NEED TO FIND
        i++;
    }

    return 0;
}