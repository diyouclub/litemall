<template>
  <div class="app-container">
    <div class="filter-container">
      <el-button class="filter-item" type="primary" @click="showCreateTabDialog">添加分类</el-button>
      <el-tabs v-model="dataForm.clsId" type="border-card">
        <el-tab-pane v-for="(item,index) in tabs" :key="index" :name="item.id+''" :label="item.clsName">
          <!-- 查询和其他操作 -->
          <div class="filter-container">
            <el-input v-model="listQuery.info_title" clearable class="filter-item" style="width: 200px;" placeholder="请输入资讯标题"/>
            <el-button class="filter-item" type="primary" icon="el-icon-search" @click="handleFilter">查找</el-button>
            <el-button class="filter-item" type="primary" icon="el-icon-edit" @click="handleCreate">添加</el-button>
            <el-button :loading="downloadLoading" class="filter-item" type="primary" icon="el-icon-download" @click="handleDownload">导出</el-button>
          </div>

          <!-- 查询结果 -->
          <el-table v-loading="listLoading" :data="list" size="small" element-loading-text="正在查询中。。。" border fit highlight-current-row>
            <el-table-column align="center" label="资讯标题" prop="title"/>

            <el-table-column align="center" label="资讯子标题" min-width="200" prop="infoShortTitle"/>
            <el-table-column align="center" label="资讯子标题" prop="newsType"/>
            <el-table-column align="center" property="infoMainImg" label="图片">
              <template slot-scope="scope">
                <img :src="scope.row.infoMainImg" width="80">
              </template>
            </el-table-column>

            <el-table-column align="center" label="资讯详情" prop="content">
              <template slot-scope="scope">
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
        </el-tab-pane>
      </el-tabs>
    </div>

    <!-- 添加资讯分类对话框 -->
    <el-dialog :visible.sync="dialogFormVisible2" title="新增资讯分类">
      <el-form ref="typeForm" :rules="typeRules" :model="typeForm" status-icon label-position="left" label-width="100px" style="width: 400px; margin-left:50px;">
        <el-form-item label="分类名" prop="clsName">
          <el-input v-model="typeForm.clsName"/>
        </el-form-item>
        <el-form-item label="分类图标" prop="clsIcon">
          <el-upload :headers="headers" :action="uploadPath" :show-file-list="false" :on-success="uploadPicUrl" class="avatar-uploader" list-type="picture-card" accept=".jpg,.jpeg,.png,.gif">
            <img v-if="typeForm.clsIcon" :src="typeForm.clsIcon" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"/>
          </el-upload>
        </el-form-item>
        <el-form-item label="展示条数" prop="indexLimit">
          <el-input v-model.number="typeForm.indexLimit" type="number"/>
        </el-form-item>
        <el-form-item label="是否显示" prop="showIndex">
          <el-switch v-model="typeForm.showIndex" :active-value="0" :inactive-value="1" active-text="是" inactive-text="否"	/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible2 = false">取消</el-button>
        <el-button type="primary" @click="createTab">确定</el-button>
      </div>
    </el-dialog>

    <el-tooltip placement="top" content="返回顶部">
      <back-to-top :visibility-height="100" />
    </el-tooltip>

    <!-- 添加或修改对话框 -->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form ref="dataForm" :rules="rules" :model="dataForm" status-icon label-position="left" label-width="100px" style="width: 400px; margin-left:50px;">
        <el-form-item label="资讯标题" prop="infoTitle">
          <el-input v-model="dataForm.infoTitle"/>
        </el-form-item>
        <el-form-item label="资讯子标题" prop="infoShortTitle">
          <el-input v-model="dataForm.infoShortTitle"/>
        </el-form-item>
        <el-form-item label="描述" prop="infoDescription">
          <el-input v-model="dataForm.infoDescription"/>
        </el-form-item>
        <el-form-item label="作者" prop="author">
          <el-input v-model="dataForm.author"/>
        </el-form-item>

        <!-- <el-form-item label="资讯类型" prop="newsType">
          <el-select v-model="dataForm.newsType">
            <el-option v-for="(item,index) in sele" :key="index" :label="item.codeName" :value="item.id"/>
          </el-select>
        </el-form-item> -->
        <el-form-item label="资讯图片" prop="infoMainImg">
          <el-upload :headers="headers" :action="uploadPath" :show-file-list="false" :on-success="uploadPicUrl" class="avatar-uploader" list-type="picture-card" accept=".jpg,.jpeg,.png,.gif">
            <img v-if="dataForm.infoMainImg" :src="dataForm.infoMainImg" class="avatar">
            <i v-else class="el-icon-plus avatar-uploader-icon"/>
          </el-upload>
        </el-form-item>
        <el-form-item style="width: 700px;" label="资讯内容">
          <editor :init="editorInit" v-model="dataForm.content"/>
        </el-form-item>
        <el-form-item label="是否显示" prop="showIndex">
          <el-switch v-model="dataForm.showIndex" :active-value="0" :inactive-value="1" active-text="是" inactive-text="否"	/>
        </el-form-item>
        <el-form-item label="公布范围" prop="scope">
          <el-radio-group v-model="dataForm.scope">
            <el-radio label="0">所有用户</el-radio>
            <el-radio label="1">指定用户</el-radio>
          </el-radio-group>
          <el-input v-if="dataForm.scope==='1'" v-model="dataForm.assignPhone" placeholder="指定用户的手机号"/>
        </el-form-item>
        <el-form-item label="置顶排序" prop="topRank">
          <el-input v-model="dataForm.topRank"/>
        </el-form-item>
        <el-form-item label="标签" prop="name">
          <el-input v-model="dataForm.name"/>
        </el-form-item>
        <el-form-item label="开启文章推荐" prop="openRelated">
          <el-switch v-model="dataForm.openRelated" :active-value="1" :inactive-value="0" active-text="开" inactive-text="关"	/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button v-if="dialogStatus=='create'" type="primary" @click="createData">确定</el-button>
        <el-button v-else type="primary" @click="updateData">确定</el-button>
      </div>
    </el-dialog>
    <el-dialog :visible.sync="contentDialogVisible" title="资讯详情">
      <div v-html="contentDetail"/>
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
import { listNews, createNews, updateNews, deleteNews, createNewsType, getNewsType } from '@/api/news'
import { createStorage, uploadPath } from '@/api/storage'
import BackToTop from '@/components/BackToTop'
import Editor from '@tinymce/tinymce-vue'
import Pagination from '@/components/Pagination' // Secondary package based on el-pagination
import { getToken } from '@/utils/auth'

export default {
  name: 'News',
  components: { BackToTop, Editor, Pagination },
  data() {
    // var validateScope = (rule, value, callback) => {
    //   if (value === '1') {
    //     callback(new Error('请输入密码'))
    //   } else {
    //     if (this.ruleForm2.checkPass !== '') {
    //       this.$refs.ruleForm2.validateField('checkPass')
    //     }
    //     callback()
    //   }
    // }
    return {
      uploadPath,
      list: undefined,
      tabs: [],
      total: 0,
      listLoading: true,
      listQuery: {
        page: 1,
        limit: 20,
        info_title: undefined,
        sort: 'add_time',
        order: 'desc'
      },
      dataForm: {
        // id: undefined,
        clsId: '',
        infoTitle: undefined,
        infoShortTitle: undefined,
        infoDescription: '',
        author: '',
        infoMainImg: undefined,
        content: '',
        showIndex: '',
        scope: '0',
        assignPhone: '',
        topRank: '',
        name: '',
        openRelated: 0
      },
      rules: {
        infoTitle: [
          { required: true, message: '资讯标题不能为空', trigger: 'blur' }
        ],
        infoShortTitle: [
          { required: true, message: '资讯子标题不能为空', trigger: 'blur' }
        ],
        infoDescription: [
          { required: true, message: '描述不能为空', trigger: 'blur' }
        ],
        author: [
          { required: true, message: '作者不能为空', trigger: 'blur' }
        ],
        topRank: [
          { required: true, message: '置顶排序不能为空', trigger: 'blur' }
        ]
      },
      typeForm: {
        clsName: '',
        clsIcon: 'https://avatar-static.segmentfault.com/852/246/852246081-5b15227f8c800_big64',
        showIndex: 0,
        indexLimit: ''
      },
      typeRules: {
        clsName: [
          { required: true, message: '分类名不能为空', trigger: 'blur' }
        ],
        indexLimit: [
          { required: true, message: '首页展示条数不能为空', trigger: 'blur' }
        ]
      },
      contentDetail: '',
      contentDialogVisible: false,
      dialogFormVisible: false,
      dialogFormVisible2: false,
      dialogStatus: '',
      textMap: {
        update: '编辑',
        create: '创建'
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
    this.getTab()
  },
  methods: {
    getTab() {
      getNewsType()
        .then(response => {
          const data = response.data.data
          console.log(data)
          this.tabs = data.items
        })
        .catch(() => {
        })
    },
    showCreateTabDialog() {
      this.dialogFormVisible2 = true
    },
    createTab() {
      this.$refs['typeForm'].validate(valid => {
        if (valid) {
          createNewsType(this.typeForm)
            .then(response => {
              console.log(response)
              // this.list.unshift(response.data.data)
              this.dialogFormVisible2 = false
              this.$notify.success({
                title: '成功',
                message: '创建资讯分类成功'
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
    // resetForm() {
    //   this.dataForm = {
    //     infoTitle: undefined,
    //     infoShortTitle: undefined,
    //     infoDescription:'',
    //     author:'',
    //     infoMainImg: undefined,
    //     content:'',
    //     showIndex:0,
    //     scope:'0',
    //     assignPhone:'',
    //     topRank:'',
    //     tags:'',
    //     openRelated:''
    //   }
    // },
    handleCreate() {
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].resetFields()
        // this.$refs['dataForm'].clearValidate()
      })
    },
    uploadPicUrl: function(response) {
      this.dataForm.infoMainImg = response.data.url
    },
    createData() {
      this.$refs['dataForm'].validate(valid => {
        if (valid) {
          const { name, content, author, ...tabInfo } = this.dataForm
          const obj = {
            tabInfo,
            content: {
              author,
              content
            },
            tagName: {
              name
            }
          }
          console.log(this.dataForm)
          console.log(name)
          console.log(content)
          console.log(author)
          console.log(tabInfo)
          console.log(obj)
          createNews(obj)
            .then(response => {
              console.log(response)
              // this.list.unshift(response.data.data)
              // this.dialogFormVisible = false
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
          'infoTitle',
          'content',
          'infoMainImg',
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
