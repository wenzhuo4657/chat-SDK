package ToOne.chatglm_sdk_master.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
  /**
     *  des: 同步响应
     * */
@Data
public class ResponseSync {
    private  String id;
    private  Long created;
    private String model;
    private  List<Choice> choices;
    private Usage usage;
    private  List<web_Search> web_search;

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

    @Data
    public  static  class web_Search{
        private  String icon;
        private  String title;
        private  String link;
        private  String media;
        private  String content;

    }





}
