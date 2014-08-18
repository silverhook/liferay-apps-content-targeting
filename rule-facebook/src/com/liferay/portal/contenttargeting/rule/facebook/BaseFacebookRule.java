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

package com.liferay.portal.contenttargeting.rule.facebook;

import com.liferay.portal.contenttargeting.api.model.BaseRule;
import com.liferay.portal.contenttargeting.model.RuleInstance;
import com.liferay.portal.contenttargeting.util.ContentTargetingRuleUtil;
import com.liferay.portal.contenttargeting.util.PortletKeys;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.facebook.FacebookConnectUtil;
import com.liferay.portal.model.Company;

import java.util.Map;

/**
 * @author Julio Camarero
 */
public abstract class BaseFacebookRule extends BaseRule {

	protected abstract void doPopulateContext(
		RuleInstance ruleInstance, Map<String, Object> context);

	@Override
	protected void populateContext(
		RuleInstance ruleInstance, Map<String, Object> context) {

		Company company = (Company)context.get("company");

		boolean isFbLoginEnabled = false;

		try {
			isFbLoginEnabled = FacebookConnectUtil.isEnabled(
				company.getCompanyId());
		}
		catch (SystemException se) {
		}

		context.put("isFbLoginEnabled", isFbLoginEnabled);

		boolean hasPortalSettingsViewPermission = false;

		if (!isFbLoginEnabled) {
			hasPortalSettingsViewPermission =
				ContentTargetingRuleUtil.hasControlPanelPortletViewPermission(
					context, PortletKeys.PORTAL_SETTINGS);
		}

		context.put(
			"hasPortalSettingsViewPermission", hasPortalSettingsViewPermission);

		doPopulateContext(ruleInstance, context);
	}

}