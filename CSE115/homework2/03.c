#include <stdio.h>

int main() {
    int num;
    
    
    printf("Enter an integer: ");
    scanf("%d", &num);
    
   
    if ((num % 2 == 0 || num % 5 == 0) && !(num % 2 == 0 && num % 5 == 0)) {
        printf("%d is a valid number.\n", num);
    } else {
        printf("%d is an invalid number.\n", num);
    }
    
    return 0;
}
