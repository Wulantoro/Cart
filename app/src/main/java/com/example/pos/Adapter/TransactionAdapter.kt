package com.example.pos.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.example.pos.Keranjang
import com.example.pos.databinding.ContentTransactionBinding
import kotlinx.android.synthetic.main.content_transaction.view.*

class TransactionAdapter (
    private val keranjangList: List<Keranjang>,
    val context: Context
        )
    : RecyclerView.Adapter<TransactionAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ContentTransactionBinding.inflate(inflater)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val keranjang = keranjangList[position]
        holder.bindView(keranjangList[position])
        holder.itemView.tvnamabrg.text = keranjang.nmbarang.toString()
        holder.itemView.tvhargabrg.text = keranjang.total.toString()
        holder.itemView.jmlbarang.text = keranjang.jumlahbelanja.toString()
    }

    override fun getItemCount(): Int {
        return keranjangList.size
    }

    class ItemViewHolder(private val binding: ContentTransactionBinding): RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bindView(keranjang: Keranjang) {
            binding.keranjang = keranjang
        }
    }




}

