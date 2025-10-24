//Read a floating point number from user and check if it has any fractional part (e.g. 4.5, 6.9,...) or not
//(e.g. 4, 5, ...). If it has a fractional part then print “Not an integer”, otherwise print “integer”

#include<stdio.h>

int main(){
        double num;

        printf("Enter the number: ");
        scanf("%lf", &num);

        if(num == int(num)){
        printf("Integer");
    }
        else{
            printf("not Integer");
        }
    return 0;
}