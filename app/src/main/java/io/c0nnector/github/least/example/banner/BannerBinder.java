package io.c0nnector.github.least.example.banner;

import android.support.annotation.LayoutRes;

import io.c0nnector.github.least.Binder;


public class BannerBinder extends Binder<BannerViewHolderHolder, Banner> {

    /**
     * Constructor
     *
     * @param bannerClass
     * @param cls
     * @param layoutId
     */
    public BannerBinder(Class<Banner> bannerClass, Class<BannerViewHolderHolder> cls, @LayoutRes int layoutId) {
        super(bannerClass, cls, layoutId);
    }

    @Override
    public void onBindViewHolder(BannerViewHolderHolder holder, Banner banner, int position) {
        holder.textView.setText(banner.bannerText);
    }
}
