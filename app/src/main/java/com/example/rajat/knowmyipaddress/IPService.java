package com.example.rajat.knowmyipaddress;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IPService {
    @GET("/?format=json")
    Call<IPaddress> getIPaddress();
}
