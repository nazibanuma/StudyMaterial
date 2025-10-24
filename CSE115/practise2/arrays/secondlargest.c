#include <stdio.h>
#include <limits.h>

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

    int secondlarge = INT_MIN;

    for (int i = 0; i < 5; i++)
    {
        if (arr1[i] != max && arr1[i] > secondlarge)
        {
            secondlarge = arr1[i];
        }
    }
    printf("%d", secondlarge);

   /* int max = INT_MIN;
    int smax = INT_MIN;
    for (int i = 0; i < 5; i++)
    {
        if (arr1[i] > max)
        {
            smax = max;
            max = arr1[i];
        }
    }

    printf("%d", smax);*/

    return 0;
}
