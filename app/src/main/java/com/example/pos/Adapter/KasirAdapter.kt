package com.example.pos.Adapter

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ProcessLifecycleOwner.get
import androidx.recyclerview.widget.RecyclerView
import com.example.pos.Barang
import com.example.pos.Model.Keranjang
import com.example.pos.UI.TransactionActivity
import com.example.pos.Utils.Converter
import com.example.pos.ViewModel.KasirActivityViewModel
import com.example.pos.databinding.ItemBarangKasirBinding
import kotlinx.android.synthetic.main.activity_kasir.view.*
import kotlinx.android.synthetic.main.item_barang.view.*
import kotlinx.android.synthetic.main.item_barang.view.tvhargabrg
import kotlinx.android.synthetic.main.item_barang.view.tvnamabrg
import kotlinx.android.synthetic.main.item_barang_kasir.view.*
import kotlinx.android.synthetic.main.number_picker.view.*
import java.lang.String

import java.lang.reflect.Array.set
import java.util.ArrayList


class KasirAdapter(
    private val barangList: List<Barang>,
    private val keranjangList: List<Keranjang>,
    private var kasirActivityViewModel: KasirActivityViewModel,
    val context: Context,
    private var selected: MutableList<Barang> = ArrayList()
)
    : RecyclerView.Adapter<KasirAdapter.ItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBarangKasirBinding.inflate(inflater)
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return barangList.size
        return keranjangList.size

    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val barang = barangList[position]
//        val barang = barangList.get(position)
        holder.bindView(barangList[position])
        holder.itemView.tvnamabrg.text = barang.namabarang
//        holder.itemView.product_selectedQuantity.text = barang.selectedQuantity.toString()
        holder.itemView.tvhargabrg.text = Converter.rupiah(barang.hargabarang.toDouble())

        holder.itemView.cart_minus_img.setOnClickListener { v: View? ->
            if (barang.selectedQuantity > 0) barang.selectedQuantity  = barang.selectedQuantity - 1
            holder.itemView.cart_product_quantity_tv.text = java.lang.String.valueOf(barang.selectedQuantity)
            if (barang.selectedQuantity == 0) selected.remove(barang)


            /*************************************/

//            kasirActivityViewModel.decrementQuantityProduct(barang)
//            Toast.makeText(context, "total = " + barang.selectedQuantity, Toast.LENGTH_SHORT).show()
//            val qty = 0
//            val qryall = qty + 1
////            holder.itemView.product_selectedQuantity.text = barang.selectedQuantity.toString()
//            holder.itemView.product_selectedQuantity.text = qryall.toString()
//
        }
//
        holder.itemView.cart_plus_img.setOnClickListener { v: View? ->
            barang.selectedQuantity = barang.selectedQuantity?.plus(1)
            holder.itemView.cart_product_quantity_tv.text = java.lang.String.valueOf(barang.selectedQuantity)




            /************************************************/

//            val p = barang.copy()
//            p.selectedQuantity = 1
//            kasirActivityViewModel.addSelectedProduct(p)
//
//            holder.itemView.cart_product_quantity_tv.text = p.selectedQuantity.toString().count().toString()
////
//            Toast.makeText(context, "total = " + p.toString(), Toast.LENGTH_SHORT).show()


        }

//        holder.cardView.setOnClickListener { view: View? ->
//            val item = selected as ArrayList<Barang>
//            val intent = Intent(context, TransactionActivity::class.java)
//            val b = Bundle()
//            b.putParcelableArrayList("cart", item)
//            intent.putExtras(b)
//            context.startActivity(intent)
//        }
    }

    class ItemViewHolder(private val binding: ItemBarangKasirBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {
        val cardView = binding.itemContainer
        fun bindView(barang: Barang) {
            binding.barang = barang
        }


    }

}

