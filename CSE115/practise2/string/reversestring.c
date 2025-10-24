#include <stdio.h>

int main()
{
    char arr[100];
    int count = 0;

    printf("enter your name: ");
    gets(arr);

    for (int i = 0; arr[i] != NULL; i++)
    {
        count++;
    }

    for (int i = 0; i <= count; i++)
    {
        printf("%c  ", arr[count - i]);
    }

    return 0;
}