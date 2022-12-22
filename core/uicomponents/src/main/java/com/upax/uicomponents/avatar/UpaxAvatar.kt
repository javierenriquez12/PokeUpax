package com.upax.uicomponents.avatar

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.withStyledAttributes
import androidx.core.widget.ImageViewCompat
import com.upax.uicomponents.utils.BaseView
import com.upax.uicomponents.R
import com.upax.uicomponents.avatar.factory.AvatarFactory
import com.upax.uicomponents.avatar.handler.UpaxAvatarHandler
import com.upax.uicomponents.avatar.model.UpaxAvatarType
import com.upax.uicomponents.databinding.ViewUpaxAvatarBinding

class UpaxAvatar constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr), BaseView<ViewUpaxAvatarBinding> {

    private val binding: ViewUpaxAvatarBinding =
        ViewUpaxAvatarBinding.inflate(LayoutInflater.from(context), this, true)
    private val upaxAvatarPresenter = UpaxAvatarHandler(this, AvatarFactory())
    private var enableBackgroundColor: Int? = null

    var type: UpaxAvatarType = UpaxAvatarType.LETTERS
        set(value) {
            upaxAvatarPresenter.setAvatarType(value)
            field = value
        }

    var letter: String = ""
        set(value) {
            binding.avatarLetterView.text = value
            field = value
        }

    var icon: Int = 0
        set(value) {
            binding.avatarIconView.setImageResource(value)
            binding.avatarIconView.tag = value
            field = value
        }

    var img: Int = 0
        set(value) {
            binding.avatarPlaceHolderView.setImageResource(value)
            binding.avatarPlaceHolderView.tag = value
            field = value
        }

    var backgroundViewColor: Int = 0
        set(value) {
            binding.avatarCardView.setCardBackgroundColor(value)
            if (isEnabled) enableBackgroundColor = value
            field = value
        }

    var iconTint: Int = 0
        set(value) {
            ImageViewCompat.setImageTintList(binding.avatarIconView, ColorStateList.valueOf(value))
            field = value
        }

    var letterColor: Int = 0
        set(value) {
            binding.avatarLetterView.setTextColor(value)
            field = value
        }

    init {
        setUpViews(attrs)
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
            img = getResourceId(R.styleable.UpaxAvatar_upax_avatar_img, 0)
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