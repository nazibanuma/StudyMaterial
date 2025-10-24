//Read a temperature in Celsius and print its Fahrenheit equivalent (Hint: C=(F-32)*5/9)

#include<stdio.h>

int main(){
        double fahrenhite,celsius;

        printf("Enter the value of the fahrenhite: \n");
        scanf("%lf",&fahrenhite);

        celsius =(((fahrenhite-32) * 5) / 9);
         
        printf("The celsius value is %lf\n",celsius);

    return 0;
}