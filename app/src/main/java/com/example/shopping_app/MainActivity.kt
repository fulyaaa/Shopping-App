package com.example.shopping_app

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.widget.TextView
import androidx.viewpager.widget.ViewPager
import com.example.shopping_app.adapter.OnboardingViewPagerAdapter
import com.example.shopping_app.databinding.ActivityMainBinding
import com.example.shopping_app.model.OnBoardingData
import com.google.android.material.tabs.TabLayout
import java.time.temporal.TemporalAdjusters.next

class MainActivity : AppCompatActivity() {

    private var _binding : ActivityMainBinding? = null
    private val binding get() = _binding!!
    var onBoardingViewPagerAdapter: OnboardingViewPagerAdapter? = null
    var tabLayout: TabLayout? = null
    var onBoardingViewPager: ViewPager? = null
    var next: TextView? = null
    var position = 0
    var sharedPreferences: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        if (restorePrefData()){
            val i = Intent(applicationContext, HomeActivity::class.java)
            startActivity(i)
            finish()
        }

        //requestWindowFeature(Window.FEATURE_NO_TITLE)
        //this.window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        //supportActionBar!!.hide()

        setContentView(view)
        //tabLayout = findViewById(R.id.tab_indicator)
        //next = findViewById(R.id.next)
        tabLayout = binding.tabIndicator
        next = binding.next


        //add to model class
        val onBoardingData: MutableList<OnBoardingData> = ArrayList()
        onBoardingData.add(OnBoardingData("Unlimited Transformation", "Our transportation network to every point of country.", R.drawable.unlimited_transportation))
        onBoardingData.add(OnBoardingData("Fast Delivery", "Modern delivering technologies. Shipping to the porch of you apartments. ", R.drawable.fast_delivery))
        onBoardingData.add(OnBoardingData("Easy Payment", "Download our shoppingapplication and buy using your smartphone or laptop.", R.drawable.easy_payment))

        setOnBoardingViewPagerAdapter(onBoardingData)


        position = onBoardingViewPager!!.currentItem
        next?.setOnClickListener {
            if (position <onBoardingData.size ){
                position++
                onBoardingViewPager!!.currentItem = position
            }
            if (position == onBoardingData.size){
                savePrefData()
                val i = Intent(applicationContext, HomeActivity::class.java)
                startActivity(i)
            }
        }

        //add a next button
        tabLayout!!.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

                position = tab!!.position
                if (tab.position ==  onBoardingData.size- 1){
                    next!!.text = "Get Started"
                }else{
                    next!!.text = "Next"
                }
            }

        })
    }

    private fun setOnBoardingViewPagerAdapter(OnBoardingData: List<OnBoardingData>){

        onBoardingViewPager = findViewById(R.id.screenPager);
        onBoardingViewPagerAdapter = OnboardingViewPagerAdapter(this, OnBoardingData)
        onBoardingViewPager!!.adapter = onBoardingViewPagerAdapter
        tabLayout?.setupWithViewPager(onBoardingViewPager)

    }

    //onBoarding screns runs first time
    private fun savePrefData(){
        sharedPreferences = applicationContext.getSharedPreferences("pref", Context.MODE_PRIVATE)
        val editor : SharedPreferences.Editor? = sharedPreferences!!.edit()
        editor?.putBoolean("isFirstTimeRun", true)
        editor?.apply()
    }

    private fun restorePrefData(): Boolean{
        sharedPreferences = applicationContext.getSharedPreferences("pref", Context.MODE_PRIVATE)
        return sharedPreferences!!.getBoolean("isFirstTimeRun", false)
    }

    //ıntercepter kullan yap parametre verme
    //intercepter servise istek atarken arya giren yapı
    //api key yollamıyoruz kullanmazsak sorun olur mu ? kullırsan artı hideri intercepter ile araya sokarsın
    //notcs le kur

}