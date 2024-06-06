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
    import wuzijuanzeng from '@/views/wuzijuanzeng/list'
    import yonghu from '@/views/yonghu/list'
    import diqu from '@/views/diqu/list'
    import yingjiwuzi from '@/views/yingjiwuzi/list'
    import wuziruku from '@/views/wuziruku/list'
    import wuzichoucuo from '@/views/wuzichoucuo/list'
    import wuzileixing from '@/views/wuzileixing/list'
    import wuzichuku from '@/views/wuzichuku/list'
    import threshold from '@/views/threshold/list'
    import message from '@/views/message/list'
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
    },{
      path: '/message',
      name: '信息中心',
      component: message
    }
      ,{
	path: '/threshold',
        name: '风险分析与预警',
        component: gongyingshang
      }
      ,{
	path: '/wuzixuqiu',
        name: '风险等级划分',
        component: wuzixuqiu
      }
      ,{
	path: '/wuzijuanzeng',
        name: '我的审批',
        component: wuzijuanzeng
      }
      ,{
	path: '/yonghu',
        name: '風險信息',
        component: yonghu
      }
      ,{
	path: '/diqu',
        name: '配送中心信息管理',
        component: diqu
      }
      ,{
	path: '/yingjiwuzi',
        name: '风险预警设置',
        component: yingjiwuzi
      }
      ,{
	path: '/wuziruku',
        name: '风险信息',
        component: wuziruku
      }
      ,{
	path: '/wuzichoucuo',
        name: '物资筹措',
        component: wuzichoucuo
      }
      ,{
	path: '/wuzileixing',
        name: '书籍信息管理',
        component: wuzileixing
      }
      ,{
	path: '/wuzichuku',
        name: '风险等级划分',
        component: wuzichuku
      },{
        path: '/threshold3',
        name: '设置阈值',
        component: threshold
      }
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
