package com.example.viewpagerdemo;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends FragmentActivity {

	static final int ITEMS = 10;
	ViewPager mViewPager;
	static ArrayList<String> CHESSES = new ArrayList<String>();
	MyAdapter myAdapter;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        CHESSES.add("Chesse0");
        
        myAdapter = new MyAdapter(getSupportFragmentManager());
        
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(myAdapter);
    }
    
    public void MyOnClick(View view) {
    	int id = view.getId();
    	int position;
    	
    	switch (id) {
		case R.id.goto_previous:
			position = mViewPager.getCurrentItem();
			CHESSES.add("Chesse"+(position + 1));
			if(position > 0){
				mViewPager.setCurrentItem(position - 1);
			}
			else{
				Toast.makeText(MainActivity.this, "This is First Fragment", Toast.LENGTH_LONG).show();
			}
			myAdapter.notifyDataSetChanged();
			break;

		case R.id.goto_next:
			position = mViewPager.getCurrentItem();
			CHESSES.add("Chesse"+(position + 1));
			if(position < (ITEMS - 1)){
				mViewPager.setCurrentItem(position + 1);
			}
			else{
				Toast.makeText(MainActivity.this, "This is Last Fragment", Toast.LENGTH_LONG).show();
			}
			myAdapter.notifyDataSetChanged();
			break;
		}
	}
    
    static class MyAdapter extends FragmentStatePagerAdapter
    {
		public MyAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int position) {
			Log.d(getClass().getSimpleName(), position+"");
			return ArrayListFragment.newInstance(position);
		}

		@Override
		public int getCount() {
			return ITEMS;
		}
		
		@Override
		public int getItemPosition(Object object) {
			return POSITION_NONE;
		}
    }
}
