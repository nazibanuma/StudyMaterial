#include <stdio.h>

int main() {
    int month;
    

    printf("Enter month number: ");
    scanf("%d", &month);
    

    if (month%2==0) {
            printf("31 days\n");
        }
    else if (month%30==0){
            printf("30 days\n");
        }
    else if (month%29==0,month%28==0) {
            printf("28 or 29 days (depending on leap year)\n");
        }  
      else if (month>=13) {
            printf("Please enter a number between 1 and 12.\n");
    }
    
    return 0;
}