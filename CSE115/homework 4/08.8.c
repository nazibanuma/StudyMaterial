#include <stdio.h>

int main() {
    int m, n;

    
    printf("Enter m: ");
    scanf("%d", &m);
    printf("Enter n: ");
    scanf("%d", &n);

    printf("All even numbers between %d and %d except those divisible by 3 are: ", m, n);

   
    for (int i = m; i <= n; i++) {
        
        if (i % 2 == 0) {
            
            if (i % 3 == 0) {
                continue;
            }
            
            printf("%d, ", i);
        }
    }

    printf("\n"); 
    return 0;
}
