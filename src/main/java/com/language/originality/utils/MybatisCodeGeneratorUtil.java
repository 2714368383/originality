package com.language.originality.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

/**
 * mybatis的代码生成工具类
 * 
 * @author chenxia
 *
 */
public class MybatisCodeGeneratorUtil {
	private final static String DATA_URL = "jdbc:mysql://localhost:3306/cc?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=GMT%2B8";
	private final static String DRIVER_NAME = "com.mysql.jdbc.Driver";
	private final static String USER_NAME = "root";
	private final static String USER_PWD = "123456";

	private static boolean GE_FLAG = true;
	// 是否开启全库生成

	public static void main(String[] args) throws Exception {
		// 生成库中的所有的表
		dynamicGenerator(GE_FLAG);
	}

	private static void dynamicGenerator(boolean type) throws Exception {
		if (type) {
			List<String> tables = getTables();
			for (String table : tables)
				singleGenerator(table);
		} else {
			// 生成指定表的代码
			singleGenerator(scanner("请输入表名"));
		}

	}

	/**
	 * <p>
	 * 读取控制台内容
	 * </p>
	 */
	public static String scanner(String tip) {
		Scanner scanner = new Scanner(System.in);
		StringBuilder help = new StringBuilder();
		help.append("请输入" + tip + "：");
		System.out.println(help.toString());
		if (scanner.hasNext()) {
			String ipt = scanner.next();
			if (StringUtils.isNotEmpty(ipt)) {
				return ipt;
			}
		}
		throw new MybatisPlusException("请输入正确的" + tip + "！");
	}

	private static void singleGenerator(String table) throws Exception {
		// 代码生成器
		AutoGenerator mpg = new AutoGenerator();

		// 全局配置
		String projectPath = globalConfig(mpg);

		// 数据源配置
		dataSourceConfig(mpg);

		// 包配置
		PackageConfig pc = getPackageConfig(mpg);

		// 自定义配置
		InjectionConfig cfg = new InjectionConfig() {
			@Override
			public void initMap() {
				// to do nothing
			}
		};
		List<FileOutConfig> focList = new ArrayList<>();
		focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
			@Override
			public String outputFile(TableInfo tableInfo) {
				// 自定义输入文件名称
				return projectPath + "/src/main/resources/mybatis/" + tableInfo.getEntityName() + "Mapper"
						+ StringPool.DOT_XML;
			}
		});
		cfg.setFileOutConfigList(focList);
		mpg.setCfg(cfg);
		mpg.setTemplate(new TemplateConfig().setXml(null));
		// 策略配置
		strategyConfig(mpg, pc, table);
	}

	/**
	 * @param: [mpg, pc]
	 * @return: void
	 * @author: Administrator
	 * @date: 2019/4/9/009 11:03
	 * @description: 策略配置
	 */
	private static void strategyConfig(AutoGenerator mpg, PackageConfig pc, String table) throws Exception {

		StrategyConfig strategy = new StrategyConfig();
		strategy.setNaming(NamingStrategy.underline_to_camel);
		strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//	        strategy.setSuperEntityClass("com.example.demo.common.BaseEntity");
		strategy.setEntityLombokModel(true);
		strategy.setRestControllerStyle(true);
//	        strategy.setSuperControllerClass("com.example.demo.common.BaseController");
		strategy.setInclude(table);
		strategy.setSuperEntityColumns("id");
		strategy.setControllerMappingHyphenStyle(true);
//	        strategy.setTablePrefix(pc.getModuleName() + "_");
		mpg.setStrategy(strategy);
		mpg.setTemplateEngine(new FreemarkerTemplateEngine());
		mpg.execute();
	}

	/**
	 * @param: [mpg]
	 * @return: com.baomidou.mybatisplus.generator.config.PackageConfig
	 * @author: Administrator
	 * @date: 2019/4/9/009 11:02
	 * @description: 包配置
	 */
	private static PackageConfig getPackageConfig(AutoGenerator mpg) {

		PackageConfig pc = new PackageConfig();
//	        pc.setModuleName(scanner("模块名"));
		pc.setParent("com.language.originality");
		mpg.setPackageInfo(pc);
		return pc;
	}

	/**
	 * @param: [mpg]
	 * @return: java.lang.String
	 * @author: Administrator
	 * @date: 2019/4/9/009 11:02
	 * @description: 全局配置
	 */
	private static String globalConfig(AutoGenerator mpg) {

		GlobalConfig gc = new GlobalConfig();
		String projectPath = System.getProperty("user.dir");
		gc.setOutputDir(projectPath + "/src/main/java");
		gc.setAuthor("ZHAIKAIXUAN");
		gc.setIdType(IdType.ASSIGN_UUID);
		gc.setFileOverride(true);
		gc.setDateType(DateType.ONLY_DATE);
		gc.setOpen(false);
		mpg.setGlobalConfig(gc);
		return projectPath;
	}

	/**
	 * @param: [mpg]
	 * @return: void
	 * @author: Administrator
	 * @date: 2019/4/9/009 11:02
	 * @description: 配置数据源
	 */
	private static void dataSourceConfig(AutoGenerator mpg) {
		DataSourceConfig dsc = new DataSourceConfig();
		dsc.setUrl(DATA_URL);
		// dsc.setSchemaName("public");
		dsc.setDriverName(DRIVER_NAME);
		dsc.setUsername(USER_NAME);
		dsc.setPassword(USER_PWD);
		mpg.setDataSource(dsc);
	}

	/**
	 * 获取数据库中的所有的表
	 */
	public static List<String> getTables() throws Exception {

		List<String> tableList = new ArrayList<String>();

		Class.forName(DRIVER_NAME);
		Connection connection = DriverManager.getConnection(DATA_URL, USER_NAME, USER_PWD);
		java.sql.Statement statement = connection.createStatement();

		ResultSet resultSet = statement.executeQuery("SELECT table_name  FROM information_schema.TABLES  "
				+ "WHERE table_schema = '" + scanner("数据库名") + "'");
		while (resultSet.next()) {
			tableList.add(resultSet.getString(1));
		}
		return tableList;
	}

}
