package com.udacity.popularmoviesstage2.network;

import android.support.v4.util.ArrayMap;
import android.util.Log;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.annotations.Expose;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by akhil on 18/06/16.
 */
public class RetrofitSingleton {

    public static final String MOVIE_DB_BASE_URL = "http://api.themoviedb.org/3/";

    private static ArrayMap<String, Retrofit> sRetrofitArrayMap = new ArrayMap<>();

    private static Retrofit setUpRetrofit (String baseUrl) {
        Gson gson = new GsonBuilder().
                registerTypeAdapter(Date.class, new DateDeserializer())
                .addSerializationExclusionStrategy(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes fieldAttributes) {
                        final Expose expose = fieldAttributes.getAnnotation(Expose.class);
                        return expose != null && !expose.serialize();
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> aClass) {
                        return false;
                    }
                })
                .addDeserializationExclusionStrategy(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes fieldAttributes) {
                        final Expose expose = fieldAttributes.getAnnotation(Expose.class);
                        return expose != null && !expose.deserialize();
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> aClass) {
                        return false;
                    }
                })
                .create();

        OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
        OkHttpClient client = builder.connectTimeout(60, TimeUnit.SECONDS).build();

        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

    }

    public static Retrofit getRetrofit(String url) {
        if (url == null) {
            url = MOVIE_DB_BASE_URL;
        }
        Retrofit retrofit = sRetrofitArrayMap.get(url);
        if(retrofit == null) {
            retrofit = setUpRetrofit(url);
            sRetrofitArrayMap.put(url, retrofit);
        }
        return retrofit;
    }


    public static final String[] DATE_FORMATS = new String[] {
            "yyyy'-'MM'-'dd"
    };


    public static class DateDeserializer implements JsonDeserializer<Date> {

        @Override
        public Date deserialize(JsonElement jsonElement, Type typeOF,
                                JsonDeserializationContext context) throws JsonParseException {
            final String jsonElementAsString = jsonElement.getAsString();
            if (jsonElementAsString != null && jsonElementAsString.trim().length() > 0) {
                for (String format : DATE_FORMATS) {
                    try {
                        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
                        return simpleDateFormat.parse(jsonElementAsString);
                    } catch (ParseException e) {

                    }
                }
            }
            Log.e("Unparsable date","---->"+ jsonElementAsString);
            return null;
        }
    }
}
