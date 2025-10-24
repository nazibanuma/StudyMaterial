#include <stdio.h>

int main()
{
    char arr[100], count = 0;

    printf("enter a sentence: ");
    gets(arr);

    for (int i = 0; arr[i] != NULL; i++)
    {
        for (int j = 0; arr[j] != ' '; j++)
        {
           
        }
        printf("%c",arr[i]);
    }

    return 0;
}