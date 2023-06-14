#include <stdio.h>

void udf(int, int); //makro fonksiyon kulland�m de�er d�nd�rmeyecek, ana g�vdeden y�k� azaltt�m
int main() {
	
	int m, n;
	char c;
	
	//kullan�c� tercihine g�re program kapanmadan tekrarlanabilsin diye do-while kulland�m
	do{
		printf("n ve m 3 basamakl�lar� i�in, n-m aras�ndaki rakamlar�n�n toplam� 10 olan say�lar�n ortalamas�n� hesaplayan programd�r \n");
		printf("hesaplama i�in 3 basamakl� 2 tane do�al say� de�eri giriniz ilki k���k olan olsun \nde�erler:");
		scanf(" %d", &n);
		scanf(" %d", &m);
		
		if( (n<100 || n>=1000 || m<100 || m>=1000 || m<n) ) { //kullan�c�dan al�nan de�erlerin kontrol�, ekstradan m<n istedi�im i�in onu da kontrol ettim
			printf("formata uygun de�er giriniz l�tfen");
			break;
		}
		
		udf(n, m); //hesaplama i�lermleri burada, �o�u i� burada yani
		
		printf("tekrar denemek ister misin? y/n \n");
		scanf(" %c", &c);
	} while( c=='y' ); //kullan�c� konsolu kapatmadan tekrar deneyebilir
	return 0;
}

void udf(int a, int b){
	//define, initialization,
	int aD=0, sayac=0, toplam=0; //aD=ara de�er, i�emleri onun �zerinden yap�yorum i�in sonunda 'n'de�i�mesin diye(a=n)

	while( a<=b ) {
		aD = a;
		//rakamlar�na ay�r�p toplamlar� 10 mu kontrol ediyorum, 10'sa 3 basamakl� say�lar�n toplam�n� loop d�nd�k�e al�yorum
		//t�rl� yollarla yap�labilir en kolay� bu gibi geldi
		
		if( ((aD/100)+((aD/10)%10)+(aD%10))==10 ) {
			sayac++; //saya� �nemli, bu kapsama giri� say�s�, istedi�imiz t�rden ka� say� var onu g�sterir
			toplam = a+toplam;
		}
		a++ ;
	}
	
	if(toplam != 0) { //bu �nemli istedi�imiz t�rden say� yoksa 0/0 oldu�u i�in program ��ker
		printf( "hesaplanan de�er %f \n", ( (float)toplam/ (float)sayac) ); //en do�ru sonu� i�in floata �evirmek gerekli
	} else {	
		printf("cevap 0 \n");
	}
}
