#include <stdio.h>
void udf(int, int, int);

void main( ) {
  //define
  int x, y, n;
  char c;
  
  //outer loop
  do{
    printf("Fibonacci sayı dizisi,");
    printf("ilk terim, ikinci terim ne olsun? ve kaçıncı terime kadar hesaplansın? \n");
    scanf(" %d ", &x);
    scanf(" %d ", &y);
    scanf(" %d", &n);
    
    if( (n>=3 && y>=x && x>=0)==0 ){
    printf("düzgün değer gir lan it. \n");
    break;
    }
    udf(x, y, n);
  
    printf(" \ndevam etmek ister misin? y/n?");
    scanf(" %c", &c);
  } while( c =='y');
}

void udf(int x, int y, int n) {
  int i;
 
  printf("1.terim: %d \t", x);
  printf("2.terim: %d \t", y);
  for(i=3; i<=n; i++){
    x = x+y;
    printf(" %d.terim: %d \t", i, x);
    if(i==n){
      break;
    }
    i++;  
    y = x+y;
    printf("%d.terim: %d \t", i, y);
    
  }
}
