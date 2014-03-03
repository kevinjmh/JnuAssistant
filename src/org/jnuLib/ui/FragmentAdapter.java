package org.jnuLib.ui;

import java.util.ArrayList;
import java.util.List;

import org.jnuLib.fragment.CampusIPFragment;
import org.jnuLib.fragment.ContentFragment;
import org.jnuLib.fragment.JWCNewsFragment;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;


/**
 * 
 * @author <a href="mailto:kris@krislq.com">Kris.lee</a>
 * @since Mar 15, 2013
 * @version 1.0.0
 */
public class FragmentAdapter extends PagerAdapter {

    private final FragmentManager mFragmentManager;
    private FragmentTransaction mTransaction = null;
    
    private List<Fragment> mFragmentList = new ArrayList<Fragment>(4);
    
    public FragmentAdapter(FragmentManager fm) {
        mFragmentManager = fm;
        
      /*  mFragmentList.add(new ContentFragment());
        mFragmentList.add(new JWCNewsFragment());
        mFragmentList.add(new CampusIPFragment());*/

    }
    public void addFragment(Fragment fm){
    	 mFragmentList.add(fm);
        
    	
    }
  
    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return ((Fragment) object).getView() == view;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if (mTransaction == null) {
            mTransaction = mFragmentManager.beginTransaction();
        }
        //String name ="";
        Fragment fragment = mFragmentManager.findFragmentById(container.getId());
        if (fragment != null) {
            mTransaction.attach(fragment);
        } else {
            fragment = getItem(position);
            mTransaction.add(container.getId(), fragment
                   );
        }
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        if (mTransaction == null) {
            mTransaction = mFragmentManager.beginTransaction();
        }
        mTransaction.detach((Fragment) object);
    }
    @Override
    public void finishUpdate(ViewGroup container) {
        if (mTransaction != null) {
            mTransaction.commitAllowingStateLoss();
            mTransaction = null;
            mFragmentManager.executePendingTransactions();
        }
    }
    public Fragment getItem(int position){
       return  mFragmentList.get(position);
    }
    public long getItemId(int position) {
        return position;
    }  
}
