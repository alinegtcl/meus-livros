package com.luisitolentino.meuslivros.presentation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.luisitolentino.meuslivros.databinding.FragmentAddBookBinding
import com.luisitolentino.meuslivros.domain.model.Book
import com.luisitolentino.meuslivros.presentation.viewmodel.BookControlViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddBookFragment : Fragment() {

    private val viewModel: BookControlViewModel by viewModel()

    private var _binding: FragmentAddBookBinding? = null
    private val binding get() = _binding!!
    private var book = Book(
        name = "Teste", writer = "Aline", status = "em progresso")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddBookBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAddBookButton()
    }

    private fun setupAddBookButton() {
        binding.buttonAddBook.setOnClickListener {
            viewModel.insert(book)
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}