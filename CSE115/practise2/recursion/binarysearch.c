#include <stdio.h>
int search(int);

int main()
{
    int index, num;

    printf("Enter a integer: ");
    scanf("%d", &index);

    int arr[index];

    for (int i = 0; i < 3; i++)
    {
        scanf("%d", &arr[i]);
    }

    printf("search for a number if that is in the array: ");
    scanf("%d", &num);

    search(num);

    return 0;
}

int search(int a)
{
    if (a == 0)
    {
        return 0;
    }
    else
    {
        return a % 10 + Sum(a / 10);
    }
}