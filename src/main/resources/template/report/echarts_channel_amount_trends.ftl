{
    tooltip: {
        trigger: "axis"
    },
    legend: {
        data: ["付费额", "新增付费额"],
        y: "bottom"
    },
    toolbox: {
        show: true,
        feature: {
            mark: {
                show: true
            },
            dataView: {
                show: true,
                readOnly: true
            },
            magicType: {
                show: false,
                type: ["line", "bar", "stack", "tiled"]
            },
            restore: {
                show: true
            },
            saveAsImage: {
                show: true
            }
        }
    },
    calculable: true,
    xAxis: [
        {
            type: "category",
            boundaryGap: false,
            data : [
            	<#list list as item> 
	        	   '${item.date}' 
	        	   <#if item_has_next>,</#if>
				</#list>
            ]
        }
    ],
    yAxis: [
        {
            type: "value",
            name: "元"
        }
    ],
    series: [
        {
            name: "付费额",
            type: "line",
            smooth:true,
            itemStyle: {
                normal: {
                    areaStyle: {
                        type: "default"
                    }
                }
            },
            data:[
	            <#list list as item> 
	    	   		${item.pay_amounts} 
	    	   		<#if item_has_next>,</#if>
				</#list>
            ]
        },
        {
            name: "新增付费额",
            type: "line",
            smooth:true,
            itemStyle: {
                normal: {
                    areaStyle: {
                        type: "default"
                    }
                }
            },
            data:[
	            <#list list as item> 
	    	   		${item.new_pay_amounts} 
	    	   		<#if item_has_next>,</#if>
				</#list>
            ]
        }
    ],
    title: {
        text: "付费额趋势"
    }
}