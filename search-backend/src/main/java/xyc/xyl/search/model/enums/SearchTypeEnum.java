package xyc.xyl.search.model.enums;


import org.apache.commons.lang3.ObjectUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * search type枚举
 *
 * @author lacy
 */
public enum SearchTypeEnum {
    /**
     * 自定义枚举类型
     */

    POST("帖子", "post"),
    USER("用户", "user"),
    PICTURE("图片", "picture");

    /**
     * 属性
     */
    private final String text;

    private final String value;

    /**
     * 构造函数
     * @param text
     * @param value
     */
    SearchTypeEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }

    /**
     * 获取值列表
     * 获取枚举类中所有枚举项的value值，并以列表形式返回。
     * @return
     */
    public static List<String> getValues() {
        return Arrays.stream(values()).map(item -> item.value).collect(Collectors.toList());
    }

    /**
     * 根据 value 获取枚举
     *
     * @param value
     * @return
     */
    public static SearchTypeEnum getEnumByValue(String value) {
        if (ObjectUtils.isEmpty(value)) {
            return null;
        }
        for (SearchTypeEnum anEnum : SearchTypeEnum.values()) {
            if (anEnum.value.equals(value)) {
                return anEnum;
            }
        }
        return null;
    }

    public String getValue() {
        return value;
    }

    public String getText() {
        return text;
    }
}
