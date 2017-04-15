//   ***登陆导航页面***
// 	Step1、获取判断localstorage.playername
//  Step2、判断localstorage.loginflag(1-已登陆 0-未登陆) 
// 	Step3、如果login=1,送playername查询能力值，跳转到myabbs.html页面
// 	Step4、如果login=0,跳转到register.html页面 


//localStorage.setItem('playername','zidane');
//localStorage.setItem('loginflag','1');
//localStorage.clear();


var playername = localStorage.playername;
var loginflag = localStorage.loginflag;

if (loginflag == undefined || loginflag == 0) { //跳转到register.html页面
	console.log('loginflag is undefined --> 未登录')
	document.location.href = '../pages/login.html';
} else if (loginflag == 1) { // 送playername查询能力值，跳转到myabbs.html页面
	console.log('loginflag==1,已登录');
	document.location.href = '../pages/myabbs.html?name='+playername;
	//	GetPlayerAbility(playername);	//通常loginflag有值，playername也有值
} else {
	console.log('loginNavigation处理失败:loginflag=' + loginflag);
}
