#include <stdio.h>

int main()
{
    int arr1[5] = {98, 21, 37, 10, 45};

    int max = arr1[0];
    for (int i = 0; i < 5; i++)
    {
        if (arr1[i] > max)
        {
            max = arr1[i];
        }
    }
    printf("maximum value: %d\n", max);

    int min = arr1[0];
    for (int i = 0; i < 5; i++)
    {
        if (arr1[i] < min)
        {
            min = arr1[i];
        }
    }

    printf("minimum value: %d", min);

    return 0;
}