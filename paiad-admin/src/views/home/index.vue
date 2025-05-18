<template>
  <div class="dashboard-homepage">
    <el-card class="welcome-card">
      <div class="welcome-content">
        <div class="avatar">
          <img src="https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif" alt="User Avatar">
        </div>
        <div class="info">
          <h1 class="greeting">欢迎回来，管理员！</h1>
          <p class="tip">今天又是充满活力的一天！</p>
        </div>
      </div>
    </el-card>

    <el-row :gutter="20" class="stats-row">
      <el-col :span="6">
        <el-card class="stats-card" shadow="hover">
          <div class="card-content">
            <div class="icon primary">
              <Icon icon="mdi:account-group" width="36" height="36" />
            </div>
            <div class="detail">
              <div class="value">2,456</div>
              <div class="label">用户总数</div>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card class="stats-card" shadow="hover">
          <div class="card-content">
            <div class="icon success">
              <Icon icon="mdi:cart-outline" width="36" height="36" />
            </div>
            <div class="detail">
              <div class="value">1,888</div>
              <div class="label">今日订单</div>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card class="stats-card" shadow="hover">
          <div class="card-content">
            <div class="icon warning">
              <Icon icon="mdi:currency-usd" width="36" height="36" />
            </div>
            <div class="detail">
              <div class="value">¥ 12,345</div>
              <div class="label">今日销售额</div>
            </div>
          </div>
        </el-card>
      </el-col>
      <el-col :span="6">
        <el-card class="stats-card" shadow="hover">
          <div class="card-content">
            <div class="icon danger">
              <Icon icon="mdi:bug" width="36" height="36" />
            </div>
            <div class="detail">
              <div class="value">12</div>
              <div class="label">待处理 Bug</div>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <el-row :gutter="20" class="charts-row">
      <el-col :span="12">
        <el-card shadow="hover">
          <template #header>
            <div class="card-header">
              <span>近七日访问量趋势</span>
            </div>
          </template>
          <v-chart class="chart" :option="lineChartOptions" autoresize />
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card shadow="hover">
          <template #header>
            <div class="card-header">
              <span>产品销售额对比</span>
            </div>
          </template>
          <v-chart class="chart" :option="barChartOptions" autoresize />
        </el-card>
      </el-col>
    </el-row>

  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue';
// 导入 ECharts 相关
import { use } from 'echarts/core';
import { CanvasRenderer } from 'echarts/renderers';
import { LineChart, BarChart } from 'echarts/charts';
import {
  TitleComponent,
  TooltipComponent,
  GridComponent,
  LegendComponent // 添加 LegendComponent 用于柱状图
} from 'echarts/components';
import VChart, { THEME_KEY } from 'vue-echarts';
import type { EChartsOption } from 'echarts';

// 注册 ECharts 组件
use([
  CanvasRenderer,
  LineChart,
  BarChart,
  TitleComponent,
  TooltipComponent,
  GridComponent,
  LegendComponent // 注册 LegendComponent
]);

// 定义图表选项
const lineChartOptions = ref<EChartsOption>({
  title: {
    // text: '近七日访问量趋势', // 标题已在卡片头，这里可以省略
    left: 'center'
  },
  tooltip: {
    trigger: 'axis',
    axisPointer: {
      type: 'shadow'
    }
  },
  xAxis: {
    type: 'category',
    data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
  },
  yAxis: {
    type: 'value'
  },
  series: [
    {
      name: '访问量',
      type: 'line',
      smooth: true, // 平滑曲线
      data: [120, 200, 150, 80, 70, 110, 130],
      itemStyle: {
        color: '#409EFF' // Element Plus Primary color
      }
    }
  ]
});

const barChartOptions = ref<EChartsOption>({
  title: {
    // text: '产品销售额对比', // 标题已在卡片头，这里可以省略
    left: 'center'
  },
  tooltip: {
    trigger: 'axis',
    axisPointer: {
      type: 'shadow'
    }
  },
  xAxis: {
    type: 'category',
    data: ['产品 A', '产品 B', '产品 C', '产品 D', '产品 E']
  },
  yAxis: {
    type: 'value'
  },
  series: [
    {
      name: '销售额',
      type: 'bar',
      data: [500, 780, 400, 650, 900],
      itemStyle: {
        color: '#67C23A' // Element Plus Success color
      }
    }
  ]
});

// 数据和方法（如果有需要从后端获取数据等）
onMounted(() => {
  // 可以在这里发起异步请求获取实际数据，然后更新 lineChartOptions.value 和 barChartOptions.value
  console.log('Homepage mounted!');
});

</script>

<style scoped>
.dashboard-homepage {
  padding: 20px;
  background-color: #f0f2f5; /* 浅灰色背景 */
  min-height: calc(100vh - var(--header-height, 0px) - var(--footer-height, 0px)); /* 保证内容区域至少填满视口高度减去其他固定高度 */
}

/* 欢迎卡片样式 */
.welcome-card {
  margin-bottom: 20px;
}

.welcome-content {
  display: flex;
  align-items: center;
}

.avatar img {
  width: 60px;
  height: 60px;
  border-radius: 50%;
  margin-right: 20px;
  object-fit: cover; /* 确保图片不变形 */
}

.info .greeting {
  font-size: 20px;
  font-weight: bold;
  margin: 0 0 5px 0;
  color: #303133;
}

.info .tip {
  font-size: 14px;
  color: #606266;
  margin: 0;
}

/* 统计数据卡片样式 */
.stats-row {
  margin-bottom: 20px;
}

.stats-card .el-card__body {
  padding: 20px; /* 调整卡片内部填充 */
}


.stats-card .card-content {
  display: flex;
  align-items: center;
  justify-content: space-between; /* 图标和文字分列两侧 */
}

.stats-card .icon {
  padding: 10px;
  border-radius: 8px;
  color: #fff; /* 图标颜色白色 */
}

.stats-card .icon.primary {
  background-color: #409EFF; /* Element Plus Primary color */
}
.stats-card .icon.success {
  background-color: #67C23A; /* Element Plus Success color */
}
.stats-card .icon.warning {
  background-color: #E6A23C; /* Element Plus Warning color */
}
.stats-card .icon.danger {
  background-color: #F56C6C; /* Element Plus Danger color */
}


.stats-card .detail {
  text-align: right; /* 数字和标签右对齐 */
}

.stats-card .value {
  font-size: 24px;
  font-weight: bold;
  color: #303133;
  margin-bottom: 5px;
}

.stats-card .label {
  font-size: 14px;
  color: #909399;
}


/* 图表区域样式 */
.charts-row {
  margin-bottom: 20px;
}

.chart {
  height: 300px; /* 必须给图表容器一个高度 */
  width: 100%;
}

/* 卡片头部样式 */
.card-header {
  font-size: 16px;
  font-weight: bold;
  color: #303133;
}

/* 更多区域样式 */
/* .more-row {
  margin-bottom: 20px;
} */
</style>
