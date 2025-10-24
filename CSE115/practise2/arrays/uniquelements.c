#include <stdio.h>

int main()
{

    int index = 5, count;
    int arr1[5] = {10, 21, 21, 98, 45};

    for (int i = 0; i < index; i++)
    {
        count = 0;
        for (int j = 0; j < index; j++)
        {
            if (arr1[i] == arr1[j])
            {
                count++;
            }
        }
        if (count == 1)
        {
            printf("%d\t", arr1[i]);
        }
    }
    return 0;
}