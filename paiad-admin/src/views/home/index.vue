<template>
  <div class="dashboard-homepage">
    <!-- 欢迎卡片 -->
    <el-card class="welcome-card">
      <div class="welcome-content">
        <div class="avatar">
          <img :src="useStore.avatar" alt="User Avatar" />
        </div>
        <div class="info">
          <h1 class="greeting">欢迎回来，{{ useStore.nickname }}！</h1>
          <p class="tip">愿你今天工作顺利、心情愉快！</p>
        </div>
      </div>
    </el-card>

    <!-- 四项统计卡片 -->
    <el-row :gutter="20" class="stats-row">
      <el-col :span="6" v-for="(stat, index) in stats" :key="index">
        <el-card class="stats-card" shadow="hover">
          <div class="card-content">
            <div :class="['icon', stat.type]">
              <Icon :icon="stat.icon" width="36" height="36" />
            </div>
            <div class="detail">
              <div class="value">{{ stat.value }}</div>
              <div class="label">{{ stat.label }}</div>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <!-- 图表区域 -->
    <el-row :gutter="20" class="charts-row">
      <el-col :span="12">
        <el-card shadow="hover">
          <template #header><div class="card-header">近七日访问量趋势</div></template>
          <v-chart class="chart" :option="lineChartOptions" autoresize />
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card shadow="hover">
          <template #header><div class="card-header">产品销售额对比</div></template>
          <v-chart class="chart" :option="barChartOptions" autoresize />
        </el-card>
      </el-col>
    </el-row>

    <!-- 待办事项 & 用户类型占比 -->
    <el-row :gutter="20" class="extra-row">
      <el-col :span="12">
        <el-card shadow="hover">
          <template #header><div class="card-header">今日待办事项</div></template>
          <el-timeline>
            <el-timeline-item v-for="(item, index) in todos" :key="index" :timestamp="item.time" :type="item.type">
              {{ item.content }}
            </el-timeline-item>
          </el-timeline>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card shadow="hover">
          <template #header><div class="card-header">用户类型占比</div></template>
          <v-chart class="chart" :option="pieChartOptions" autoresize />
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { use } from 'echarts/core'
import { CanvasRenderer } from 'echarts/renderers'
import { LineChart, BarChart, PieChart } from 'echarts/charts'
import {
  TitleComponent,
  TooltipComponent,
  GridComponent,
  LegendComponent
} from 'echarts/components'
import VChart from 'vue-echarts'
import type { EChartsOption } from 'echarts'
import { Icon } from '@iconify/vue'
import useUserStore from '@/store/modules/user.ts'

const useStore = useUserStore()

use([
  CanvasRenderer,
  LineChart,
  BarChart,
  PieChart,
  TitleComponent,
  TooltipComponent,
  GridComponent,
  LegendComponent
])

const stats = [
  { icon: 'mdi:account-group', label: '用户总数', value: '2,456', type: 'primary' },
  { icon: 'mdi:cart-outline', label: '今日订单', value: '1,888', type: 'success' },
  { icon: 'mdi:currency-usd', label: '今日销售额', value: '¥12,345', type: 'warning' },
  { icon: 'mdi:bug', label: '待处理 Bug', value: '12', type: 'danger' }
]

const todos = [
  { time: '09:00', content: '更新产品数据', type: 'primary' },
  { time: '11:00', content: '修复用户反馈问题', type: 'success' },
  { time: '14:00', content: '审查报表数据', type: 'warning' },
  { time: '16:30', content: '部署测试版本', type: 'danger' }
]

const lineChartOptions = ref<EChartsOption>({
  tooltip: { trigger: 'axis' },
  xAxis: { type: 'category', data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun'] },
  yAxis: { type: 'value' },
  series: [
    {
      name: '访问量',
      type: 'line',
      smooth: true,
      data: [120, 200, 150, 80, 70, 110, 130],
      areaStyle: {},
      lineStyle: { width: 3, color: '#409EFF' },
      symbol: 'circle',
      itemStyle: { color: '#409EFF' }
    }
  ]
})

const barChartOptions = ref<EChartsOption>({
  tooltip: { trigger: 'axis', axisPointer: { type: 'shadow' } },
  xAxis: { type: 'category', data: ['产品 A', '产品 B', '产品 C', '产品 D', '产品 E'] },
  yAxis: { type: 'value' },
  series: [
    {
      name: '销售额',
      type: 'bar',
      data: [500, 780, 400, 650, 900],
      barWidth: '50%',
      itemStyle: { color: '#67C23A', borderRadius: [5, 5, 0, 0] }
    }
  ]
})

const pieChartOptions = ref<EChartsOption>({
  tooltip: { trigger: 'item' },
  legend: { top: '5%', left: 'center' },
  series: [
    {
      name: '用户类型',
      type: 'pie',
      radius: ['40%', '70%'],
      avoidLabelOverlap: false,
      label: { show: false, position: 'center' },
      emphasis: {
        label: { show: true, fontSize: '18', fontWeight: 'bold' }
      },
      labelLine: { show: false },
      data: [
        { value: 1048, name: '普通用户' },
        { value: 735, name: 'VIP 用户' },
        { value: 580, name: '管理员' },
        { value: 300, name: '游客' }
      ]
    }
  ]
})
</script>

<style scoped>
.dashboard-homepage {
  padding: 20px;
  background-color: #f4f5f7;
}

.welcome-card {
  margin-bottom: 20px;
  background: linear-gradient(135deg, #e0f7ff, #ffffff);
}

.welcome-content {
  display: flex;
  align-items: center;
}

.avatar img {
  width: 64px;
  height: 64px;
  border-radius: 50%;
  object-fit: cover;
  margin-right: 20px;
}

.greeting {
  font-size: 22px;
  font-weight: 600;
  color: #303133;
  margin: 0 0 5px 0;
}

.tip {
  font-size: 14px;
  color: #606266;
  margin: 0;
}

.stats-row,
.charts-row,
.extra-row {
  margin-bottom: 20px;
}

.stats-card .el-card__body {
  padding: 20px;
}

.card-content {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.icon {
  padding: 12px;
  border-radius: 12px;
  color: #fff;
}
.icon.primary { background: #409EFF; }
.icon.success { background: #67C23A; }
.icon.warning { background: #E6A23C; }
.icon.danger  { background: #F56C6C; }

.detail {
  text-align: right;
}
.value {
  font-size: 26px;
  font-weight: bold;
  color: #303133;
}
.label {
  font-size: 14px;
  color: #909399;
}

.chart {
  height: 300px;
  width: 100%;
}

.card-header {
  font-weight: 600;
  font-size: 16px;
  color: #303133;
}

.extra-row .el-col {
  display: flex;
  flex-direction: column;
}
.extra-row .el-card {
  flex: 1;
  display: flex;
  flex-direction: column;
}
.el-card .el-timeline,
.el-card .chart {
  flex: 1;
}

</style>
