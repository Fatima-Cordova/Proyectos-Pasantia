package com.example.notasdemo.retrofit;

import com.example.notasdemo.retrofit.model.response.CSVUploadResponse;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface RetrofitServices {

    @Multipart
    @POST("upload/")
    Call<CSVUploadResponse> uploadAttachment(@Part MultipartBody.Part filePart);
}
