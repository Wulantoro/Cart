package com.example.pos.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pos.Barang
import com.example.pos.R
import com.example.pos.ViewModel.KasirActivityViewModel
import com.example.pos.ViewModel.TransactionViewModel
import com.example.pos.ViewModel.TransactionViewModel_AssistedFactory
import kotlinx.android.synthetic.main.list_item_selected.view.*
import kotlinx.android.synthetic.main.number_picker.view.*

class KeranjangAdapter(private var selectedProduct : MutableList<Barang>, private var context: Context,
private var kasirActivityViewModel: KasirActivityViewModel
) : RecyclerView.Adapter<KeranjangAdapter.ViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KeranjangAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item_selected, parent, false))
    }

    override fun getItemCount() = selectedProduct.size

    fun updateList(ps: List<Barang>){
        selectedProduct.clear()
        selectedProduct.addAll(ps)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(selectedProduct[position], context, kasirActivityViewModel)



    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(barang: Barang, context: Context, kasirActivityViewModel: KasirActivityViewModel) {
            itemView.product_name.text = barang.namabarang
            itemView.product_price.text = barang.hargabarang
            itemView.productQuantity.text = barang.selectedQuantity.toString()
        }
    }




}