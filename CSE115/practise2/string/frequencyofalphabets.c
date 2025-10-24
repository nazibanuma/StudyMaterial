#include <stdio.h>

int main()
{
    char arr[100], count = 0, choice;

    printf("enter a sentence: ");
    gets(arr);

    printf("Input the character to find frequency:");
    scanf("%c", &choice);

    for (int i = 0; arr[i] != NULL; i++)
    {
        if (choice == arr[i])
        {
            count++;
        }
    }
    printf("The frequency of %c is : %d ",choice,count);

    return 0;
}