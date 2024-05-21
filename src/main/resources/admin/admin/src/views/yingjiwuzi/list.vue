<template>
  <div style="padding: 20px;">
    <!-- 数据表格 -->
    <el-table :data="tableData" stripe style="width: 100%">
      <el-table-column v-if="false" prop="id" label="zhujian">
      </el-table-column>
      <el-table-column prop="heat" label="温度" width="180">
      </el-table-column>
      <el-table-column prop="humidity" label="湿度" width="180">
      </el-table-column>
      <el-table-column prop="num" label="粮食存储数量">
      </el-table-column>
      <el-table-column prop="stockTime" label="粮食存储时间">
      </el-table-column>
<!--      <el-table-column prop="road" label="道路状态">-->
<!--      </el-table-column>-->
<!--      <el-table-column prop="road" label="道路状态">-->
<!--      </el-table-column>-->
      <el-table-column prop="address" label="储备点">
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button size="mini" @click="open(scope.$index, scope.row)">编辑</el-button>
        </template>
      </el-table-column>
    </el-table>
    <!--3. 分页条 -->
    <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="page"
                   :page-sizes="[10, 20, 30, 40]" :page-size="rows" layout="total, sizes, prev, pager, next, jumper" :total="total">
    </el-pagination>

    <!-- 4.新增/编辑窗体 -->
    <el-dialog :title="title" :visible.sync="dialogFormVisible" @close="clear()">
      <el-form :model="book" :rules="rules" ref="book">

        <el-form-item v-if="false" label="编号" :label-width="formLabelWidth">
          <el-input v-model="book.id" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="地址信息" :label-width="formLabelWidth">
          <el-input v-model="book.address" readonly autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="温度" :label-width="formLabelWidth" prop="heat">
          <el-input v-model="book.heat" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="湿度" :label-width="formLabelWidth" prop="humidity">
          <el-input v-model="book.humidity" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="粮食存储数量" :label-width="formLabelWidth" prop="num">
          <el-input v-model="book.num" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="粮食存储时间" :label-width="formLabelWidth" prop="stockTime">
          <el-input v-model="book.stockTime" autocomplete="off"></el-input>
          <!--          <el-select v-model="book.booktype" placeholder="请选择书籍类别">-->
          <!--            <el-option :key="'key_'+by.id" v-for="by in booktypes" :label="by.name" :value="by.name"></el-option>-->
          <!--          </el-select>-->
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="clear()">取 消</el-button>
        <el-button type="primary" @click="doSub()">确 定</el-button>
      </div>
    </el-dialog>
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
        category: '',
        num: '',
        address: ''
      },
      title: '设置阈值',
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
    this.role = 1;
    let params= {
      "role": this.role
    }
    this.query(params)
  },
  methods: {
    clear() {
      //清空表单方法
      this.dialogFormVisible = false;
      this.book = {
        id: '',
        address: '',
        num: '',
        heat: '',
        humidity: '',
        stockTime: ''
      };

    },
    doSub() {
      //新增或者编辑提交到后台的方法
      let url = "threshold/update";
      let params = {
        id: this.book.id,
        address: this.book.address,
        category: this.book.category,
        num: this.book.num,
        stockTime: this.book.stockTime,

      }

      this.$refs['book'].validate((valid) => {
        if (valid) {//校验
          //验证通过，发送请求
          this.$http({
            url: url,
            method: "post",
            data: this.book
          }).then(({ data }) => {
            console.log(data);
            if (data && data.code === 0) {
              this.$message({
                message: "提交成功",
                type: 'success'
              });
              this.clear();
              let params= {
                "role": this.role
              }
              this.query(params);
            } else {
              this.$message.error(data.msg);
            }
          }).catch(err => {})

        } else {

          console.log('error submit!!');
          return false;
        }
      });


    },
    open(idx, row) {
      //打开添加/编辑书籍信息的窗体
      this.dialogFormVisible = true;
      if (row) {
        this.book.humidity = row.humidity  ;
        this.book.id = row.id;
        this.book.address = row.address;
        this.book.heat = row.heat;
        this.book.num = row.num;
        this.book.stockTime = row.stockTime;
      }
    },
    query(params) {
      //通用查询方法
      let url = "threshold/query";
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
        role: this.role,
        rows: r,
        page: this.page
      }
      this.query(params)

    },
    handleCurrentChange(p) {
      console.log("当前展示的页码是第" + p + "页")
      let params = {
        role: this.role,
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