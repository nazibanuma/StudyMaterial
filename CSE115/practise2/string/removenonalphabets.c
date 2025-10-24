#include <stdio.h>

int main()
{
    char arr[100], count = 0;

    printf("enter a sentence: ");
    gets(arr);

    for (int i = 0; arr[i] != NULL; i++)
    {
        if (arr[i] >= 'A' && arr[i] <= 'Z' || arr[i] >= 'a' && arr[i] <= 'z')
        {
            printf("%c", arr[i]);
        }
    }

    return 0;
}