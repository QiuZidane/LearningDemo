/********************************
 * 欢迎界面
 *
 *********************************/
countDownTime();
// kfzx-wengzj
function countDownTime() {
	var nowtime = new Date;
	var endtime = new Date("2016/05/11,18:00:00");
	var lefttime = parseInt((endtime.getTime() - nowtime.getTime()) / 1000);
	var day = parseInt(lefttime / (24 * 60 * 60));
	var hour = parseInt(lefttime / (60 * 60) % 24);
	var minute = parseInt(lefttime / 60 % 60);
	var second = parseInt(lefttime % 60);
	hour = checkTime(hour);
	minute = checkTime(minute);
	second = checkTime(second);
	$('#day').html(day);
	$('#hour').html(hour);
	$('#minute').html(minute);
	//	$('#second').html(second);

	function checkTime(time) {
		if (time < 10) {
			time = '0' + time;
		}
		return time;
	}
}
setInterval(countDownTime, 1000);

// 每次进入页面访问数据库，获得player总人数，刷新参加人数统计
// ajax 获取所有球员的属性，进入页面时获取一次，生成球员列表 
function GetPlayerCount() {
	$.ajax({
		//提交数据的类型 POST GET
		type: "POST",
		//提交的网址
		url: clubserver.URL + "A3GetPlayerData",
		//提交的数据
		data: {
			joinflag: 0
		},
		//返回数据的格式
		datatype: "html", //"xml", "html", "script", "json", "jsonp", "text".
		//在请求之前调用的函数
		beforeSend: function() {
			// $("#msg").html("logining");
		},
		//成功返回之后调用的函数            
		success: function(data) {
			console.log('成功返回数据:welcomepage.js');
		},
		//调用执行后调用的函数
		complete: function(XMLHttpRequest, textStatus) {
			//alert(XMLHttpRequest.responseText); //XMLHttpRequest.responseText是返回的信息，用这个来放JSON数据
			try {
				var jsonObject = JSON.parse(XMLHttpRequest.responseText);
				var playercount = jsonObject['totalnum'];
				$('#CDjoins').html(playercount);

			} catch (e) {
				console.log("error=" + e.message);
				console.log("compareabi.js成功返回信息=>" + XMLHttpRequest.responseText + "\n=>无法转换为JSON");
			}
			// HideLoading();
		},
		//调用出错执行的函数
		error: function() {
			//请求出错处理
		}
	});
}

// 按钮事件，链接到注册判断页面
$('#joinBtn').click(function() {
	document.location.href = '../pages/loginNavigation.html';
});

// 背景图片变化功能
var teambg = document.getElementById('teambg');
var bgindex = 0;

// 透明度渐隐
function teamBgChange() {

	var bgopacity = 1;
	opacityReduce();

	function opacityReduce() {
		if (bgopacity > 0) {
			bgopacity -= 0.05;
			$('#teambg').css({
				'opacity': bgopacity
			});
			setTimeout(opacityReduce, 50);
		} else {
		}
	}
}

function teamBgShow() {
	// 先把透明度置为0
	var bgopacity = 0;
	$('#teambg').css({
		'opacity': bgopacity
	});
	
	// 按顺序读取球队图片
	if (bgindex < 9) {
		bgindex++;
	} else {
		bgindex = 1;
	}
	var bgname = "t" + bgindex + ".png";
	
	$('#teambg').css({
		'background': 'url(../img/' + bgname + ') no-repeat center center'
	});

	// 透明度慢慢恢复为1
	opacityIncrease();
	
	function opacityIncrease() {
		if (bgopacity < 1) {
			bgopacity += 0.05;
			$('#teambg').css({
				'opacity': bgopacity
			});
			setTimeout(opacityIncrease, 50);
		} else {}
	}
	
	setTimeout(teamBgChange,4500);
}

setTimeout(teamBgShow,1000);
setInterval(teamBgShow, 7500);