<template>
  <div class="app-container">

    <!-- 查询和其他操作 -->
    <div class="filter-container">
      <el-input v-model="listQuery.title" clearable class="filter-item" style="width: 200px;" placeholder="请输入资讯标题"/>
      <el-input v-model="listQuery.subtitle" clearable class="filter-item" style="width: 200px;" placeholder="请输入资讯子标题"/>
      <el-button class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">查找</el-button>
      <el-button class="filter-item" type="primary" icon="el-icon-edit" @click="handleCreate">添加</el-button>
      <el-button :loading="downloadLoading" class="filter-item" type="primary" icon="el-icon-download" @click="handleDownload">导出</el-button>
    </div>

    <!-- 查询结果 -->
    <el-table v-loading="listLoading" :data="list" size="small" element-loading-text="正在查询中。。。" border fit highlight-current-row>
      <el-table-column align="center" label="资讯标题" prop="title"/>

      <el-table-column align="center" label="资讯子标题" min-width="200" prop="subtitle"/>
      <el-table-column align="center" label="资讯子标题" prop="newsType"/>
      <el-table-column align="center" property="picUrl" label="图片">
        <template slot-scope="scope">
          <img :src="scope.row.picUrl" width="80">
        </template>
      </el-table-column>

      <el-table-column align="center" label="资讯详情" prop="content">
        <template slot-scope="scope">
          <el-dialog :visible.sync="contentDialogVisible" title="资讯详情">
            <div v-html="contentDetail"/>
          </el-dialog>
          <el-button type="primary" size="mini" @click="showContent(scope.row.content)">查看</el-button>
        </template>
      </el-table-column>

      <el-table-column align="center" label="底价" prop="price"/>

      <el-table-column align="center" label="阅读数量" prop="readCount"/>

      <el-table-column align="center" label="操作" min-width="200" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" @click="handleUpdate(scope.row)">编辑</el-button>
          <el-button type="danger" size="mini" @click="handleDelete(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />

    <el-tooltip placement="top" content="返回顶部">
      <back-to-top :visibility-height="100" />
    </el-tooltip>

    <!-- 添加或修改对话框 -->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form ref="dataForm" :rules="rules" :model="dataForm" status-icon label-position="left" label-width="100px" style="width: 400px; margin-left:50px;">
        <el-form-item label="资讯标题" prop="title">
          <el-input v-model="dataForm.title"/>
        </el-form-item>
        <el-form-item label="资讯子标题" prop="subtitle">
          <el-input v-model="dataForm.subtitle"/>
        </el-form-item>
        <el-form-item label="资讯类型" prop="newsType">
          <el-select v-model="dataForm.newsType">
            <el-option v-for="(item,index) in sele" :key="index" :label="item.codeName" :value="item.id"/>
          </el-select>
        </el-form-item>
        <el-form-item label="资讯图片" prop="picUrl">
          <el-upload :headers="headers" :action="uploadPath" :show-file-list="false" :on-success="uploadPicUrl" class="avatar-uploader" list-type="picture-card" accept=".jpg,.jpeg,.png,.gif">
            <img v-if="dataForm.picUrl" :src="dataForm.picUrl" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"/>
          </el-upload>
        </el-form-item>
        <el-form-item style="width: 700px;" label="资讯内容">
          <editor :init="editorInit" v-model="dataForm.content"/>
        </el-form-item>
        <el-form-item label="商品低价" prop="price">
          <el-input v-model="dataForm.price"/>
        </el-form-item>
        <el-form-item label="阅读量" prop="readCount">
          <el-input v-model="dataForm.readCount"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button v-if="dialogStatus=='create'" type="primary" @click="createData">确定</el-button>
        <el-button v-else type="primary" @click="updateData">确定</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<style>
.el-dialog {
  width: 800px;
}
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #20a0ff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 120px;
  height: 120px;
  line-height: 120px;
  text-align: center;
}
.avatar {
  width: 120px;
  height: 120px;
  display: block;
}
</style>

<script>
import { listNews, createNews, updateNews, deleteNews, seleNews } from '@/api/news'
import { createStorage, uploadPath } from '@/api/storage'
import BackToTop from '@/components/BackToTop'
import Editor from '@tinymce/tinymce-vue'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination
import { getToken } from '@/utils/auth'

export default {
  name: 'News',
  components: { BackToTop, Editor, Pagination },
  data() {
    return {
      uploadPath,
      list: undefined,
      sele: [],
      total: 0,
      listLoading: true,
      listQuery: {
        page: 1,
        limit: 20,
        title: undefined,
        subtitle: undefined,
        sort: 'add_time',
        order: 'desc'
      },
      dataForm: {
        id: undefined,
        titile: undefined,
        subtitle: undefined,
        picUrl: undefined,
        content: '',
        price: undefined,
        readCount: undefined,
        goods: [],
        newsType: ''
      },
      contentDetail: '',
      contentDialogVisible: false,
      dialogFormVisible: false,
      dialogStatus: '',
      textMap: {
        update: '编辑',
        create: '创建'
      },
      rules: {
        title: [
          { required: true, message: '资讯标题不能为空', trigger: 'blur' }
        ],
        subtitle: [
          { required: true, message: '资讯子标题不能为空', trigger: 'blur' }
        ],
        content: [
          { required: true, message: '资讯内容不能为空', trigger: 'blur' }
        ],
        newsType: [
          { required: true, message: '请选择资讯类型', trigger: 'change' }
        ]
      },
      downloadLoading: false,
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
  computed: {
    headers() {
      return {
        'X-Litemall-Admin-Token': getToken()
      }
    }
  },
  created() {
    this.getList()
    this.getSele()
  },
  methods: {
    getSele() {
      seleNews(this.listQuery)
        .then(response => {
          this.sele = response.data.data
        })
        .catch(() => {
        })
    },
    getList() {
      this.listLoading = true
      listNews(this.listQuery)
        .then(response => {
          this.list = response.data.data.items
          console.log(this.list)

          this.total = response.data.data.total
          this.listLoading = false
        })
        .catch(() => {
          this.list = []
          this.total = 0
          this.listLoading = false
        })
    },
    handleFilter() {
      this.listQuery.page = 1
      this.getList()
    },
    resetForm() {
      this.dataForm = {
        id: undefined,
        titile: undefined,
        subtitle: undefined,
        picUrl: undefined,
        content: '',
        price: undefined,
        readCount: undefined,
        goods: []
      }
    },
    handleCreate() {
      this.resetForm()
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    uploadPicUrl: function(response) {
      console.log(response)

      this.dataForm.picUrl = response.data.url
    },
    createData() {
      this.$refs['dataForm'].validate(valid => {
        if (valid) {
          createNews(this.dataForm)
            .then(response => {
              this.list.unshift(response.data.data)
              this.dialogFormVisible = false
              this.$notify.success({
                title: '成功',
                message: '创建资讯成功'
              })
            })
            .catch(response => {
              this.$notify.error({
                title: '失败',
                message: response.data.errmsg
              })
            })
        }
      })
    },
    showContent(content) {
      this.contentDetail = content
      this.contentDialogVisible = true
    },
    handleUpdate(row) {
      this.dataForm = Object.assign({}, row)
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    updateData() {
      this.$refs['dataForm'].validate(valid => {
        if (valid) {
          updateNews(this.dataForm)
            .then(() => {
              for (const v of this.list) {
                if (v.id === this.dataForm.id) {
                  const index = this.list.indexOf(v)
                  this.list.splice(index, 1, this.dataForm)
                  break
                }
              }
              this.dialogFormVisible = false
              this.$notify.success({
                title: '成功',
                message: '更新资讯成功'
              })
            })
            .catch(response => {
              this.$notify.error({
                title: '失败',
                message: response.data.errmsg
              })
            })
        }
      })
    },
    handleDelete(row) {
      deleteNews(row)
        .then(response => {
          this.$notify.success({
            title: '成功',
            message: '删除资讯成功'
          })
          const index = this.list.indexOf(row)
          this.list.splice(index, 1)
        })
        .catch(response => {
          this.$notify.error({
            title: '失败',
            message: response.data.errmsg
          })
        })
    },
    handleDownload() {
      this.downloadLoading = true
      import('@/vendor/Export2Excel').then(excel => {
        const tHeader = [
          '资讯ID',
          '资讯标题',
          '资讯子标题',
          '资讯内容',
          '资讯图片',
          '商品低价',
          '阅读量',
          '资讯商品'
        ]
        const filterVal = [
          'id',
          'title',
          'subtitle',
          'content',
          'picUrl',
          'price',
          'readCount',
          'goods'
        ]
        excel.export_json_to_excel2(tHeader, this.list, filterVal, '资讯信息')
        this.downloadLoading = false
      })
    }
  }
}
</script>
