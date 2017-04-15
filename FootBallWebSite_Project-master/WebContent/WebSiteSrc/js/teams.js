/*
 * 1、进入页面读取全量球员数据 --DONE
 * 2、数据存放到本地数组allplayers(存放一个个的球员对象) --DONE
 * 3、遍历数组内的球员对象,加入到teamData，每加入一个totalabi和numOfPlayer增加相应数值 --DONE
 * 4、遍历每个teamData，将队员名加入到table内，更新总实力到table内
 * 5、柱状图表--考虑实现
 * 
 * 
 */
// 图形区域设置
// echart的参数
var barWidth = 10; // 柱条宽度
var team1Color = 'rgb(12,142,207)'; //'#4cb749';
var team2Color = 'rgb(223,77,0)'; //'#FC3E10';
var team3Color = 'rgb(77,184,73)'; //'#4cb749';
var team4Color = 'rgb(245,200,18)'; //'#FC3E10';
var gridleft = '2%';
var gridright = '9%';
var gridbottom = '1%';
var textcolor = 'rgb(51,122,183)';
var teamChart = echarts.init(document.getElementById('chartArea'));

// 球员参数
var playerArray = new Array(); // 存放所有球员数据

//球员对象构造函数,记录所有的能力
function playerData() {
	this.playername = "empty";
	this.isCaptain = false; //是否队长

	this.totalabi = 0; //总实力
	this.body_abi = 50; //体质能力
	this.tech_abi = 50; //技术能力
	this.spec_abi = 50; //特殊能力
	this.attack_abi = 50; //进攻能力
	this.defence_abi = 50; //防守能力	

	this.team = "" //队伍
	this.department = "empty"; //部门
}

//队伍构造函数，记录每个队伍的人员和总能力
function teamData() {
	this.totalabi = 0; //总实力
	this.bodyabi = 0;
	this.techabi = 0;
	this.specabi = 0;
	this.attackabi = 0;
	this.defenceabi = 0;
	this.numOfPlayer = 0; //队员数目
	this.player = new Array(); //记录队员名
}

/*
 * 遍历数组内的球员对象,加入到teamData，
 * 每加入一个球员：
 * 	1、totalabi和numOfPlayer增加相应数值
 *	2、各项数值增加
 *
 */

var team1 = new teamData();
var team2 = new teamData();
var team3 = new teamData();
var team4 = new teamData();

function setTeam(Array) {

	for (key in Array) {

		var player = Array[key];

		if (player['team'] == 'A队') {
			team1.numOfPlayer++;
			team1.player.push(player['playername']);
			team1.totalabi += player['totalabi'];
			team1.bodyabi += player['body_abi'];
			team1.techabi += player['tech_abi'];
			team1.specabi += player['spec_abi'];
			team1.attackabi += player['attack_abi'];
			team1.defenceabi += player['defence_abi'];
		}

		if (player['team'] == 'B队') {
			team2.numOfPlayer++;
			team2.player.push(player['playername']);
			team2.totalabi += player['totalabi'];
			team2.bodyabi += player['body_abi'];
			team2.techabi += player['tech_abi'];
			team2.specabi += player['spec_abi'];
			team2.attackabi += player['attack_abi'];
			team2.defenceabi += player['defence_abi'];

		}

		if (player['team'] == 'C队') {
			team3.numOfPlayer++;
			team3.player.push(player['playername']);
			team3.totalabi += player['totalabi'];
			team3.bodyabi += player['body_abi'];
			team3.techabi += player['tech_abi'];
			team3.specabi += player['spec_abi'];
			team3.attackabi += player['attack_abi'];
			team3.defenceabi += player['defence_abi'];
		}

		if (player['team'] == 'D队') {
			team4.numOfPlayer++;
			team4.player.push(player['playername']);
			team4.totalabi += player['totalabi'];
			team4.bodyabi += player['body_abi'];
			team4.techabi += player['tech_abi'];
			team4.specabi += player['spec_abi'];
			team4.attackabi += player['attack_abi'];
			team4.defenceabi += player['defence_abi'];
		}

		//加入html.ul菜单中
		addPlayerToTeam(player['team'], player['playername'], player['isCaptain']);

	}

	//计算平均值
	team1.totalabi = parseInt(team1.totalabi / team1.numOfPlayer);
	team1.bodyabi = parseInt(team1.bodyabi / team1.numOfPlayer);
	team1.techabi = parseInt(team1.techabi / team1.numOfPlayer);
	team1.specabi = parseInt(team1.specabi / team1.numOfPlayer);
	team1.attackabi = parseInt(team1.attackabi / team1.numOfPlayer);
	team1.defenceabi = parseInt(team1.defenceabi / team1.numOfPlayer);

	team2.totalabi = parseInt(team2.totalabi / team2.numOfPlayer);
	team2.bodyabi = parseInt(team2.bodyabi / team2.numOfPlayer);
	team2.techabi = parseInt(team2.techabi / team2.numOfPlayer);
	team2.specabi = parseInt(team2.specabi / team2.numOfPlayer);
	team2.attackabi = parseInt(team2.attackabi / team2.numOfPlayer);
	team2.defenceabi = parseInt(team2.defenceabi / team2.numOfPlayer);

	team3.totalabi = parseInt(team3.totalabi / team3.numOfPlayer);
	team3.bodyabi = parseInt(team3.bodyabi / team3.numOfPlayer);
	team3.techabi = parseInt(team3.techabi / team3.numOfPlayer);
	team3.specabi = parseInt(team3.specabi / team3.numOfPlayer);
	team3.attackabi = parseInt(team3.attackabi / team3.numOfPlayer);
	team3.defenceabi = parseInt(team3.defenceabi / team3.numOfPlayer);

	team4.totalabi = parseInt(team4.totalabi / team4.numOfPlayer);
	team4.bodyabi = parseInt(team4.bodyabi / team4.numOfPlayer);
	team4.techabi = parseInt(team4.techabi / team4.numOfPlayer);
	team4.specabi = parseInt(team4.specabi / team4.numOfPlayer);
	team4.attackabi = parseInt(team4.attackabi / team4.numOfPlayer);
	team4.defenceabi = parseInt(team4.defenceabi / team4.numOfPlayer);


//		console.log("team1:");
//		console.log(team1);
//		console.log("team2:");
//		console.log(team2);
//		console.log("team3:");
//		console.log(team3);
//		console.log("team4:");
//		console.log(team4);

	var teamChart_option = {
		title: {
			text: '',
			subtext: ''
		},
		tooltip: {
			trigger: 'axis',
			axisPointer: {
				type: 'shadow'
			}
		},
		legend: {
			//		data: ['zidane', 'kfzx'],
			//		itemHeight: 10

		},
		grid: {
			left: gridleft,
			right: gridright,
			bottom: gridbottom,
			containLabel: true
		},
		xAxis: {
			max: 100,
			type: 'value',
			boundaryGap: [0, 1]
		},
		yAxis: {
			type: 'category',
			data: ['特殊', '防守', '进攻', '体质', '技术', '总实力']

		},
		series: [{
			name: 'A队',
			type: 'bar',
			data: [team1.specabi, team1.defenceabi, team1.attackabi, team1.bodyabi, team1.techabi, team1.totalabi],
			barWidth: barWidth,
			itemStyle: {
				normal: {
					color: team1Color
				}
			}
		}, {
			name: 'B队',
			type: 'bar',
			data: [team2.specabi, team2.defenceabi, team2.attackabi, team2.bodyabi, team2.techabi, team2.totalabi],
			barWidth: barWidth,
			itemStyle: {
				normal: {
					color: team2Color
				}

			}
		}, {
			name: 'C队',
			type: 'bar',
			data: [team3.specabi, team3.defenceabi, team3.attackabi, team3.bodyabi, team3.techabi, team3.totalabi],
			barWidth: barWidth,
			itemStyle: {
				normal: {
					color: team3Color
				}
			}
		}, {
			name: 'D队',
			type: 'bar',
			data: [team4.specabi, team4.defenceabi, team4.attackabi, team4.bodyabi, team4.techabi, team4.totalabi],
			barWidth: barWidth,
			itemStyle: {
				normal: {
					color: team4Color
				}

			}
		}]
	};
	teamChart.setOption(teamChart_option);


}

// ajax 获取所有球员的属性 , 生成每队人员列表和实力分析图		
function GetAllPlayerData() {
	$.ajax({
		//提交数据的类型 POST GET
		type: "POST",
		//提交的网址
		url: clubserver.URL + "A3GetPlayerData",
		//提交的数据
		data: {
			joinflag: 1
		},
		//返回数据的格式
		datatype: "html", //"xml", "html", "script", "json", "jsonp", "text".
		//在请求之前调用的函数
		beforeSend: function() {
			// $("#msg").html("logining");
		},
		//成功返回之后调用的函数            
		success: function(data) {
			//			console.log('成功返回数据-->compareabi.js');
		},
		//调用执行后调用的函数
		complete: function(XMLHttpRequest, textStatus) {
			//alert(XMLHttpRequest.responseText); //XMLHttpRequest.responseText是返回的信息，用这个来放JSON数据
			try {
				var jsonObject = JSON.parse(XMLHttpRequest.responseText);
				var playerDataJson = jsonObject['playerlist'];
				for (var key in playerDataJson) {
					var pD = new playerData()
					pD.playername = key;
					var playObject = playerDataJson[key]; // 取出对应的属性JSON

					pD.totalabi = parseInt(playObject['ability']);
					pD.body_abi = parseInt(playObject['body_abi']);
					pD.tech_abi = parseInt(playObject['tech_abi']);
					pD.spec_abi = parseInt(playObject['spec_abi']);
					pD.attack_abi = parseInt(playObject['attack_abi']);
					pD.defence_abi = parseInt(playObject['defence_abi']);

					pD.team = playObject['team'];
					pD.department = playObject["department"];
					pD.isCaptain = playObject['captain'] == 1 ? true : false;

					playerArray.push(pD); // 球员数值加入数组

				}

				//				console.log(playerArray);

				setTeam(playerArray);

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

var playerItem = "<li><img src='../img/tshirt19.png'>%player</li>"; //球员元素（在球队列表中）
var playerItem_captian = "<li><img src='../img/captain.png'>%player</li>";
var playerReg = "%player"; //普通球员替换规则
//把一个球员添加到一个球队
var addPlayerToTeam = function(playerTeam, playerName, isCaptain) {
	var ulId = "#team" + playerTeam.substring(0, 1);
	var newPlayerItem;
	//	console.log("isCaptain=" + isCaptain);
	if (isCaptain) {
		newPlayerItem = playerItem_captian.replace(playerReg, playerName);
	} else {
		newPlayerItem = playerItem.replace(playerReg, playerName);
	}
	var obj = $(ulId);
	if (obj) {
		var originHTML = obj.html();
		obj.html(originHTML + newPlayerItem);
	}

};

//设置队伍底色
$('#teamNameA').css({
	'background-color': team1Color
})
$('#teamNameB').css({
	'background-color': team2Color
})
$('#teamNameC').css({
	'background-color': team3Color
})
$('#teamNameD').css({
	'background-color': team4Color
})


// 设置点击球员事件--链接过去球员属性页面，
// 入参=球员名
var ulItem = document.querySelectorAll("ul");
//console.log(ulItem);
for (key in ulItem) {
	console.log(ulItem[key]);
	ulItem[key].addEventListener('click',selectPlayer,false);
}

function selectPlayer(event) {
	var selectedPlayer = event.target;
	var pngLocal = selectedPlayer.innerHTML.indexOf("png");
	var playername = selectedPlayer.innerHTML.substring(31);
//	console.log(selectedPlayer.innerHTML);
//	console.log(playername);
	document.location.href = '../pages/playerabi.jsp?name='+playername.toString();
	localStorage.setItem('lastpage','teams.html');

}


