package com.ojanbelajar.testapp2.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ojanbelajar.testapp2.adapter.CartAdapter
import com.ojanbelajar.testapp2.adapter.FoodAdapter
import com.ojanbelajar.testapp2.data.Resource
import com.ojanbelajar.testapp2.databinding.FragmentCartBinding
import dagger.hilt.android.AndroidEntryPoint
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast
import kotlin.math.roundToInt

@AndroidEntryPoint
class CartFragment : Fragment() {
    lateinit var adapter: CartAdapter
    lateinit var binding: FragmentCartBinding
    lateinit var model: CartViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCartBinding.inflate(layoutInflater,container,false)
        model =  ViewModelProvider(requireActivity()).get(CartViewModel::class.java)
        adapter = CartAdapter(requireContext()){ cart -> }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRv()
        getCart()
    }

    private fun getCart(){
        model.getCart().observe(requireActivity(),{ result ->
            if (result != null) {
                when (result){
                    is Resource.Error -> {}
                    is Resource.Loading -> {}
                    is Resource.Success -> {
                        var total = 0
                        var tax = 0
                        var finalTotal = 0
                        if (result.data?.isNotEmpty() == true){
                            binding.tvTotalProduct.text = "${result.data.size} products"
                            for (i in result.data){
                                total += i.total
                                tax += i.taxes
                            }
                        }
                        finalTotal = roundToThousand(total+tax).roundToInt()
                        binding.subtotal.text = "Rp. $total"
                        binding.taxes.text = "Rp. $tax"
                        binding.btnSubmit.text = "Charge Rp. ${roundToThousand(total+tax)}"
                        binding.btnSubmit.setOnClickListener {
                            if (result.data?.isNotEmpty() == true){
                                startActivity<PaymentActivity>("total" to finalTotal)
                            } else {
                                toast("Cart kosong")
                            }

                        }
                        adapter.setData(result.data)
                    }
                }
            }
        })
    }

    private fun roundToThousand(i: Int)= (Math.ceil(i / 1000.0) * 1000);

    private fun setRv(){
        binding.rvCart.layoutManager = LinearLayoutManager(requireContext())
        binding.rvCart.adapter = adapter
    }
}