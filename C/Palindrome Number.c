#include <stdio.h>
void udf(int);

void main() {
  int n;
  char c;
  
  do{
    printf("Polindrom testi için bir sayı giriniz.");
    scanf("%d", &n);
    udf(n);
    printf("devam etmek ister misin?y/n? ");
    scanf(" %c", &c);
  } while( c=='y' );
}

void udf(int n) {
  //define 
  int nx, yn=0;
  nx = n;
  
  while(n>0) {
   int r; //içeride kullanılacak sadece
    r = (n%10);
    yn = (yn*10) + r;
    n = n/10;   
  }
  if( yn==nx ) {
    printf("polidromik sayı np \n");
    
  } else {
    printf("polidromik değil srrx \n");
  }
}

//belli karakter sınırını açınca hata veriyor neden?
//baktım int karakter sınırını aşıyor, üşenmesem uzunluk sınırı eklerdim de neyse
//bu arada türler için sınır vermeme gerek var mı?
//mesela int yerine char girince nolcak?
//çalışacak yine bu benim sorumluluğum altında yani