package ToOne.chatglm_sdk_master.session;

import ToOne.chatglm_sdk_master.model.RequestSSE;
import ToOne.chatglm_sdk_master.model.ResponseSync;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;

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

    ResponseSync completionsSync(RequestSSE chatCompletionRequest) throws IOException;

      /**
         *  des: 流式调用
         * */
      EventSource completionsStream(RequestSSE chatCompletionRequest, EventSourceListener eventSourceListener) throws IOException;




}