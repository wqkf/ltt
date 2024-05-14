<template>
  <div style="padding: 20px;">
    <!-- 数据表格 -->
    <el-table :data="tableData" stripe style="width: 100%">
      <el-table-column prop="heat" label="温度" width="180">
      </el-table-column>
      <el-table-column prop="humidity" label="湿度" width="180">
      </el-table-column>
      <el-table-column v-if="role!=3" prop="num" label="存储数量">
      </el-table-column>
      <el-table-column v-if="role!=3" prop="stockTime" label="入库时间">
      </el-table-column>
      <el-table-column v-if="role==3" prop="roadStatus" label="道路状态">
      </el-table-column>
      <el-table-column prop="level" label="风险等级">
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  data() {
    return {
      bookname: '',
      tableData: [{"heat":"大于阈值10","humidity":"大于阈值10","num":"大于阈值10","stockTime":"大于阈值10天","roadStatus":"","level":"蓝"},
        {"heat":"大于阈值20","humidity":"大于阈值20","num":"大于阈值20","stockTime":"大于阈值20天","roadStatus":"","level":"黄"},
        {"heat":"大于阈值30","humidity":"大于阈值30","num":"大于阈值30","stockTime":"大于阈值30天","roadStatus":"","level":"橙"},
        {"heat":"大于阈值40","humidity":"大于阈值40","num":"大于阈值40","stockTime":"大于阈值40天","roadStatus":"无路","level":"红"}],
      role: '',
      page: 1,
      rows: 10,
      total: 0,
      //0727
      title: '新增窗体',
      dialogFormVisible: false,
      booktypes: [{
        id: 1,
        name: '言情'
      }, {
        id: 2,
        name: '玄幻'
      }, {
        id: 3,
        name: '历史'
      }, {
        id: 4,
        name: '都市'
      }],
      formLabelWidth: '100px',
      book: {
        id: '',
        bookname: '',
        price: '',
        booktype: ''
      },
      rules: {
        bookname: [{
          required: true,
          message: '请输入书籍名称',
          trigger: 'blur'
        }],
        price: [{
          required: true,
          message: '请输入价格',
          trigger: 'blur'
        }],
        booktype: [{
          required: true,
          message: '请选择类别',
          trigger: 'blur'
        }]
      }
    }
  },
  mounted() {
    this.role = this.$storage.get("role");
  },
  methods: {
    del(idx, row) {
      this.$confirm('确定要删除id为' + row.id + '的书籍信息吗, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        let url = this.axios.urls.BOOK_DEL;
        this.axios.get(url, {
          params: {
            id: row.id
          }
        }).then(resp => {
          if (resp.data.success) {
            this.$message({
              message: resp.data.msg,
              type: 'success'
            });
            this.query({});
          } else {
            this.$message({
              message: resp.data.msg,
              type: 'error'
            });
          }
        }).catch(err => {

        });
      })

    },
    doSub() {
      //新增或者编辑提交到后台的方法
      let url = this.axios.urls.BOOK_ADD;
      if (this.title == '编辑窗体') {
        url = this.axios.urls.BOOK_EDIT;
      }
      let params = {
        id: this.book.id,
        bookname: this.book.bookname,
        price: this.book.price,
        booktype: this.book.booktype

      }

      this.$refs['book'].validate((valid) => {
        if (valid) {//校验
          //验证通过，发送请求
          this.axios.post(url, params).then(resp => {
            console.log(resp);
            if (resp.data.success) {
              this.$message({
                message: resp.data.msg,
                type: 'success'
              });
              this.clear();
              this.query({});
            } else {
              this.$message.error(resp.data.msg);
            }
          }).catch(err => {})

        } else {

          console.log('error submit!!');
          return false;

        }
      });


    },
    clear() {
      //清空表单方法
      this.title = '新增窗体'
      this.dialogFormVisible = false;
      this.book = {
        id: '',
        bookname: '',
        price: '',
        booktype: ''
      };

    },
    open(idx, row) {
      //打开添加/编辑书籍信息的窗体
      this.dialogFormVisible = true;
      if (row) {
        this.title = '编辑窗体',
            this.book.id = row.id;
        this.book.bookname = row.bookname;
        this.book.price = row.price;
        this.book.booktype = row.booktype;
      }
    },
    query(params) {
      //通用查询方法
      let url = this.axios.urls.BOOK_LIST;
      this.axios.get(url, {
        params: params
      }).then(resp => {
        console.log(resp);
        this.tableData = resp.data.rows;
        this.total = resp.data.total;
      }).catch(err => {

      })
    },
    onSubmit() {
      //搜索方法
      let params = {
        bookname: this.bookname
      }
      this.query(params);

    },
    handleSizeChange(r) {
      console.log("当前展示的记录数为" + r + "条")
      let params = {
        bookname: this.bookname,
        rows: r,
        page: this.page
      }
      this.query(params)

    },
    handleCurrentChange(p) {
      console.log("当前展示的页码是第" + p + "页")
      let params = {
        bookname: this.bookname,
        page: p,
        rows: this.rows
      }
      this.query(params)
    }

  }
}
</script>

<style>
</style>
