package ToOne.chatglm_sdk_master.session;

import ToOne.chatglm_sdk_master.model.ChatCompletionResponse;
import ToOne.chatglm_sdk_master.model.ChatCompletionSSERequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import okhttp3.sse.EventSource;

import java.io.IOException;

/**
 * @className: OpenAiSession
 * @author: wenzhuo4657
 * @date: 2024/5/20 19:00
 * @Version: 1.0
 * @description: 定义会话服务api
 */
public interface OpenAiSession {
      /**
         *  des: 同步调用
         * */

    ChatCompletionResponse completionsSync(ChatCompletionSSERequest chatCompletionRequest) throws IOException;
}