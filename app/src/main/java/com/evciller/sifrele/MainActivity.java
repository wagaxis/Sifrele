package com.evciller.sifrele;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.SharedPreferences;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    Button Giris, Cikis, Kayit;
    String gir="Şifrele", cik="Çıkış", kayıtlılar="Kayıtlı Şifre", kaydedilenSifre="KayitYok";
    Boolean tercih;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Giris = (Button) findViewById(R.id.giris);
        Kayit = (Button) findViewById(R.id.kayitli);
        Cikis = (Button) findViewById(R.id.cikis);

        Giris.setText(gir);
        Cikis.setText(cik);
        Kayit.setText(kayıtlılar);


        Giris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent gonder=new Intent(getApplication(),Tercihler.class);
                startActivity(gonder);
            }
        });

        Kayit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(),"Yapım Aşamasında...",Toast.LENGTH_SHORT).show();
                  SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

                    String kaydedilenSifre = sharedPref.getString("sifre", "KayitYok");
                if(kaydedilenSifre!="KayitYok") {
                    // yapılan tercihlere göre rast gele üretim başlıyor.
                    tercih=true;
                    Intent yolla = new Intent(getApplication(), Sifre.class); // şifreyi göstereceğimiz sayfaya yol açıyorum.
                    yolla.putExtra("kayit", kaydedilenSifre);// oluşan şifre dizisi ni diğer sayfaya yolluyorum.
                    yolla.putExtra("tercihYok",tercih);
                    startActivity(yolla);// sonuc sayfasını başlatıyoruz.
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Kayıtlı Şifre Bulunamadı!!!",Toast.LENGTH_SHORT);
                }

            }
        });

        Cikis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(1);
            }
        });

    }
}
