package com.luisitolentino.meuslivros.presentation.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.luisitolentino.meuslivros.databinding.TileBookBinding
import com.luisitolentino.meuslivros.domain.model.Book

class BookAdapter(
    private val bookList: List<Book>,
    private val onClickListener: (Book) -> Unit
) : androidx.recyclerview.widget.ListAdapter<Book, BookAdapter.BookViewHolder>(differCallback) {

    private lateinit var binding: TileBookBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BookViewHolder {
        binding = TileBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class BookViewHolder(binding: TileBookBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(book: Book) {
            binding.apply {
                textBookName.text = book.name
                textWriterName.text = book.writer
                textStatus.text = book.status
                root.setOnClickListener { onClickListener(book) }
            }
        }
    }

    companion object {
        val differCallback = object : DiffUtil.ItemCallback<Book>() {
            override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
                return oldItem == newItem
            }
        }
    }

}