#include <stdio.h>


long long int factorial(int num) {
    long long int fact = 1;
    for (int i = 1; i <= num; i++) {
        fact *= i; 
    }
    return fact;
}

int main() {
    int n;
    double sum = 0.0;

    printf("Enter a positive integer n: ");
    scanf("%d", &n);

    
    for (int i = 1; i <= n; i++) {
        sum += 1.0 / factorial(i); 
    }

    
    return 0;
}
