package paiad.mqtt;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.mqttv5.client.*;
import org.eclipse.paho.mqttv5.client.persist.MemoryPersistence;
import org.eclipse.paho.mqttv5.common.MqttException;
import org.eclipse.paho.mqttv5.common.MqttMessage;
import org.eclipse.paho.mqttv5.common.packet.MqttProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MqttClientManager {
    private final MqttClient client;
    private final MqttConfig config;

    @Autowired
    public MqttClientManager(MqttConfig config) throws MqttException {
        this.config = config;
        this.client = new MqttClient(
                config.getBroker().getUrl(),
                config.getClient().getClientId(),
                new MemoryPersistence()
        );
        setupCallback();
    }

    private void setupCallback() {
        client.setCallback(new MqttCallback() {
            @Override
            public void disconnected(MqttDisconnectResponse disconnectResponse) {
                log.info("Disconnected: {}", disconnectResponse.getReasonString());
            }

            @Override
            public void mqttErrorOccurred(MqttException exception) {
                log.error("Error: {}", exception.getMessage(), exception);
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                log.info("Received message: {} on topic: {}", new String(message.getPayload()), topic);
            }

            @Override
            public void deliveryComplete(IMqttToken token) {
                log.info("Message delivery complete");
            }

            @Override
            public void connectComplete(boolean reconnect, String serverURI) {
                log.info("Connected to: {}, Reconnect: {}", serverURI, reconnect);
            }

            @Override
            public void authPacketArrived(int reasonCode, MqttProperties properties) {
                log.info("Auth packet arrived, reason code: {}", reasonCode);
            }
        });
    }

    @PostConstruct
    public void connect() throws MqttException {
        //当enable为false时，不开启mqtt服务
        if (!config.isEnabled()) {
            return;
        }

        MqttConnectionOptions connOpts = new MqttConnectionOptions();
        connOpts.setCleanStart(config.getClient().isCleanStart());
        connOpts.setSessionExpiryInterval(config.getClient().getSessionExpiryInterval());
        connOpts.setAutomaticReconnect(config.getClient().isAutomaticReconnect());

        // 设置认证信息（如果有）
        if (config.getCredentials() != null && config.getCredentials().getUsername() != null) {
            connOpts.setUserName(config.getCredentials().getUsername());
            connOpts.setPassword(config.getCredentials().getPassword().getBytes());
        }

        log.info("Connecting to: {}", config.getBroker().getUrl());
        client.connect(connOpts);

        // 订阅配置的 topic
        if (config.getTopics() != null && config.getTopics().getSubscribe() != null) {
            for (MqttConfig.Topic topic : config.getTopics().getSubscribe()) {
                client.subscribe(topic.getTopic(), topic.getQos());
                log.info("Subscribed to topic: {}", topic.getTopic());
            }
        }
    }

    @PreDestroy
    public void disconnect() throws MqttException {
        if (!config.isEnabled()) {
            return;
        }

        if (client.isConnected()) {
            client.disconnect();
            log.info("Disconnected");
        }
        client.close();
    }

    public MqttClient getClient() {
        return client;
    }
}
