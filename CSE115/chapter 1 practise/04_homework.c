#include<stdio.h>

int main(){
        double dividend,divisor,quotient,remainder;

        printf("Enter the value of the dividend:\n");
        scanf("%lf", &dividend);

        printf("Enter the value of the divisor:\n");
        scanf("%lf", &divisor);

        quotient=(dividend / divisor);
        remainder=(dividend - quotient);

        printf("The quotient is %lf%lf", quotient);
        printf("The remainder is %lf%lf", remainder);

    return 0;
}