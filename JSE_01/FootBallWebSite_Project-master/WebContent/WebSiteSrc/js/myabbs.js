/********************************
 *  作者：kfzx-qiusd 
 *  说明：有空在研究深入JQ...
 *
 *********************************/
/*
 *  ========== 全局变量定义开始 ========== 
 * 
 */
var opacityvalue = 1; //这个透明度在两个提示框都用到，设置为全局变量

var modalshow = false; //是否显示确认提交的模态框

var descmodalshow = false; //提示选择参加联赛模态框是否有显示

var joinLeague = ""; //是否参加联赛，数据库字段是字符型，yes=参加 no=不参加

var playername = localStorage.playername; // 本地存储，获取用户名

//var timer1;
//var timer2;

// 体质的SLIDER
var bodeabbs1 = $('#bodeabbs1').slider()
	.on('slideStop', GetandCalPlayerAbilities)
	.data('slider');
var bodeabbs2 = $('#bodeabbs2').slider()
	.on('slideStop', GetandCalPlayerAbilities)
	.data('slider');
var bodeabbs3 = $('#bodeabbs3').slider()
	.on('slideStop', GetandCalPlayerAbilities)
	.data('slider');
var bodeabbs4 = $('#bodeabbs4').slider()
	.on('slideStop', GetandCalPlayerAbilities)
	.data('slider');

// 技术的SLIDER
var tech_abbs1 = $('#tech_abbs1').slider()
	.on('slideStop', GetandCalPlayerAbilities)
	.data('slider');
var tech_abbs2 = $('#tech_abbs2').slider()
	.on('slideStop', GetandCalPlayerAbilities)
	.data('slider');
var tech_abbs3 = $('#tech_abbs3').slider()
	.on('slideStop', GetandCalPlayerAbilities)
	.data('slider');
var tech_abbs4 = $('#tech_abbs4').slider()
	.on('slideStop', GetandCalPlayerAbilities)
	.data('slider');

// 特殊能力的SLIDER
var spec_abbs1 = $('#spec_abbs1').slider()
	.on('slideStop', GetandCalPlayerAbilities)
	.data('slider');
var spec_abbs2 = $('#spec_abbs2').slider()
	.on('slideStop', GetandCalPlayerAbilities)
	.data('slider');
var spec_abbs3 = $('#spec_abbs3').slider()
	.on('slideStop', GetandCalPlayerAbilities)
	.data('slider');

// 进攻能力的SLIDER
var attack_abbs1 = $('#attack_abbs1').slider()
	.on('slideStop', GetandCalPlayerAbilities)
	.data('slider');
var attack_abbs2 = $('#attack_abbs2').slider()
	.on('slideStop', GetandCalPlayerAbilities)
	.data('slider');
var attack_abbs3 = $('#attack_abbs3').slider()
	.on('slideStop', GetandCalPlayerAbilities)
	.data('slider');

// 防守能力的SLIDER
var defen_abbs1 = $('#defen_abbs1').slider()
	.on('slideStop', GetandCalPlayerAbilities)
	.data('slider');
var defen_abbs2 = $('#defen_abbs2').slider()
	.on('slideStop', GetandCalPlayerAbilities)
	.data('slider');
var defen_abbs3 = $('#defen_abbs3').slider()
	.on('slideStop', GetandCalPlayerAbilities)
	.data('slider');

// 获取大项的progressBar
var body_progress = document.getElementById("body_abi_pg");
var tech_progress = document.getElementById("tech_abi_pg");
var spec_progress = document.getElementById("spec_abi_pg");
var attack_progress = document.getElementById("attack_abi_pg");
var defence_progress = document.getElementById("defence_abi_pg");

//提交按钮事件
var joinBtn = document.getElementById("joinBtn");
joinBtn.addEventListener('click', submitABI, false);
var submitToServer = document.getElementById('submitToServer');
submitToServer.addEventListener('click', submitTS, false);

//确认框内按回车的处理
$('#submitModal').on('shown.bs.modal', function() {
	modalshow = true;
	document.getElementById('verifycodeinput').focus();
})

//参加联赛按钮未选择的处理
$('#descmodal').on('shown.bs.modal', function() {
	descmodalshow = true;
})

document.onkeydown = function(event) {
	if (event.keyCode == 13 && modalshow) {
		submitTS();
	}
	if (event.keyCode == 13 && descmodalshow) {
		$('#descmodal').modal('hide');
	}
}

// 参加联赛按钮设置，这里用了icheck插件
$('#icheckbtn1').iCheck({
	checkboxClass: 'icheckbox_square-blue',
	radioClass: 'iradio_square-blue',
	increaseArea: '100%', // optional
	labelHover: true,
	cursor: true
});
$('#icheckbtn2').iCheck({
	checkboxClass: 'icheckbox_square-blue',
	radioClass: 'iradio_square-blue',
	increaseArea: '100%', // optional
	labelHover: true,
	cursor: true
});
$('#joinlabel1').on('click', function() {
	$('#icheckbtn1').iCheck('check');
	joinLeague = "yes";

});
$('#joinlabel2').on('click', function() {
	$('#icheckbtn2').iCheck('check');
	joinLeague = "no";
	
});

//console.log(joinlabel);

/*
 *  ========== 全局变量定义结束 ========== 
 * 
 */

//球员能力对象,记录所有的能力   
var ability = {
	//大项:
	totalabi: 50, //总实力
	body_abi: 50, //体质能力
	tech_abi: 50, //技术能力
	spec_abi: 50, //特殊能力
	attack_abi: 50, //进攻能力
	defence_abi: 50, //防守能力
	//细项:
	speed: bodeabbs1.getValue(), //速度
	strength: bodeabbs2.getValue(), //强壮
	stamina: bodeabbs3.getValue(), //体能
	health: bodeabbs4.getValue(), //受伤抗性
	passing: tech_abbs1.getValue(), //传球
	touching: tech_abbs2.getValue(), //停球
	dribbling: tech_abbs3.getValue(), //盘带
	heading: tech_abbs4.getValue(), //头球
	minding: spec_abbs1.getValue(), //意志力
	rating: spec_abbs2.getValue(), //出勤率
	rating: spec_abbs3.getValue(), //团队意识
	shoot: attack_abbs1.getValue(), //射门
	offtheball: attack_abbs2.getValue(), //跑位
	creativity: attack_abbs3.getValue(), //创造力
	taking: defen_abbs1.getValue(), //抢断
	marking: defen_abbs2.getValue(), //盯人
	positioning: defen_abbs3.getValue() //防守站位

}

//  1、获取球员每个小项的能力
//	2、计算大项能力和总实力
//  每次拉动SLIDER，所有控件都会联动，目前暂不影响性能，后续分开控制。
function GetandCalPlayerAbilities(init) {
	//*****获取小项的能力*****
	ability.speed = bodeabbs1.getValue(); //速度
	ability.strength = bodeabbs2.getValue(); //强壮
	ability.stamina = bodeabbs3.getValue(); //体能
	ability.health = bodeabbs4.getValue(); //受伤抗性
	ability.passing = tech_abbs1.getValue(); //传球
	ability.touching = tech_abbs2.getValue(); //停球
	ability.dribbling = tech_abbs3.getValue(); //盘带
	ability.heading = tech_abbs4.getValue(); //头球
	ability.minding = spec_abbs1.getValue(); //意志力
	ability.teamwork = spec_abbs2.getValue(); //出勤率
	ability.rating = spec_abbs3.getValue(); //团队意识
	ability.shoot = attack_abbs1.getValue(); //射门
	ability.offtheball = attack_abbs2.getValue(); //跑位
	ability.creativity = attack_abbs3.getValue(); //创造力
	ability.taking = defen_abbs1.getValue(); //抢断
	ability.marking = defen_abbs2.getValue(); //盯人
	ability.positioning = defen_abbs3.getValue(); //防守站位

	//*****计算大项能力*****
	//权值在weight.js定义
	//体质属性，共四项
	var speed_w = weightfunc.speed_w; //速度权重值
	var strength_w = weightfunc.strength_w; //强壮权重值
	var stamina_w = weightfunc.stamina_w; //体能权重值
	var health_w = weightfunc.health_w; //受伤抗性	
	ability.body_abi = ability.speed * speed_w + ability.strength * strength_w + ability.stamina * stamina_w + ability.health * health_w;
	if (init == 0) { //初始化，主要是为了美观。。不然每次进入页面，体质属性计算出来都是52
		ability.body_abi = 50;
	} else {
		ability.body_abi = parseInt(ability.body_abi);
	}

	//技术属性，共四项
	var passing_w = weightfunc.passing_w; //传球权重值
	var touching_w = weightfunc.touching_w; //停球权重值
	var dribbling_w = weightfunc.dribbling_w; //盘带权重值
	var heading_w = weightfunc.heading_w; //头球权重值
	ability.tech_abi = ability.passing * passing_w + ability.touching * touching_w + ability.dribbling * dribbling_w + ability.heading * heading_w;
	//	ability.tech_abi.toFixed(2);
	ability.tech_abi = parseInt(ability.tech_abi);

	//特殊属性，共三项
	var minding_w = weightfunc.minding_w; //意志力权重值	
	var rating_w = weightfunc.rating_w; //出勤率权重值
	var teamwork_w = weightfunc.teamwork_w; //团队意识权重值
	ability.spec_abi = ability.minding * minding_w + ability.rating * rating_w + ability.teamwork * teamwork_w;
	ability.spec_abi = parseInt(ability.spec_abi);

	//进攻属性，共三项
	var shoot_w = weightfunc.shoot_w; //射门
	var offtheball_w = weightfunc.offtheball_w; //跑位
	var creativity_w = weightfunc.creativity_w; //创造力	
	var techOnAttack_w = weightfunc.techOnAttack_w; //技术在进攻属性权重		
	var bodyOnAttack_w = weightfunc.bodyOnAttack_w; // 体质在进攻属性权重
	ability.attack_abi = ability.shoot * shoot_w + ability.offtheball * offtheball_w + ability.creativity * creativity_w + ability.tech_abi * techOnAttack_w + ability.body_abi * bodyOnAttack_w;
	ability.attack_abi = parseInt(ability.attack_abi);

	//防守属性，共三项
	var taking_w = weightfunc.taking_w; //抢断
	var marking_w = weightfunc.marking_w; //盯人
	var positioning_w = weightfunc.positioning_w; //防守站位
	var techOnDef_w = weightfunc.techOnDef_w; // 技术在防守属性权重
	var bodyOnDef_w = weightfunc.bodyOnDef_w; // 体质在防守属性权重
	ability.defence_abi = ability.taking * taking_w + ability.marking * marking_w + ability.positioning * positioning_w + ability.tech_abi * techOnDef_w + ability.body_abi * bodyOnDef_w; //防守能力
	ability.defence_abi = parseInt(ability.defence_abi);

	//总能力，共五项
	var body_w = weightfunc.body_w; //体质
	var tech_w = weightfunc.tech_w; //技术
	var spec_w = weightfunc.spec_w; //特殊
	var attack_w = weightfunc.attack_w; //进攻
	var defence_w = weightfunc.defence_w; //防守
	ability.totalabi = ability.body_abi * body_w + ability.tech_abi * tech_w + ability.spec_abi * spec_w + ability.attack_abi * attack_w + ability.defence_abi * defence_w;
	ability.totalabi = parseInt(ability.totalabi);
	$('#abilityId').html(ability.totalabi);

	/*
	 * 
	 * 设置大项的属性条数值
	 * 
	 */
	body_progress.setAttribute('style', 'width: ' + ability.body_abi + '%');
	tech_progress.setAttribute('style', 'width: ' + ability.tech_abi + '%');
	spec_progress.setAttribute('style', 'width: ' + ability.spec_abi + '%');
	attack_progress.setAttribute('style', 'width: ' + ability.attack_abi + '%');
	defence_progress.setAttribute('style', 'width: ' + ability.defence_abi + '%');

	/*
	 * 
	 * 设置大项的属性条颜色
	 * 
	 */
	setProgessBarColor("body_abi_pg", ability.body_abi);
	setProgessBarColor("tech_abi_pg", ability.tech_abi);
	setProgessBarColor("spec_abi_pg", ability.spec_abi);
	setProgessBarColor("attack_abi_pg", ability.attack_abi);
	setProgessBarColor("defence_abi_pg", ability.defence_abi);

	var myChart = echarts.init(document.getElementById('highchartDiv'));
	var option = {
		title: {
			//text: '多雷达图'
		},
		tooltip: {
			trigger: 'axis'
		},
		legend: { //说明
			x: 'center',
			//data:['球员1'] // 标题，可省
		},
		radar: [{
			indicator: [{
				text: '技术',
				max: 110
			}, {
				text: '进攻',
				max: 110
			}, {
				text: '特殊',
				max: 110
			}, {
				text: '体质',
				max: 110
			}, {
				text: '防守',
				max: 110
			}],
			center: ['47.5%', '52%'],
			radius: 90, //半径长度
			startAngle: 90,
			splitNumber: 4,
			//			shape: 'circle',//默认按定点数
			name: {
				// formatter:'【{value}】', //文字格式
				textStyle: {
					color: '#72ACD1', //文字颜色
					fontSize: 14
				}

			},
			splitArea: {
				areaStyle: {
					color: ['rgba(114, 172, 209, 0.2)', 'rgba(114, 172, 209, 0.6)', 'rgba(114, 172, 209, 0.6)',
						'rgba(114, 172, 209, 0.8)', 'rgba(114, 172, 209, 0.8)', 'rgba(114, 172, 209, 1)'
					],
					shadowColor: 'rgba(0, 0, 0, 0.2)',
					shadowBlur: 20
				}
			},
		}],
		series: [{
			type: 'radar',
			tooltip: {
				trigger: 'item'
			},
			itemStyle: {
				normal: {
					areaStyle: {
						type: 'default'
					}
				}
			},
			data: [{
				value: [ability.tech_abi, ability.attack_abi, ability.spec_abi, ability.body_abi, ability.defence_abi], //对应='技术', '防守', '特殊', '体质', '进攻'
				name: playername,
				areaStyle: {
					normal: {
						color: 'rgba(200, 102, 99,0.7)' //能力覆盖区域颜色
					}
				}
			}]
		}]
	};
	myChart.setOption(option); //设置雷达图

	//	console.clear();	
	//	for (var key in ability) {
	//		if (ability[key] != 50) {
	//			console.log(key + "=" + ability[key]); //speed = xx
	//		}
	//	}

};

/*
 * 
 * 设置大项的属性条颜色
 * 
 */
function setProgessBarColor(abilityName, ability) {
	var level5 = '#d81919' //传奇
	var level4 = '#9900ff' //史诗
	var level3 = '#41aff9' //精良
	var level2 = '#5cb85c' //优秀
	var level1 = '#f0ad4e' //普通

	var progressBar = $('#' + abilityName);
	if (ability < 40) {
		progressBar.css({
			'background': level1
		});
	} else if (ability < 65) {
		progressBar.css({
			'background': level2
		});
	} else if (ability < 80) {
		progressBar.css({
			'background': level3
		});
	} else if (ability < 90) {
		progressBar.css({
			'background': level4
		});
	} else {
		progressBar.css({
			'background': level5
		});
	}
}

// 提示没有选择是否加入联赛
//function warningJoin() {
//	function changeToRed() {
//		$('#joinseasondesc').css({
//			'color': 'red'
//		});
//		$('#joinselectiong').css({
//			'color': 'red'
//		});
//
//	}
//
//	function changeToBlack() {
//		$('#joinseasondesc').css({
//			'color': 'black'
//		});
//		$('#joinselectiong').css({
//			'color': 'black'
//		});
//	}
//
//	timer1 = setInterval(changeToRed, 200);
//	timer2 = setInterval(changeToBlack, 400);
//
//
//
//}

//点击页面的提交按钮
function submitABI() {
	//判断是否已选择参加联赛情况
	if (document.getElementById('icheckbtn1').checked == false && document.getElementById('icheckbtn2').checked == false) {
		//		warningJoin();
		$('#descmodal').modal('show');
	} else {

		$('#submitModal').modal('show')

		// 模态框出现，将slider置为不可用
		setSliderStatus(false);

		// 模态框赋值
		var tableRows = document.getElementById('comformTable').tBodies[0].rows;
		tableRows[5].cells[1].innerHTML = ability.totalabi;
		tableRows[0].cells[1].innerHTML = ability.body_abi;
		tableRows[1].cells[1].innerHTML = ability.tech_abi;
		tableRows[2].cells[1].innerHTML = ability.spec_abi;
		tableRows[3].cells[1].innerHTML = ability.attack_abi;
		tableRows[4].cells[1].innerHTML = ability.defence_abi;

		//计算4位随机数
		var RADDOMCODE = parseInt(9999 - Math.random() * 10000);
		if (RADDOMCODE < 1000) {
			RADDOMCODE += 1000;
		}
		document.getElementById('verifycode').innerHTML = RADDOMCODE;
		document.getElementById("verifycodeinput").value = "";
	}

}

//模态框消失后，将slider置为enable
$('#submitModal').on('hidden.bs.modal', function() {
	setSliderStatus(true);
	modalshow = false;
})

//descmodal模态框消失后,
$('#descmodal').on('hidden.bs.modal', function() {
	descmodalshow = false;
})

//点击确认框的确认按钮
function submitTS() {
	var inputcode = document.getElementById('verifycodeinput').value;
	var verifycode = document.getElementById('verifycode').innerHTML;
	if (inputcode != verifycode) {
		$("#errordesc").html("验证码输入有误!");
		//清空输入
		document.getElementById("verifycodeinput").value = "";
		opacityvalue = 1;
		$("#errordesc").css({
			'opacity': opacityvalue
		});
		setTimeout(cleanTips, 2000);

	} else {
		SubmitPlayerAbi();
		//模态框消失后，将slider置为enable
		setSliderStatus(true);
		$('#submitModal').modal('hide');
		//清空输入
		document.getElementById("verifycodeinput").value = "";

	}

}

// ajax的post方法:
// 确认提交方法，调用A2接口
function SubmitPlayerAbi() {
	$.ajax({
		//提交数据的类型 POST GET
		type: "POST",
		//提交的网址
		url: clubserver.URL + "A2UpdatePlayer",
		//提交的数据
		data: {
			joinleague: joinLeague,
			playername: playername,
			totalabi: ability.totalabi,
			body_abi: ability.body_abi,
			tech_abi: ability.tech_abi,
			spec_abi: ability.spec_abi,
			attack_abi: ability.attack_abi,
			defence_abi: ability.defence_abi,
			speed: ability.speed,
			strength: ability.strength,
			stamina: ability.stamina,
			health: ability.health,
			passing: ability.passing,
			touching: ability.touching,
			dribbling: ability.dribbling,
			heading: ability.heading,
			minding: ability.minding,
			rating: ability.rating,
			shoot: ability.shoot,
			offtheball: ability.offtheball,
			creativity: ability.creativity,
			taking: ability.taking,
			marking: ability.marking,
			positioning: ability.positioning,
			photo:selectedImg

		},
		//返回数据的格式
		datatype: "html", //"xml", "html", "script", "json", "jsonp", "text".
		//在请求之前调用的函数
		beforeSend: function() {
			// $("#msg").html("logining");
		},
		//成功返回之后调用的函数            
		success: function(data) {
			try {
				setTimeout(function() {
					opacityvalue = 1;
					$("#submitResultDesc").css({
						'opacity': 1
					});
					$("#submitResultDesc").html("恭喜！您的数据已登记!");
					$("#submitResultDesc").css({
						'color': 'green'
					});

				}, 2000);

				setTimeout(cleanTips, 6000);
			} catch (e) {
				$("#submitResultDesc").html("发生错误，您的数据未登记，请联系【开发团队】->右上角!");
				$("#submitResultDesc").css({
					'color': 'red',
					'opacity': 1
				});
			}
		},
		//调用执行后调用的函数
		complete: function(XMLHttpRequest, textStatus) {
			//alert(XMLHttpRequest.responseText); //XMLHttpRequest.responseText是返回的信息，用这个来放JSON数据
			try {
				$("#submitResultDesc").html("提交成功! 后台处理中...");
				$("#submitResultDesc").css({
					'color': 'green'
				});
				opacityvalue = 1;
				$("#submitResultDesc").css({
					'opacity': 1
				});
				setTimeout(cleanTips, 1000);
			} catch (e) {
				$("#submitResultDesc").html("提交失败！请联系【开发团队】->右上角!");
				$("#submitResultDesc").css({
					'color': 'red',
					'opacity': 1
				});
			}
			// HideLoading();
		},
		//调用出错执行的函数
		error: function() {
			//请求出错处理
		}
	});
}

// ajax的post方法:
// 调用A1接口，查询球员属性
function GetAbility(name) {
	$.ajax({
		//提交数据的类型 POST GET
		type: "POST",
		//提交的网址
		url: clubserver.URL + "A1SearchPlayer", // clubserver.URL在constants.js内定义
		//提交的数据
		data: {
			name: (name == undefined) ? localStorage.playername : name
		},
		//返回数据的格式
		datatype: "html", //"xml", "html", "script", "json", "jsonp", "text".
		//在请求之前调用的函数
		beforeSend: function() {
			// $("#msg").html("logining");
		},
		//成功返回之后调用的函数            
		success: function(data) {
			//console.log('成功返回数据');
		},
		//调用执行后调用的函数
		complete: function(XMLHttpRequest, textStatus) {
			//			console.log('XMLHttpRequest.responseText>>>' + XMLHttpRequest.responseText); //XMLHttpRequest.responseText是返回的信息，用这个来放JSON数据
			try {
				var jsonObject = JSON.parse(XMLHttpRequest.responseText);

				bodeabbs1.setValue(parseInt(jsonObject["speed"]));
				bodeabbs2.setValue(parseInt(jsonObject["strength"]));
				bodeabbs3.setValue(parseInt(jsonObject["stamina"]));
				bodeabbs4.setValue(parseInt(jsonObject["health"]));
				tech_abbs1.setValue(parseInt(jsonObject["passing"]));
				tech_abbs2.setValue(parseInt(jsonObject["touching"]));
				tech_abbs3.setValue(parseInt(jsonObject["dribbling"]));
				tech_abbs4.setValue(parseInt(jsonObject["heading"]));
				spec_abbs1.setValue(parseInt(jsonObject["minding"]));
				spec_abbs2.setValue(parseInt(jsonObject["teamwork"]));
				spec_abbs3.setValue(parseInt(jsonObject["rating"]));
				attack_abbs1.setValue(parseInt(jsonObject["shoot"]));
				attack_abbs2.setValue(parseInt(jsonObject["offtheball"]));
				attack_abbs3.setValue(parseInt(jsonObject["creativity"]));
				defen_abbs1.setValue(parseInt(jsonObject["taking"]));
				defen_abbs2.setValue(parseInt(jsonObject["marking"]));
				defen_abbs3.setValue(parseInt(jsonObject["positioning"]));

				GetandCalPlayerAbilities(); //初始化能力值

				//非查询他人时，赋值
				if (name == undefined) {
					$('#usernameId').html(playername.toString());
					$('#departmentId').html(jsonObject["department"].toString());
				}

				//				console.log(playername);
				//				console.log(jsonObject["department"]);

			} catch (e) {
				console.log("error=" + e.message);
				//console.log("返回信息=>" + XMLHttpRequest.responseText + "\n=>无法转换为JSON");
			}
		},
		//调用出错执行的函数
		error: function() {
			//请求出错处理
			console.log('myabbs->GetAbility fail');
		}
	});
}

// 缓慢隐藏文字-透明度
function cleanTips() {
	opacityvalue -= 0.1;
	$('#errordesc').css({
		'opacity': opacityvalue
	});
	$('#submitResultDesc').css({
		'opacity': opacityvalue
	});
	if (opacityvalue > 0) {
		setTimeout(cleanTips, 50);
	}
	if (opacityvalue <= 0) {
		//清空
		$("#submitResultDesc").html("");
		$('#errordesc').html("");
	}
}

function setSliderStatus(status) {
	if (status == true) {
		bodeabbs1.enable();
		bodeabbs2.enable();
		bodeabbs3.enable();
		bodeabbs4.enable();
		tech_abbs1.enable();
		tech_abbs2.enable();
		tech_abbs3.enable();
		tech_abbs4.enable();
		spec_abbs1.enable();
		spec_abbs2.enable();
		spec_abbs3.enable();
		attack_abbs1.enable();
		attack_abbs2.enable();
		attack_abbs3.enable();
		defen_abbs1.enable();
		defen_abbs2.enable();
		defen_abbs3.enable();
	} else {
		bodeabbs1.disable();
		bodeabbs2.disable();
		bodeabbs3.disable();
		bodeabbs4.disable();
		tech_abbs1.disable();
		tech_abbs2.disable();
		tech_abbs3.disable();
		tech_abbs4.disable();
		spec_abbs1.disable();
		spec_abbs2.disable();
		spec_abbs3.disable();
		attack_abbs1.disable();
		attack_abbs2.disable();
		attack_abbs3.disable();
		defen_abbs1.disable();
		defen_abbs2.disable();
		defen_abbs3.disable();
	}

}

/*
 * 下面是生成参考别的球员列表
 * 
 */
var playerArray = new Array(); //球员属性数组 存放一个个的球员属性对象(playerData)
//部门-球员登记   
var playerDept = {
	dep1: [], //广州测试部 
	dep2: [], //广州研发支持部
	dep3: [], // 广州海外支持部
	dep4: [], //广州开发一部
	dep5: [], //广州开发二部
	dep6: [], //广州开发三部
	dep7: [], //广州开发四部
	dep8: [], //广州行政部
	dep9: [], //珠海研发部
	dep10: [], //北京研发部
	dep11: [], //上海研发部
	dep12: [], //杭州研发部
	dep13: [] //其他机构
}

//球员能力对象,记录所有的能力   
function playerData() {
	this.playername = "empty";
	this.department = "empty"; //部门

}

var player1select = document.getElementById("player1list");
player1select.addEventListener('click', selectplayer1, false);

//实现点击部门list后更新部门button的文字
function selectplayer1(event) {
	var selectedplayer = event.target; // 获取点击目标
	var name = selectedplayer.innerHTML;
	document.getElementById("selectedplayer1").innerHTML = name;
	document.getElementById("selectedplayer1").setAttribute('tag', selectedplayer.innerHTML);
	GetAbility(name);
}

var setPlayerList = function(dataarray) {
	for (var i = 0; i < dataarray.length; i++) {
		//console.log("department=" + dataarray[i].department + " name=" + dataarray[i].playername);
		switch (dataarray[i].department) {
			case '广州测试部':
				playerDept.dep1.push(dataarray[i].playername);
				break;
			case '广州研发支持部':
				playerDept.dep2.push(dataarray[i].playername);
				break;
			case '广州海外支持部':
				playerDept.dep3.push(dataarray[i].playername);
				break;
			case '广州开发一部':
				playerDept.dep4.push(dataarray[i].playername);
				break;
			case '广州开发二部':
				playerDept.dep5.push(dataarray[i].playername);
				break;
			case '广州开发三部':
				playerDept.dep6.push(dataarray[i].playername);
				break;
			case '广州开发四部':
				playerDept.dep7.push(dataarray[i].playername);
				break;
			case '广州行政部':
				playerDept.dep8.push(dataarray[i].playername);
				break;
			case '珠海研发部':
				playerDept.dep9.push(dataarray[i].playername);
				break;
			case '北京研发部':
				playerDept.dep10.push(dataarray[i].playername);
				break;
			case '上海研发部':
				playerDept.dep11.push(dataarray[i].playername);
				break;
			case '杭州研发部':
				playerDept.dep12.push(dataarray[i].playername);
				break;
			default:
				playerDept.dep13.push(dataarray[i].playername);
		}

	}

	var Fragment1 = document.createDocumentFragment();
	setList('广州测试部', playerDept.dep1, Fragment1);
	setList('广州研发支持部', playerDept.dep2, Fragment1);
	setList('广州海外支持部', playerDept.dep3, Fragment1);
	setList('广州开发一部', playerDept.dep4, Fragment1);
	setList('广州开发二部', playerDept.dep5, Fragment1);
	setList('广州开发三部', playerDept.dep6, Fragment1);
	setList('广州开发四部', playerDept.dep7, Fragment1);
	setList('广州行政部', playerDept.dep8, Fragment1);
	setList('珠海研发部', playerDept.dep9, Fragment1);
	setList('北京研发部', playerDept.dep10, Fragment1);
	setList('上海研发部', playerDept.dep11, Fragment1);
	setList('杭州研发部', playerDept.dep12, Fragment1);
	setList('其他机构', playerDept.dep13, Fragment1);

	document.getElementById('player1list').appendChild(Fragment1);

	//将部门名li加入oFragment
	function setList(departmentname, playerarr, oFragment) {
		// 格式:
		// <li role="presentation" class="dropdown-header">部门名</li>
		// <li><a href="#">名字1</a></li>
		// <li><a href="#">名字2</a></li>
		// <li role="presentation" class="divider"></li>	//分割线
		//		var oFragment = document.createDocumentFragment();
		if (playerarr.length > 0) {
			var department_li = document.createElement('li');
			department_li.innerHTML = departmentname;
			department_li.setAttribute('role', 'presentation'); // role="presentation"
			department_li.setAttribute('class', 'dropdown-header'); // class="dropdown-header"
			oFragment.appendChild(department_li);
			for (var i = 0; i < playerarr.length; i++) {
				var player_li = document.createElement('li');
				var player_a = document.createElement('a');
				player_a.setAttribute('href', '#');
				player_a.innerHTML = playerarr[i];
				player_li.appendChild(player_a);
				oFragment.appendChild(player_li);
			}
			var dividerline = document.createElement('li');
			dividerline.setAttribute('role', 'presentation'); // role="presentation"
			dividerline.setAttribute('class', 'divider'); // class="dropdown-header"
			oFragment.appendChild(dividerline);
		}
	}
}

// ajax 获取所有球员的属性，进入页面时获取一次，生成球员列表 
var GetAllPlayerData = function() {
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
			//console.log('成功返回数据-->compareabi.js');
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
					pD.department = playObject["department"];
					playerArray.push(pD);
				}

				setPlayerList(playerArray);

			} catch (e) {
				console.log("error=" + e.message);
				//				console.log("compareabi.js成功返回信息=>" + XMLHttpRequest.responseText + "\n=>无法转换为JSON");
			}
			// HideLoading();
		},
		//调用出错执行的函数
		error: function() {
			//请求出错处理
		}
	});
}

// 头像处理
var userfaceDiv = document.getElementById('userheadDiv');
userfaceDiv.onmouseover = function() {
	this.className += " hover";
	this.innerHTML = "重新选择头像";
};
userfaceDiv.onmouseout = function() {
	this.className = this.className.replace(/\s?hover/, "");
	this.innerHTML = "";
};

//点击头像选择按钮--弹出模态框
$('#userheadDiv').click(function() {
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
//选择头像后userheadDiv更新为选择的头像
$('#selectimgBtn').click(function(){
	$('#userheadDiv').css({
		'background-image':'url(../img/userimg/'+selectedImg+')'
	});	
})

//改变slider-selection的颜色
//$('#tech_abbs1 .slider-selection').css('background', 'rgb(120, 142, 207)');
//$('.slider-selection').css('background', 'rgb(120, 142, 207)');

//改变背景颜色
//$('#'+abilityName).css({'background':'#9900ff'});

/* 
 ** 【  slider方法 】**
 * 
 * 	var g1 = $('#G').setValue(50); --设置数值
 *	console.log(g1.getValue()); --获取数值
 * 
 * 
 */