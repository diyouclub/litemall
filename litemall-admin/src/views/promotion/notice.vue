<template>
  <div class="app-container">
    <div class="filter-container">
      <el-input v-model="listQuery.notice_name" clearable class="filter-item" style="width: 200px;" placeholder="请输入资讯标题"/>
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">查找</el-button>
      <el-button class="filter-item" type="primary" icon="el-icon-edit" @click="handleCreate">添加</el-button>
    </div>
    <el-table v-loading="listLoading" :data="list" size="small" element-loading-text="正在查询中。。。" fit highlight-current-row>
      <el-table-column type="expand">
        <template slot-scope="scope" >
          <el-card shadow="always">
            <h5 class="txt-center">{{ scope.row.notice_name }}</h5>
            <p class="txt-center">{{ scope.row.author+'\u0020\u0020'+scope.row.create_time }}</p>
            <div v-html="scope.row.notice_content"/>
          </el-card>
        </template>
      </el-table-column>
      <el-table-column align="center" label="公告标题" min-width="200" prop="notice_name"/>
      <el-table-column align="center" label="作者" prop="author"/>
      <el-table-column align="center" label="创建时间" min-width="120" prop="create_time"/>

      <el-table-column align="center" label="操作" min-width="200" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" @click="handleUpdate(scope.row,'scan')">查看</el-button>
          <el-button type="primary" size="mini" @click="handleUpdate(scope.row,'update')">编辑</el-button>
          <el-button type="danger" size="mini" @click="handleDelete(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />

    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" width="70%">
      <el-form
        ref="dataForm"
        :rules="rules"
        :disabled="dialogStatus==='scan'"
        :model="dataForm"
        status-icon
        label-position="left"
        label-width="100px"
      >
        <el-form-item label="公告标题" prop="notice_name">
          <el-input v-model="dataForm.notice_name"/>
        </el-form-item>
        <el-form-item label="作者" prop="author">
          <el-input v-model="dataForm.author"/>
        </el-form-item>
        <el-form-item label="公告内容" prop="notice_content">
          <editor :init="editorInit" :disabled="dialogStatus==='scan'" v-model="dataForm.notice_content"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button v-if="dialogStatus=='create'" type="primary" @click="createData">确定</el-button>
        <el-button v-if="dialogStatus=='update'" type="primary" @click="updateData">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
import { listNotice/*, createNotice, updateNotice*/, deleteNotice } from '@/api/notice'
import { createStorage } from '@/api/storage'
import BackToTop from '@/components/BackToTop'
import Editor from '@tinymce/tinymce-vue'
import Pagination from '@/components/Pagination'
export default {
  name: 'Notice',
  components: { BackToTop, Editor, Pagination },
  data() {
    return {
      listLoading: true,
      listQuery: {
        page: 1,
        limit: 20,
        notice_name: undefined,
        sort: '',
        order: 'desc'
      },
      dialogStatus: '',
      textMap: {
        update: '编辑',
        create: '创建',
        scan: '查看'
      },
      dialogFormVisible: false,
      total: 0,
      list: [],
      dataForm: {
        notice_name: '',
        author: '',
        notice_content: ''
      },
      rules: {
        notice_name: [
          { required: true, message: '公告标题不能为空', trigger: 'blur' }
        ],
        author: [
          { required: true, message: '作者不能为空', trigger: 'blur' }
        ],
        notice_content: [
          { required: true, message: '内容不能为空' }
        ]
      },
      editorInit: {
        language: 'zh_CN',
        convert_urls: false,
        plugins: [
          'advlist anchor autolink autosave code codesample colorpicker colorpicker contextmenu directionality emoticons fullscreen hr image imagetools importcss insertdatetime link lists media nonbreaking noneditable pagebreak paste preview print save searchreplace spellchecker tabfocus table template textcolor textpattern visualblocks visualchars wordcount'
        ],
        toolbar: [
          'searchreplace bold italic underline strikethrough alignleft aligncenter alignright outdent indent  blockquote undo redo removeformat subscript superscript code codesample',
          'hr bullist numlist link image charmap preview anchor pagebreak insertdatetime media table emoticons forecolor backcolor fullscreen'
        ],
        images_upload_handler: function(blobInfo, success, failure) {
          const formData = new FormData()
          formData.append('file', blobInfo.blob())
          createStorage(formData)
            .then(res => {
              success(res.data.data.url)
            })
            .catch(() => {
              failure('上传失败，请重新上传')
            })
        }
      }
    }
  },
  created() {
    this.getList()
  },
  methods: {
    handleFilter() {
      this.listQuery.page = 1
      this.getList()
    },
    handleCreate() {
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
      this.dataForm = {
        notice_name: '',
        author: '',
        notice_content: ''
      }
    },
    getList() {
      listNotice(this.listQuery)
        .then(response => {
          this.list = response.data.data.items
          this.total = response.data.data.total
          this.listLoading = false
        })
        .catch(() => {
          this.list = []
          this.total = 0
          this.listLoading = false
        })
    },
    handleUpdate(row, str) {
      this.dialogStatus = str
      this.dialogFormVisible = true
      this.dataForm = Object.assign(row, {})
    },
    handleDelete(row) {
      this.$confirm('此操作将删除该条公告, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        deleteNotice(row)
          .then(response => {
            this.getList()
            this.$notify.success({
              title: '成功',
              message: '删除资讯成功'
            })
          })
          .catch(response => {
            this.$notify.error({
              title: '失败',
              message: response.data.errmsg
            })
          })
      }).catch(() => {

      })
    },
    createData() {
      this.$refs['dataForm'].validate(valid => {})
    },
    updateData() {

    }
  }
}
</script>
<style scoped>
    .txt-center{
        text-align: center
    }
</style>
