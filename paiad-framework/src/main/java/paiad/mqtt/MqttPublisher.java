package paiad.mqtt;

import jakarta.annotation.PostConstruct;
import org.eclipse.paho.mqttv5.client.MqttClient;
import org.eclipse.paho.mqttv5.common.MqttException;
import org.eclipse.paho.mqttv5.common.MqttMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * mqtt 消息发布到特定的主题上
 * */
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
        for (MqttConfig.Topic topic : config.getTopics().getPublish()) {
            String messageContent = "Message to " + topic.getTopic() + " at " + System.currentTimeMillis();
            MqttMessage message = new MqttMessage(messageContent.getBytes());
            message.setQos(topic.getQos());
            client.publish(topic.getTopic(), message);
            System.out.println("发布消息:" + messageContent + " 到主题(topic): " + topic.getTopic());
        }
    }
}
