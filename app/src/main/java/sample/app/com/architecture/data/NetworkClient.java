package sample.app.com.architecture.data;

import java.io.IOException;

import okhttp3.Credentials;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by priyadhingra on 3/23/2018.
 */

public class NetworkClient {
    private static final String BASE_URL = "https://developers.zomato.com/api/v2.1/";
    private static NetworkClient instance;
    private Retrofit retrofit;
    private Retrofit retrofitAuth;

    public static NetworkClient getInstance() {
        if (instance == null) {
            instance = new NetworkClient();
        }

        return instance;
    }

    public <T> T createAuth(final Class<T> service) {
        create(service);
        if (retrofitAuth == null) {
            retrofitAuth = createRetrofit(true);
        }
        return retrofitAuth.create(service);
    }


    private Retrofit createRetrofit(final boolean auth) {

        final OkHttpClient client = createOkHttpClient();

        final Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client);
        return builder.build();
    }

    private OkHttpClient createOkHttpClient() {
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.retryOnConnectionFailure(true);
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();

                Request.Builder builder = originalRequest.newBuilder().header("Authorization",
                        Credentials.basic("user-key", "c750173e8cf7e5fdc2c331cf897ee8c3"));

                Request newRequest = builder.build();
                return chain.proceed(newRequest);
            }
        })
                .addInterceptor(interceptor).build();

        return okHttpClient;
    }

    public <T> T create(final Class<T> service) {
        if (retrofit == null) {
            retrofit = createRetrofit(false);
        }

        return retrofit.create(service);
    }
}
