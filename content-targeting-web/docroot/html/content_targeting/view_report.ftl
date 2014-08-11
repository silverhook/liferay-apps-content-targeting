<#--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
-->

<#include "../init.ftl" />

<@portlet["renderURL"] var="backURL">
	<@portlet["param"] name="mvcPath" value="${contentTargetingPath.VIEW_REPORTS}" />
	<@portlet["param"] name="redirect" value="${redirect}" />
	<@portlet["param"] name="className" value="${className}" />
	<@portlet["param"] name="classPK" value="${classPK?string}" />
</@>

<@liferay_ui["header"]
	backURL="${backURL}"
	title=report.getName(locale)
/>

<div class="report-description">
	${report.getDescription(locale)}
</div>

<@portlet["actionURL"] name="updateReport" var="updateReportURL">
	<@portlet["param"] name="redirect" value="${currentURL}" />
	<@portlet["param"] name="reportKey" value="${report.getReportKey()}" />
	<@portlet["param"] name="classPK" value="${classPK?string}" />
</@>

<@aui["button-row"]>
	<@aui["button"] href="${updateReportURL}" value="update-report"/>
</@>

${reportHtml}