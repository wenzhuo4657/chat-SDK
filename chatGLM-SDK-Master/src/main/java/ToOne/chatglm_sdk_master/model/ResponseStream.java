package ToOne.chatglm_sdk_master.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @className: ResponseStream
 * @author: wenzhuo4657
 * @date: 2024/5/21 14:17
 * @Version: 1.0
 * @description: 流式响应
 */
public class ResponseStream {
    private  String id;
    private  Long created;

    private List<Choice> choices;
    private Usage usage;
    private  List<web_Search> web_search;

    public String getId() {
        return id;
    }

    public ResponseStream setId(String id) {
        this.id = id;
        return this;
    }

    public Long getCreated() {
        return created;
    }

    public ResponseStream setCreated(Long created) {
        this.created = created;
        return this;
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public ResponseStream setChoices(List<Choice> choices) {
        this.choices = choices;
        return this;
    }

    public Usage getUsage() {
        return usage;
    }

    public ResponseStream setUsage(Usage usage) {
        this.usage = usage;
        return this;
    }

    public List<web_Search> getWeb_search() {
        return web_search;
    }

    public ResponseStream setWeb_search(List<web_Search> web_search) {
        this.web_search = web_search;
        return this;
    }

    @Data
    public static class Choice {
        private Long index;
        @JsonProperty("finish_reason")
        /**
         * 模型推理终止的原因。
         * */
        private String finishReason;
        private delta delta;
    }

    @Data
    public   static class  delta{
        private  String role;
        private  String content;
        private List<tool_calls> tool_calls;


        @Data
        private  static  class  tool_calls{
            private  String id;
            private  String type;
            private  function function_;


            @Data
            private static class function{
                private  String name;
                private Object arguments;
             }
        }


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