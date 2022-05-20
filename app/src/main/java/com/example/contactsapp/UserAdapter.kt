package com.example.contactsapp

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.annotation.Dimension
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.marginTop
import androidx.recyclerview.widget.RecyclerView

class UserAdapter : RecyclerView.Adapter<UserViewHolder>() {
    var userList: List<User> = emptyList()
    var listener: (Int)->Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val inflater = parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView = inflater.inflate(R.layout.user_item, parent, false) as ConstraintLayout
        if (viewType == 0) {
            val cardView: CardView = itemView.findViewById(R.id.cardView)
            var params: ViewGroup.MarginLayoutParams = cardView.layoutParams as ViewGroup.MarginLayoutParams
            (cardView.layoutParams as ViewGroup.MarginLayoutParams).setMargins(
                params.leftMargin,
                parent.resources.getDimension(R.dimen.cardMarginVertical).toInt() * 2,
                params.rightMargin,
                params.bottomMargin
            )
        } else if (viewType == userList.size - 1) {
            val cardView: CardView = itemView.findViewById(R.id.cardView)
            var params: ViewGroup.MarginLayoutParams = cardView.layoutParams as ViewGroup.MarginLayoutParams
            (cardView.layoutParams as ViewGroup.MarginLayoutParams).setMargins(
                params.leftMargin,
                params.topMargin,
                params.rightMargin,
                parent.resources.getDimension(R.dimen.cardMarginVertical).toInt() * 2,
            )
        }
        return UserViewHolder(itemView, listener)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(userList[position])
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }
}