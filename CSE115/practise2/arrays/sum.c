#include <stdio.h>

int main()
{
    int arr[5] = {10, 21, 37, 98, 45};
    int sum = 0;

    for (int i = 0; i < 5; i++)
    {
        sum = sum + arr[i];
    }
    printf("%d", sum);
    return 0;
}