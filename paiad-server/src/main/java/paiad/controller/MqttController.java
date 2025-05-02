package paiad.controller;

import cn.dev33.satoken.util.SaResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.eclipse.paho.mqttv5.common.MqttException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import paiad.mqtt.MqttPublisher;
import paiad.mqtt.MqttSubscriber;

@RestController
@RequestMapping("/mqtt/")
@Tag(name = "Mqtt接口文档")
public class MqttController {
    @Resource
    private MqttPublisher mqttPublisher;
    @Resource
    private MqttSubscriber mqttSubscriber;

    @PostMapping("publish")
    @Operation(summary = "发布消息")
    private SaResult publish(@RequestParam(value = "topic", defaultValue = "emqx/publish") String topic,
                             @RequestParam(value = "msg", defaultValue = "Hello, world!") String msg,
                             @RequestParam(value = "qos", defaultValue = "1") int qos) {
        mqttPublisher.publish(topic, msg, qos);
        return SaResult.ok();
    }

    @PostMapping("subscriber")
    @Operation(summary = "订阅主题")
    private SaResult subscriber(@RequestParam(value = "topic", defaultValue = "emqx/subsribe") String topic,
                                @RequestParam(value = "qos", defaultValue = "1") int qos) {
        mqttSubscriber.subscribe(topic, qos);
        return SaResult.ok();
    }
}
