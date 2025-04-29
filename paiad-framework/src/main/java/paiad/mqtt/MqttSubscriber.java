package paiad.mqtt;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.mqttv5.client.MqttClient;
import org.eclipse.paho.mqttv5.common.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 订阅EMQX服务器的特定主题，接受消息
 * */
@Component
@Slf4j
public class MqttSubscriber {
    private final MqttClient client;
    private final MqttConfig config;

    @Autowired
    public MqttSubscriber(MqttClientManager clientManager, MqttConfig config) {
        this.client = clientManager.getClient();
        this.config = config;
    }

    @PostConstruct
    public void subscribe() throws MqttException {
        if (!config.isEnabled()) {
            return;
        }

        if (config.getTopics() != null && config.getTopics().getSubscribe() != null) {
            for (MqttConfig.Topic topic : config.getTopics().getSubscribe()) {
                client.subscribe(topic.getTopic(), topic.getQos());
                log.info("订阅的主题(topic): {}", topic.getTopic());
            }
        }
    }

}
