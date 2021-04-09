package com.example.pos.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pos.Barang
import com.example.pos.R
import kotlinx.android.synthetic.main.list_item_selected.view.*
import java.util.ArrayList

class TransactionAdapter(itemList: List<Barang>) :
    RecyclerView.Adapter<TransactionAdapter.ViewHolder>() {

    private var item: List<Barang>

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TransactionAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_selected, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TransactionAdapter.ViewHolder, position: Int) {
        val itm = item[position]

        holder.bind(item[position])

//        holder.product_name.text = java.lang.String.valueOf(itm.namabarang)
//        holder.product_price.text = java.lang.String.valueOf(itm.hargabarang)
//        holder.productQuantity.text = java.lang.String.valueOf(itm.selectedQuantity)

    }

    override fun getItemCount(): Int {
        return item.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(barang: Barang) {
            itemView.product_name.text = barang.namabarang
            itemView.product_price.text = barang.hargabarang
            itemView.productQuantity.text = barang.selectedQuantity.toString()
        }


    }

    init {
        item = ArrayList()
        item = itemList
    }
}