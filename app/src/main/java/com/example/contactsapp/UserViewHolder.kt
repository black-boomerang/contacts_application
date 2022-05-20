package com.example.contactsapp

import android.animation.Animator
import android.animation.AnimatorInflater
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class UserViewHolder(itemView: ConstraintLayout, listener: (Int) -> Unit) : RecyclerView.ViewHolder(itemView) {
    private val loginTextView: TextView = itemView.findViewById(R.id.login_text_view)
    private val nameTextView: TextView = itemView.findViewById(R.id.name_text_view)
    private val imageView: ImageView = itemView.findViewById(R.id.imageView)
    private val cardView: CardView = itemView.findViewById(R.id.cardView)
    private lateinit var data: User

    init {
        cardView.setOnClickListener {
            val animatorSet = AnimatorInflater.loadAnimator(cardView.context,
                R.animator.card_click_animation)
            animatorSet.setTarget(cardView)
            animatorSet.start()
            animatorSet.addListener(object: Animator.AnimatorListener {
                override fun onAnimationStart(p0: Animator?) {}
                override fun onAnimationCancel(p0: Animator?) {}
                override fun onAnimationRepeat(p0: Animator?) {}
                override fun onAnimationEnd(p0: Animator?) {
                    listener(data.id)
                }
            })
        }
    }

    fun bind(user: User) {
        data = user
        loginTextView.text = data.login
        nameTextView.text = data.name
        Picasso.get().load(data.imageUrl).into(object : com.squareup.picasso.Target {
            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                imageView.background = BitmapDrawable(imageView.context.resources, bitmap)
            }

            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {}

            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {}
        })
    }
}