package paiad.mqtt;

import cn.hutool.core.date.DateUtil;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.mqttv5.client.MqttClient;
import org.eclipse.paho.mqttv5.common.MqttException;
import org.eclipse.paho.mqttv5.common.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * MQTT 消息发布到特定的主题上
 */
@Slf4j
@Component
public class MqttPublisher {
    private final MqttClient client;
    private final MqttConfig config;

    @Autowired
    public MqttPublisher(MqttClientManager clientManager, MqttConfig config) {
        this.client = clientManager.getClient();
        this.config = config;
    }

    @PostConstruct
    public void publish() throws MqttException {
        if (client == null) {
            log.info("MQTT 客户端未初始化，跳过发布操作。");
            return;
        }

        if (config.getTopics() != null && config.getTopics().getPublish() != null) {
            for (MqttConfig.Topic topic : config.getTopics().getPublish()) {
                String messageContent = DateUtil.now() + "\n消息发布-->主题[" + topic.getTopic() + "]:\nHello, World!";
                MqttMessage message = new MqttMessage(messageContent.getBytes(StandardCharsets.UTF_8));
                message.setQos(topic.getQos());
                client.publish(topic.getTopic(), message);
                log.info("发布消息：{} 到主题：{}", messageContent, topic.getTopic());
            }
        }
    }

    public void publishMessage(String topic, String messageContent, int qos) throws MqttException {
        if (client == null) {
            log.info("MQTT 客户端未初始化，无法发布消息到主题：{}", topic);
            return;
        }

        if (topic == null || topic.trim().isEmpty()) {
            log.warn("主题不能为空，跳过发布操作。");
            return;
        }

        if (messageContent == null) {
            log.warn("消息内容不能为空，跳过主题 {} 的发布操作。", topic);
            return;
        }

        if (qos < 0 || qos > 2) {
            log.warn("无效的 QoS 等级：{}，必须在 0 到 2 之间。默认使用 1。", qos);
            qos = 1;
        }

        MqttMessage message = new MqttMessage(messageContent.getBytes(StandardCharsets.UTF_8));
        message.setQos(qos);
        client.publish(topic, message);
        log.info("发布自定义消息：{} 到主题：{}，QoS：{}", messageContent, topic, qos);
    }
}
