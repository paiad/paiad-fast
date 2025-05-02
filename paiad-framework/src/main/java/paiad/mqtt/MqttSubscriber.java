package paiad.mqtt;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.mqttv5.client.MqttClient;
import org.eclipse.paho.mqttv5.common.MqttException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 订阅 EMQX 服务器的特定主题，接收消息
 */
@Slf4j
@Component
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
        if (client == null) {
            log.info("MQTT 客户端未初始化，跳过订阅操作。");
            return;
        }

        if (config.getTopics() != null && config.getTopics().getSubscribe() != null) {
            for (MqttConfig.Topic topic : config.getTopics().getSubscribe()) {
                client.subscribe(topic.getTopic(), topic.getQos());
                log.info("订阅主题：{}", topic.getTopic());
            }
        }
    }

    public void subscribe(String topic, int qos){
        if (client == null) {
            log.info("MQTT 客户端未初始化，无法订阅主题：{}", topic);
            return;
        }

        if (topic == null || topic.trim().isEmpty()) {
            log.warn("主题不能为空，跳过订阅操作。");
            return;
        }

        if (qos < 0 || qos > 2) {
            log.warn("无效的 QoS 等级：{}，必须在 0 到 2 之间。默认使用 1。", qos);
            qos = 1;
        }

        try {
            client.subscribe(topic, qos);
            log.info("动态订阅主题：{}，QoS：{}", topic, qos);
        }catch (MqttException e){
            log.error("MQTT 主题订阅失败，主题：{}，错误：{}", topic, e.getMessage());
        }
    }
}
