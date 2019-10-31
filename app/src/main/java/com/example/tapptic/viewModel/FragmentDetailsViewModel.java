package com.example.tapptic.viewModel;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tapptic.model.Api;
import com.example.tapptic.model.NumberLightDetails;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragmentDetailsViewModel extends ViewModel {

    private MutableLiveData<NumberLightDetails> numberDetails;
    private MutableLiveData<String> text;

    public LiveData<String> getText() {
        if (text == null) {
            text = new MutableLiveData<String>();
        }
        return text;
    }

    public LiveData<NumberLightDetails> getNumberDetails(String name) {

        if (numberDetails == null) {
            numberDetails = new MutableLiveData<NumberLightDetails>();
            loadNumberDetails((name != null) ? name : "1");
        }

        return numberDetails;
    }

    public void loadNumberDetails(String name) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);
        Call<NumberLightDetails> call = api.getNumberDetails(name);

        call.enqueue(new Callback<NumberLightDetails>() {
            @Override
            public void onResponse(Call<NumberLightDetails> call, Response<NumberLightDetails> response) {

                numberDetails.setValue(response.body());
            }

            @Override
            public void onFailure(Call<NumberLightDetails> call, Throwable t) {
                text.setValue("No internet");
            }
        });
    }
}
