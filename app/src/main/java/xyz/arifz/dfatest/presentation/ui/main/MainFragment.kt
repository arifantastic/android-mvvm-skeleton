package xyz.arifz.dfatest.presentation.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer

class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels { viewModelFactory }

    private lateinit var viewModelFactory: MainViewModelFactory
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getUsersWithPosts()

        viewModel.usersWithPosts.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Result.Success -> {
                    // Update UI with result.data
                }
                is Result.Error -> {
                    // Handle error
                }
            }
        })
    }
}
