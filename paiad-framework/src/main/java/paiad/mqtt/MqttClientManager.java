package paiad.mqtt;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.mqttv5.client.*;
import org.eclipse.paho.mqttv5.client.persist.MemoryPersistence;
import org.eclipse.paho.mqttv5.common.MqttException;
import org.eclipse.paho.mqttv5.common.MqttMessage;
import org.eclipse.paho.mqttv5.common.packet.MqttProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MqttClientManager {
    @Getter
    private MqttClient client;
    private final MqttConfig config;

    @Autowired
    public MqttClientManager(MqttConfig config) {
        this.config = config;
    }

    private void setupCallback() {
        client.setCallback(new MqttCallback() {
            @Override
            public void disconnected(MqttDisconnectResponse disconnectResponse) {
                log.info("断开连接：{}", disconnectResponse.getReasonString());
            }

            @Override
            public void mqttErrorOccurred(MqttException exception) {
                log.error("发生错误：{}", exception.getMessage(), exception);
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                log.info("接收到消息：{}，主题：{}", new String(message.getPayload()), topic);
            }

            @Override
            public void deliveryComplete(IMqttToken token) {
                log.info("消息投递完成");
            }

            @Override
            public void connectComplete(boolean reconnect, String serverURI) {
                log.info("连接完成：{}，是否重连：{}", serverURI, reconnect);
            }

            @Override
            public void authPacketArrived(int reasonCode, MqttProperties properties) {
                log.info("认证数据包到达，原因码：{}", reasonCode);
            }
        });
    }

    @PostConstruct
    public void connect() throws MqttException {
        if (!config.isEnabled()) {
            log.info("MQTT 功能已禁用，跳过初始化。");
            return;
        }

        try {
            client = new MqttClient(
                    config.getBroker().getUrl(),
                    config.getClient().getClientId(),
                    new MemoryPersistence()
            );
            setupCallback();

            MqttConnectionOptions connOpts = new MqttConnectionOptions();
            connOpts.setCleanStart(config.getClient().isCleanStart());
            connOpts.setSessionExpiryInterval(config.getClient().getSessionExpiryInterval());
            connOpts.setAutomaticReconnect(config.getClient().isAutomaticReconnect());
            connOpts.setKeepAliveInterval(config.getClient().getKeepAliveInterval());

            if (config.getCredentials() != null && config.getCredentials().getUsername() != null) {
                connOpts.setUserName(config.getCredentials().getUsername());
                connOpts.setPassword(config.getCredentials().getPassword().getBytes());
            }

            log.info("正在连接到：{}", config.getBroker().getUrl());
            client.connect(connOpts);
        } catch (MqttException e) {
            log.error("MQTT 客户端初始化失败：{}", e.getMessage(), e);
            throw e;
        }
    }

    @PreDestroy
    public void disconnect() throws MqttException {
        if (client == null) {
            return;
        }

        if (client.isConnected()) {
            client.disconnect();
            log.info("已断开连接");
        }
        client.close();
    }
}
