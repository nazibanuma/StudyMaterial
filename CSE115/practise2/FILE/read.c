#include <stdio.h>
#include <string.h>

int main()
{
    FILE *fp;
    fp = fopen("test.txt", "r");
    // if (fp != NULL)
    // {
    //     printf("file opened");
    // }
    // else
    // {
    //     printf("error");
    // }
    char c;
    while ((c = getc(fp)) != EOF) // end of file
    {
        putchar(c);
    }
    fclose(fp);
    return 0;
}