package com.example.tapptic.viewModel;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

import com.example.tapptic.model.Api;
import com.example.tapptic.model.NumberLight;
import com.example.tapptic.model.NumberLightDetails;
import com.example.tapptic.view.FragmentListDirections;
import com.example.tapptic.view.MainActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragmentListViewModel extends ViewModel {

    private MutableLiveData<List<NumberLight>> numbersList;
    private MutableLiveData<NumberLightDetails> numberLightDetails;

    private MutableLiveData<String> text;

    public LiveData<String> getText() {
        if (text == null) {
            text = new MutableLiveData<String>();
        }
        return text;
    }

    public LiveData<List<NumberLight>> getNumbersList() {

        if (numbersList == null) {
            numbersList = new MutableLiveData<List<NumberLight>>();
        }
        loadNumbers();


        return numbersList;
    }


    public LiveData<NumberLightDetails> getDetails() {
        if (numberLightDetails == null) {
            numberLightDetails = new MutableLiveData<NumberLightDetails>();
        }

        return numberLightDetails;
    }

    public void loadNumbers() {

        Api api = getRetrofitInstance().create(Api.class);
        Call<List<NumberLight>> call = api.getNumbers();

        call.enqueue(new Callback<List<NumberLight>>() {
            @Override
            public void onResponse(Call<List<NumberLight>> call, Response<List<NumberLight>> response) {
                numbersList.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<NumberLight>> call, Throwable t) {
                text.setValue("No internet");
            }
        });
    }

    private void loadNumberDetails(String name) {

        Api api = getRetrofitInstance().create(Api.class);
        Call<NumberLightDetails> call = api.getNumberDetails(name);

        call.enqueue(new Callback<NumberLightDetails>() {
            @Override
            public void onResponse(Call<NumberLightDetails> call, Response<NumberLightDetails> response) {
                numberLightDetails.setValue(response.body());

            }

            @Override
            public void onFailure(Call<NumberLightDetails> call, Throwable t) {
                text.setValue("no internet");
            }
        });
    }

    public void actionOnClickItem(View view, String name, boolean isTabletLandMode) {
        loadNumberDetails((name != null) ? name : "1");
        if (!isTabletLandMode) {
            Navigation.findNavController(view).navigate(FragmentListDirections.actionFragmentListToFragmentDetails(name));
        }
    }

    private Retrofit getRetrofitInstance() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }

}
