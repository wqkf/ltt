const base = {
    get() {
        return {
            url : "http://localhost:8080/springboots8124/",
            name: "springboots8124",
            // 退出到首页链接
            indexUrl: ''
        };
    },
    getProjectName(){
        return {
            projectName: "粮食应急物流风险预警系统"
        } 
    }
}
export default base
