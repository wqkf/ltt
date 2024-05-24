<template>
  <div style="padding: 20px;">
    <!-- 数据表格 -->
    <el-table :data="tableData" stripe style="width: 100%">
      <el-table-column v-if="false" prop="id" label="zhujian">
      </el-table-column>
      <el-table-column prop="name" label="姓名" width="180">
      </el-table-column>
      <el-table-column prop="age" label="年龄" width="180">
      </el-table-column>
      <el-table-column prop="idNumber" label="身份证号">
      </el-table-column>
      <el-table-column prop="model" label="驾驶车型">
      </el-table-column>
      <el-table-column prop="carNumber" label="车牌号">
      </el-table-column>
    </el-table>
    <!--3. 分页条 -->
    <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="page"
                   :page-sizes="[10, 20, 30, 40]" :page-size="rows" layout="total, sizes, prev, pager, next, jumper" :total="total">
    </el-pagination>
  </div>
</template>

<script>
export default {
  data() {
    return {
      heat: '',
      humidity: '',
      role: '',
      num:  '',
      stockTime: '',
      tableData: [],
      page: 1,
      rows: 10,
      total: 0,
      //0727
      flag: '',
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
        category: '',
        num: '',
        address: ''
      },
      rules: {
        category: [{
          required: true,
          message: '请输入粮食种类',
          trigger: 'blur'
        }],
        num: [{
          required: true,
          message: '请输入存储数量',
          trigger: 'blur'
        }],
        stockTime: [{
          required: true,
          message: '请输入入库时间',
          trigger: 'blur'
        }]
      }
    }
  },
  mounted() {
    this.role = this.$storage.get("role");
    let params= {
      "page": 1
    }
    this.query(params)
  },
  methods: {
    getButtons(row) {
      const buttons = [];
      console.log(row)
      if (row.status == '已审批') {
        buttons.push({
          text: '已审批'
        });
      }
      if (row.status == '待审批') {
        buttons.push({
          text: '通过',
              onClick: (rowData) => {
            console.log('通过', rowData);
                let url = "approve/update";
                this.$http({
                  url: url,
                  method: "post",
                  data: rowData
                }).then(({data}) => {
                  if (data && data.code === 0) {
                    this.tableData = data.data.records;
                    this.total = data.data.total;
                    this.$message({
                      message: "设置成功",
                      type: "success",
                      duration: 1500,
                    });
                  } else {
                    this.$message.error(data.msg);
                  }

                }).catch(err => {

                })
                let params= {
                  "page": 1
                }
                this.query(params)
          },
        },{
          text: '拒绝',
          onClick: (rowData) => {
            let url = "approve/reject";
            this.$http({
              url: url,
              method: "post",
              data: rowData
            }).then(({data}) => {
              this.$message({
                message: "设置成功",
                type: "success",
                duration: 1500,
              });
            }).catch(err => {

            })
            let params= {
              "page": 1
            }
            this.query(params)
          },
        });
      }
      return buttons;
    },
    clear() {
      //清空表单方法
      this.title = '新增窗体'
      this.dialogFormVisible = false;
      this.book = {
        id: '',
        address: '',
        num: '',
        category: '',
        stockTime: ''
      };

    },
    open(idx, row) {
      //打开添加/编辑书籍信息的窗体
      this.dialogFormVisible = true;
      if (row) {
        this.title = '编辑窗体';
        this.book.id = row.id;
        this.book.address = row.address;
        this.book.category = row.category;
        this.book.num = row.num;
        this.book.stockTime = row.stockTime;
      }
    },
    query(params) {
      //通用查询方法
      let url = "yonghu/page";
      this.$http({
        url: url,
        method: "post",
        data: params
      }).then(({data}) => {
        console.log(data);
        this.tableData = data.data.records;
        this.total = data.data.total;
      }).catch(err => {

      })
    },
    onSubmit() {
      //搜索方法
      let params = {
        heat: this.heat,
        num: this.num,
        humidity: this.humidity,
        stockTime: this.stockTime
      }
      this.query(params);

    },
    handleSizeChange(r) {
      console.log("当前展示的记录数为" + r + "条")
      let params = {
        heat: this.heat,
        num: this.num,
        humidity: this.humidity,
        stockTime: this.stockTime,
        rows: r,
        page: this.page
      }
      this.query(params)

    },
    handleCurrentChange(p) {
      console.log("当前展示的页码是第" + p + "页")
      let params = {
        heat: this.heat,
        num: this.num,
        humidity: this.humidity,
        stockTime: this.stockTime,
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
