#include<stdio.h>

int main(){
        int i=1,j,num=50,count=0, isPrime=1;

        while(count<num){

            isPrime=1;
            
            if (i == 1)
        {
            isPrime = 0;
        }
        for (j = 2; j <= i / 2; j++)
        {

            if (i % j == 0)
            {
                isPrime = 0;
                break;
            }
        
        }
        if(isPrime == 1)    
            {
                printf("%d\t", i);
                count++;
            }
            i++;
        }

            
        
    return 0;
}
