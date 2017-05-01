if (localStorage.getItem('loginflag') == undefined) {
	alert('您还没登陆!');
} else {
	var selected = confirm('是否签退?');
	if (selected == 1) {
		localStorage.clear();
		alert('已签退');
		document.location.href = '../pages/welcomepage.html';
	}
}