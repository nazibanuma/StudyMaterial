#include<stdio.h>
#include<math.h>

int main(){
        double const PI=3.1416;
        int length,cube;
        double radius,height,cylinder;

        printf("Enter the value of length: \n");
        scanf("%d",&length);

        printf("Enter the value of radius,height: \n");
        scanf("%lf%lf",&radius,&height);

        cube=pow(length,3);
        cylinder=(PI * pow(radius,2) * height);

        printf("Volume of the cube is %d\n", cube);
        printf("Volume of the cylinder is %lf%lf", cylinder);
        
return 0;
}