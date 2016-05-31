package io.c0nnector.github.least.example.banner;

import io.c0nnector.github.least.Binder;
import io.c0nnector.github.least.example.R;


public class BannerBinder extends Binder<BannerViewHolderHolder, Banner> {


    @Override
    public void onBindViewHolder(BannerViewHolderHolder holder, Banner banner, int position) {
        holder.textView.setText(banner.bannerText);
    }

    @Override
    public int getLayoutId() {
        return R.layout.layout_banner;
    }

    @Override
    public Class<BannerViewHolderHolder> getViewHolderClass() {
        return BannerViewHolderHolder.class;
    }

    @Override
    public Class<Banner> getItemClass() {
        return Banner.class;
    }
}
