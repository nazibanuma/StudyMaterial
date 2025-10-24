#include <stdio.h>

// C Program to check whether the input is a character, digit or a special character.

int main()
{
    char ch;

    printf("Enter any character: \n");
    scanf("%c", &ch);

    if ((ch <= 'a' && ch >= 'z') || (ch <= 'A' && ch >= 'Z'))
    {
        printf("the input is character", &ch);
    }
    else if ((ch <= '0') && (ch >= '9'))
    {
        printf("the input is digit", &ch);
    }
    else
    {
        printf("the input is a special character", &ch);
    }

    return 0;
}