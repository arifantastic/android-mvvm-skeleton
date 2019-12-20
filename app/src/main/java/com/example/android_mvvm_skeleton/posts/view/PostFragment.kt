package com.example.android_mvvm_skeleton.posts.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android_mvvm_skeleton.customview.hide
import com.example.android_mvvm_skeleton.databinding.FragmentPostBinding
import com.example.android_mvvm_skeleton.di.Injectable
import com.example.android_mvvm_skeleton.di.injectViewModel
import com.example.android_mvvm_skeleton.posts.adapter.PostAdapter
import com.example.android_mvvm_skeleton.posts.viewmodel.PostViewModel
import com.example.android_mvvm_skeleton.utils.NetworkUtils
import javax.inject.Inject

class PostFragment : Fragment(), Injectable {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: PostViewModel

    private lateinit var binding: FragmentPostBinding
    private val adapter: PostAdapter by lazy { PostAdapter() }
    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = injectViewModel(viewModelFactory)
        viewModel.connectivityAvailable = NetworkUtils.isConnected(context!!)

        binding = FragmentPostBinding.inflate(inflater, container, false)
        context ?: return binding.root

        setupRecyclerView(binding.rvPost)
        subscribeUi(adapter)
        return binding.root
    }

    private fun setupRecyclerView(mRecyclerView: RecyclerView) {
        linearLayoutManager =
            LinearLayoutManager(mRecyclerView.context, LinearLayoutManager.VERTICAL, false)
        mRecyclerView.layoutManager = linearLayoutManager
        mRecyclerView.adapter = adapter
    }

    private fun subscribeUi(adapter: PostAdapter) {
        viewModel.posts.observe(viewLifecycleOwner, Observer {
            binding.pbPost.hide()
            adapter.submitList(it)
        })
    }
}