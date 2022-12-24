package com.upax.uicomponents.avatar.factory

import com.upax.uicomponents.avatar.model.UpaxAvatarType

class AvatarFactory {

    fun getAvatarType(type: UpaxAvatarType) =
        when (type) {
            UpaxAvatarType.LETTERS -> AvatarLetter()
            UpaxAvatarType.IMG -> AvatarImage()
            UpaxAvatarType.PLACEHOLDER -> AvatarPlaceholder()
        }

}