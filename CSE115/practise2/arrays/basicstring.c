#include <stdio.h>

int main() {
    // Write C code here
    char name[100];
    printf("Enter your name :: ");
    gets(name);
    printf("My name is %s ",name);
    //puts(name);

    return 0;
}