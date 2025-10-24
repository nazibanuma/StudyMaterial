#include <stdio.h>
#include <math.h>

int main() {
    long long binary;
    int decimal = 0, remainder, position = 0;

    printf("Enter a binary number: ");
    scanf("%lld", &binary);


    while (binary != 0) {
        remainder = binary % 10;
        decimal += remainder * pow(2, position); 
        binary /= 10; 
        position++; 
    }


    printf("The decimal is: %d\n", decimal);

    return 0;
}
