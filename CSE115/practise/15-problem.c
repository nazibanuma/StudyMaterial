#include<stdio.h>
//1 + 1/2 + 1/3 + 1/4 + 1/5 ... 1/n terms

int main(){

    int j;
    float i,sum=0,term=5;

    for(i=1;i<=term;i++){

        
        
        sum= sum + (1.0/i);
     
    }
       printf("%f", sum);
   


        
    return 0;
}