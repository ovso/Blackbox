package io.github.ovso.blackbox.ui.video;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import io.github.ovso.blackbox.R;
import io.github.ovso.blackbox.Security;
import io.github.ovso.blackbox.main.base.view.AdsActivity;

public class LandscapeVideoActivity extends AdsActivity {

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_fullscreen_video);
    if (getIntent().hasExtra("video_id")) {
      YouTubePlayerFragment youTubePlayerFragment =
          (YouTubePlayerFragment) getFragmentManager().findFragmentById(R.id.youtube_fragment);
      youTubePlayerFragment.initialize(Security.KEY.getValue(),
          new YouTubePlayer.OnInitializedListener() {
            @Override public void onInitializationSuccess(YouTubePlayer.Provider provider,
                YouTubePlayer youTubePlayer, boolean b) {
              youTubePlayer.loadVideo(getIntent().getStringExtra("video_id"));
            }

            @Override public void onInitializationFailure(YouTubePlayer.Provider provider,
                YouTubeInitializationResult youTubeInitializationResult) {
            }
          });
    } else {
      Toast.makeText(this, R.string.no_videos_found, Toast.LENGTH_SHORT).show();
      finish();
    }
  }
}
