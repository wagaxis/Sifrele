package com.evciller.sifrele;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

public class  Sifre extends AppCompatActivity {
//Butonlar tanımlandı
    Button res, kaydet, degis, degistir;
//EditText Tadnımlandı
    EditText goruntu;
    //değişkenler tanımlandı
    char sifreniz[];
    char qsifreniz[];
    int basanak=0;
    //Yedek değişkenler
    int qBasamakSayisi = 0;
    boolean qharfliOlsun, qbuyukHarfli, qBuKu, qsimgeOlsun, qharfAcik, Atercih;

    String tasi = "", kayitliSifre = "", qtasi = "";
    //Button isimleri
    String resetButon = "Tercihleri Değiştir",degisButon = "Değiştir", kaydetButon = "Kaydet";

//Kaynak
    char [] Bharfler = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};//0
    char [] Kharfler = {'a', 'b', 'c', 'd', 'e'  , 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};//1
    char [] simgeler = {'!', '?', '+', '-', '*', '/', '_', '.', ':', ',', ';', '£', '^', '#', '@', '$', '%', '&', '{', '}', '(', ')', '[', ']', '=', '€', '<', '>', '|','~'};//2
    char [] rakamlar = {'0','1','2','3','4','5','6','7','8','9'};//3

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sifre);

        tasi = "";

        goruntu = (EditText) findViewById(R.id.sifreler);//editText i tanımladık
        /*EditText e xml de

        android:inputType="none"
        android:textIsSelectable="true"

        Bu Kodlar ile Yazmayı engelledim ve koyyalama yapabilmelerini sağladım.
        */
        Bundle gelen = getIntent().getExtras();//Diğer class tan gelen verileri çekmeye yarıyor

        Button res = (Button) findViewById(R.id.reset);//reset buttonu tanımladık
        Button kayit = (Button) findViewById(R.id.kayit);//kayıt buttonu tanımladık
        Button degis = (Button) findViewById(R.id.sifreDegis); //sifre değiştirme buttonları
        Button degistir =(Button) findViewById(R.id.degistir);
        res.setText(resetButon);
        kayit.setText(kaydetButon);
        degis.setText(degisButon);
        degistir.setText(degisButon);

        if(gelen != null)
        {
            sifreniz = gelen.getCharArray("sonuc");//gelen şifreyi "sifreniz" e atanıyor.
            basanak = gelen.getInt("basamak");//gelen basamak değerini "basamak" a atanıyor.
            kayitliSifre = gelen.getString("kayit");
            qBasamakSayisi = gelen.getInt("Ybasamak");
            qBuKu = gelen.getBoolean("Ybuku");
            qbuyukHarfli = gelen.getBoolean("Ybuyuk");
            qharfAcik = gelen.getBoolean("YhAcik");
            qharfliOlsun = gelen.getBoolean("Yharfli");
            qsimgeOlsun = gelen.getBoolean("Ysimge");
            Atercih = gelen.getBoolean("tercihYok");
        }
        else
        {
            int say = 0;
            if(say<2)
            {
                Toast.makeText(getApplicationContext(), "Bir Sorun Olustu Tekrar Deneyiniz!", Toast.LENGTH_SHORT).show();
                say++;
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Uygulamanın altına \" HATA aldım.\" yazınız.", Toast.LENGTH_LONG).show();
            }
        }

        if(qBasamakSayisi<32)
        {
            degistir.setVisibility(degistir.INVISIBLE);
            degis.setVisibility(degis.VISIBLE);
        }
        else
        {
            degistir.setVisibility(degistir.VISIBLE);
            degis.setVisibility(degis.INVISIBLE);
        }

        qsifreniz = new char[qBasamakSayisi];

        for(int i = 0; i < basanak; i++)
        {
            tasi = tasi + sifreniz[i];// dizideki elemanları yanayana ekliyoruz tek string oluyor.
        }

        if(tasi=="")
        {
            tasi=kayitliSifre;
        }

        goruntu.setText(tasi);// editText e yazdırıyoruz.

//Tekrar belirle butonuna basılınca
        res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Tekrar geri göndermek yerine tercihleri sakla ve sürekli değiştir yani sadece oluşturu kullan.
                Intent giden = new Intent(getApplication(),Tercihler.class);
                startActivity(giden);
                finish();
            }
        });
// Kaydet Butonuna basılınca
        kayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

                    String olusturulanSifre = goruntu.getText().toString(); //Edittextten alınıyor

                    if(olusturulanSifre==null || olusturulanSifre.equals("")){ //alınan değerlerin boş olup olmaması kontrol ediliyor

                        Toast.makeText(getApplicationContext(), "Kayıt Bölümü Boş!!",Toast.LENGTH_SHORT).show();

                    }else { //değerler boş değilse

                        SharedPreferences.Editor editor = sharedPref.edit(); //SharedPreferences'a kayıt eklemek için editor oluşturuyoruz
                        editor.putString("sifre",olusturulanSifre); //string değer ekleniyor
                        editor.commit(); //Kayıt
                        Toast.makeText(getApplicationContext(), "Kayıt Yapıldı.",Toast.LENGTH_SHORT).show();
                    }
                //Toast.makeText(getApplicationContext(),"Yapım Aşamasında...",Toast.LENGTH_SHORT).show();
            }
        });
//Değiştir Butonu na basılınca
        degis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Atercih == true)
                {
                    Toast.makeText(getApplicationContext(), "Lütfen Tercih Yapınız!!",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    goruntu.setText("");//EditText i boşaltıyoruz.
                    qtasi="";//Oluşturulan kod u sıfırlıyoruz.

                    tekrarla();

                    for(int i = 0; i < qBasamakSayisi; i++)
                    {
                        qtasi = qtasi + qsifreniz[i];// dizideki elemanları yanayana ekliyoruz tek string oluyor.
                    }
                    goruntu.setText(qtasi);
                }
            }

            //Önceden yapılan terciklerle tekrar rast gele şifre oluşturma...
            public void tekrarla(){
                Random salla = new Random();// rastgele sayı üretmek için javanın kütüphanesinden fonksiyon çağırıyorum.

                for(int i=0;i<qBasamakSayisi;i++)
                {
                    int karistir = salla.nextInt(4); // 0 ile 3 arasından sayı arasından sayı seçtiriyorum

                    if(karistir == 0 && qharfliOlsun == true && (qbuyukHarfli == true || qBuKu == true))//Harf olsun seçili ise büyük harf olsunda seçili ise Büyük harf alınıyor ama ikiside seçili ise çalışacak
                    {
                        int buyukHarfSec = salla.nextInt(26);//26 tane harfin içinden rastgele harf seçiyor.
                        qsifreniz[i] = Bharfler[buyukHarfSec];// seçilen karakter diziye atanıyor.
                    }
                    else if(karistir == 1 && qharfliOlsun == true && (qbuyukHarfli == false || qBuKu == true))//Harf olsun seçili ise küçük harf olsunda seçili ise küçük harf alınıyor ama ikiside seçili ise çalışacak
                    {
                        int kucukHarfSec = salla.nextInt(26);//26 tane harfin içinden rastgele harf seçiyor.
                        qsifreniz[i] = Kharfler[kucukHarfSec];// seçilen karakter diziye atanıyor.
                    }
                    else if(karistir == 2 && qsimgeOlsun == true)//simge olsun seçili ise simge giren sıkım
                    {
                        int simgeSec = salla.nextInt(30);//30 tane simge arasından 1 tanesini seçiyor.
                        qsifreniz[i] = simgeler[simgeSec];// seçilen karakter diziye atanıyor.
                    }
                    else if(karistir == 3)
                    {
                        int sayiSec = salla.nextInt(10);//10 rakam arasından 1 tanesi seçiliyor.
                        qsifreniz[i] = rakamlar[sayiSec];// seçilen karakter diziye atanıyor.
                    }
                    else// eğer sadece rakam olsun seçili ise bura kullanılacak
                    {
                        int sayiSec = salla.nextInt(10);//10 rakam arasından 1 tanesi seçiliyor.
                        qsifreniz[i] = rakamlar[sayiSec];// seçilen karakter diziye atanıyor.
                    }
                }
            }
        });

        degistir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Atercih == true)
                {
                    Toast.makeText(getApplicationContext(), "Lütfen Tercih Yapınız!!",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    goruntu.setText("");//EditText i boşaltıyoruz.
                    qtasi="";//Oluşturulan kod u sıfırlıyoruz.

                    tekrarla();

                    for(int i = 0; i < qBasamakSayisi; i++)
                    {
                        qtasi = qtasi + qsifreniz[i];// dizideki elemanları yanayana ekliyoruz tek string oluyor.
                    }
                    goruntu.setText(qtasi);
                }
            }

            //Önceden yapılan terciklerle tekrar rast gele şifre oluşturma...
            public void tekrarla(){
                Random salla = new Random();// rastgele sayı üretmek için javanın kütüphanesinden fonksiyon çağırıyorum.

                for(int i=0;i<qBasamakSayisi;i++)
                {
                    int karistir = salla.nextInt(4); // 0 ile 3 arasından sayı arasından sayı seçtiriyorum

                    if(karistir == 0 && qharfliOlsun == true && (qbuyukHarfli == true || qBuKu == true))//Harf olsun seçili ise büyük harf olsunda seçili ise Büyük harf alınıyor ama ikiside seçili ise çalışacak
                    {
                        int buyukHarfSec = salla.nextInt(26);//26 tane harfin içinden rastgele harf seçiyor.
                        qsifreniz[i] = Bharfler[buyukHarfSec];// seçilen karakter diziye atanıyor.
                    }
                    else if(karistir == 1 && qharfliOlsun == true && (qbuyukHarfli == false || qBuKu == true))//Harf olsun seçili ise küçük harf olsunda seçili ise küçük harf alınıyor ama ikiside seçili ise çalışacak
                    {
                        int kucukHarfSec = salla.nextInt(26);//26 tane harfin içinden rastgele harf seçiyor.
                        qsifreniz[i] = Kharfler[kucukHarfSec];// seçilen karakter diziye atanıyor.
                    }
                    else if(karistir == 2 && qsimgeOlsun == true)//simge olsun seçili ise simge giren sıkım
                    {
                        int simgeSec = salla.nextInt(30);//30 tane simge arasından 1 tanesini seçiyor.
                        qsifreniz[i] = simgeler[simgeSec];// seçilen karakter diziye atanıyor.
                    }
                    else if(karistir == 3)
                    {
                        int sayiSec = salla.nextInt(10);//10 rakam arasından 1 tanesi seçiliyor.
                        qsifreniz[i] = rakamlar[sayiSec];// seçilen karakter diziye atanıyor.
                    }
                    else// eğer sadece rakam olsun seçili ise bura kullanılacak
                    {
                        int sayiSec = salla.nextInt(10);//10 rakam arasından 1 tanesi seçiliyor.
                        qsifreniz[i] = rakamlar[sayiSec];// seçilen karakter diziye atanıyor.
                    }
                }
            }
        });
    }
}
