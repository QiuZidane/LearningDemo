/********************************
 *  作者：kfzx-qiusd 
 *  公共值
 *
 *********************************/
/*
 *  :::::::::: 全局变量定义开始 :::::::::: 
 * 
 */
//权重值
var weightfunc = {
	//体质属性，共四项
	speed_w: 0.35, //速度权重值
	strength_w: 0.2, //强壮权重值
	stamina_w: 0.35, //体能权重值
	health_w: 0.1, //受伤抗性

	//技术属性，共四项
	passing_w: 0.3, //传球权重值
	touching_w: 0.3, //停球权重值
	dribbling_w: 0.3, //盘带权重值
	heading_w: 0.1, //头球权重值

	//特殊属性，共两项
	minding_w: 0.3, //意志力权重值
	teamwork_w:0.5,//团队意识
	rating_w: 0.2, //出勤率权重值

	//进攻属性，共三项
	shoot_w: 0.2, //射门
	offtheball_w: 0.2, //跑位
	creativity_w: 0.15, //创造力
	techOnAttack_w: 0.3, // 技术在进攻属性权重
	bodyOnAttack_w: 0.15, // 体质在进攻属性权重

	//防守属性，共三项
	taking_w: 0.15, //抢断
	marking_w: 0.15, //盯人
	positioning_w: 0.25, //防守站位
	techOnDef_w: 0.15, // 技术在防守属性权重
	bodyOnDef_w: 0.3, // 体质在防守属性权重

	//总能力，共五项
	body_w: 0.25, //体质
	tech_w: 0.4, //技术
	spec_w: 0.05, //特殊
	attack_w: 0.15, //进攻
	defence_w: 0.15 //防守	

}

//服务器地址 比如POS地址
var clubserver = {
	URL: "http://localhost:8080/FootBallWebSite/"	
}
