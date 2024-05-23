package ToOne.chatglm_sdk_master.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.*;


/**
     *  des:  sse调用请求
     * */

@Data
@Slf4j
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestSSE {

    /**
     * 模型
     */
    private String model = Model.GLM_4.getCode();

    /**
     * 请求ID
     */
    @JsonProperty("request_id")
    private String requestId = String.format("xfg-%d", System.currentTimeMillis());
    /**
     * 控制温度【随机性】
     */
    private float temperature = 0.9f;
    /**
     * 多样性控制；
     */
    @JsonProperty("top_p")
    private float topP = 0.7f;

    /**
     * 型输出最大 tokens，最大输出为8192，默认值为1024
    * */
    private Integer max_tokens=8192;


      /**
         *  des: 模型在遇到stop所制定的字符时将停止生成，目前仅支持单个停止词，格式为["stop_word1"]
         * */
    private List<String> stop;


/**
 * 客户端用户唯一id
 * */
    private String user_id;

/**
 *  调用语言模型时，将当前对话信息列表作为提示输入给模型,
 *  可能的消息类型包括 System message、User message、Assistant message 和 Tool message。
 *   暂时仅支持System message、User message，根据role字段进行区分
 */
    private  List<Message> messages;
//    fasle为一次返回，true为流式调用
    private boolean stream=false;

    {
        stop = new ArrayList();
        stop.add("stop_word1");
        messages=new ArrayList<>();
  }

    public void setStream(boolean stream) {
        this.stream = stream;
    }

    public boolean isStream() {
        return stream;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Message{
        private String role;
        private String content;

        public static MessageBuilder builder(){
            return new MessageBuilder();
        }

        public  static class MessageBuilder {
            private String role;
            private String content;

            public MessageBuilder() {
            }

            public MessageBuilder role(String role) {
                this.role = role;
                return this;
            }

            public MessageBuilder content(String content) {
                this.content = content;
                return this;
            }

            public Message build() {
                return new Message(this.role, this.content);
            }


        }




    }


      @Override
      public String toString() {
        Map<String,Object> params = new HashMap<>();
        params.put("request_id",requestId);
        params.put("model",model);
        params.put("temperature",temperature);
        params.put("topP", topP);
        params.put("max_tokens",max_tokens);
        params.put("stop",stop);
        params.put("user_id",user_id);
        params.put("messages",messages);
        params.put("stream",stream);
          try {
              return new ObjectMapper().writeValueAsString(params);
          } catch (JsonProcessingException e) {
              log.info("json转换出错{}", e);
              throw new RuntimeException(e);
          }


      }
  }
