package com.ojanbelajar.testapp2.ui

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.ojanbelajar.testapp2.databinding.ActivityPaymentBinding
import com.ojanbelajar.testapp2.databinding.CustomPayDialogBinding
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import kotlin.math.floor

class PaymentActivity : AppCompatActivity() {

    private lateinit var binding : ActivityPaymentBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val total = intent.getIntExtra("total",0)
        binding.total!!.text = "Rp. $total"
        binding.exact!!.text = total.toString()
        binding.nearestTo5!!.text = nearestTo5(total).toString()
        binding.nearestTo10!!.text = nearestTo10(total).toString()
        binding.bankMethod!!.setOnClickListener {
            toast("Implement later with midtrans")
        }
        binding.qrisMethod!!.setOnClickListener {
            toast("Implement later with midtrans")
        }
        binding.debitMethod!!.setOnClickListener {
            toast("Implement later with midtrans")
        }
        binding.btnBack!!.setOnClickListener {
            finish()
        }
        binding.custom!!.setOnClickListener {
            val inflater = layoutInflater
            val binding = CustomPayDialogBinding.inflate(inflater)
            val alertDialog = AlertDialog.Builder(this)
                .setView(binding.root)
                .create()
            alertDialog.show()

            binding.pay.setOnClickListener {
                if (binding.edtCustomPayment.text.isNotEmpty()){
                    val value = binding.edtCustomPayment.text.toString().toInt()
                    if (value <= total) toast("Harus lebih besar dari total")
                    else {
                        startActivity<ReceiptActivity>("charge" to (value-total), "total" to total)
                    }
                } else {
                    toast("Isi Value")
                }
            }
        }
        binding.exact!!.setOnClickListener {
            startActivity<ReceiptActivity>("charge" to  0, "total" to total)
        }
        binding.nearestTo5!!.setOnClickListener {
            startActivity<ReceiptActivity>("charge" to nearestTo5(total)-total, "total" to total)
        }
        binding.nearestTo10!!.setOnClickListener {
            startActivity<ReceiptActivity>("charge" to nearestTo10(total)-total, "total" to total)
        }
    }

    private fun nearestTo5(i: Int): Int = ((floor((i/1000)/5.0) *5).toInt() + 5) * 1000
    private fun nearestTo10(i: Int): Int = ((floor((i/1000)/5.0) *5).toInt() + 10) * 1000
}