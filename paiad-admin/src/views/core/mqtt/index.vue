<template>
  <div class="mqtt-chat-box">
    <div class="chat-header">
      <span class="header-title">MQTT Chat</span>
      <div class="header-actions">
        <el-button
          :type="clientInitData.connected ? 'danger' : 'success'"
          :icon="clientInitData.connected ? 'CircleClose' : 'CircleCheck'"
          @click="clientInitData.connected ? closeConnection() : openConnectionDialog()"
          size="small"
        >
          {{ clientInitData.connected ? '断开' : '连接' }}
        </el-button>
        <el-button
          :type="subscriptionInitData.subscription ? 'warning' : 'primary'"
          :icon="subscriptionInitData.subscription ? 'Minus' : 'Plus'"
          @click="subscriptionInitData.subscription ? unSubscriptionTopicHandler() : openSubscriptionDialog()"
          size="small"
          :disabled="!clientInitData.connected"
        >
          {{ subscriptionInitData.subscription ? '取消订阅' : '订阅' }}
        </el-button>
      </div>
    </div>

    <div class="message-box">
      <el-scrollbar ref="chatScrollbar" class="chat-scroll">
        <div v-for="(msg, index) in chatMessages" :key="index" :class="['message-item', msg.sender]">
          <div class="message-content">
            <div class="message-meta">
              <span class="message-sender">{{ msg.sender === 'me' ? 'Me' : 'Subscribe Theme' }}</span>
              <span class="message-topic">@ {{ msg.topic }}</span>
              <span class="message-time">{{ formatTime(msg.timestamp) }}</span>
            </div>
            <div class="message-bubble">{{ msg.message }}</div>
          </div>
        </div>
      </el-scrollbar>
    </div>

    <div class="message-input-area">
      <div class="publish-controls-top">
        <el-form :inline="true" :model="publishInfo" class="publish-form-inline">
          <el-form-item label="发布主题">
            <el-input v-model="publishInfo.topic" placeholder="e.g., my/chat/message" size="small" />
          </el-form-item>
          <el-form-item label="QoS">
            <el-select v-model="publishInfo.qos" placeholder="QoS" size="small" style="width: 100px">
              <el-option v-for="q in qosList" :key="q" :label="'QoS ' + q" :value="q" />
            </el-select>
          </el-form-item>
        </el-form>
      </div>
      <el-input
        v-model="publishInfo.payload"
        placeholder="输入要发送的消息..."
        @keyup.enter.native="handleEnterToSend"
        class="send-input"
      >
        <template #append>
          <el-button
            type="primary"
            @click="doPublish"
            :disabled="!clientInitData.connected || !publishInfo.payload"
            style="background: #7ba8f5;"
          >
            <span style="color: #ffffff">发送</span>
            <el-icon class="el-icon--right" style="color: #ffffff"><Right /></el-icon>
          </el-button>
        </template>
      </el-input>

    </div>

    <el-dialog
      v-model="connectionDialogVisible"
      title="连接设置"
      width="400px"
      :close-on-click-modal="false"
      destroy-on-close
    >
      <el-form :model="connectionInfo" label-width="80px">
        <el-form-item label="协议">
          <el-input v-model="connectionInfo.protocol" placeholder="ws" />
        </el-form-item>
        <el-form-item label="主机">
          <el-input v-model="connectionInfo.host" placeholder="localhost" />
        </el-form-item>
        <el-form-item label="端口">
          <el-input v-model="connectionInfo.port" placeholder="8083" />
        </el-form-item>
        <el-form-item label="用户名">
          <el-input v-model="connectionInfo.username" placeholder="admin" />
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="connectionInfo.password" type="password" show-password placeholder="********" />
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="connectionDialogVisible = false">取消</el-button>
          <el-button type="success" @click="createConnection">连接</el-button>
        </span>
      </template>
    </el-dialog>

    <el-dialog
      v-model="subscriptionDialogVisible"
      title="订阅主题"
      width="400px"
      :close-on-click-modal="false"
      destroy-on-close
    >
      <el-form :model="subscriptionInfo" label-width="80px">
        <el-form-item label="主题">
          <el-input v-model="subscriptionInfo.topic" placeholder="e.g., my/chat/#" />
        </el-form-item>
        <el-form-item label="QoS">
          <el-select v-model="subscriptionInfo.qos" placeholder="选择 QoS">
            <el-option v-for="q in qosList" :key="q" :label="'QoS ' + q" :value="q" />
          </el-select>
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="subscriptionDialogVisible = false">取消</el-button>
          <el-button type="primary" @click="subscriptionTopicHandler">订阅</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, onMounted, onUnmounted, nextTick } from 'vue';
import mqtt from 'mqtt';
import { ElNotification, ElMessage } from 'element-plus';

const qosList = [0, 1, 2];
const connectionInfo = ref({
  protocol: 'ws',
  host: 'localhost',
  port: '8083',
  clientId: 'emqx_vue_client_' + Math.random().toString().substring(2, 8),
  username: 'admin',
  password: '123456',
});

const clientInitData = ref({ connected: false });
const subscriptionInitData = ref({ subscription: false });

const client = ref(null);
const chatMessages = ref([]);
const chatScrollbar = ref(null);

const subscriptionInfo = ref({ topic: 'emqx/subscribe', qos: 0 });
const publishInfo = ref({ topic: 'emqx/publish', qos: 0, payload: '' });

const connectionDialogVisible = ref(false);
const subscriptionDialogVisible = ref(false);

// --- Lifecycle Hooks ---
onMounted(() => {
  // Optional: Auto-connect on mount if you want to remember settings
  // createConnection(); // Keep this commented out for now to prevent initial connection attempts
});

onUnmounted(() => {
  if (client.value) {
    client.value.end(true); // Disconnect cleanly on component unmount
  }
});

// --- Utility Functions ---
const formatTime = (timestamp) => {
  if (!timestamp) return '';
  const date = new Date(timestamp);
  return date.toLocaleTimeString([], { hour: '2-digit', minute: '2-digit', second: '2-digit' });
};

const scrollToBottom = () => {
  nextTick(() => {
    if (chatScrollbar.value) {
      chatScrollbar.value.setScrollTop(chatScrollbar.value.wrapRef.scrollHeight);
    }
  });
};

const handleEnterToSend = (event) => {
  if (event.key === 'Enter' && !event.shiftKey) { // Send on Enter, allow Shift+Enter for new line
    event.preventDefault(); // Prevent default new line
    doPublish();
  }
};

// --- Connection Logic ---
const openConnectionDialog = () => {
  console.log('Attempting to open connection dialog...');
  connectionDialogVisible.value = true;
  console.log('connectionDialogVisible set to:', connectionDialogVisible.value);
};

const createConnection = () => {
  // --- IMPORTANT DEBUGGING LOGS ---
  console.log('--- createConnection called ---');
  console.log('Current connectionInfo:', JSON.parse(JSON.stringify(connectionInfo.value))); // Deep copy to prevent reactivity issues in log
  const { protocol, host, port, ...options } = connectionInfo.value;

  // Validate inputs before constructing URL
  if (!protocol || !host || !port) {
    ElMessage.error('连接信息不完整：协议、主机和端口都不能为空。');
    console.error('Connection info missing:', { protocol, host, port });
    return; // Stop execution if critical info is missing
  }

  const url = `${protocol}://${host}:${port}/mqtt`;
  console.log('Constructing URL:', url);

  if (client.value && client.value.connected) {
    console.log('Existing client connected, attempting to end it.');
    client.value.end(true); // Ensure previous connection is closed
  }

  try {
    client.value = mqtt.connect(url, options);
    console.log('MQTT client initiated.');

    client.value.on('connect', () => {
      clientInitData.value.connected = true;
      connectionDialogVisible.value = false; // Close dialog on successful connection
      ElNotification({ type: 'success', title: '连接成功', message: `已连接到 ${url}`, position: 'top-right', duration: 1500 });
      console.log('MQTT client connected successfully!');
    });

    client.value.on('error', (err) => {
      ElNotification({ type: 'error', title: '连接失败', message: err.message, position: 'top-right',duration: 1500 });
      clientInitData.value.connected = false;
      console.error('MQTT connection error:', err);
    });

    client.value.on('close', () => {
      if (clientInitData.value.connected) {
        // ElNotification({ type: 'info', title: '连接关闭', message: 'MQTT 连接已断开', position: 'top-right',duration: 1500 });
      }
      clientInitData.value.connected = false;
      subscriptionInitData.value.subscription = false;
      console.log('MQTT connection closed.');
    });

    client.value.on('message', (recvTopic, message) => {
      console.log(`Received message on topic "${recvTopic}":`, message.toString());
      chatMessages.value.push({
        sender: 'other',
        topic: recvTopic,
        message: message.toString(),
        timestamp: Date.now()
      });
      scrollToBottom();
    });
  } catch (e) {
    ElMessage.error(`无法初始化 MQTT 客户端: ${e.message}`);
    console.error('Error initializing MQTT client:', e);
  }
};

const closeConnection = () => {
  if (client.value) {
    console.log('Attempting to close connection.');
    client.value.end(false, () => {
      clientInitData.value.connected = false;
      subscriptionInitData.value.subscription = false;
      ElNotification({ type: 'info', title: '断开连接', message: '已断开 MQTT 连接', position: 'top-right',duration: 1500 });
      console.log('MQTT connection gracefully ended.');
    });
  }
};

// --- Subscription Logic ---
const openSubscriptionDialog = () => {
  if (!clientInitData.value.connected) {
    ElMessage.warning('请先连接到 MQTT 服务器');
    return;
  }
  subscriptionDialogVisible.value = true;
};

const subscriptionTopicHandler = () => {
  if (!client.value || !clientInitData.value.connected) {
    ElMessage.warning('请先连接到 MQTT 服务器');
    return;
  }
  const { topic, qos } = subscriptionInfo.value;
  if (!topic) {
    ElMessage.warning('请输入订阅主题');
    return;
  }
  client.value.subscribe(topic, { qos }, (err) => {
    if (err) {
      ElNotification({ type: 'error', title: '订阅失败', message: err.message, position: 'top-right',duration: 1500 });
      return;
    }
    subscriptionInitData.value.subscription = true;
    subscriptionDialogVisible.value = false; // Close dialog on successful subscription
    ElNotification({ type: 'success', title: '订阅成功', message: `已订阅主题: ${topic}`, position: 'top-right',duration: 1500 });
  });
};

const unSubscriptionTopicHandler = () => {
  if (!client.value || !clientInitData.value.connected) {
    ElMessage.warning('请先连接到 MQTT 服务器');
    return;
  }
  const { topic } = subscriptionInfo.value;
  if (!topic) {
    ElMessage.warning('请输入要取消订阅的主题');
    return;
  }
  client.value.unsubscribe(topic, (err) => {
    if (err) {
      ElNotification({ type: 'error', title: '取消订阅失败', message: err.message, position: 'top-right',duration: 1500 });
      return;
    }
    subscriptionInitData.value.subscription = false;
    ElNotification({ type: 'info', title: '已取消订阅', message: `已取消订阅主题: ${topic}`, position: 'top-right',duration: 1500 });
  });
};

// --- Publish Logic ---
const doPublish = () => {
  if (!client.value || !clientInitData.value.connected) {
    ElMessage.warning('请先连接到 MQTT 服务器');
    return;
  }
  const { topic, qos, payload } = publishInfo.value;
  if (!topic || !payload.trim()) { // Check for empty payload after trimming whitespace
    ElMessage.warning('主题和消息不能为空');
    return;
  }
  client.value.publish(topic, payload, { qos }, (err) => {
    if (err) {
      ElNotification({ type: 'error', title: '发送失败', message: err.message, position: 'top-right',duration: 1500 });
      return;
    }
    chatMessages.value.push({ sender: 'me', topic, message: payload, timestamp: Date.now() });
    publishInfo.value.payload = ''; // Clear message after sending
    scrollToBottom();
  });
};
</script>

<style scoped>
@import url('../../../styles/mqtt.css');
</style>
