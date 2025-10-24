#include<stdio.h>
#include<math.h>

int main()

{ double x, result;
  
   //input the value of x
  printf("Enter the value of x: ");
  scanf("%lf", &x);


  // Computing the value of 5x3-4x2+√(x)+3
  result =(5 * pow(x,3))-(4 * pow(x,2))+ sqrt(x) + 3;

  //Displaying the result
  printf("the result of this equation: %lf", result);
  
  return 0;
}