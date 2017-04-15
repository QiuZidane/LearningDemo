/**
 * Grid-light theme for Highcharts JS
 * @author Torstein Honsi
 */

// Load the fonts
Highcharts.createElement('link', {
//	href: '//fonts.googleapis.com/css?family=Dosis:400,600',		//这段不注释会超卡
	rel: 'stylesheet',
	type: 'text/css'
}, null, document.getElementsByTagName('head')[0]);

Highcharts.theme = {		//第一个元素是能力标线的颜色
	colors: ["#0c8ecf", "#f7a35c", "#90ee7e", "#7798BF", "#aaeeee", "#ff0066", "#eeaaee",
		"#55BF3B", "#DF5353", "#7798BF", "#aaeeee"],
	chart: {
		backgroundColor: null,
		style: {
			fontFamily: "Dosis, sans-serif"
		}
	},
	title: {
		style: {
			fontSize: '16px',
			fontWeight: 'bold',
			textTransform: 'uppercase'
		}
	},
	tooltip: {
		borderWidth: 0,
		backgroundColor: 'rgba(219,219,216,0.8)',
		shadow: false
	},
	legend: {
		itemStyle: {
			fontWeight: 'bold',
			fontSize: '13px'
		}
	},
	xAxis: {
		gridLineWidth: 0.4,		
		gridLineColor: "#55595a",	// 标准线颜色XY都要改
		labels: {
			style: {
				fontSize: '13px',
				color: "#337ab7"		// 文字颜色
				
			}
		}
	},
	yAxis: {
		gridLineWidth: 0.4,	
		minorTickInterval: 'auto',
		gridLineColor: "#55595a",  	// 标准线颜色XY都要改
		title: {
			style: {
				textTransform: 'uppercase'
			}
		},
		labels: {
			style: {
				fontSize: '13px'
			}
		}
	},
	plotOptions: {
		candlestick: {
			lineColor: '#404048'
		}
	},


	// General
	background2: '#F0F0EA'

};

// Apply the theme
Highcharts.setOptions(Highcharts.theme);
