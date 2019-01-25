<template>
  <div class="app-container">

    <!-- 查询和其他操作 -->
    <div class="filter-container">
      <el-date-picker
        v-model="dateValue"
        type="daterange"
        value-format="yyyy-MM-dd HH:mm:ss"
        range-separator="至"
        start-placeholder="开始日期"
        end-placeholder="结束日期"/>
    </div>

    <!-- 查询结果 -->
    <el-table
      v-loading="listLoading"
      :data="list"
      size="small"
      element-loading-text="正在查询中。。。"
      border
      fit
      highlight-current-row>
      <el-table-column align="center" label="ID" prop="id"/>
      <el-table-column align="center" label="用户名" prop="apply_user_name"/>
      <el-table-column align="center" label="开户银行" prop="bandName"/>
      <el-table-column align="center" label="银行卡号" prop="bankCard"/>
      <el-table-column align="center" label="时间" prop="addTime"/>

      <el-table-column align="center" label="手续费" prop="brokerage"/>

      <el-table-column align="center" label="实际金额" prop="money"/>

      <el-table-column align="center" label="到账金额" prop="finallyMoney"/>

      <el-table-column align="center" label="状态" >
        <template slot-scope="scope">
          <p v-if="scope.row.auditFlag==='0'">待审核</p>
          <p v-else-if="scope.row.auditFlag==='1'">审核通过</p>
          <p v-else>审核不通过</p>
        </template>
      </el-table-column>

      <el-table-column align="center" label="审核" width="200" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button :disabled="scope.row.auditFlag !=='0'" type="primary" size="mini" @click="handlePass(scope.row,true)">通过</el-button>
          <el-button :disabled="scope.row.auditFlag !=='0'" type="danger" size="mini" @click="handlePass(scope.row,fasle)">不通过</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />

    <el-tooltip placement="top" content="返回顶部">
      <back-to-top :visibility-height="100"/>
    </el-tooltip>

  </div>
</template>

<script>
import { listIncome, listAudit } from '@/api/income'
import BackToTop from '@/components/BackToTop'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination

export default {
  name: 'GrouponRule',
  components: { BackToTop, Pagination },
  data() {
    return {
      list: [],
      total: 0,
      listLoading: true,
      dateValue: ['', ''],
      listQuery: {
        page: 1,
        limit: 20,
        goodsId: undefined,
        sort: 'add_time',
        order: 'desc'
      },
      dataForm: {
        id: undefined,
        goodsId: '',
        discount: '',
        discountMember: '',
        expireTime: undefined
      },
      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: '编辑',
        create: '创建'
      },
      rules: {
        goodsId: [{ required: true, message: '商品不能为空', trigger: 'blur' }]
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    handlePass(row, flag) {
      const params = {
        applyId: row.id,
        auditFlag: flag ? '1' : '2'
      }
      listAudit(params).then(res => {
        if (!res.errno) {
          this.$message({
            message: res.errmsg,
            type: 'success'
          })
        }
        this.getList()
      })
    },
    getList() {
      const params = {
        startTime: this.dateValue[0],
        endTime: this.dateValue[1],
        page: this.listQuery.page,
        limit: this.listQuery.limit
      }
      this.listLoading = true
      listIncome(params).then(res => {
        const data = res.data.data
        this.list = data.commissionResults
        this.total = data.total
        this.listLoading = false
        console.log(res.data.data.commissionResults)
      })
    }

  }
}
</script>
