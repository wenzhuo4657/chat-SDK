package ToOne.chatglm_sdk_master.session;

import ToOne.chatglm_sdk_master.model.ChatCompletionSSERequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;

/**
 * @className: OpenAiSession
 * @author: wenzhuo4657
 * @date: 2024/5/20 19:00
 * @Version: 1.0
 * @description: 定义会话服务api
 */
public interface OpenAiSession {

    EventSource completions(ChatCompletionSSERequest chatCompletionRequest, EventSourceListener eventSourceListener) throws JsonProcessingException;
}