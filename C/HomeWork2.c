#include <stdio.h>

int main() {
	
	float n, hY, d1; //type casting do�ru olsun diye ilgili de�erler foat al�n�r
	int d2;
	char a, c='%'; //y�zde i�areti kullanabilmek i�in
	
	do { //tekrar denenebilirlik i�in
	
		printf("bir kare alma program�d�r, l�tfen bir tam say� de�er girin de�er: \n");
		scanf(" %f", &n);
		//tek sat�rda da yap�labilir, b�ylesi daha okunakl� hem de i� kompleksle�irse ��zmesi kolay olur
	
		d1 = n*n;
		d2 = (int)n*(int)n;
		hY = ( (d1-d2)/d1 )*100;
		//hY ='hataY�zdesi' , hata pay� kontrol edilir, do�ruysa 0 oldu�undan else'e ge�ilir
		
		if( hY>0 ) { //hata y�zdesinin 0'dan k���k oldu�u bir durum yok
			printf("krd�m tam say� gir demedim mi ben? Cevap hatal� ��kacak bak: \n");
			printf("%c%.2f hatayla %d de�erini buldun. Asl�nda cevap: %f \n",c, hY, d2, d1); 
			//b�y�k	say�larda y�zde 0 yakla��yor ondan formatl� yazd�rd�m int istedi�inizi biliyorum
	
		} else if( hY==0 ) { //0'sa hata yok zaten
			printf("cevap: %d \n", d2);
	
		} else {
			// denklem 0'da hatal� oluyor (0/0 tan�ms�zl���), b�yle ��zd�m sorunu
			printf("cevap: 0 \n ");
		}
		printf("tekrar denemek ister misin? y/n \n");
		scanf(" %c", &a);
	} while( a=='y' );
	return 0;
}
