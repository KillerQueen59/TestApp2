package com.ojanbelajar.testapp2.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ojanbelajar.testapp2.adapter.FoodAdapter
import com.ojanbelajar.testapp2.data.Resource
import com.ojanbelajar.testapp2.data.local.entity.CartEntity
import com.ojanbelajar.testapp2.databinding.CustomAddDialogBinding
import com.ojanbelajar.testapp2.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.custom_add_dialog.*
import org.jetbrains.anko.sdk27.coroutines.onCheckedChange
import org.jetbrains.anko.support.v4.toast

@AndroidEntryPoint
class MainFragment: Fragment() {

    lateinit var binding: FragmentMainBinding
    lateinit var model : MainViewModel
    lateinit var adapter: FoodAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater,container,false)
        model =  ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        adapter = FoodAdapter(requireContext()){ food ->
            val inflater = layoutInflater
            val binding = CustomAddDialogBinding.inflate(inflater)

            var half = false
            var whole = false
            var service = false
            var gst = binding.gst.isChecked
            var notes = ""
            var count = 0
            var total = 0
            var tax = 0
            binding.btnAdd.setOnClickListener {
                binding.qty.text = (++count).toString()
            }
            binding.btnSub.setOnClickListener {
                if (count > 0) binding.qty.text = (--count).toString()
            }
            binding.checkHalf.onCheckedChange { buttonView, isChecked ->
                half = isChecked
                if(isChecked){
                    binding.checkWhole.isChecked = false
                }
            }
            binding.checkWhole.onCheckedChange { buttonView, isChecked ->
                whole = isChecked
                if(isChecked){
                    binding.checkHalf.isChecked = false
                }
            }
            binding.service.onCheckedChange { buttonView, isChecked ->
                service = isChecked
            }
            binding.gst.onCheckedChange { buttonView, isChecked ->
                gst = isChecked
            }

            notes = binding.addNotes.text.toString()

            val alertDialog = AlertDialog.Builder(requireContext())
                .setView(binding.root)
                .create()
            alertDialog.show()
            binding.btnAddItem.setOnClickListener {
                if (count > 0){
                    total = food.price * count
                    if (half) total += 0
                    if (whole) total += 20000
                    if (service){
                        tax += (total*0.05).toInt()
                    }
                    if (gst){
                        tax += (total*0.1).toInt()
                    }

                    val cart = CartEntity(food.id.toInt(),food.name,food.price,half,whole,count,notes,tax,total)
                    model.insertCart(cart)
                    alertDialog.dismiss()
                } else {
                    toast("Item tidak boleh 0")
                }

            }
            binding.close.setOnClickListener {
                alertDialog.dismiss()
            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRv()
        searchFood("a")
    }

    private fun searchFood(q: String) {
        model.searchFood(q).observe(requireActivity(),{ result ->
            if (result != null) {
                when (result){
                    is Resource.Error -> {}
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        adapter.setData(result.data)
                    }
                }
            }
        })

    }

    private fun setRv(){
        binding.rvFood.layoutManager = LinearLayoutManager(requireContext())
        binding.rvFood.adapter = adapter
    }


}
