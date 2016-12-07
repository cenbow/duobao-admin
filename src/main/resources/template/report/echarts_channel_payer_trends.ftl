{
    tooltip: {
        trigger: "axis"
    },
    legend: {
        data: ["付费人数", "新增付费人数"],
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
            name: "人数"
        }
    ],
    series: [
        {
            name: "付费人数",
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
	    	   		${item.pay_num} 
	    	   		<#if item_has_next>,</#if>
				</#list>
            ]
        },
        {
            name: "新增付费人数",
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
	    	   		${item.new_pay_num} 
	    	   		<#if item_has_next>,</#if>
				</#list>
            ]
        }
    ],
    title: {
        text: "付费人数趋势"
    }
}