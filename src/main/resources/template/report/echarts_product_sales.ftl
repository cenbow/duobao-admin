{
    title : {
       	text: '${title}'
    },
    tooltip : {
        trigger: 'axis'
    },
    legend: {
        data:['销售额'],
        x: "center",
        y: "bottom"
    },
    toolbox: {
        show : true,
        feature : {
            mark : {show: true},
            dataView : {show: true, readOnly: false},
            magicType : {show: true, type: ['line', 'bar']},
            restore : {show: true},
            saveAsImage : {show: true}
        }
    },
    calculable : true,
    xAxis : [
        {
            type : 'category',
            boundaryGap : false,
            data : [
            	<#list list as item> 
	        	   '${item.date}' 
	        	   <#if item_has_next>,</#if>
				</#list>
            ]
        }
    ],
    yAxis : [
        {
            type : 'value',
           	name: "元"
        }
    ],
    series : [
    	{
            name:'销售额',
            type:'line',
            smooth:true,
            itemStyle: {normal: {areaStyle: {type: 'default'}}},
            data:[
	            <#list list as item> 
	    	   		${item.buy_count} 
	    	   		<#if item_has_next>,</#if>
				</#list>
            ],
            markPoint : {
                data : [
                    {type : 'max', name: '最大值'},
                    {type : 'min', name: '最小值'}
                ]
            },
            markLine : {
                data : [
                    {type : 'average', name: '平均值'}
                ]
            }
        }
    ]
}