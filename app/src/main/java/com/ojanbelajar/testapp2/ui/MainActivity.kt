package com.ojanbelajar.testapp2.ui

import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ojanbelajar.testapp2.R
import com.ojanbelajar.testapp2.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding : ActivityMainBinding
    private val mainFragment: Fragment = MainFragment()
    private val cartFragment: Fragment = CartFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_fragment, mainFragment)
            .commit()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.cart_fragment, cartFragment)
            .commit()


    }
}