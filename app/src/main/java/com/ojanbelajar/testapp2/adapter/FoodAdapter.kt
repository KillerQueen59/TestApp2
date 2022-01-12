package com.ojanbelajar.testapp2.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ojanbelajar.testapp2.R
import com.ojanbelajar.testapp2.data.remote.Food
import com.ojanbelajar.testapp2.databinding.ItemFoodBinding
import com.ojanbelajar.testapp2.databinding.CustomAddDialogBinding
import org.jetbrains.anko.layoutInflater
import org.jetbrains.anko.toast

class FoodAdapter(private val context: Context, private val listener: (Food) -> Unit) : RecyclerView.Adapter<FoodAdapter.FoodAdapterViewHolder>() {

    private var listData = ArrayList<Food>()


    fun setData(newListData: List<Food>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodAdapterViewHolder =
        FoodAdapterViewHolder(ItemFoodBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: FoodAdapterViewHolder, position: Int) {
        val food = listData[position]
        holder.bind(food,context)
        holder.itemView.setOnClickListener {
            listener(food)
        }
    }

    override fun getItemCount(): Int = listData.size

    class FoodAdapterViewHolder(private val binding: ItemFoodBinding): RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(food: Food, context: Context){
            binding.tvNameFood.text = food.name
            binding.tvPriceFood.text = "Rp. ${food.price}"
            Glide.with(context).load(food.image).into(binding.ivFood)
        }
    }
    interface OnItemClickListener {
        fun onItemClick(item: Food)
    }
}