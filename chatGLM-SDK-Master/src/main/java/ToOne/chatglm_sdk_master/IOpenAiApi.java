package ToOne.chatglm_sdk_master;


import ToOne.chatglm_sdk_master.model.ChatCompletionResponse;
import ToOne.chatglm_sdk_master.model.ChatCompletionSSERequest;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * @className: IOpenAiApi
 * @author: wenzhuo4657
 * @date: 2024/5/18 19:35
 * @Version: 1.0
 * @description: 定义第三方请求格式，chatglm
 */
public interface IOpenAiApi {
    String v4_completions = "api/paas/v4/chat/completions";

    @POST(v4_completions)
    Single<ChatCompletionResponse> completions(@Body ChatCompletionSSERequest chatCompletionRequest);


}