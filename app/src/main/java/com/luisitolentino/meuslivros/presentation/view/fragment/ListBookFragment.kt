package com.luisitolentino.meuslivros.presentation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.luisitolentino.meuslivros.R
import com.luisitolentino.meuslivros.databinding.FragmentListBookBinding
import com.luisitolentino.meuslivros.presentation.viewmodel.BookControlViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
class ListBookFragment : Fragment() {

    private val viewModel: BookControlViewModel by viewModel()

    private var _binding: FragmentListBookBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBookBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAddBookButton()
    }

    private fun setupAddBookButton() {
        binding.buttonAddNewBook.setOnClickListener {
            findNavController().navigate(R.id.go_to_addBookFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}