/**
 * 数据库连接
 */
var ioc = {
    conf : {
        type : "org.nutz.ioc.impl.PropertiesProxy",
        fields : {
            paths : ["conf/config.properties"]
        }
    },
    dataSource : {
        type : "com.aibinong.backyard.datasource.AbnDruidDataSource",
        events : {
            create : "init",
            depose : 'close'
        },
        fields : {
            url : {java:"$conf.get('db.url')"},
            username : {java:"$conf.get('db.username')"},
            password : {java:"$conf.get('db.password')"},
            testWhileIdle : true,
            validationQuery : {java:"$conf.get('db.validationQuery')"},
            maxActive : {java:"$conf.get('db.maxActive')"},
            filters : "mergeStat",
            connectionProperties : "druid.stat.slowSqlMillis=2000",
            connectionInitSqls: ["set names utf8mb4;"],
            filters: "wall"
        }
    },

    dao : {
        type : "org.nutz.dao.impl.NutDao",
        args : [{refer:"dataSource"}]
    },
    redisDao: {
    	type: "com.aibinong.backyard.dao.RedisDao",
    	events: {
            create: "init",
            depose: "close"
        },
	    fields: {
	        host: {java:"$conf.get('redis.host')"},
	        port: {java:"$conf.get('redis.port')"},
	        password: {java:"$conf.get('redis.password')"},
	        maxIdel: {java:"$conf.get('redis.maxIdel')"},
	        maxTotal: {java:"$conf.get('redis.maxTotal')"},
	        timeout: {java:"$conf.get('redis.timeout')"},
	        waitTime: {java:"$conf.get('redis.waitTime')"}
	    }
    }
};