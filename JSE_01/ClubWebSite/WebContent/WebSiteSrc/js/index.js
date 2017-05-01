// 绑定列表点击事件，点击后会传递event
var navList = document.getElementById('nav-pills');
if (navList) {
	navList.addEventListener('click', navSelect, false); //导航页面事件
	navList.addEventListener('click', listSelect, false); //按钮变色事件	
	navList.addEventListener('click', setScrolling, false); //iframe-scrolling锁定事件
};

function navSelect(event) {
	var selectedBtn = event.target; // click事件对象
	if (selectedBtn.nodeName == 'A') {
		function refresh() {
			var pageUrl = "../pages/" + selectedBtn.getAttribute('urltag');
			console.log(selectedBtn.innerHTML + ": 加载:" + pageUrl);
			iframeRefresh(pageUrl);
		}
		if (selectedBtn.getAttribute('id') == 'logoutBtn') {
			if (localStorage.getItem('loginflag') == undefined) {
				alert('您还没登陆!');
			} else {
				var selected = confirm('是否签退?');
				if (selected == 1) {
					localStorage.clear();
					refresh();
				}
			}
		} else {
			refresh();
		}

	}

}

function iframeRefresh(pageUrl) {
	var iframe = document.getElementById('middleFrameID');
	iframe.setAttribute('src', pageUrl);
	//	document.iframe.location.reload();
}

// 点击导航栏，激活+变色相应的li
function listSelect(event) {
	var SELECTED = 'active'; //class='active';
	var UNSELECTED = 'noactive';
	var activeT = selectedlist(); // 查找激活状态对象
	var selectT = event.target.parentNode; // click事件对象  这里用了parentNode，因为选中到的是<a>元素，而需要处理的是父节点<li>

	function setStatus(T) {
		var activeFlag = T.getAttribute('class');
		if (activeFlag == UNSELECTED) {
			T.setAttribute('class', SELECTED);
		} else {
			T.setAttribute('class', UNSELECTED);
		};
	}

	function selectedlist() {
		var listitem = document.querySelectorAll("li[name]"); // 查找所有有name属性的li
		for (var i = 0; i < listitem.length; i++) {
			if (listitem[i].className == SELECTED) {
				return listitem[i];
			};
		};
	}

	if (selectT.nodeName == 'LI') {
		if (activeT) { // 存在已激活的list，处理该list与选中的list
			setStatus(activeT);
			setStatus(selectT);
		} else { // 不存在激活状态的list，只需要处理click对象即可
			setStatus(selectT);
		};
	}

}

//球员总览和联赛队伍页面可以滑动Scroll
function setScrolling(event) {
	var selectT = event.target.innerHTML;
	if (selectT == "球员总览" || selectT == "联赛队伍" || selectT == "留言板") {
		$('#middleFrameID').attr('scrolling', 'yes');
		$('#middleFrameID').css({
			'overflow': 'scroll'
		});
	} else {
		$('#middleFrameID').attr('scrolling', 'no');
		$('#middleFrameID').css({
			'overflow': 'hidden'
		});
	}
}

// 点击签退按钮
//$('#logoutBtn').click(function() {
//	if (localStorage.getItem('loginflag') == undefined) {
//		alert('您还没登陆!');
//	} else {
//		var selected = confirm('是否签退?');
//		if (selected == 1) {
//			localStorage.clear();
//			alert('已签退');
////			document.location.href = '../pages/welcomepage.html';
//		}
//	}
//});