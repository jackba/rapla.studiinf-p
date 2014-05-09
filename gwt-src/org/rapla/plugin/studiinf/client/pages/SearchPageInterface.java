package org.rapla.plugin.studiinf.client.pages;

import java.util.List;

import org.rapla.plugin.freiraum.common.ResourceDescription;

public interface SearchPageInterface {

	public void updateResults(
			List<ResourceDescription> ressourcesMatched);

	public String getResourceType();
	
	public String getHistoryKey();

	public void handleClickCount(String targetId);
	
}