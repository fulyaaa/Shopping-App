package com.example.shopping_app.domain.feature.onBoarding.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.example.shopping_app.R
import com.example.shopping_app.databinding.ActivityMainBinding
import com.example.shopping_app.databinding.OnboardingScreenLayoutBinding
import com.example.shopping_app.model.OnBoardingData

class OnboardingViewPagerAdapter(private var context:Context, private var onBoardingDataList: List<OnBoardingData>) : PagerAdapter() {


    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return onBoardingDataList.size
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view :View = LayoutInflater.from(context).inflate(R.layout.onboarding_screen_layout, null);
        val imageView : ImageView
        val title : TextView
        val description : TextView

        imageView = view.findViewById(R.id.imageView);
        title = view.findViewById(R.id.title);
        description = view.findViewById(R.id.description);

        imageView.setImageResource(onBoardingDataList[position].imageUrl)
        title.text = onBoardingDataList[position].title
        description.text = onBoardingDataList[position].description

        container.addView(view)
        return view
    }
}