package com.example.mobiledevelopmentcourselabapp.presentation.view.videoPlayer.adapter

import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.SurfaceHolder
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobiledevelopmentcourselabapp.databinding.ItemVideoBinding


class VideoAdapter : RecyclerView.Adapter<VideoAdapter.VideoHolder>() {

    private val links = listOf(
        "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
        "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4",
        "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/SubaruOutbackOnStreetAndDirt.mp4",
        "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/TearsOfSteel.mp4"
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoHolder {
        val binding =
            ItemVideoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VideoHolder(binding)
    }

    override fun getItemCount() = links.size

    override fun onBindViewHolder(holder: VideoHolder, position: Int) {
        val item = links[position]
        holder.bind(item)
    }

    override fun onViewRecycled(holder: VideoHolder) {
        holder.unbind()
        super.onViewRecycled(holder)
    }

    inner class VideoHolder(private val binding: ItemVideoBinding) : RecyclerView.ViewHolder(binding.root)  {

        private var mediaPlayer: MediaPlayer? = null

        fun bind(link: String) {
            mediaPlayer = MediaPlayer()
            mediaPlayer?.setDataSource(link)

            binding.root.setOnClickListener {
                if (mediaPlayer?.isPlaying == true) mediaPlayer?.pause() else mediaPlayer?.start() }

            binding.root.holder.addCallback(
                object : SurfaceHolder.Callback {
                    override fun surfaceCreated(p0: SurfaceHolder) {
                        val surfaceHolder: SurfaceHolder = binding.root.holder
                        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS)

                        mediaPlayer?.setDisplay(surfaceHolder)
                        mediaPlayer?.prepareAsync()

                        mediaPlayer?.setOnPreparedListener { mediaPlayer?.start() }
                    }

                    override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {}

                    override fun surfaceDestroyed(p0: SurfaceHolder) {
                        mediaPlayer?.stop()
                    }
                }
            )
        }

        fun unbind() {
            mediaPlayer?.release()
            mediaPlayer = null
        }
    }
}