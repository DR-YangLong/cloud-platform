package site.yanglong.cloud;

import site.yanglong.cloud.config.server.model.ApplicationConfig;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple CloudConfigServer.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    public static void main(String[] args) {
        QueryWrapper<ApplicationConfig> wrapper = new QueryWrapper<>();
        wrapper.setEntity(new ApplicationConfig());
        wrapper.eq("appName", "a").eq("profileName", "b").eq("label", "c");
        System.out.println(wrapper.getSqlSegment());
        String val="{\"label\":\"master\",\"name\":\"config-server\",\"profiles\":[\"default\"],\"propertySources\":[{\"name\":\"config-server-default\",\"source\":{\"eureka.client.service-url.defaultZ\":\"http://eureka-first:9001/eureka/\",\"server.port\":\"8005\"}},{\"name\":\"application-default\",\"source\":{\"common-str\":\"common-str\"}}]}";
    }
}
