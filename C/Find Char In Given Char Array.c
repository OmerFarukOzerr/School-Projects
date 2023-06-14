#include <stdio.h>

int main() {
  char c, h; 
  char arr[] = {'a', 'b', 'c', 'd', 'e', 'f'};
  int bulundu_flag=0, aD=0, diziUzunluk=0;

    do { 
      
      printf(" istenen harfi ön tanımlı bir dizide arayan varsa yazdıran program \n");
      printf("hangi harf aransın? harf:");
      scanf(" %c", &h);
      diziUzunluk = sizeof(arr)/sizeof(char);
    
        for(int i=0; i<diziUzunluk; i++) {
          if(arr[i]== h) {
            bulundu_flag =1;
            printf("bulunan değerler: %d.elaman \t", i+1); 
          }
        }

      if(bulundu_flag==0)
        printf("değer bulunamadı");

      printf(" tekrar denemek ister misin?(evet için 'y')");
      scanf(" %c", &c);
      
    } while(c=='y');
  
  return 0;
}