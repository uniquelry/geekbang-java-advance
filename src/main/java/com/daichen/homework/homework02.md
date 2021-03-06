# 第一题

> 根据上述自己对于 1 和 2 的演示，写一段对于不同 GC 和堆内存的总结

```text
1. 对于设置堆内存大小过小，young GC 和 full GC 触发次数频繁，系统大量的时间都在做 GC。
2. 内存越大 G1 GC 的回收效果越好。内存大的情况下，G1 GC 的垃圾回收由于串行 GC、并行 GC 以及 CMS GC
```

# 第二题

> 写一段代码，使用 HttpClient 或 OkHttp 访问 http://localhost:8801

``` java
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
```