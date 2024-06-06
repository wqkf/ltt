<template>
  <div style="padding: 20px;">
    <!-- 搜索栏 -->
    <el-form :inline="true" class="demo-form-inline" :model="book" ref="book">
      <el-form-item label="书籍名称">
        <el-input v-model="name" placeholder="书籍名称"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">查询</el-button>
        <el-button type="success" @click="open(null)">新增</el-button>
      </el-form-item>
    </el-form>
    <!-- 数据表格 -->
    <el-table :data="tableData" stripe style="width: 100%">
      <el-table-column v-if="false" prop="id" label="书籍名称" width="180">
      </el-table-column>
      <el-table-column v-if="false" prop="categoryId" label="类型名称" width="180">
      </el-table-column>
      <el-table-column  prop="name" label="书籍名称" width="180">
      </el-table-column>
      <el-table-column prop="actor" label="书籍作者" width="180">
      </el-table-column>
      <el-table-column prop="img" label="书籍封面" width="180">
      </el-table-column>
      <el-table-column prop="jianjie" label="简介">
      </el-table-column>
      <el-table-column prop="title" label="封面标题">
      </el-table-column>
      <el-table-column prop="categoryName" label="书籍类型">
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

        <el-form-item label="书籍名称" :label-width="formLabelWidth">
          <el-input v-model="book.name" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="简介" :label-width="formLabelWidth">
          <el-input v-model="book.jianjie" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="书籍作者" :label-width="formLabelWidth" prop="actor">
          <el-input v-model="book.actor" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="封面" :label-width="formLabelWidth" prop="img">
          <el-input v-model="book.img" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="封面标题" :label-width="formLabelWidth" prop="title">
          <el-input v-model="book.title" autocomplete="off"></el-input>
        </el-form-item>

        <el-form-item label="书籍类型" :label-width="formLabelWidth" prop="categoryId">
        <el-select v-model="book.categoryId" placeholder="请选择书籍类别">
            <el-option :key="'key_'+by.id" v-for="by in booktypes" :label="by.name" :value="by.id">
            </el-option>
          </el-select>
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
      name: '',
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
        name: '文学类'
      }, {
        id: 2,
        name: '学术类'
      }, {
        id: 3,
        name: '非小说类散文'
      }, {
        id: 4,
        name: '小说类'
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
  mounted(){
    let params= {
      "page": 1
    }
    this.query(params)
  }
  ,
  methods: {
    del(idx, row) {
      this.$confirm('确定要删除id为' + row.id + '的信息吗, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        let url = "home/deleteBook?bid=" + row.id;
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
            let params = {
              name: this.name,
              // num: this.num,
              // rows: r,
              page: this.page
            }
            this.query(params);
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
      let url = "home/save";
      if (this.title == '编辑窗体') {
        url = "home/update";
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
        name: '',
        jianjie: '',
        categoryId: '',
        actor: '',
        img: '',
        title: '',
      };

    },
    open(idx, row) {
      //打开添加/编辑书籍信息的窗体
      this.dialogFormVisible = true;
      if (row) {
        this.title = '编辑窗体';
        this.book.id = row.id;
        this.book.name = row.name;
        this.book.categoryId = row.categoryId;
        this.book.jianjie = row.jianjie;
        this.book.title = row.title;
        this.book.img = row.img;
        this.book.actor = row.actor;
      }
    },
    query(params) {
      //通用查询方法
      let url = "home/page";
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
        name: this.name,
        num: this.num,
        category: this.category,
        stockTime: this.stockTime
      }
      this.query(params);

    },
    handleSizeChange(r) {
      console.log("当前展示的记录数为" + r + "条")
      let params = {
        name: this.name,
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
      this.page = p;
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
