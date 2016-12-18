package com.testerhome.nativeandroid.injection;

import com.testerhome.nativeandroid.data.THRepository;
import com.testerhome.nativeandroid.data.THRepositoryImpl;
import com.testerhome.nativeandroid.data.remote.MockTHRestServiceImpl;
import com.testerhome.nativeandroid.data.remote.THRestService;

/**
 * Created by Bin Li on 2016/12/18.
 */

public class Injection {

    private static THRestService thRestService;

    public static THRepository provideThRepo(){
        return new THRepositoryImpl(provideThRestService());
    }

    static THRestService provideThRestService(){
        if (thRestService == null){
            thRestService = new MockTHRestServiceImpl();
        }

        return thRestService;
    }
}
