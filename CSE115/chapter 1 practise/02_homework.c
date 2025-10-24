#include<stdio.h>
#include<math.h>

int main(){
        double a,b,c,x;
         
         //inputing the variables: ax2+bx+c=0 
         printf("Enter the value of a: \n");
         scanf("%lf", &a);

        printf("Enter the value of b: \n");
         scanf("%lf", &b);

         printf("Enter the value of c: \n");
         scanf("%lf", &c);

        // Describing the equation
         x= ((-b+sqrt(pow(b,2)- 4 *a *c)) / 2*a);
        x= ((-b-sqrt(pow(b,2)- 4 *a *c)) / 2*a);
        
        printf("Solve of the equation is %lf", x);

return 0;
}