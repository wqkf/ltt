const menu = {
    list() {
        return [
            {
                "backMenu": [
                    {
                        "child": [
                            {
                                "appFrontIcon": "cuIcon-present",
                                "buttons": [
                                    "新增",
                                    "查看",
                                    "修改",
                                    "删除"
                                ],
                                "menu": "书籍信息管理",
                                "menuJump": "列表",
                                "tableName": "wuzileixing"
                            }
                        ],
                        "menu": "书籍信息"
                    }
                ],
                "frontMenu": [],
                "hasBackLogin": "是",
                "hasBackRegister": "否",
                "hasFrontLogin": "否",
                "hasFrontRegister": "否",
                "roleName": 1,
                "tableName": "users"
            }
        ]    }
}
export default menu;
