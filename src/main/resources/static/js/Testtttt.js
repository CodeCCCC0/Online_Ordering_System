let app = new Vue({//SELECT * FROM fruits WHERE f_name REGEXP '[]';
    el: "#search",
    data: {
        searchText: "",
        food: [],
        foods: [],
        search: [],
        orderStatus: [],
        isCollect: 0
    },
    methods: {
        showFoods: function (offset, limit, regexp){
            console.log(offset + ", " + limit + ", " + regexp);
        },
        search: function (){
            //获取搜索框中的搜索词
            var text = this.searchText;
            if(text==""||text==undefined||text==null){
                //如果单击搜索按钮时搜索栏为空，则显示全部菜品
                this.showFoods();
            }else{
                //因为获取全部菜品信息的函数showFoods()在进入搜索页，页面加载完毕时已被执行过，这里不必再次执行
                var reg = new RegExp("[" + text + "]");//根据搜索词创建正则表达式
                var results = new Array();//存储搜索结果
                //直接在showFoods()的结果数组foods中匹配，将匹配上的元素存入results中
                for(var i=0; i<foods.length; i++){
                    if(reg.test(foods[i].foodName)){
                        results.push(foods[i]);
                    }
                }
                console.log(results);
                if(results.length==0){
                    alert("无结果！");
                }else{
                    //将搜索结果放入foods数组中，vue会将其渲染到页面上
                    this.foods = results;
                }
            }
        },
        isCollect: function (){
            var uname = new URL(window.location.href).searchParams.get("uname");
            //查询collection表获取该菜品的收藏状态
            axios({
                method: "post",
                url: "http://localhost:8080/TakeoutSystem/isCollect",
                headers: {
                    "Content-Type": "application/json;charset=utf-8",
                },
                data: JSON.stringify({
                    "foodId":this.food.foodId,	//菜品编号
                    "uname":uname,		//用户名
                }),
            }).then((res) => {
                if(res.data===1){
                    /*
                    isCollect通过vue的v-bind与界面上收藏按钮的class属性匹配，当其为1时，会
                    应用class中的notAllowed样式，禁用收藏按钮
                     */
                    this.isCollect = 1;
                }else{
                    this.isCollect = 0;
                }
            }, (err) => {
                alert(err);
            });
        },
        addToCollection: function (){
            axios({
                method: "post",
                url: "http://localhost:8080/TakeoutSystem/addToCollect",
                headers: {
                    "Content-Type": "application/json;charset=utf-8",
                },
                data: JSON.stringify({
                    "food_id":this.food.foodId,		//菜品编号
                    "food_name":this.food.foodName,	//菜品名
                    "uname":uname,			//收藏者的用户名
                }),
            }).then((res) => {
                this.isCollect = 1;//禁用收藏按钮
                window.location.reload();//刷新页面
            }, (err) => {
                alert(err);
            });
        },
        //菜品下架
        delFood: function (foodId, foodName){
            //下架前弹窗询问，确认后执行程序
            var check = confirm("确定要下架" + foodId + ": " + foodName + "吗？");
            if(check){
                //根据菜品编号查找并从表中删除对应菜品
                axios({
                    method: "post",
                    url: "http://localhost:8080/TakeoutSystem/delfood",
                    header: {
                        "Content-Type": "application/json; charset=utf-8"
                    },
                    data: JSON.stringify(foodId),
                }).then((res) => {
                    //成功后刷新页面
                    window.location.reload();
                }, (err) => {
                    alert(err);
                });
            }
        },
        /*
        接单操作
            点击接单按钮后，系统将订单ID以及该订单在集合中的下标传入，将订单ID作为参数传入后端，修改订单状态。
        成功后刷新页面，接收订单状态的数组orderStatus中对应订单的状态码会变为1，同时页面列表中被接单订单的
        接单按钮的通过v-bind绑定，依orderStatus[i]状态决定启用的class样式会被启用，从而修改该订单接单按钮的
        文本为取消接单，修改按钮click所绑定的方法为取消接单的方法。
         */
        orderReceive: function (orderId, item){
            axios({
                method: "post",
                url: "http://localhost:8080/TakeoutSystem/orderReceive",
                header: {
                    "Content-Type": "application/json; charset=utf-8"
                },
                data: JSON.stringify(orderId),
            }).then((res) => {
                window.location.reload();
            }, (err) => {
                alert(err);
            });
        },
        /*
        取消接单操作
            取消接单操作即是接单操作的反向操作，按下取消接单按钮后，修改状态码为0，从而禁用class样式，启用按钮
        并修改按钮文本为接单。
         */
        cancelReceive: function (orderId, item){
            axios({
                method: "post",
                url: "http://localhost:8080/TakeoutSystem/cancelReceive",
                header: {
                    "Content-Type": "application/json; charset=utf-8"
                },
                data: JSON.stringify(orderId),
            }).then((res) => {
                window.location.reload();
            }, (err) => {
                alert(err);
            });
        }
    },
    mounted: function (){
        let page = parseInt(new URL(window.location.href).searchParams.get("page"));
        let searchText = new URL(window.location.href).searchParams.get("search");
        if(isNaN(page) && searchText!==null){
            this.showFoods(0, this.quantityPerPage);
        }else if(searchText!==null){
            this.showFoods((page-1)*this.quantityPerPage, this.quantityPerPage);
        }else{
            this.showFoods((page-1)*this.quantityPerPage, this.quantityPerPage, searchText);
        }
        this.getFoodsCount();
    }
});
/*
    //取消接单操作
		function cancelReceive(order_id){
			console.log(order_id);
			$.ajax({
				type:"post",
				contentType:"application/json;charset=utf-8",
				url:"http://localhost:8080/Takeout_System/product/orderStatusUpdate.do",
				data:JSON.stringify({
					"order_id":order_id,
					"orderStatus":"未接单",
				}),
				success:function(data){
					if(searchText.value){
						refresh();
						searchOrder();
					}else{
						refresh();
					}
				},
				error:function(xhr, status, error){
					alert("[" + status + "]" + error);
				},
			});
		}
//菜品下架
		function delProduct(product_id, product_name){
			//下架前弹窗询问，确认后执行程序
			var con = confirm("确定要下架 " + product_id + ": " + product_name + " 吗？");
			if(con){
				//根据菜品编号查找并从表中删除对应菜品
				$.ajax({
					type:"post",
					dataType:"json",
					contentType:"application/json;charset=utf-8",
					url:"http://localhost:8080/Takeout_System/product/delProduct.do",
					data:JSON.stringify(product_id),	//菜品编号
					success:function(data){
						//成功后刷新页面
						if(searchText.value){
							refresh();
							searchPro();
						}else{
							refresh();
						}
					},
					error:function(xhr, status, error){
						alert(error);
					},
				});
			}
		}
		//接单操作
		function orderReceive(order_id){
			console.log(order_id);
			$.ajax({
				type:"post",
				contentType:"application/json;charset=utf-8",
				url:"http://localhost:8080/Takeout_System/product/orderStatusUpdate.do",
				data:JSON.stringify({
					"order_id":order_id,
					"orderStatus":"已接单",
				}),
				success:function(data){
					if(searchText.value){
						refresh();
						searchOrder();
					}else{
						refresh();
					}
				},
				error:function(xhr, status, error){
					alert("[" + status + "]" + error);
				},
			});
		}
 */