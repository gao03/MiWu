package com.github.miwu.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.github.miwu.R
import com.github.miwu.databinding.MiIndicatorsCardBinding
import com.github.miwu.databinding.MiRoundSeekBarCardBinding


class MiIndicatorsCard(
    context: Context,
    attr: AttributeSet,
) : ConstraintLayout(context, attr) {

    private var binding: MiIndicatorsCardBinding
    private val listeners = ArrayList<(Int) -> Unit>()

    init {
        binding = MiIndicatorsCardBinding.inflate(LayoutInflater.from(context), this, true)
        binding.add.setOnClickListener {
            setProgress(getProgress() + 1)
        }

        binding.subtraction.setOnClickListener {
            setProgress(getProgress() -1)
        }
    }

    fun setProgressMax(size: Int) {
        binding.seekbar.setDotSize(size)
    }

    fun setProgress(progress: Int) {
        listeners.forEach { it(progress) }
        binding.seekbar.setIndex(progress)
        binding.value.text = progress.toString()
    }

    fun getProgress() = binding.seekbar.getIndex()

    fun setTitle(title: String) {
        binding.title.text = title
    }

    fun setOnProgressChangerListener(block: (Int) -> Unit) {
        listeners.add(block)
    }

}