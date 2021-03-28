package com.daichen.homework.week02;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author daichen
 * @version v1.0
 * @create 2021/3/28 10:45 下午
 * @description
 */
public class OkHttpApi {

    private final static String VISIT_URL = "http://localhost:8801/";

    public static void main(String[] args) {
        // 1. 第一步创建 OkHttpClient 对象
        final OkHttpClient okHttpClient = new OkHttpClient();

        // 2. 拼接参数

        // 3. 第二步创建 request
        final Request request = new Request.Builder()
                .url(VISIT_URL).get().build();

        // 4. 新建一个 Call 对象
        final Call call = okHttpClient.newCall(request);

        // 5. 请求网络
        ExecutorService executorService = Executors.newFixedThreadPool(
                Runtime.getRuntime().availableProcessors() + 2);
        executorService.execute(() -> {
            try {
                Response response = call.execute();
                if (response.isSuccessful()) {
                    ResponseBody responseBody = response.body();
                    if (responseBody != null) {
                        System.out.println(responseBody.string());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
