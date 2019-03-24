package com.evciller.sifrele;

import android.content.Context;
import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class MainActivity extends AppCompatActivity {

    private static final String REKLAM_ID = "ca-app-pub-4906974194293016/7319692912";
    private InterstitialAd interstitial;

    Button Giris, Bilgi, Cikis, Kayit;
    String gir="Şifrele", hak="Nasıl Kullanılır", cik="Çıkış", kayıtlılar="Kayıtlı Şifre", kaydedilenSifre="KayitYok";
    Boolean tercih;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Giris = (Button) findViewById(R.id.giris);
        Bilgi = (Button) findViewById(R.id.bilgi);
        Kayit = (Button) findViewById(R.id.kayitli);
        Cikis = (Button) findViewById(R.id.cikis);

        Giris.setText(gir);
        Bilgi.setText(hak);
        Cikis.setText(cik);
        Kayit.setText(kayıtlılar);

        AdView adView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);


        interstitial = new InterstitialAd(this);
        interstitial.setAdUnitId(REKLAM_ID);
        AdRequest adRequest1 = new AdRequest.Builder().build();

        interstitial.loadAd(adRequest1);

        interstitial.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                if (interstitial.isLoaded()) {
                    interstitial.show();
                }
            }
        });

        Giris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent gonder=new Intent(getApplication(),Tercihler.class);
                startActivity(gonder);
            }
        });

        Bilgi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gonder=new Intent(getApplication(),bilgi.class);
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
