package io.github.ovso.blackbox.ui.base.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.DaggerAppCompatActivity;
import io.github.ovso.blackbox.R;
import io.github.ovso.blackbox.main.base.view.MyAdView;

public abstract class BaseActivity extends DaggerAppCompatActivity {
  protected @BindView(R.id.toolbar) Toolbar toolbar;
  private Unbinder bind;
  @BindView(R.id.ad_container) ViewGroup adContainer;
  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(getLayoutResID());
    bind = ButterKnife.bind(this);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayShowTitleEnabled(isTitle());
    onCreated(savedInstanceState);
    adContainer.addView(MyAdView.getAdmobAdView(this));
  }

  protected abstract int getLayoutResID();

  protected abstract void onCreated(@Nullable Bundle savedInstanceState);

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    finish();
    return super.onOptionsItemSelected(item);
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    if (bind != null) {
      bind.unbind();
    }
  }

  public boolean isTitle() {
    return true;
  }
}