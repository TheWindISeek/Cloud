// window.alert("hello js")
//
// document.write("hello js")
//
// console.log("hello js")
//
// var age = 20
//
// let num = 3.12
//
// const PI = 3.14
//
// var und
//
// alert(typeof age)
//
// alert(typeof  num)
//
// alert(typeof  PI)
//
// alert(typeof und)

// var x = true;
//
// function on() {
//     document.write("on");
// }
//
// function off() {
//     document.write("off");
// }
//
// setInterval(function () {
//     x = !x;
//     if(x) {
//         on();
//     } else {
//         off();
//     }
// }, 1000)

// console.log(location.href);
//
// document.write("3s 跳转到首页...")
// setTimeout(function (){
//     //注意这里需要加上https://
//     //否则会被识别为 当前路径下的页面 而不是 将要你去跳转到的页面
//     location.href = "https://www.baidu.com"
// },3000)

//由于我是在开头用的 所以会导致我无法访问到更后面的数据 因此这里返回的是null
var id = document.getElementById("username");
console.log("id");
console.log(id);
// //DOM方式绑定
// id.onclick = function () {
//     alert("username");
// }
var ii = 20;
var input = document.getElementsByTagName("input");
console.log("tag name");
console.log(input);

var a = document.querySelector("#username");
console.log("query selector");
console.log(a);

var hobbys = document.getElementsByName("hobby");
console.log("name");
console.log(hobbys);

var clss = document.getElementsByClassName("cls");
console.log("class name")
console.log(clss);
console.log("\n\n\n\n")
// for(let i = 0; i < name.length; ++i) {
//     alert(name[i])
// }

function on () {
    alert("on!!");
}

