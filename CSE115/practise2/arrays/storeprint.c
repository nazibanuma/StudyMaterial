#include <stdio.h>

int main()
{
    int index;

    printf("enter the index: ");
    scanf("%d", &index);

    int arr[index];

    printf("enter the elements: ");
    for (int i = 0; i < index; i++)
    {
        scanf("%d", &arr[i]);
    }

    printf("the array: ");
    for (int i = 0; i < index; i++)
    {
        printf("%d\t",arr[i]);
    }
    return 0;
}