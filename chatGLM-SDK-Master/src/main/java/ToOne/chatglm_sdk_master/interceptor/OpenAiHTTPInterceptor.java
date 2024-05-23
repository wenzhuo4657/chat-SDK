package ToOne.chatglm_sdk_master.interceptor;


import ToOne.chatglm_sdk_master.session.Configuration;
import ToOne.chatglm_sdk_master.utils.BearerTokenUtils;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * @className: OpenAiHTTPInterceptor
 * @author: wenzhuo4657
 * @date: 2024/5/20 17:34
 * @Version: 1.0
 * @description: HTTP用户鉴权,使用jwt,在请求发送前进行
 */
public class OpenAiHTTPInterceptor implements Interceptor {

    private final Configuration configuration;

    public OpenAiHTTPInterceptor(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request orgin=chain.request();
        Request request = orgin.newBuilder()
                .url(orgin.url())
                .header("Authorization", "Bearer " + BearerTokenUtils.getToken(configuration.getApiKey(), configuration.getApiSecret()))
                .header("Content-Type", Configuration.JSON_CONTENT_TYPE)
                .header("User-Agent", Configuration.DEFAULT_USER_AGENT)
                .method(orgin.method(), orgin.body())
                .build();
        return chain.proceed(request);

    }
}