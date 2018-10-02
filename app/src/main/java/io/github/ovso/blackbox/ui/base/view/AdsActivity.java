package io.github.ovso.blackbox.ui.base.view;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.InterstitialAd;

public class AdsActivity extends Activity {
  InterstitialAd interstitialAd;

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    interstitialAd = MyAdView.getAdmobInterstitialAd(getApplicationContext());
    interstitialAd.setAdListener(new AdListener() {
      @Override public void onAdClosed() {
        super.onAdClosed();
        finish();
      }
    });
  }

  @Override public void onBackPressed() {
    if (interstitialAd.isLoaded()) {
      interstitialAd.show();
    } else {
      finish();
    }
  }
}
