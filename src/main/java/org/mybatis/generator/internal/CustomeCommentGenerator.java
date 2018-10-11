/**
 *    Copyright 2006-2015 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.mybatis.generator.internal;

import java.util.List;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.config.MergeConstants;

/**
 * 生成数据库表中comments字段
 * 
 * @author guooo
 *
 */
public class CustomeCommentGenerator extends DefaultCommentGenerator {




    @Override
	public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {
		// 生成方法注释
		method.addJavaDocLine("/**");
		String methodName = method.getName();

		if ("selectByExample".equals(methodName)) {
			method.addJavaDocLine(" * 根据条件查询列表");
		} else if (methodName.startsWith("updateByExample")) {
			method.addJavaDocLine(" * 选择性更新数据库记录");
		} else if ("updateByPrimaryKeySelective".equals(methodName)) {
			method.addJavaDocLine(" * 根据主键来更新部分数据库记录");
		} else if ("deleteByPrimaryKey".equals(methodName)) {
			method.addJavaDocLine(" * 根据主键删除数据库的记录");
		} else if ("insert".equals(methodName)) {
			method.addJavaDocLine(" * 插入数据库记录");
		} else if ("selectByPrimaryKey".equals(methodName)) {
			method.addJavaDocLine(" * 根据主键获取一条数据库记录");
		} else if ("updateByPrimaryKey".equals(methodName)) {
			method.addJavaDocLine(" * 根据主键来更新数据库记录");
		} else if ("selectAll".equals(methodName)) {
			method.addJavaDocLine(" * 获取全部数据库记录");
		} else if ("countByExample".equals(methodName)) {
			method.addJavaDocLine(" * 根据条件计数");
		} else if ("insertSelective".equals(methodName)) {
			method.addJavaDocLine(" * 插入不为空的记录");
		}
		method.addJavaDocLine(" *");
		List<Parameter> parameterList = method.getParameters();
		String paramterName;
		for (Parameter parameter : parameterList) {
			paramterName = parameter.getName();
			method.addJavaDocLine(" * @param " + paramterName + " 条件 ");
		}

        method.addJavaDocLine(" */");
	}
	

	
	
	@Override
	public void addFieldComment(Field field, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
		// 添加字段注释
		StringBuffer sb = new StringBuffer();
		field.addJavaDocLine("/**");
		if (introspectedColumn.getRemarks() != null)
			field.addJavaDocLine(" * " + introspectedColumn.getRemarks()+ "<br>");
		sb.append(" * 表 : ");
		sb.append(introspectedTable.getFullyQualifiedTable());
		field.addJavaDocLine(sb.toString() + "<br>");
		field.addJavaDocLine(" * 对应字段 : "+ introspectedColumn.getActualColumnName() + "<br>");
		field.addJavaDocLine(" */");
	}
    @Override
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {
        innerClass.addJavaDocLine("/**");
        innerClass.addJavaDocLine(" * 表名 : " + introspectedTable.getFullyQualifiedTable() + "<br>");
        innerClass.addJavaDocLine(" * 表注释: " + introspectedTable.getRemarks() + "<br>");
        innerClass.addJavaDocLine(" */");
    }

	@Override
	public void addClassComment(InnerClass innerClass,
			IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {
		innerClass.addJavaDocLine("/**");
		innerClass.addJavaDocLine(" * 表名 : " + introspectedTable.getFullyQualifiedTable()+ "<br>");
		innerClass.addJavaDocLine(" * 表注释: " + introspectedTable.getRemarks()+ "<br>");
		innerClass.addJavaDocLine(" */");
	}
	
	@Override
	public void addFieldComment(Field field, IntrospectedTable introspectedTable) {
		//该字段针对 实现Serializable接口的生成的UUID注释,默认无
	}
	
	@Override
	public void addGetterComment(Method method, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
		// get方法注释
		method.addJavaDocLine("/**");
		method.addJavaDocLine(" * @return " + introspectedColumn.getRemarks());
		method.addJavaDocLine(" */");
	}

	@Override
	public void addSetterComment(Method method, IntrospectedTable introspectedTable,
			IntrospectedColumn introspectedColumn) {
		// set方法注释
		method.addJavaDocLine("/**");
		Parameter parm = method.getParameters().get(0);
		method.addJavaDocLine(" * @param " + parm.getName()+"  "+introspectedColumn.getRemarks());
		// addJavadocTag(method, false);
		method.addJavaDocLine(" */");
	}

	@Override
	public void addComment(XmlElement xmlElement) {
		xmlElement.addElement(new TextElement("<!--")); //$NON-NLS-1$

		StringBuilder sb = new StringBuilder();
		sb.append("  " + MergeConstants.NEW_ELEMENT_TAG);
		xmlElement.addElement(new TextElement(sb.toString()));
		String s = getDateString();
		if (s != null) {
			sb.setLength(0);
			sb.append("  This element is automatically generated by MyBatis Generator,Do not modify ! Generated on "); //$NON-NLS-1$
			sb.append(s);
			sb.append('.');
			xmlElement.addElement(new TextElement(sb.toString()));
		}

		xmlElement.addElement(new TextElement("-->")); //$NON-NLS-1$
	}
}
