package com.ojanbelajar.testapp2.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ojanbelajar.testapp2.data.local.entity.CartEntity
import com.ojanbelajar.testapp2.data.remote.Food
import com.ojanbelajar.testapp2.databinding.ItemCartBinding

class CartAdapter (private val context: Context, private val listener: (CartEntity) -> Unit) : RecyclerView.Adapter<CartAdapter.CartAdapterViewHolder>() {

    private var listData = ArrayList<CartEntity>()


    fun setData(newListData: List<CartEntity>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapterViewHolder =
        CartAdapterViewHolder(ItemCartBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: CartAdapterViewHolder, position: Int) {
        val cart = listData[position]
        holder.bind(cart,context)
        holder.itemView.setOnClickListener {
            listener(cart)
        }
    }

    override fun getItemCount(): Int = listData.size

    class CartAdapterViewHolder(private val binding: ItemCartBinding): RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(cart: CartEntity, context: Context){
            binding.tvNameFood.text = "${cart.name} x${cart.qty}"
            if (cart.half) binding.type.text = "Half"
            if (cart.whole) binding.type.text = "Whole"
            binding.total.text = "Rp. ${cart.total}"

        }
    }
    interface OnItemClickListener {
        fun onItemClick(item: Food)
    }
}