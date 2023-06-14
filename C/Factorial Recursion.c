#include <stdio.h>

int hesapla(int n) {
  if( n==0 ) 
    return 1;
  else
  return n*hesapla(n-1);
}

void main( ) {
  //define
  int n, cevap;
  char c;
  
  do {
    printf("hesaplanmasını istediğiniz değeri girin");
    scanf(" %d", &n);
    if( n<0 ) {
      printf("başka bir değer dene bakayim \n");
      break;
      
    }
    cevap = hesapla(n);
    printf("cevap: %d", cevap);
    printf("tekrar denemek ister misin? y/n \n");
    scanf(" %c", &c);
  } while( c=='y' );  
}

