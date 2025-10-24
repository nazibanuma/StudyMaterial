//5. Check if the roots of the equation: ax^2+bx+c=0 are real or not. If they are real, then print them;
//otherwise print “No real root exist.” Read a,b,c from user.

#include <stdio.h>
#include <math.h>

int main() {
    double a, b, c, segment, root1, root2;
    
    
    printf("Enter a, b, and c: ");
    scanf("%lf %lf %lf", &a, &b, &c);
    
   
    segment = b * b - 4 * a * c;
    
    
    if (segment > 0) {
        root1 = (-b + sqrt(segment)) / (2 * a);
        root2 = (-b - sqrt(segment)) / (2 * a);
        printf("The roots are real and distinct: %lf and %lf\n", root1, root2);
    } else if (segment == 0) {
        root1 = -b / (2 * a);
        printf("The roots are real and equal: %.2lf\n", root1);
    } else {
        printf("No real root exists.\n");
    }
    
    return 0;
}
