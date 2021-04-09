package com.example.pos.Adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pos.Barang
import com.example.pos.UI.UpdateActivity
import com.example.pos.Utils.Converter
import com.example.pos.ViewModel.AdminActivityViewModel
import com.example.pos.databinding.ItemBarangBinding
import kotlinx.android.synthetic.main.item_barang.view.*


class BarangAdapter(private val barangList: ArrayList<Barang>, val context: Context, val viewModel: AdminActivityViewModel)
    : RecyclerView.Adapter<BarangAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBarangBinding.inflate(inflater)
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return barangList.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        val barang = barangList[position]
        holder.bindView(barangList[position])

        holder.itemView.tvnamabrg.text = barang.namabarang
        holder.itemView.tvhargabrg.text = Converter.rupiah(barang.hargabarang.toDouble())
        holder.itemView.tvstock.text = barang.stockbarang
        holder.imgedit.setOnClickListener{ v ->
            val intent = Intent(v.context, UpdateActivity::class.java)
            intent.putExtra("idbarang", barangList[position].idbarang)
            v.context.startActivity(intent)
        }

        holder.imghapus.setOnClickListener { v ->
            viewModel.delettebarang(barang)
        }
    }

    class ItemViewHolder(private val binding: ItemBarangBinding) : RecyclerView.ViewHolder(binding.root) {
//        val cardView = binding.itemContainer
        val imghapus = binding.imghapus
        val imgedit = binding.imgedit
        fun bindView(barang: Barang){
            binding.barang = barang
        }
    }
}

