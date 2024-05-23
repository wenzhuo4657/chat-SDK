package ToOne.chatglm_sdk_master.session.defaults;

import ToOne.chatglm_sdk_master.IOpenAiApi;

import ToOne.chatglm_sdk_master.model.RequestSSE;
import ToOne.chatglm_sdk_master.model.ResponseSync;
import ToOne.chatglm_sdk_master.session.Configuration;
import ToOne.chatglm_sdk_master.session.OpenAiSession;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import okhttp3.*;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;

import java.io.IOException;

/**
 * @className: DefaultOpenAiSession
 * @author: wenzhuo4657
 * @date: 2024/5/20 19:04
 * @Version: 1.0
 * @description: OpenAiSession默认实现
 */
public class DefaultOpenAiSession implements OpenAiSession {
    private final Configuration configuration;

    private final EventSource.Factory factory;

    private IOpenAiApi openAiApi;




    public DefaultOpenAiSession(Configuration configuration) {
        this.configuration = configuration;
        this.openAiApi = configuration.getOpenAiApi();
        this.factory = configuration.createRequestFactory();
    }

    @Override
    public ResponseSync completionsSync(RequestSSE chatCompletionRequest) throws IOException {
        Request request = new Request.Builder()
                .url(configuration.getApiHost().concat(IOpenAiApi.v4_completions))
                .post(RequestBody.create(MediaType.parse(Configuration.JSON_CONTENT_TYPE), chatCompletionRequest.toString()))
                .build();

        OkHttpClient okHttpClient = configuration.getOkHttpClient();
        Response response = okHttpClient.newCall(request).execute();
        if(!response.isSuccessful()){
            throw new RuntimeException("Request failed");
        }
        return JSON.parseObject(response.body().string(), ResponseSync.class);

    }


    @Override
    public EventSource completionsStream(RequestSSE chatCompletionRequest, EventSourceListener eventSourceListener) throws IOException {


        // 构建请求信息
        Request request = new Request.Builder()
                .url(configuration.getApiHost().concat(IOpenAiApi.v4_completions))
                .post(RequestBody.create(MediaType.parse(Configuration.JSON_CONTENT_TYPE), chatCompletionRequest.toString()))
                .build();

        // 返回事件结果
        return factory.newEventSource(request,eventSourceListener );
    }
}