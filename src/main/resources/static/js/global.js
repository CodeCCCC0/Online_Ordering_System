let header1 = new Vue({
    el: "#hd1",
    data: {
        titleHref: "/TakeoutSystem/index.html",
        personalPage: "/TakeoutSystem/personal.html",
        cartPage: "/TakeoutSystem/myCart.html",
        orderPage: "/TakeoutSystem/myOrder.html",
        isSign: 0,
        username: null,
        searchText: "",
        isShow: false
    },
    methods: {
        showSet: function (){
            this.isShow = true;
        },
        closeSet: function (){
            setTimeout(()=>{
                this.isShow = false;
            }, 3000);
        },
        getSignStatus: function (){
            axios({
                method: "post",
                url: "/TakeoutSystem/getsignstatus",
                headers: {
                    "Content-Type":"application/json"
                }
            }).then((res)=>{
                if(res.data!==null && res.data!=="" && res.data!==undefined){
                    this.username = res.data;
                    this.isSign = 1;
                }else{
                    this.isSign = 0;
                }
            }, (err)=>{
                console.log("error: " + err);
            });
        },
        search: function (){
            let text = this.searchText;
            if(text!=="" && text!==null && text!==undefined){
                window.location.href = "/TakeoutSystem/searchFoods.html?search=" + text;
            }
        },
        signOut: function (){
            axios({
                method: "get",
                url: "/TakeoutSystem/signout",
                headers: {
                    "Content-Type":"application/json"
                }
            }).then((res)=>{
                console.log("signout");
                window.location.href("/TakeoutSystem/index.html");
            }, (err)=>{
                console.log("error: " + err);
            });
        }
    },
    mounted: function (){
        this.getSignStatus();
        let searchText = new URL(window.location.href).searchParams.get("search");
        if(searchText!=null || searchText!=="" || searchText!==undefined){
            this.searchText = searchText;
        }
    }
});

let footer1 = new Vue({
    el: "#ft",
    data: {
        fixed: 0,
    },
    methods: {
        isFixed: function (){
            let bodyClientHeight = document.body.clientHeight;
            let innerHeight = window.innerHeight;
            console.log(innerHeight - bodyClientHeight);
            if((innerHeight - bodyClientHeight)<120){
                this.fixed = 0;
            }else{
                this.fixed = 1;
            }
        }
    },
    mounted: function (){
        this.isFixed();
    }
});