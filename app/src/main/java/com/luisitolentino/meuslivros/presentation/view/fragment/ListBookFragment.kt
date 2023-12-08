package com.luisitolentino.meuslivros.presentation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.luisitolentino.meuslivros.R
import com.luisitolentino.meuslivros.databinding.FragmentListBookBinding
import com.luisitolentino.meuslivros.domain.model.Book
import com.luisitolentino.meuslivros.domain.utils.Constants.LABEL_PUT_EXTRA_BOOK_ID
import com.luisitolentino.meuslivros.presentation.view.adapter.BookAdapter
import com.luisitolentino.meuslivros.presentation.viewmodel.BookControlViewModel
import com.luisitolentino.meuslivros.presentation.viewmodel.ListBookState
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListBookFragment : Fragment() {

    private val viewModel: BookControlViewModel by viewModel()

    private var _binding: FragmentListBookBinding? = null
    private val binding get() = _binding!!

    private lateinit var bookAdapter: BookAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBookBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getAllBooks()
        setupViewmodel()
        setupAddBookButton()
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAllBooks()
    }

    private fun setupViewmodel() {
        lifecycleScope.launch {
            viewModel.stateList.collect {
                when (it) {
                    ListBookState.EmptyState -> binding.textEmptyState.visibility = View.VISIBLE
                    is ListBookState.Failure -> {}
                    ListBookState.HideLoading -> binding.loadingListBook.visibility = View.GONE
                    ListBookState.ShowLoading -> binding.loadingListBook.visibility = View.VISIBLE
                    is ListBookState.SearchAllSuccess -> setupRecycler(it.books)
                }
            }
        }
    }

    private fun setupRecycler(books: List<Book>) {
        bookAdapter = BookAdapter(books, ::onBookClick).apply { submitList(books) }
        binding.recyclerListBooks.adapter = bookAdapter
    }

    private fun onBookClick(book: Book) {
        val bundle = Bundle()
        bundle.putInt(LABEL_PUT_EXTRA_BOOK_ID, book.id)
        findNavController().navigate(R.id.go_to_addBookFragment, bundle)

    }

    private fun setupAddBookButton() {
        binding.buttonAddNewBook.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt(LABEL_PUT_EXTRA_BOOK_ID, -1)
            findNavController().navigate(R.id.go_to_addBookFragment, Bundle())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}