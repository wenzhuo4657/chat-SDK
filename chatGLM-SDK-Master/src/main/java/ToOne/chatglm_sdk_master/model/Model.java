package ToOne.chatglm_sdk_master.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Model {

    GLM_4("glm-4","通用大模型,推荐使用 SSE 或异步调用方式请求接口"),
    glm_3_turbo("glm-3-turbo","通用大模型，推荐使用 SSE 或异步调用方式请求接口"),
    glm_4v("glm_4v","通用大模型，推荐使用 SSE 或异步调用方式请求接口")
    ;
    private final String code;
    private final String info;
}
