#include <stdio.h>

int main()
{

    int arr[5] = {10, 21, 21, 98, 45};
    int count=0;

    for (int i = 0; i < 5; i++)
    {
        for (int j = i + 1; j < 5; j++)
        {
            if (arr[i] == arr[j])
            {
                count++;
                break;
            }
        }
    }

    printf("%d", count);

    return 0;
}