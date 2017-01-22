package com.zx.rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.zx.rxjava.bean.Vip;
import com.zx.rxjava.service.RetrofitService;

import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class MainActivity extends AppCompatActivity {

    @Bind(R.id.btn_vip)
    Button btnVip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    private void initRequest() {
        Retrofit retrofit = AppClient.retrofit(getApplicationContext());
        RetrofitService retrofitService = retrofit.create(RetrofitService.class);
        HashMap<String, String> map = new HashMap<>();
        map.put("function", "GetCustomerList");
        map.put("keyWord", "");
        map.put("orgID", "8269d278-271b-45f6-9cec-637116b42661");
        map.put("orderField", "");
        map.put("orderType", "0");
        map.put("pageSize", "20");
        map.put("pageNum", "1");

        Call<GetVipListRespons> call = retrofitService.getVipList(map);
        call.enqueue(new Callback<GetVipListRespons>() {
            @Override
            public void onResponse(Call<GetVipListRespons> call, Response<GetVipListRespons> response) {
                List<Vip> rows = response.body().getData().getRows();
                for(Vip vip : rows) {
                    Log.e("tag", vip.getCustomerName());
                }

            }

            @Override
            public void onFailure(Call<GetVipListRespons> call, Throwable t) {

            }
        });

    }

    @OnClick(R.id.btn_vip)
    public void onClick() {
        initRequest();
    }
}
