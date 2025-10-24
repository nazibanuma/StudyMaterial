#include <stdio.h>

int main()
{
    char arr1[100], arr2[100];

    printf("enter the 1st string: ");
    gets(arr1);

    printf("enter the 1st string: ");
    gets(arr2);

    for (int i = 0; arr1[i] != NULL; i++)
    {
        printf("%c", arr1[i]);
    }
    printf(" ");
    for (int i = 0; arr2[i] != NULL; i++)
    {
        printf("%c", arr2[i]);
    }

    return 0;
}