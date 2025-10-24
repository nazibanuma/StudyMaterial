//Write a C program to find Greatest Common Divisor (GCD ) of two given integers. 

#include<stdio.h>

int main(){
        
    int n1, n2, rem=1;

    printf("Enter two integer: ");
    scanf("%d %d", &n1, &n2);

    while(n2 != 0){
        rem= n1%n2;
        n1= n2;
        n2= rem;
 }

 printf("the GCD value is: %d",n1);



    return 0;
}