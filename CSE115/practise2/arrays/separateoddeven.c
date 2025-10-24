#include <stdio.h>

int main()
{
    int arr[5] = {98, 21, 37, 10, 45};
    int a1[5], count1 = 0;
    int a2[5], count2 = 0;

    for (int i = 0; i < 5; i++)
    {
        if (arr[i] % 2 == 0)
        {
            a1[count1] = arr[i];
            count1++;
        }
        else
        {
            a2[count2] = arr[i];
            count2++;
        }
    }

    for (int i = 0; i < count1; i++)
    {
        printf("%d\t", a1[i]);
    }

    printf("\n");

    for (int i = 0; i < count2; i++)
    {
        printf("%d\t", a2[i]);
    }

    return 0;
}