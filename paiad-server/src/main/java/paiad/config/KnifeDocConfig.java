package paiad.config;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.NetworkInterface;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@Component
@Slf4j
public class KnifeDocConfig {

    @Value("${server.port:8080}")
    private String port;

    @Value("${server.servlet.context-path:}")
    private String prefix;

    private String star = "\uD83C\uDF1F --> ";
    @PostConstruct
    public void init() {
        List<String> ipAddresses = getAllIpAddresses();

        StringBuilder sb = new StringBuilder();
        sb.append("\n---------------------------------------------------\n");
        sb.append("Knife4j文档地址:\n");

        // 添加localhost
        sb.append(star).append("http://localhost:").append(port).append(prefix).append("/doc.html\n");

        // 添加所有IP地址
        for (String ip : ipAddresses) {
            sb.append(star).append("http://").append(ip).append(":").append(port).append(prefix).append("/doc.html\n");
        }

        sb.append("---------------------------------------------------");
        log.info(sb.toString());
    }

    private List<String> getAllIpAddresses() {
        List<String> ipList = new ArrayList<>();

        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();

            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();

                if (!networkInterface.isUp() || networkInterface.isLoopback()) {
                    continue;
                }

                Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddress = inetAddresses.nextElement();
                    String hostAddress = inetAddress.getHostAddress();

                    // 只添加IPv4地址
                    if (!hostAddress.contains(":") && !hostAddress.startsWith("127.")) {
                        ipList.add(hostAddress);
                    }
                }
            }
        } catch (Exception e) {
            log.error("获取网络接口信息失败", e);
        }

        return ipList;
    }
}
