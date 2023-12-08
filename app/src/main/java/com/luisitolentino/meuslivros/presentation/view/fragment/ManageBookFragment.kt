package com.luisitolentino.meuslivros.presentation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.luisitolentino.meuslivros.R
import com.luisitolentino.meuslivros.databinding.FragmentAddBookBinding
import com.luisitolentino.meuslivros.domain.model.Book
import com.luisitolentino.meuslivros.domain.utils.Constants.LABEL_PUT_EXTRA_BOOK_ID
import com.luisitolentino.meuslivros.presentation.viewmodel.BookControlViewModel
import com.luisitolentino.meuslivros.presentation.viewmodel.ManageBookState
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.properties.Delegates

class ManageBookFragment : Fragment() {

    private val viewModel: BookControlViewModel by viewModel()

    private var _binding: FragmentAddBookBinding? = null
    private val binding get() = _binding!!

    private lateinit var book: Book
    private var bookId by Delegates.notNull<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddBookBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBundleId()
        setupViewmodel()
        setupManagementBookButtons()
        setupView()
    }

    private fun setupBundleId() {
        bookId = requireArguments().getInt(LABEL_PUT_EXTRA_BOOK_ID)
    }

    private fun setupView() {
        if (bookId != 0) {
            viewModel.getBookById(bookId)
        } else {
            setupAddBook()
        }
    }

    private fun setupAddBook() {
        binding.apply {
            buttonAddBook.visibility = View.VISIBLE
            buttonUpdateBook.visibility = View.GONE
            buttonDeleteBook.visibility = View.GONE
        }
    }

    private fun setupViewmodel() {
        lifecycleScope.launch {
            viewModel.stateManagement.collect {
                when (it) {
                    ManageBookState.HideLoading -> {}
                    ManageBookState.ShowLoading -> {}
                    ManageBookState.InsertSuccess -> {
                        Toast.makeText(
                            activity, getString(R.string.label_added_book),
                            Toast.LENGTH_SHORT
                        ).show()
                        findNavController().popBackStack()
                    }

                    is ManageBookState.Failure -> {}
                    is ManageBookState.GetByIdSuccess -> fillFields(it.book)
                    ManageBookState.UpdateSuccess -> {
                        Toast.makeText(
                            activity, getString(R.string.label_changed_book),
                            Toast.LENGTH_SHORT
                        ).show()
                        findNavController().popBackStack()
                    }
                }
            }
        }
    }

    private fun fillFields(book: Book) {
        binding.apply {
            editTextBookName.setText(book.name)
            editTextWriters.setText(book.writer)
            editTextStatus.setText(book.status)
            buttonAddBook.visibility = View.GONE
            buttonUpdateBook.visibility = View.VISIBLE
            buttonDeleteBook.visibility = View.VISIBLE
        }
    }

    private fun setupManagementBookButtons() {
        binding.buttonAddBook.setOnClickListener {
            binding.apply {
                book = Book(
                    editTextBookName.text.toString(),
                    editTextWriters.text.toString(),
                    editTextStatus.text.toString()
                )
            }
            viewModel.insert(book)
        }
        binding.buttonUpdateBook.setOnClickListener {
            binding.apply {
                book = Book(
                    editTextBookName.text.toString(),
                    editTextWriters.text.toString(),
                    editTextStatus.text.toString(),
                    id = bookId
                )
            }
            viewModel.update(book)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}