//比较函数的函数
//iCol = 0是处理第一列(序号)-降序排列
//iCol = 1是处理第二列(序号)-升序排列
function generateCompareTRs(iCol) {
	return function compare(tr1, tr2) {
		var v1 = tr1.cells[iCol].firstChild.nodeValue;
		var v2 = tr2.cells[iCol].firstChild.nodeValue;

		// 序号列，降序
		if (iCol == 0) {
			//当v1大于v2时返回-1，为降序
			if (parseInt(v1) > parseInt(v2)) {
				return -1;
			}
			// 当v1小于v2返回1，升序
			else if (parseInt(v1) < parseInt(v2)) {
				return 1;
			} else {
				return 0;
			}
		}
		// 姓名列，升序
		else {
			if (v1 > v2)
				return 1;
			else if (v1 < v2) return -1;
			else return 0;
		}
	}

}

//排序表格的函数
function sortTable(iCol) {
	var oTable = document.getElementById("tblSort");
	var oTBody = oTable.tBodies[0];
	var aRows = oTBody.rows;

	//创建一个数组，用于保存aRows中的行引用
	var aTRs = new Array;
	//	for ( var row in aRows) { ---遍历会多了一些垃圾数据
	//		aTRs.push(aRows[row]);	
	//	}
	for (var i = 0; i < aRows.length; i++) {
		aTRs.push(aRows[i]);
	}
//	console.log(aTRs);
	aTRs.sort(generateCompareTRs(iCol));
//	console.log(aTRs);
	
	var oFragment = document.createDocumentFragment();
	for (var i=0;i<aTRs.length;i++) {
		oFragment.appendChild(aTRs[i]);
	}
	oTBody.appendChild(oFragment);

}

//sortTable(1);

//var tablerow1 = document.getElementById("tblSort").rows[0];
//if (tablerow1) {
//	console.log(tablerow1.cells[0].innerHTML);
//	console.log(tablerow1.cells[0].firstChild.nodeName); //-- > #text
//	console.log(tablerow1.cells[0].firstChild.nodeValue); //-- > 序号
//}

var table1 = document.getElementById("tblSort");
if (table1) {
	console.log(table1.tBodies);
}