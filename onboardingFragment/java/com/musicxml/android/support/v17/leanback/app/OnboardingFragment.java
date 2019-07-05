package com.musicxml.android.support.v17.leanback.app;

import android.app.Fragment;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.musicxml.R;

/**
 * Created by Joshua on 01/01/2018.
 */

public class OnboardingFragment extends Fragment implements View.OnClickListener {
    LinearLayout contentView;
    LinearLayout pageIndicator;
    private TextView header,text;
    private int page = 0;


    protected int getPageCount() {
        return 1;
    }

    protected CharSequence getPageTitle(int pageIndex) {
        return "";
    }

    protected CharSequence getPageDescription(int pageIndex) {
        return "";
    }

    protected View onCreateBackgroundView(LayoutInflater inflater, ViewGroup container) {
        return null;
    }

    protected View onCreateContentView(LayoutInflater inflater, ViewGroup container) {
        return null;
    }
    protected View onCreateForegroundView(LayoutInflater inflater, ViewGroup container) {
        return null;
    }

    protected void onFinishFragment() {
    }

    protected void onPageChanged(int newPage, int previousPage) {

    }
    protected int getCurrentPageIndex(){
        return page;
    }


    // private fragment functions


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        page = 0;
        View v = inflater.inflate(R.layout.fragment_onboarding_root, null);
        contentView = v.findViewById(R.id.fragment_onboarding_content_view);
        contentView.setOnClickListener(this);
        View contentChild = onCreateContentView(inflater,contentView);
        if(contentView.getChildCount()==0) {
            contentView.addView(contentChild);
        }

        pageIndicator = v.findViewById(R.id.fragment_onboarding_page_indicator);
        pageIndicator.setOnClickListener(this);
        text = v.findViewById(R.id.fragment_onboarding_page_text);
        header = v.findViewById(R.id.fragment_onboarding_page_header);
        setText();
        refreshPageIndicator(inflater);

        return v;
    }
    private void refreshPageIndicator(LayoutInflater inflater){
        int howMany = getPageCount()-pageIndicator.getChildCount();
        for(int i = 0; i<howMany; i++){
            View v = inflater.inflate(R.layout.fragment_onboarding_page_selector,pageIndicator);
            if(i == page){
                v.setSelected(true);
            }
        }
    }

private void setText(){
        header.setText(getPageTitle(page));
        text.setText(getPageDescription(page));
}
    /**
     * turns the page
     * @param v
     */
    @Override
    public void onClick(View v) {
        //og.("page number", page+"<"+getPageCount());
        int previous = page;
        int pageCount = getPageCount();
        page++;
        if(page >= pageCount){
            onFinishFragment();
            return;
        }
        else {
            onPageChanged(page, previous);

            for (int i = 0; i < pageIndicator.getChildCount(); i++) {
                pageIndicator.getChildAt(i).setSelected(false);

            }
            pageIndicator.getChildAt(page).setSelected(true);
            setText();
        }


    }
}
