package io.github.ovso.blackbox.ui.video

import android.os.Bundle
import android.widget.Toast
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerFragment
import io.github.ovso.blackbox.R
import io.github.ovso.blackbox.data.Keys
import io.github.ovso.blackbox.ui.base.view.AdsActivity
import kotlinx.android.synthetic.main.activity_fullscreen_video.*

class LandscapeVideoActivity : AdsActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_fullscreen_video)
    if (intent.hasExtra("video_id")) {
      val youTubePlayerFragment = youtube_fragment as YouTubePlayerFragment
      youTubePlayerFragment.initialize(Keys.KEY,
          object : YouTubePlayer.OnInitializedListener {
            override fun onInitializationSuccess(
              provider: YouTubePlayer.Provider,
              youTubePlayer: YouTubePlayer,
              b: Boolean
            ) {
              youTubePlayer.loadVideo(intent.getStringExtra("video_id"))
            }

            override fun onInitializationFailure(
              provider: YouTubePlayer.Provider,
              youTubeInitializationResult: YouTubeInitializationResult
            ) {
            }
          })
    } else {
      Toast.makeText(this, R.string.no_videos_found, Toast.LENGTH_SHORT)
          .show()
      finish()
    }
  }
}
