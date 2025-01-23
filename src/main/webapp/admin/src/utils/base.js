const base = {
    get() {
        return {
            url : "http://localhost:8080/feipinmaimaihuishouguanlixitong/",
            name: "feipinmaimaihuishouguanlixitong",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/feipinmaimaihuishouguanlixitong/front/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "废品买卖回收管理系统"
        } 
    }
}
export default base
