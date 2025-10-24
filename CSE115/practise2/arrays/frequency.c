#include <stdio.h>

int main()
{
    int i, j, k, arr[5] = {10, 21, 21, 37, 45};
    int count = 0, unique = 1;

    for (int i = 0; i < 5; i++)
    {
        unique = 1;
        for (int j = i + 1; j < 5; j++)
        {
            if (arr[i] == arr[j])
            {
                unique = 0;
                break;
            }
        }
    }
    if (unique==1)
    {
        for (int k = 0; k < 5; k++)
        {
            count = 0;
            if (arr[i] == arr[k])
            {
                count++;
            }
        }
        printf("%d occurs %d times\n", arr[i], count);
    }

    return 0;
}