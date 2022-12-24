package com.upax.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.upax.profile.databinding.FragmentProfileBinding
import com.upax.uicomponents.avatar.model.UpaxAvatarType

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.profileUrlEditText.setText("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png")
        binding.profileLoadImageButton.setOnClickListener {
            binding.profileUpaxAvatar.type = UpaxAvatarType.IMG
            binding.profileUpaxAvatar.setImageWithUrl(
                binding.profileUrlEditText.text.toString(),
                ::loadImageFailed
            )
            setUpAvatar()
        }
    }

    private fun loadImageFailed() {
        setUpLetterAvatar()
    }

    private fun setUpAvatar() {
        val textUrl = binding.profileUrlEditText.text.toString()
        if(textUrl.isEmpty()) setUpImageAvatar()
    }

    private fun setUpImageAvatar() {
        binding.profileUpaxAvatar.type = UpaxAvatarType.PLACEHOLDER
        binding.profileUpaxAvatar.placeholder = R.drawable.ic_baseline_person_24
    }

    private fun setUpLetterAvatar(){
        binding.profileUpaxAvatar.type = UpaxAvatarType.LETTERS
        binding.profileUpaxAvatar.letter = binding.profileUrlEditText.text.toString()
    }
}