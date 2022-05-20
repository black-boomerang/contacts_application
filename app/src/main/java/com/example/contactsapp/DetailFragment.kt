package com.example.contactsapp

import UserDetailContent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView

class DetailFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false).apply {
            findViewById<ComposeView>(R.id.compose_view).setContent {
                val userId = arguments?.getInt("USER_ID") ?: 0
                val user = UserRepository.getUserById(userId)
                UserDetailContent(user)
            }
        }
    }
}