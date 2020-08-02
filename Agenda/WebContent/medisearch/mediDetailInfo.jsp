<%@ page import="java.net.URLEncoder"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<c:catch var="err">
	<c:set var="predURL" 
		value="http://apis.data.go.kr/1471057/MdcinPrductPrmisnInfoService/getMdcinPrductItem?ServiceKey=${key}${item}" />
	<c:import var="pred" url="${predURL}" charEncoding="UTF-8"/>
	<x:parse  var="pr" xml="${pred}"/>
{"item_name":"<x:out select="$pr/response/body/items/item/ITEM_NAME"/>",
"effect":"<x:out select="$pr/response/body/items/item/EE_DOC_DATA/DOC/SECTION/ARTICLE/PARAGRAPH"/>",
"capacity":"<x:out select="$pr/response/body/items/item/UD_DOC_DATA/DOC/SECTION"/>",
"warning":"<x:out select="$pr/response/body/items/item/NB_DOC_DATA/DOC/SECTION"/>"}
</c:catch>
<c:if test="${err!=null }">
	${err}
</c:if>