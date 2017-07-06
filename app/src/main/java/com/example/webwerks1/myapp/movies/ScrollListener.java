package com.example.webwerks1.myapp.movies;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.AbsListView;

import com.example.webwerks1.myapp.OnLoadMoreListener;

/**
 * Created by webwerks1 on 16/5/16.
 */
public class ScrollListener extends RecyclerView.OnScrollListener{

    private int lastVisibleItem;
    OnLoadMoreListener onLoadMoreListener;
    RecyclerView.LayoutManager mLayoutManager;
    int page=1;
    int firstVisibleItem;
    public static boolean loading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;



    public ScrollListener(LinearLayoutManager linearLayoutManager,OnLoadMoreListener loadMoreListener){
        this.mLayoutManager=linearLayoutManager;
        this.onLoadMoreListener=loadMoreListener;

    }

    public ScrollListener(GridLayoutManager gridLayoutManager){
        this.mLayoutManager=gridLayoutManager;

    }



    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);


        totalItemCount = mLayoutManager.getItemCount();
        visibleItemCount = mLayoutManager.getChildCount();

        if(mLayoutManager instanceof LinearLayoutManager){
            lastVisibleItem = ((LinearLayoutManager)mLayoutManager).findLastVisibleItemPosition();
            firstVisibleItem=((LinearLayoutManager)mLayoutManager).findFirstVisibleItemPosition();
            pastVisiblesItems=((LinearLayoutManager)mLayoutManager).findFirstVisibleItemPosition();
        }
        else if(mLayoutManager instanceof GridLayoutManager) {

            lastVisibleItem=((GridLayoutManager)mLayoutManager).findLastVisibleItemPosition();
            pastVisiblesItems=((GridLayoutManager)mLayoutManager).findFirstVisibleItemPosition();
        }



        if(dy>0){

            if (loading)
            {
                if ( (visibleItemCount + pastVisiblesItems) >= totalItemCount)
                {
                    loading = false;
                    Log.v("...", "Last Item Wow !");
                    //Do pagination.. i.e. fetch new data

                    onLoadMoreListener.onLoadMore(true);
                }
            }
            loading=true;

        }


    }

}
