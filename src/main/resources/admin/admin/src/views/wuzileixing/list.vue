<template>
  <div style="padding: 20px;">
    <!-- 搜索栏 -->
    <el-form :inline="true" class="demo-form-inline" :model="book" ref="book">
      <el-form-item label="地址信息">
        <el-input v-model="address" placeholder="地址信息"></el-input>
      </el-form-item>
      <el-form-item label="粮食种类">
        <el-input v-model="category" placeholder="粮食种类"></el-input>
      </el-form-item>
      <el-form-item label="存储数量">
        <el-input v-model="num" placeholder="存储数量"></el-input>
      </el-form-item>
      <el-form-item label="入库时间">
        <el-input v-model="stockTime" placeholder="入库时间"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">查询</el-button>
        <el-button type="success" @click="open(null)">新增</el-button>
      </el-form-item>
    </el-form>
    <!-- 数据表格 -->
    <el-table :data="tableData" stripe style="width: 100%">
      <el-table-column v-if="false" prop="id" label="地址信息" width="180">
      </el-table-column>
      <el-table-column prop="address" label="地址信息" width="180">
      </el-table-column>
      <el-table-column prop="category" label="粮食种类" width="180">
      </el-table-column>
      <el-table-column prop="num" label="粮食存储数量">
      </el-table-column>
      <el-table-column prop="stockTime" label="粮食入库时间">
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <!-- {{scope.row}} -->
          <el-button size="mini" @click="open(scope.$index, scope.row)">编辑</el-button>
          <el-button size="mini" type="danger" @click="del(scope.$index, scope.row)">删除</el-button>
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
          <el-input v-model="book.address" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="粮食种类" :label-width="formLabelWidth" prop="category">
          <el-input v-model="book.category" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="存储数量" :label-width="formLabelWidth" prop="num">
          <el-input v-model="book.num" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="入库时间" :label-width="formLabelWidth" prop="stockTime">
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
      address: '',
      category: '',
      num:  '',
      stockTime: '',
      tableData: [],
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
  methods: {
    del(idx, row) {
      this.$confirm('确定要删除id为' + row.id + '的配送中心信息吗, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        let url = "stock/delete";
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
    doSub() {
      //新增或者编辑提交到后台的方法
      let url = "stock/save";
      if (this.title == '编辑窗体') {
        url = "stock/update";
      }
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
              this.query({});
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
      let url = "stock/query";
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
        num: this.num,
        category: this.category,
        stockTime: this.stockTime
      }
      this.query(params);

    },
    handleSizeChange(r) {
      console.log("当前展示的记录数为" + r + "条")
      let params = {
        address: this.address,
        num: this.num,
        category: this.category,
        stockTime: this.stockTime,
        rows: r,
        page: this.page
      }
      this.query(params)

    },
    handleCurrentChange(p) {
      console.log("当前展示的页码是第" + p + "页")
      let params = {
        address: this.address,
        num: this.num,
        category: this.category,
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
