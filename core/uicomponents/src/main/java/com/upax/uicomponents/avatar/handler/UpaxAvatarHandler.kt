package com.upax.uicomponents.avatar.handler

import com.upax.uicomponents.utils.BaseView
import com.upax.uicomponents.avatar.factory.AvatarFactory
import com.upax.uicomponents.avatar.model.UpaxAvatarType
import com.upax.uicomponents.databinding.ViewUpaxAvatarBinding
import com.upax.uicomponents.utils.isVisible

internal class UpaxAvatarHandler (avatarInterface: BaseView<ViewUpaxAvatarBinding>, private val factory: AvatarFactory) {
    private val view = avatarInterface.getView()

    fun setAvatarType(avatarType: UpaxAvatarType){
        val visibleViews = factory.getAvatarType(avatarType).visibleViews()
        with(view){
            avatarLetterView.isVisible(visibleViews.first)
            avatarPlaceHolderView.isVisible(visibleViews.second)
            avatarImageView.isVisible(visibleViews.third)
        }
    }
}