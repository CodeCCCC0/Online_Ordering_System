let selectColumn = new Vue({
    el: "#outerDiv",
    data: {
        loopNum: ["一", "二", "三", "四", "五"],
        searchText: "",
        foods: [],
        pages: 0,
        resultsCount: 0,
        quantityPerPage: 20,
    },
    methods: {
        //检索
        searchFoods: async function (searchWord, offset, limit){
            //通过get方式将参数传到后端，在后端通过MySQL的REGEXP匹配检索词与菜品名，OFFSET和LIMIT获取内容
            let url = "/TakeoutSystem/searchfoods?search=" + searchWord + "&offset=" + offset + "&limit=" + limit;
            let foods = await axios.get(url)
                .then((response)=>{
                        return response.data;
                    },
                    (err)=>{
                        console.log("error: " + err);
                    });
            console.log(foods);
            if(foods!==null){
                //将结果放入foods集合中，vue将其渲染到页面上
                this.foods = foods;
            }else{
                alert("列表为空！");
            }
        },
        //获取检索结果条数
        getResultsCount: function (searchWord){
            //与检索一样，通过REGEXP匹配检索词与菜品名，后通过MySQL的COUNT()函数计算检索结果中food_id的数量
            axios({
                method: "get",
                url: "/TakeoutSystem/getresultscount?search=" + searchWord,
                headers: {
                    "Content-Type": "application/json;charset=utf-8"
                }
            }).then((res) => {
                console.log(res);
                //将结果条数展示到页面上
                this.resultsCount = res.data;
                //计算分页数
                this.pages = Math.ceil(res.data/this.quantityPerPage);
            }, (err) => {
                console.log(err);
            });
        }
    },
    mounted: function (){
        /*
        分页栏中的页码点击后都会将关键词以及页码通过get方式传来，在页面加载完毕后从url中获取页码以及检索的关键字信息，
        其中quantityPerPage为每一页展示的条目数量，这里设置为20
         */
        let page = parseInt(new URL(window.location.href).searchParams.get("page"));
        let searchText = new URL(window.location.href).searchParams.get("search");
        this.searchText = searchText;
        if (isNaN(page)) {
            //页码为空时展示第一页的内容
            this.searchFoods(searchText, 0, this.quantityPerPage);
        } else {
            //计算出每一页的起始条目下标，从该条目后一条开始往后二十条为本页所展示的内容
            this.searchFoods(searchText, (page - 1) * this.quantityPerPage, this.quantityPerPage);
        }
        this.getResultsCount(searchText);
    }
});