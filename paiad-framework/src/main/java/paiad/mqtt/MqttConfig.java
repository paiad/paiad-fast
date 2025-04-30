package paiad.mqtt;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "mqtt")
public class MqttConfig {
    private boolean enabled;
    private Broker broker;
    private Client client;
    private Credentials credentials;
    private Topics topics;

    @Data
    public static class Broker {
        private String host;
        private int port;
        private String protocol;

        public String getUrl() {
            return protocol + "://" + host + ":" + port;
        }
    }

    @Data
    public static class Client {
        private String clientId;
        private boolean cleanStart;
        private long sessionExpiryInterval;
        private boolean automaticReconnect;
    }

    @Data
    public static class Credentials {
        private String username;
        private String password;
    }

    @Data
    public static class Topics {
        private List<Topic> publish;
        private List<Topic> subscribe;
    }

    @Data
    public static class Topic {
        private String topic;
        private int qos;
    }
}
