package org.rapla.plugin.studiinf.client.ui;

import org.rapla.plugin.studiinf.client.Navigation;
import org.rapla.plugin.studiinf.client.Studiinf;
import org.rapla.plugin.studiinf.client.pages.AbstractDetailPage;
import org.rapla.plugin.studiinf.client.pages.AbstractPage;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.SpanElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.TouchStartEvent;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

public class NavButton extends Composite implements NavigationButtonSpec, HasText {

	private static NavButtonUiBinder uiBinder = GWT
			.create(NavButtonUiBinder.class);

	interface NavButtonUiBinder extends UiBinder<Widget, NavButton> {
	}
	AbstractPage targetPage;
	String targetId;
	int numberValue = 0;
	FontIcon fontIcon;
	String text;
	boolean enabled = true;

	public NavButton(String text,AbstractPage targetPage,String targetId) {
		initWidget(uiBinder.createAndBindUi(this));
		setTargetPage(targetPage);
		setTargetId(targetId);
		setText(text);
	}
	
	public NavButton(FontIcon fontIcon, String text,AbstractPage targetPage,String targetId){
		this(text,targetPage, targetId);
		setIcon(fontIcon);
	}
	public NavButton(int number, FontIcon fontIcon,String text, AbstractPage targetPage,String targetId){
		this(fontIcon,text,targetPage,targetId);
		setNumber(number);
	}
	
	@UiField
	Button button;
	
	@UiField
	SpanElement number;
	
	@UiField
	SpanElement icon;
	
	@UiField
	SpanElement textLabel;
	
	
	@UiHandler("button")
	void onClick( ClickEvent e){
		handlePressEvent();
	}
	
	@UiHandler("button")
	void onTouchStart( TouchStartEvent e){
		handlePressEvent();
	}
	
	protected void handlePressEvent(){
		if(getEnabled()){
			if(targetId == null){
				Navigation.goToPage(targetPage);
			}else{
				Navigation.goToPage(targetPage, targetId);
			}
		}
	}
	

	@Override
	public String getTargetId() {
		return targetId;
	}

	@Override
	public void setTargetId(String targetId) {
		this.targetId = targetId;
		updateEnabledState();
	}

	@Override
	public int getNumber() {
		return numberValue;
	}

	@Override
	public void setNumber(int numberValue) {
		this.numberValue = numberValue;
		if(numberValue > 0){
			number.setInnerHTML(Studiinf.i18n.numberFormat(numberValue));
		}else{
			number.setInnerHTML("");
		}
	}

	@Override
	public FontIcon getIcon() {
		return fontIcon;
	}

	@Override
	public void setIcon(FontIcon fontIcon) {
		this.fontIcon = fontIcon;
		if(fontIcon.getUrl().startsWith("icon-")){
			icon.setInnerHTML("<span class='"+fontIcon.getUrl()+"'></span>");
		}else{
			icon.setInnerHTML("");
		}
	}

	@Override
	public String getText() {
		return text;
	}

	@Override
	public void setText(String text) {
		this.text = text;
		if(text != null){
			textLabel.setInnerSafeHtml(SafeHtmlUtils.fromString(text));
		}else{
			textLabel.setInnerHTML("");
		}
	}

	@Override
	public AbstractPage getTargetPage() {
		return targetPage;
	}

	@Override
	public void setTargetPage(AbstractPage targetPage) {
		this.targetPage = targetPage;
		updateEnabledState();
	}

	@Override
	public void setSize(double viewPortSize) {
		this.getElement().getStyle().setProperty("fontSize", viewPortSize+"vh");		
	}

	@Override
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
		updateEnabledState();
	}

	@Override
	public boolean getEnabled() {
		boolean result = enabled;
		if(targetPage == null){
			result = false;
		}else{
			try {
				AbstractDetailPage d = (AbstractDetailPage) targetPage;
				if(d.getHistoryKey() == null || targetId == null){
					result = false;
				}
			} catch (Exception e) {
				result = enabled;
			}
		}
		return result;
	}
	
	private void updateEnabledState(){
		if(getEnabled()){
			this.getElement().removeAttribute("disabled");
		}else{
			this.getElement().setAttribute("disabled","disabled");
		}
	}
	


}
