/*
 * created by kfzx-qiusd 2016-03-02
 * 
 */

var departmentlist = document.getElementById("departmentlist");
departmentlist.addEventListener('click', selectDepartment, false);

var submitinput = document.getElementById("submitinput");
submitinput.addEventListener('click', submitCheck, false);


//实现点击部门list后更新部门button的文字
function selectDepartment(event) {
	var selectedlist = event.target; // 获取点击目标
	document.getElementById("mydepartment").innerHTML = selectedlist.innerHTML;
	document.getElementById("mydepartment").setAttribute('tag', selectedlist.innerHTML);
}

//点击头像选择按钮--弹出模态框
$('#selectimg').click(function() {
	$('#userimgmodal').modal('show')

});

//头像处理
var aImg = document.querySelectorAll('img[tag]');
var selectedImg;	//选择的头像
for (i = 0; i < aImg.length; i++) {
	aImg[i].onclick = function() {
		for (i = 0; i < aImg.length; i++) {
			aImg[i].className = "";	// 清空所有
		}
		this.className = "current"
		selectedImg = this.src.substring(this.src.indexOf('face'));		
	}
}
$('#selectimgBtn').click(function(){
	$('#selectedimgId').attr('src','../img/userimg/'+selectedImg);
	document.getElementById("selectimg").innerHTML = '重新选择头像';
})

//提交时检查合法性
function submitCheck(event) {
	
	var userface = document.getElementById("selectimg").innerHTML; //选择头像
	console.log(userface);
	var userdepartment = document.getElementById("mydepartment").getAttribute('tag');
	var username = document.getElementById("nameinput").value;
	var userpassword = document.getElementById("passwordinput").value;
	
	if (userface.indexOf('重新') == -1) {
		alert("请选择头像！");
	} else if (userdepartment == "选择部门") {
		alert("请选择部门！");
	} else if (username == "") {
		alert("请输入用户名!");
	} else if (userpassword == "") {
		alert("请输入密码!");
	} else {
		//调用B1-注册接口---牟宁;		
		var result = confirm("您的注册信息为:\n\n用户名：" + username + "\n密码：" + userpassword + "\n部门：" + userdepartment + "\n\n用户名不要写错噢!")
		if (result) {
			var base64dealer = new Base64();
			var base64Password = base64dealer.encode(userpassword); //Base64加密处理
			//alert("base64 encode:" + base64Password);
			//base64Password = base64md.decode(base64Password); //Base64解密处理
			//alert("base64 decode:" + base64Password);
			RegisterPost(userdepartment, username, base64Password);

		} else {
			alert("取消了，请重新确认");
		}

	}
}

// ajax的post方法:
// Register的post方法，调用B1接口
function RegisterPost(deparment, name, password) {
	$.ajax({
		//提交数据的类型 POST GET
		type: "POST",
		//提交的网址--改为调用B1接口
		url: clubserver.URL + "RegisterServlet",
		//提交的数据
		data: {
			deparment: deparment,
			photo: selectedImg,
			name: name,
			password: password
		},
		//返回数据的格式
		datatype: "html", //"xml", "html", "script", "json", "jsonp", "text".
		//在请求之前调用的函数
		beforeSend: function() {
			//$("#msg").html("Registering");
		},
		//成功返回之后调用的函数            
		success: function(data) {
			//$("#msg").html(decodeURI(data));
		},
		//调用执行后调用的函数
		complete: function(XMLHttpRequest, textStatus) {
			//alert(XMLHttpRequest.responseText); //XMLHttpRequest.responseText是返回的信息，用这个来放JSON数据
			try {
				var jsonObject = JSON.parse(XMLHttpRequest.responseText);
				if (jsonObject["retcode"] == "0") {
					console.log("注册成功，跳转到个人属性页面"); // 跳转时
					localStorage.setItem('userimg', selectedImg);
					localStorage.setItem('playername', name);
					localStorage.setItem('loginflag', '1');
					document.location.href = '../pages/myabbs.html';
				} else {
					alert('注册失败:已存在同名用户，请在您姓名后加数字或字母，比如"张三A"');
				}

			} catch (e) {
				e.message;
				console.log("register error=" + e.message);
				alert("返回信息=>" + XMLHttpRequest.responseText + "\n=>无法转换为JSON");
			}
		},
		//调用出错执行的函数
		error: function() {
			alert("调用登陆接口失败，请检查网络环境");
		}
	});
}

