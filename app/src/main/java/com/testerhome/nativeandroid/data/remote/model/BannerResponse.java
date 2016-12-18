package com.testerhome.nativeandroid.data.remote.model;

import java.util.List;

/**
 * Created by Bin Li on 2016/12/18.
 */
public class BannerResponse {

    private List<AdsEntity> ads;

    public void setAds(List<AdsEntity> ads) {
        this.ads = ads;
    }

    public List<AdsEntity> getAds() {
        return ads;
    }

}
