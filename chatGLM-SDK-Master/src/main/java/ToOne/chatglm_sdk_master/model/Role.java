package ToOne.chatglm_sdk_master.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Role {
    /**
     * user 用户输入的内容，role位user
     */
    user("user"),
    /**
     *  System message
     */
    assistant("system"),
    ;
    private final String code;

}
