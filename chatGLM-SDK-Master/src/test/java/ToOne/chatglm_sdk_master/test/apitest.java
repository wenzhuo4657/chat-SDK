package ToOne.chatglm_sdk_master.test;

import ToOne.chatglm_sdk_master.model.ChatCompletionResponse;
import ToOne.chatglm_sdk_master.model.ChatCompletionSSERequest;
import ToOne.chatglm_sdk_master.model.EventType;
import ToOne.chatglm_sdk_master.model.Role;
import ToOne.chatglm_sdk_master.session.Configuration;
import ToOne.chatglm_sdk_master.session.OpenAiSession;
import ToOne.chatglm_sdk_master.session.OpenAiSessionFactory;
import ToOne.chatglm_sdk_master.session.defaults.DefaultOpenAiSessionFactory;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;
import org.jetbrains.annotations.Nullable;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

/**
 * @className: apitest
 * @author: wenzhuo4657
 * @date: 2024/5/21 10:48
 * @Version: 1.0
 * @description:
 */
@Slf4j
public class apitest {
    private OpenAiSession session;

    @Before
    public void test_OpenaAiSessionFactory(){
        Configuration configuration=new Configuration();
        configuration.setApiHost("https://open.bigmodel.cn/");
        configuration.setApiSecretKey("dd48fa165df16a61c7add47c69dcd099.y88gEvsbZcVi65ja");
        configuration.setLevel(HttpLoggingInterceptor.Level.BODY);

        OpenAiSessionFactory factory=new DefaultOpenAiSessionFactory(configuration);
        this.session=factory.openSession();
    }

      /**
         *  des: sse调用
         * */

      @SneakyThrows
      @Test
      public void test_SSE_sync() throws JsonProcessingException, InterruptedException {
          CountDownLatch countDownLatch = new CountDownLatch(1);
          ChatCompletionSSERequest request=new ChatCompletionSSERequest();
          request.setStream(false);
          request.setMessages(

                  /**
                   * 1，使用匿名内部类的形式创建ArrayList的子类，
                   * 2，代码块初始化实例， add方法被继承到此类中所以无需添加前缀，、
                   *
                   * */
                  new ArrayList<ChatCompletionSSERequest.Message>(){

                        /**
                           *  des: 当一个类实现了 Serializable 接口以支持序列化和反序列化操作时，JVM会使用这个serialVersionUID来确保类的兼容
                         *
                         *  版本控制：当类的结构发生改变（比如增加、删除或修改了成员变量）后重新序列化对象，如果没有匹配的serialVersionUID，反序列化时可能会抛出InvalidClassException异常。通过手动设置此ID，可以在一定程度上控制类的向前和向后兼容性。
                         *
                         * 性能优化：在反序列化过程中，JVM会比较序列化时保存的serialVersionUID与当前类的serialVersionUID是否一致，如果一致则可以直接反序列化，这比反射检查类结构要快得多。
                         *
                         * 安全性增强：自动生成的serialVersionUID包含了类的结构信息，可能暴露一些不安全的细节。手动指定可以减少这种风险，尽管这不是主要的安全机制。
                           * */
                      private static final long serialVersionUID = -7988151926241837899L;
                      {
                          add(ChatCompletionSSERequest.Message.builder()
                                  .role(Role.user.getCode())
                                  .content("写个java冒泡排序")
                                  .build());
                      }
                  }
          );
          // 请求
          ChatCompletionResponse response = session.completionsSync(request);

          log.info("测试结果：{}", JSON.toJSONString(response));
      }


}