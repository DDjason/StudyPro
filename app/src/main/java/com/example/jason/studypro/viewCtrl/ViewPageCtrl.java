package com.example.jason.studypro.viewCtrl;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jason.studypro.R;
import com.example.jason.studypro.viewModel.VPageModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Yangyd
 * E-mail: yangyd@erongdu.com
 * Date: 2017/10/9$ 16:38$
 * <p/>
 */
public class ViewPageCtrl{
    private List<View> viewList;//view数组
    private List<CharSequence> titleList;
    private PagerAdapter pagerAdapter;
    private ViewPager.OnPageChangeListener pageChangeListener;
    private VPageModel viewModel;
    public ViewPageCtrl(LayoutInflater inflater1){
        viewModel = new VPageModel();
        viewModel.setProcess(0);
        LayoutInflater inflater = inflater1;
        View view1 = inflater.inflate(R.layout.view_page_layout1, null);
        View view2 = inflater.inflate(R.layout.view_page_layout2,null);
        View view3 = inflater.inflate(R.layout.view_page_layout3, null);

        viewList = new ArrayList<View>();// 将要分页显示的View装入数组中
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);
        titleList = new ArrayList<>();
        titleList.add("111");
        titleList.add("222");
        titleList.add("333");

        pagerAdapter = new PagerAdapter() {

            @Override
            public int getCount() {
                return viewList.size();
            }

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                // TODO Auto-generated method stub
                return arg0 == arg1;
            }

            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                // TODO Auto-generated method stub
                container.removeView(viewList.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                // TODO Auto-generated method stub
                container.addView(viewList.get(position));


                return viewList.get(position);
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titleList.get(position);
            }
        };

        pageChangeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                viewModel.setProcess(positionOffset);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        };
    }

    public ViewPager.OnPageChangeListener getPageChangeListener() {
        return pageChangeListener;
    }

    public PagerAdapter getPagerAdapter() {
        return pagerAdapter;
    }

    public VPageModel getViewModel() {
        return viewModel;
    }
}
