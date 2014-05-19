package org.rapla.plugin.studiinf.client.pages;

import java.util.List;

import org.rapla.plugin.freiraum.common.ResourceDescription;

public interface SearchPageInterface {

	/**
	 * Displays the result of the search
	 */
	public void updateResults(
			List<ResourceDescription> ressourcesMatched);

	/**
	 * Returns type of the resource
	 */
	public String getResourceType();
	
	/**
	 * Returns history key of the page for navigation
	 */
	public String getHistoryKey();

	/**
	 * handles click count for most frequent results on search pages
	 */
	public void handleClickCount(String targetId);
	
}