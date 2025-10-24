#include <stdio.h>
#include <string.h>

int main()
{
    FILE *fp;
    char name[30];
    fp = fopen("test.txt", "a");
    if (fp == NULL)
    {
        printf("Error");
        return 0;
    }

    printf("Enter text to write file(hit only enter to  stop):\n");

    // while (1)
    // { // its a infinite loop

    //     gets(name);
    //     if (strcmp(name, "") == 0)
    //         break;
    //     fprintf(fp, name);
    //     fprintf(fp, "\n");
    // }

    for(int i=0;i<3;i++){
        gets(name);
        fprintf(fp,name);
        fprintf(fp,"\n");
    }
    fclose(fp);
    return 0;
}