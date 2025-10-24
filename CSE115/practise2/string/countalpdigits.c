#include<stdio.h>

int main(){
        char arr[100];
        int count1=0,count2=0,count3=0;

        printf("enter the string: ");
        gets(arr);

        for(int i=0;arr[i]!='\0';i++){
            if(arr[i]>'A' && arr[i]<'Z' || arr[i]<'z' && arr[i]>'a'){
                count1++;
            }
            else if(arr[i]>='0' && arr[i]<='9'){
                count2++;
            }
            else{
                count3++;
            }
        }
        printf("Number of Alphabets in the string is : %d\n",count1);
        printf("Number of Digits in the string is : %d\n",count2);
        printf("Number of Special characters in the string is : %d\n",count3);
    return 0;
}
