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
public class ChatCompletionSSERequest {

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
    private Integer max_tokens=1024;

    /**
     * 型输出最大 tokens，最大输出为8192，默认值为1024
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

    {
        stop = new ArrayList();
        stop.add("stop_word1");
        messages=new ArrayList<>();
  }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Message{
        private String role;
        private String content;
    }


      @Override
      public String toString() {
          return "ChatCompletionSSERequest{" +
                  "model='" + model + '\'' +
                  ", requestId='" + requestId + '\'' +
                  ", temperature=" + temperature +
                  ", topP=" + topP +
                  ", max_tokens=" + max_tokens +
                  ", stop=" + stop +
                  ", user_id='" + user_id + '\'' +
                  ", messages=" + messages.toString() +
                  '}';
      }
  }
