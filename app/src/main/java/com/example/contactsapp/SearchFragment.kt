package com.example.contactsapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.os.bundleOf
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {
    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)

        val recyclerView: RecyclerView = view.findViewById(R.id.search_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)

        val adapter = UserAdapter()
        recyclerView.adapter = adapter

        val progressBar: ProgressBar = view.findViewById(R.id.progressBar)
        viewLifecycleOwner.lifecycleScope.launch(context = Dispatchers.Main) {
            progressBar.visibility = View.VISIBLE
            adapter.userList = UserRepository.loadUsers()
            progressBar.visibility = View.GONE
            adapter.notifyDataSetChanged()
        }
        adapter.listener = { userId ->
            val bundle = bundleOf("USER_ID" to userId)
            findNavController().navigate(R.id.action_search_to_detail, bundle)
        }

        return view
    }
}