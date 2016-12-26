<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%
	String path = request.getContextPath();
	pageContext.setAttribute("path", path);
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title></title>
<script src="${path}/res/common/js/jquery.js" type="text/javascript"></script>
<script src="${path}/res/common/js/jquery.ext.js" type="text/javascript"></script>

<script type="text/javascript">
	function del(url, sid) {
		if (!confirm('您确定要删除吗?')) {
			return;
		}
		var frm = $("#tableForm");
		frm.attr("action", url);
		$("[name='id']").val(sid);
		$("[name='action']").val('delete');

		frm.submit();
	}
	var i = -1;
	// 选中‘反选 复选框
	function doChecked(nodeName) {
		i = i * -1;
		if (i > 0) {
			$("[name=\"cid\"]").attr("checked", 'true');//全选
		} else {
			$("[name=\"cid\"]").removeAttr("checked");//取消全选
		}
	}
	//批量删除
	function delBatch(url) {
		var sid = "";
		var checked = $("[name=\"cid\"]:checked");
		if (checked.length <= 0) {
			alert("请选择您要操作的数据");
			return;
		}
		if (!confirm("您确定删除吗？")) {
			return;
		}
		checked.each(function() {
			sid += $(this).val() + ",";
		});
		sid = sid.substring(0, sid.length - 1);

		var frm = $("#tableForm");
		frm.attr("action", url);
		$("[name='sid']").val(sid);
		$("[name='action']").val('delete');
		frm.submit();
	}
</script>
</head>
<body>
	<div class="box-positon">
		<div class="rpos">当前位置: Crash日志 - 列表</div>
		<div class="clear"></div>
	</div>
	<div class="body-box">
		<!-- 批量删除  -->
		<div style="margin-top: 15px;">
			<input type="button" value="批量删除"
				onclick="delBatch('query.jsp','cid');" />
		</div>
		<!-- 查询结果  -->
		<form id="tableForm" method="post">
			<input type="hidden" name="action" /> 
			<input type="hidden" name="id" />
			<table class="pn-ltable" style="" width="100%" cellspacing="1"
				cellpadding="0" border="0">
				<thead class="pn-lthead">
					<tr>
						<th width="30"><input type='checkbox'
							onclick="doChecked('cid')" /></th>
						<th>文件名</th>
						<th>用户JID</th>
					</tr>
				</thead>
				<tbody class="pn-ltbody">
					<c:forEach items="${items}" var="item">
						<tr onmouseover="this.bgColor='#eeeeee'"
							onmouseout="this.bgColor='#ffffff'">
							<td><input type="checkbox" name="cid" value="${item}" /></td>
							<td>${item}</td>
							<td align="center"><a target="_blank" href="/droid/file/queryLogContent?id=${item}" class="pn-opt">查看</a> |
								<a href="/droid/file/deleteLogFile?id=${item}" class="pn-opt">删除</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</form>
	</div>
</body>
</html>