<template>
  <div style="padding: 20px;">
    <!-- 搜索栏 -->
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item label="配送中心">
        <el-input v-model="address" placeholder="配送中心"></el-input>
      </el-form-item>
      <el-form-item label="时间">
        <el-input v-model="monitorTime" placeholder="时间"></el-input>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="onSubmit">查询</el-button>
      </el-form-item>
    </el-form>
    <!-- 数据表格 -->
    <el-table :data="tableData" stripe style="width: 100%">
      <el-table-column v-if="false" prop="id" label="zhujian">
      </el-table-column>
      <el-table-column prop="address" label="配送中心" width="180">
      </el-table-column>
      <el-table-column prop="heat" label="温度" width="180">
      </el-table-column>
      <el-table-column prop="humidity" label="湿度" width="180">
      </el-table-column>
      <el-table-column prop="num" label="粮食存储数量">
      </el-table-column>
      <el-table-column prop="stockTime" label="粮食存储时间">
      </el-table-column>
      <el-table-column prop="monitorTime" label="监测时间">
      </el-table-column>
      <el-table-column prop="level" label="风险等级">
      </el-table-column>
      <el-table-column v-if="role==4" label="操作">
        <template slot-scope="scope">
          <!-- {{scope.row}} -->
          <el-button size="mini" type="danger" @click="del(scope.$index, scope.row)">删除</el-button>
        </template>
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
      title: '新增窗体',
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
  },
  methods: {
    del(idx, row) {
      this.$confirm('确定要删除id为' + row.id + '的風險信息吗, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        let url = "risk/delete";
        this.$http({
          url: url,
          method: "post",
          data: row
        }).then(({data}) => {
          if (data && data.code === 0) {
            this.$message({
              message: "删除成功",
              type: 'success'
            });
            this.query({});
          } else {
            this.$message({
              message: data.msg,
              type: 'error'
            });
          }
        }).catch(err => {

        });
      })

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
      let url = "risk/page";
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
        address: this.address,
        monitorTime: this.monitorTime,
        role: 2
      }
      this.query(params);

    },
    handleSizeChange(r) {
      console.log("当前展示的记录数为" + r + "条")
      let params = {
        address: this.address,
        monitorTime: this.monitorTime,
        role: 2,
        rows: r,
        page: this.page
      }
      this.query(params)

    },
    handleCurrentChange(p) {
      console.log("当前展示的页码是第" + p + "页")
      let params = {
        address: this.address,
        monitorTime: this.monitorTime,
        role: 2,
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
