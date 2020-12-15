package xyz.guqing.travelpath.model.constant;

/**
 * 系统常量类
 *
 * @author guqing
 */
public interface TravelPathConstant {

    /**
     * 排序规则：降序
     */
    String ORDER_DESC = "descending";
    /**
     * 排序规则：升序
     */
    String ORDER_ASC = "ascending";

    /**
     * 验证码有效期
     */
    Long CAPTCHA_EXPIRE = 300L;

    /**
     * 异步线程池名称
     */
    String ASYNC_POOL = "creekAsyncThreadPool";

    /**
     * Java默认临时目录
     */
    String JAVA_TEMP_DIR = "java.io.tmpdir";

    String USER_HOME = System.getProperties().getProperty("user.home");

    /**
     * 注册用户角色ID
     */
    Long REGISTER_ROLE_ID = 1L;

    String DEFAULT_PASSWORD = "123456";
}
