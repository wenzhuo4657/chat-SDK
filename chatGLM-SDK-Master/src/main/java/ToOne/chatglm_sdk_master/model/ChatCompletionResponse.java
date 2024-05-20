package ToOne.chatglm_sdk_master.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ChatCompletionResponse {

    private String id;
    /**
     * 处理状态，PROCESSING（处理中），SUCCESS（成功），FAIL（失败）
     * 注：处理中状态需通过查询获取结果
     * */
    private Long task_status;
    private String model;

    /**
     * 用户在客户端请求时提交的任务编号或者平台生成的任务编号
     * */
    private String request_id;
    private List<Choice> choices;
    private Usage usage;


    @Data
    public static class Choice {
        private Long index;
        @JsonProperty("finish_reason")
        /**
         * 模型推理终止的原因。
        * */
        private String finishReason;
        private Message message;
    }

    @Data
    public static class Message {
        private String role;
        private String content;
        private  List tool_calls;//请求未支持相关，所以响应为null
    }

    @Data
    public static class Usage {
        private int completion_tokens;
        private int prompt_tokens;
        private int total_tokens;
    }

}
