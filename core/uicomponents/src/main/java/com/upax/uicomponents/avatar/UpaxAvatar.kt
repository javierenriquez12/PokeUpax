package com.upax.uicomponents.avatar

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.withStyledAttributes
import androidx.core.widget.ImageViewCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.upax.uicomponents.utils.BaseView
import com.upax.uicomponents.R
import com.upax.uicomponents.avatar.factory.AvatarFactory
import com.upax.uicomponents.avatar.handler.UpaxAvatarHandler
import com.upax.uicomponents.avatar.model.UpaxAvatarType
import com.upax.uicomponents.databinding.ViewUpaxAvatarBinding

class UpaxAvatar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr), BaseView<ViewUpaxAvatarBinding> {

    private val binding: ViewUpaxAvatarBinding =
        ViewUpaxAvatarBinding.inflate(LayoutInflater.from(context), this, true)
    private val upaxAvatarPresenter = UpaxAvatarHandler(this, AvatarFactory())
    private var enableBackgroundColor: Int? = null

    var type: UpaxAvatarType = UpaxAvatarType.LETTERS
        set(value) {
            field = value
            upaxAvatarPresenter.setAvatarType(value)
        }

    var letter: String = ""
        set(value) {
            binding.avatarLetterView.text = value
            field = value
        }

    var icon: Int = 0
        set(value) {
            binding.avatarPlaceHolderView.setImageResource(value)
            binding.avatarPlaceHolderView.tag = value
            field = value
        }

    var placeholder: Int = 0
        set(value) {
            field = value
            binding.avatarImageView.setImageResource(value)
            binding.avatarImageView.tag = value
        }

    var backgroundViewColor: Int = 0
        set(value) {
            field = value
            binding.avatarCardView.setCardBackgroundColor(value)
            if (isEnabled) enableBackgroundColor = value

        }

    var iconTint: Int = 0
        set(value) {
            field = value
            ImageViewCompat.setImageTintList(binding.avatarPlaceHolderView, ColorStateList.valueOf(value))

        }

    var letterColor: Int = 0
        set(value) {
            field = value
            binding.avatarLetterView.setTextColor(value)
        }

    init {
        setUpViews(attrs)
    }

    fun setImageWithUrl(imageUrl: String, onImageLoadFailed : () -> Unit){
        Glide.with(context)
            .load(imageUrl)
            .centerCrop()
            .listener(
                object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        onImageLoadFailed.invoke()
                        return true
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                })
            .into(binding.avatarImageView)
    }

    private fun setUpViews(attrs: AttributeSet?) {
        context.withStyledAttributes(attrs, R.styleable.UpaxAvatar) {
            type = UpaxAvatarType.get(
                getInt(
                    R.styleable.UpaxAvatar_upax_avatar_type,
                    UpaxAvatarType.LETTERS.typeId
                )
            )
            getString(R.styleable.UpaxAvatar_upax_avatar_letters)?.let { letter = it }
            icon = getResourceId(R.styleable.UpaxAvatar_upax_avatar_icon, 0)
            placeholder = getResourceId(R.styleable.UpaxAvatar_upax_avatar_placeholder, 0)
            backgroundViewColor = getInt(
                R.styleable.UpaxAvatar_upax_avatar_background_color,
                context.getColor(com.upax.resources.R.color.purple_700)
            )
            iconTint = getInt(
                R.styleable.UpaxAvatar_upax_avatar_icon_tint,
                context.getColor(com.upax.resources.R.color.white)
            )
            letterColor = getInt(
                R.styleable.UpaxAvatar_upax_avatar_letter_color,
                context.getColor(com.upax.resources.R.color.white)
            )
        }
    }

    override fun getView(type: Int) = binding
}