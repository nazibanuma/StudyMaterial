#include <stdio.h>

int main()
{
    char arr[100];
    char arr2[100];
    int temp;

    printf("string to be copied: ");
    gets(arr);

    printf("the copied string: ");
    for (int i = 0; arr[i] != '\0'; i++)
    {
        temp = arr[i];
        arr[i] = arr2[i];
        arr2[i] = temp;
        printf("%c", arr2[i]);
    }

    // for (int i = 0; arr[i] != '\0'; i++)
    // {
    //     printf("%s  ", arr2[i]);
    // }

    return 0;
}