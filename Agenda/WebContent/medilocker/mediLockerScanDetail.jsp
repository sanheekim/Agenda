<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%System.out.println("Detail 진입"); %>
<c:catch var="err">
	<c:forEach items="${item }" var="encItem" varStatus="status">
			<c:set var="detailURL"
		value="http://apis.data.go.kr/1471057/MdcinPrductPrmisnInfoService/getMdcinPrductItem?ServiceKey=${key}${encItem }"></c:set>
		<c:import var = "detailImport" url="${detailURL }" charEncoding="UTF-8"/>
		<x:parse var="detail" xml="${detailImport } "/>
		"${list[status.index] }":{"ITEM_NAME":"<x:out select="$detail/response/body/items/item/ITEM_NAME"/>"},
	</c:forEach>
</c:catch>

