var playerItem = "<li><img src='../img/tshirt19.png'>%player</li>";		//球员元素（在球队列表中）
var pickingLeftItem = "<div class='pickingLeftItem'>%player</div>";		//候选区左侧球员选项
var pickingRightItem = "<div class='pickingRightItem'>%player</div>"; 	//候选区右侧球员选项
var playerReg = "%player";	//替换规则

var playerListStr = '[{"name":"王大雷"},{"name":"黄博文"},{"name":"郜林"},{"name":"高拉特"},{"name":"李学鹏"},{"name":"冯潇霆"},{"name":"金英权"},{"name":"郑智"},{"name":"于汉超"},{"name":"张琳芃"},{"name":"王大雷"},{"name":"黄博文"},{"name":"郜林"},{"name":"高拉特"},{"name":"李学鹏"},{"name":"冯潇霆"},{"name":"金英权"},{"name":"郑智"},{"name":"于汉超"},{"name":"张琳芃"},{"name":"王大雷"},{"name":"黄博文"},{"name":"郜林"},{"name":"高拉特"},{"name":"李学鹏"},{"name":"冯潇霆"},{"name":"金英权"},{"name":"郑智"},{"name":"于汉超"},{"name":"张琳芃"}]';
var playerArray;  //所有球员数组
var pickingArray = new Array(); //处在候选区的球员数组
var pc = 0;

var drawResult = {
		A:[],
		B:[],
		C:[],
		D:[]
};

var resetCode; //重置验证码
var submitCode; //提交验证码

//把一个球员添加到一个球队
var addPlayer = function(playerTeam, playerName){
	var ulId = "#team" + playerTeam;
	var newPlayerItem = playerItem.replace(playerReg, playerName);
	var obj = $(ulId);
	if(obj){
		var originHTML = obj.html();
		obj.html(originHTML+newPlayerItem);
	}
	
	if(playerTeam == "A"){
		drawResult.A.push(playerName);
	}else if(playerTeam == "B"){
		drawResult.B.push(playerName);
	}else if(playerTeam == "C"){
		drawResult.C.push(playerName);
	}else if(playerTeam == "D"){
		drawResult.D.push(playerName);
	}
};

//重置分队结果
var resetPlayer = function(){
	//清除已分队数据
	var teamArray = ["A", "B", "C", "D"];
	for(i=0; i<teamArray.length; i++){
		ulId = "#team" + teamArray[i];
		var obj = $(ulId);
		if(obj){
			obj.html("");
		}
	}
	
	//清除候选区数据
	var obj = $("#pickingPlayer");
	if(obj){
		obj.html("");
	}
	if(pickingArray.length != 0){
		pickingArray.splice(0, pickingArray.length);//清空【候选球员】数组
	}
	
	//全局指针复位
	pc = 0;
	
	//解禁【选人】按钮
	$("#pick").attr("disabled", false);
	
	//复位验证码输入框
	$("#resetCodeInput").val("");
	$("#submitCodeInput").val("");
	
	drawResult = {A:[], B:[], C:[], D:[]};
};

//选出四位球员
var pickPlayer = function(){
	var obj = $("#pickingPlayer");
	if(obj){
		if(pc == playerArray.length){
			$("#pickWarnning").modal("toggle");
			return;
		}
		var maxPick = pc+4;
		if(pickingArray.length != 0){
			pickingArray.splice(0, pickingArray.length);//清空【候选球员】数组
		}
		for(i=pc;i<maxPick && i<playerArray.length;i++){
			var playerName = playerArray[i].name;
			if(i%2==0){//left
				htmlStr = pickingLeftItem.replace(playerReg, playerName);
			}else{//right
				htmlStr = pickingRightItem.replace(playerReg, playerName);
			}
			pickingArray.push(playerName);//把当前球员添加到【候选球员】数组
			obj.html(obj.html()+htmlStr);
			pc++;
		}
		$("#pick").attr("disabled", true);
	}
};

//随机派到队伍
var drawPlayer = function(){
	if(pickingArray.length != 0){ //进行分组
		var pickedArray = [0, 0, 0, 0];
		var teamArray = ["A", "B", "C", "D"];
		for(i=0; i<pickingArray.length; i++){
			var k = getRandomNum(0, pickedArray.length-1);
			while(pickedArray[k] == 1){
				k = getRandomNum(0, pickedArray.length-1);
			}
			pickedArray[k] = 1;
			addPlayer(teamArray[k], pickingArray[i]);
		}
		
		//清除候选区数据
		var obj = $("#pickingPlayer");
		if(obj){
			obj.html("");
		}
		if(pickingArray.length != 0){
			pickingArray.splice(0, pickingArray.length);//清空【候选球员】数组
		}
		
		//解禁【选人】按钮
		$("#pick").attr("disabled", false);
		
		
	}else{
		$("#drawWarnning").modal("toggle");
	}
};

var checkSubmitCode = function(){
	if(submitCode == Number($("#submitCodeInput").val().trim())){//submitCode判断通过
		$("#submitWarnning").modal("hide");
		submitDraw();
		submitCode = 0;
	}else{
		$("#submitCodeInput").tooltip('show');
		$("#submitCodeInput").focus();
	}
}

//提交结果
var submitDraw = function(){
	var drawResultJson = JSON.stringify(drawResult);
	alert(drawResultJson);
};

var clickSubmit = function(){
	if(($("#teamA").html().trim() != "") || ($("#teamB").html().trim() != "") ||
			($("#teamC").html().trim() != "") || ($("#teamD").html().trim() != "")){//有可提交的数据
		$("#submitWarnning").modal("toggle");
		submitCode = 12345679;
//		submitCode = getRandomNum(1000, 9999);
//		$("#submitCode").html(submitCode);
	}else{//无可重置的数据
		$("#submitErrorWarnning").modal("toggle");
	}
}


var clickReset = function(){
	if(($("#teamA").html().trim() != "") || ($("#teamB").html().trim() != "") ||
			($("#teamC").html().trim() != "") || ($("#teamD").html().trim() != "") ||
				($("#pickingPlayer").html().trim() != "")){//有可重置的数据
		$("#resetWarnning").modal("toggle");
		resetCode = getRandomNum(1000, 9999);
		$("#resetCode").html(resetCode);
	}else{//无可重置的数据
		$("#resetErrorWarnning").modal("toggle");
	}
}

var checkResetCode = function(){
	if(resetCode == Number($("#resetCodeInput").val().trim())){//resetCode判断通过
		$("#resetWarnning").modal("hide");
		resetPlayer();
		resetCode = 0;
	}else{
		$("#resetCodeInput").tooltip('show');
		$("#resetCodeInput").focus();
	}
}

//获取随机数
var getRandomNum = function(min, max){   
	var Range = max - min;
	var Rand = Math.random();   
	return(min + Math.round(Rand * Range));   
};

$(document).ready(function(){
	playerArray = JSON.parse(playerListStr);
});
