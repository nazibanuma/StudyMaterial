#include <stdio.h>

int main() {
    int num;
    
    
    printf("Enter an integer: ");
    scanf("%d", &num);
    
    
    if (num % 5 == 0 && num % 11 == 0) {
        printf("%d is a multiple of both 5 and 11.\n", num);
    } else if (num % 5 == 0) {
        printf("%d is a multiple of only 5.\n", num);
    } else if (num % 11 == 0) {
        printf("%d is a multiple of only 11.\n", num);
    } else {
        printf("%d is neither a multiple of 5 nor 11.\n", num);
    }
    
    return 0;
}
