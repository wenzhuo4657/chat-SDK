package ToOne.chatglm_sdk_master.session.defaults;

import ToOne.chatglm_sdk_master.interceptor.OpenAiHTTPInterceptor;
import ToOne.chatglm_sdk_master.session.Configuration;
import ToOne.chatglm_sdk_master.session.OpenAiSession;
import ToOne.chatglm_sdk_master.session.OpenAiSessionFactory;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

import java.util.concurrent.TimeUnit;

/**
 * @className: DefaultOpenAiSessionFactory
 * @author: wenzhuo4657
 * @date: 2024/5/20 19:23
 * @Version: 1.0
 * @description: 默认工厂
 */
public class DefaultOpenAiSessionFactory implements OpenAiSessionFactory {
    private final Configuration configuration;

    public DefaultOpenAiSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }
    @Override
    public OpenAiSession openSession() {
//        1,logger配置
        HttpLoggingInterceptor httpLoggingInterceptor=new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(configuration.getLevel());

//        2，开启HttpClient
       OkHttpClient okHttpClient= new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .addInterceptor(new OpenAiHTTPInterceptor(configuration))
                .connectTimeout(configuration.getConnectTimeout(),TimeUnit.SECONDS)
               .writeTimeout(configuration.getWriteTimeout(),TimeUnit.SECONDS)
               .readTimeout(configuration.getReadTimeout(),TimeUnit.SECONDS)
               .build();
       configuration.setOkHttpClient(okHttpClient);


        return new DefaultOpenAiSession(configuration);
    }
}