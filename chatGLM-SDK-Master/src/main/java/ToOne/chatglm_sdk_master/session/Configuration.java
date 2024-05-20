package ToOne.chatglm_sdk_master.session;

import ToOne.chatglm_sdk_master.IOpenAiApi;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSources;

/**
 * @className: Configuration
 * @author: wenzhuo4657
 * @date: 2024/5/20 17:39
 * @Version: 1.0
 * @description:  配置字段列表
 */
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class Configuration {

    @Getter
    private String apiKey;//需要加密的字段
    @Getter
    private String apiSecret;// 在验证或签名实例中使用的秘密字节


    public static final String DEFAULT_USER_AGENT = "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)";
    public static final String APPLICATION_JSON = "application/json";
    public static final String JSON_CONTENT_TYPE = APPLICATION_JSON + "; charset=utf-8";


    @Getter
    @Setter
    private String apiHost = "https://open.bigmodel.cn/";//httpClient请求url


    @Setter
    @Getter
    private HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.HEADERS;//日志等级配置



    @Setter
    @Getter
    private long connectTimeout = 450;
    @Setter
    @Getter
    private long writeTimeout = 450;
    @Setter
    @Getter
    private long readTimeout = 450;



    @Getter
    @Setter
    private OkHttpClient okHttpClient;//没调用一次ToOne.chatglm_sdk_master.session.OpenAiSessionFactory.openSession()就会更新



    @Setter
    @Getter
    private IOpenAiApi openAiApi;


    public EventSource.Factory createRequestFactory() {
        return EventSources.createFactory(okHttpClient);
    }






}