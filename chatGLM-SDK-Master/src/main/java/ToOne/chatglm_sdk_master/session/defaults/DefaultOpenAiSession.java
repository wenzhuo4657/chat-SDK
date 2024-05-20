package ToOne.chatglm_sdk_master.session.defaults;

import ToOne.chatglm_sdk_master.IOpenAiApi;
import ToOne.chatglm_sdk_master.model.ChatCompletionSSERequest;
import ToOne.chatglm_sdk_master.session.Configuration;
import ToOne.chatglm_sdk_master.session.OpenAiSession;
import com.fasterxml.jackson.core.JsonProcessingException;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;

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
    public EventSource completions(ChatCompletionSSERequest chatCompletionRequest, EventSourceListener eventSourceListener) throws JsonProcessingException {
        Request request = new Request.Builder()
                .url(configuration.getApiHost().concat(IOpenAiApi.v4_completions))
                .post(RequestBody.create(MediaType.parse(Configuration.APPLICATION_JSON), chatCompletionRequest.toString()))
                .build();
        return factory.newEventSource(request,eventSourceListener);
    }
}