import Vue from 'vue';
//配置路由
import VueRouter from 'vue-router'
Vue.use(VueRouter);
//1.创建组件
import Index from '@/views/index'
import Home from '@/views/home'
import Login from '@/views/login'
import NotFound from '@/views/404'
import UpdatePassword from '@/views/update-password'
import pay from '@/views/pay'
import register from '@/views/register'
import center from '@/views/center'
    import gongyingshang from '@/views/gongyingshang/list'
    import wuzixuqiu from '@/views/modules/wuzixuqiu/list'
    // import wuzijuanzeng from '@/views/modules/wuzijuanzeng/list'
    import yonghu from '@/views/yonghu/list'
    // import diqu from '@/views/modules/diqu/list'
    // import yingjiwuzi from '@/views/modules/yingjiwuzi/list'
    // import wuziruku from '@/views/modules/wuziruku/list'
    // import wuzichoucuo from '@/views/modules/wuzichoucuo/list'
    import wuzileixing from '@/views/wuzileixing/list'
    // import wuzichuku from '@/views/modules/wuzichuku/list'


//2.配置路由   注意：名字
const routes = [{
    path: '/index',
    name: '首页',
    component: Index,
    children: [{
      // 这里不设置值，是把main作为默认页面
      path: '/',
      name: '首页',
      component: Home,
      meta: {icon:'', title:'center'}
    }, {
      path: '/updatePassword',
      name: '我的审批',
      component: UpdatePassword,
      meta: {icon:'', title:'updatePassword'}
    }, {
      path: '/pay',
      name: '支付',
      component: pay,
      meta: {icon:'', title:'pay'}
    }, {
      path: '/center',
      name: '个人信息',
      component: center,
      meta: {icon:'', title:'center'}
    }
      ,{
	path: '/threshold',
        name: '风险阈值设置',
        component: gongyingshang
      }
      ,{
	path: '/wuzixuqiu',
        name: '风险等级划分',
        component: wuzixuqiu
      }
    //   ,{
	// path: '/wuzijuanzeng',
    //     name: '物资捐赠',
    //     component: wuzijuanzeng
    //   }
      ,{
	path: '/yonghu',
        name: '風險信息',
        component: yonghu
      }
    //   ,{
	// path: '/diqu',
    //     name: '地区',
    //     component: diqu
    //   }
    //   ,{
	// path: '/yingjiwuzi',
    //     name: '应急物资',
    //     component: yingjiwuzi
    //   }
    //   ,{
	// path: '/wuziruku',
    //     name: '物资入库',
    //     component: wuziruku
    //   }
    //   ,{
	// path: '/wuzichoucuo',
    //     name: '物资筹措',
    //     component: wuzichoucuo
    //   }
      ,{
	path: '/wuzileixing',
        name: '配送中心信息管理',
        component: wuzileixing
      }
    //   ,{
	// path: '/wuzichuku',
    //     name: '物资出库',
    //     component: wuzichuku
    //   }
    ]
  },
  {
    path: '/login',
    name: 'login',
    component: Login,
    meta: {icon:'', title:'login'}
  },
  {
    path: '/register',
    name: 'register',
    component: register,
    meta: {icon:'', title:'register'}
  },
  {
    path: '/',
    name: '首页',
    redirect: '/index'
  }, /*默认跳转路由*/
  {
    path: '*',
    component: NotFound
  }
]
//3.实例化VueRouter  注意：名字
const router = new VueRouter({
  mode: 'hash',
  /*hash模式改为history*/
  routes // （缩写）相当于 routes: routes
})

export default router;
