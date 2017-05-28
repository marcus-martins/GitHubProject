package com.project.marcus.githubproject.network;

import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by marcus on 28/05/17.
 */

public class RestServiceGenerator {

    private static final String BASE_URL = "https://api.github.com/";

    private static RxJava2CallAdapterFactory rxAdapter = RxJava2CallAdapterFactory
            .createWithScheduler(Schedulers.io());

    private static Retrofit.Builder builder =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(rxAdapter);

    private static Retrofit retrofit = builder.build();

    public static <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }

}
