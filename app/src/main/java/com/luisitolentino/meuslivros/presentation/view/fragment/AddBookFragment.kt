package com.luisitolentino.meuslivros.presentation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.luisitolentino.meuslivros.databinding.FragmentAddBookBinding
import com.luisitolentino.meuslivros.domain.model.Book
import com.luisitolentino.meuslivros.presentation.viewmodel.AddBookState
import com.luisitolentino.meuslivros.presentation.viewmodel.BookControlViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddBookFragment : Fragment() {

    private val viewModel: BookControlViewModel by viewModel()

    private var _binding: FragmentAddBookBinding? = null
    private val binding get() = _binding!!
    private var book = Book(
        name = "Teste", writer = "Aline", status = "em progresso"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddBookBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewmodel()
        setupAddBookButton()
    }

    private fun setupViewmodel() {
        lifecycleScope.launch {
            viewModel.stateManagement.collect {
                when (it) {
                    AddBookState.HideLoading -> {}
                    AddBookState.ShowLoading -> {}
                    AddBookState.InsertSuccess -> {
                        Toast.makeText(
                            activity, "Livro adicionado! Boa leitura!",
                            Toast.LENGTH_SHORT
                        ).show()
                        findNavController().popBackStack()
                    }
                }
            }
        }
    }

    private fun setupAddBookButton() {
        binding.buttonAddBook.setOnClickListener {
            viewModel.insert(book)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}