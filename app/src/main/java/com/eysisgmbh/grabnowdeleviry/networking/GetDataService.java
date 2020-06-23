package com.eysisgmbh.grabnowdeleviry.networking;


import com.eysisgmbh.grabnowdeleviry.model.ChangeStatusModel;
import com.eysisgmbh.grabnowdeleviry.model.LoginModel;
import com.eysisgmbh.grabnowdeleviry.model.OrderDetailModel;
import com.eysisgmbh.grabnowdeleviry.model.OrderModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface GetDataService {

    //Login Register Password and Profile Apis

    @FormUrlEncoded
    @POST("deliveryBoyLogin")
    Call<LoginModel> login(@Field("mobile_country_code") String mobile_country_code, @Field("mobile_no") String mobile_no,
                           @Field("password") String password, @Field("device_token") String device_token, @Field("device_type") String device_type, @Field("language_code") String language_code);


    @FormUrlEncoded
    @POST("delivery-boy/onGoingOrders")
    Call<OrderModel> onGoingOrders(@Header("Authorization") String authorization, @Field("page") String page, @Field("language_code") String language_code);

    @FormUrlEncoded
    @POST("delivery-boy/deliveredOrders")
    Call<OrderModel> deliveredOrders(@Header("Authorization") String authorization, @Field("page") String page, @Field("language_code") String language_code);

    @FormUrlEncoded
    @POST("delivery-boy/orderDetail")
    Call<OrderDetailModel> orderDetail(@Header("Authorization") String authorization, @Field("id") String id, @Field("language_code") String language_code);

    @FormUrlEncoded
    @POST("delivery-boy/changeOrderStatus")
    Call<ChangeStatusModel> changeOrderStatus(@Header("Authorization") String authorization, @Field("id") String id, @Field("language_code") String language_code);


}
